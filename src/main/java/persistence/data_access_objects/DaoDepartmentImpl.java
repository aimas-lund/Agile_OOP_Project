package persistence.data_access_objects;

import core.buildings.Department;
import core.buildings.DepartmentBeds;
import core.buildings.OutDepartment;
import core.persons.Gender;
import core.persons.Patient;
import core.persons.Staff;
import persistence.Database;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DaoDepartmentImpl<T extends Department> implements IDao<T> {
    private static ArrayList<String> uniqueids;
    private final Database database = new Database();

    @Override
    public boolean save(T department) {
        if (!saveDepartment(department)) {
            return false;
        } else if (savePatients(department)) {
            return false;
        } else if (saveStaff(department)) {
            return false;
        }
        database.commit();
        return true;
    }

    private boolean saveDepartment(T department) {
        String sql = "insert into departments values(?, ?, ?, ?, ?)";

        try {
            PreparedStatement statement = database.prepareStatement(sql);
            statement.setString(1, department.getUniqueId());
            statement.setString(2, department.getName());
            statement.setString(5, department.getClass().getSimpleName());

            if (department instanceof DepartmentBeds) {
                statement.setInt(3, ((DepartmentBeds) department).getTotalCapacity());
                statement.setInt(4, ((DepartmentBeds) department).getCurrentCapacity());
            } else {
                statement.setNull(3, Types.INTEGER);
                statement.setNull(4, Types.INTEGER);
            }

            return database.executePreparedStatement(statement, false);

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean saveStaff(T department) {
        String sql = "insert into staff_in_departments values('%s', '%s')";
        ArrayList<String> tempUniqueIds = new ArrayList<>();

        try {
            PreparedStatement statement = database.prepareStatement(sql);

            for (Staff staff :
                    department.getStaff()) {
                if (uniqueids.contains(staff.getUniqueId())) {
                    continue;
                }
                statement.setString(1, staff.getUniqueId());
                statement.setString(2, department.getUniqueId());
                statement.addBatch();
            }

            if (database.executePreparedStatement(statement, false)) {
                uniqueids.addAll(tempUniqueIds);
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    private boolean savePatients(T department) {
        String sql = "insert into patients_in_departments values('%s', '%s', %s, %s)";
        ArrayList<String> tempUniqueIds = new ArrayList<>();

        try {
            PreparedStatement statement = database.prepareStatement(sql);

            for (Patient patient :
                    department.getPatients()) {
                if (uniqueids.contains(patient.getUniqueId())) {
                    continue;
                }

                statement.setString(1, patient.getUniqueId());
                statement.setString(2, department.getUniqueId());

                if (department instanceof DepartmentBeds) {
                    statement.setObject(3, ((DepartmentBeds) department).getBedWithPatient(patient));
                    statement.setNull(4, Types.BOOLEAN);
                } else if (department instanceof OutDepartment) {
                    statement.setNull(3, Types.INTEGER);
                    statement.setBoolean(4, ((OutDepartment) department).isPatientWaiting(patient));
                }
                statement.addBatch();
                tempUniqueIds.add(patient.getUniqueId());
            }

            if (database.executePreparedStatement(statement, false)) {
                uniqueids.addAll(tempUniqueIds);
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public ArrayList<T> find(HashMap<String, String> params) {
        database.connectToDB();

        String sql = "select * from patients where ";
        String values = "";

        for (Map.Entry<String, String> entry :
                params.entrySet()) {
            String value = entry.getValue();

            value = " = " + "'" + value + "'";

            values = values.concat(entry.getKey() + value + " and ");
        }

        sql = sql + values.substring(0, values.length() - 4);

        Statement statement = database.createStatement();

        ArrayList<T> patients = new ArrayList<>();

        try {
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {

                Date birthdate = new SimpleDateFormat("yyyy-MM-dd").parse(resultSet.getString("birthdate"));
                patients.add((T) new Patient(
                        resultSet.getString("uniqueId"),
                        resultSet.getString("name"),
                        resultSet.getString("surname"),
                        birthdate,
                        Gender.valueOf(resultSet.getString("gender")),
                        resultSet.getString("homeaddress"),
                        Integer.parseInt(resultSet.getString("phonenumber"))
                ));
            }

        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        database.disconnectFromDB();
        return patients;
    }

    public <T extends Patient> T find(T patient) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("uniqueid", patient.getUniqueId());
        return (T) find(hashMap).get(0); // Returns arrayList of length 1
    }

    public boolean delete(T patient) {
        return delete(patient.getUniqueId());
    }

    @Override
    public boolean delete(String uniqueId) {
        String sql = "delete from patients where uniqueid = '%s'";
        sql = String.format(sql, uniqueId);

        return database.executeStatement(sql);
    }

    @Override
    public boolean update(T patient) {
        String[] information = patient.getPersonInformation();
        String sql = "UPDATE patients set uniqueid = '%s', name = '%s', surname = '%s', birthdate = date('%s'), " +
                "gender = '%s', homeaddress = '%s', phonenumber = '%s' where uniqueId = '%s'";

        for (String value :
                information) {
            if (value == null) return false;

            sql = sql.replaceFirst("%s", value.replaceAll(" ", "_"));
        }

        sql = String.format(sql, patient.getUniqueId());

        return database.executeStatement(sql);
    }

}