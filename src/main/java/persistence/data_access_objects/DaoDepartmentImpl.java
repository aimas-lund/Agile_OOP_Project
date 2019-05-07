package persistence.data_access_objects;

import core.buildings.*;
import core.persons.Patient;
import core.persons.Staff;
import persistence.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.*;

public class DaoDepartmentImpl<T extends Department> implements IDao<T> {
    private static ArrayList<String> uniqueids = new ArrayList<>();
    private final Database database = new Database();

    static {
        getUniqueids();
    }

    private static void getUniqueids() {
        String sqlPatients = "select patientId from patients_in_departments";
        String sqlStaff = "select staffId from staff_in_departments";
        Database database = new Database();

        try {
            PreparedStatement statement = database.prepareStatement(sqlPatients);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                uniqueids.add(resultSet.getString("patientId"));
            }

            statement = database.prepareStatement(sqlStaff);

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                uniqueids.add(resultSet.getString("staffId"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        database.disconnectFromDB();
    }

    @Override
    public boolean save(T department) {
        if (!saveDepartment(department)) {
            return false;
        } else if (!saveAllPatients(department)) {
            return false;
        } else if (!saveAllStaff(department)) {
            return false;
        }
        database.commit();
        database.disconnectFromDB();
        return true;
    }

    public boolean saveDepartment(T department) {
        String sql = "insert into departments values(?, ?, ?, ?, ?)";

        try {
            PreparedStatement statement = database.prepareStatement(sql, false);
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
            return false;
        }
    }

    public boolean saveAllStaff(T department) {
        String sql = "insert into staff_in_departments values(?, ?)";
        ArrayList<String> tempUniqueIds = new ArrayList<>();

        try {
            PreparedStatement statement = database.prepareStatement(sql, false);

            for (Staff staff :
                    department.getStaff()) {

                if (uniqueids.contains(staff.getUniqueId())) {
                    continue;
                }
                statement.setString(1, staff.getUniqueId());
                statement.setString(2, department.getUniqueId());
                statement.addBatch();
                tempUniqueIds.add(staff.getUniqueId());
            }

            if (!database.executePreparedStatementBatch(statement, false)) {
                return false;
            }

            uniqueids.addAll(tempUniqueIds);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean saveAllPatients(T department) {
        String sql = "insert into patients_in_departments values(?, ?, ?, ?)";
        ArrayList<String> tempUniqueIds = new ArrayList<>();

        try {
            PreparedStatement statement = database.prepareStatement(sql, false);

            for (Patient patient :
                    department.getPatients()) {
                if (uniqueids.contains(patient.getUniqueId())) {
                    continue;
                }

                buildPatientStatement(department, statement, patient);

                statement.addBatch();
                tempUniqueIds.add(patient.getUniqueId());
            }

            if (!database.executePreparedStatementBatch(statement, false)) {
                return false;
            }
            uniqueids.addAll(tempUniqueIds);
            return true;

        } catch (SQLException e) {
            return false;
        }
    }

    private void buildPatientStatement(T department, PreparedStatement statement, Patient patient) throws SQLException {
        statement.setString(1, patient.getUniqueId());
        statement.setString(2, department.getUniqueId());

        if (department instanceof DepartmentBeds) {
            Bed bed = ((DepartmentBeds) department).getBedWithPatient(patient);
            if (bed == null) {
                statement.setNull(3, Types.INTEGER);
            } else {
                statement.setInt(3, bed.getId());
            }
            statement.setBoolean(4, false);
        } else if (department instanceof OutDepartment) {
            statement.setNull(3, Types.INTEGER);
            statement.setBoolean(4, ((OutDepartment) department).isPatientWaiting(patient));
        }
    }

    public boolean save(Patient patient, T department) {
        String sql = "insert into patients_in_departments values(?, ?, ?, ?)";
        PreparedStatement statement;
        try {
            statement = database.prepareStatement(sql);

            if (uniqueids.contains(patient.getUniqueId())) {
                return false;
            }

            buildPatientStatement(department, statement, patient);

            if (!database.executePreparedStatement(statement)) {
                return false; // Values will always be inserted, proper checks were made
            }

            uniqueids.add(patient.getUniqueId());
            return true;

        } catch (SQLException e) {
            return false;
        }
    }

    public boolean save(Staff staff, T department) {
        String sql = "insert into staff_in_departments values(?, ?)";

        try {
            PreparedStatement statement = database.prepareStatement(sql);

            if (uniqueids.contains(staff.getUniqueId())) {
                return false;
            }

            statement.setString(1, staff.getUniqueId());
            statement.setString(2, department.getUniqueId());

            if (!database.executePreparedStatement(statement)) {
                return false;
            }

            uniqueids.add(staff.getUniqueId());
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public ArrayList<T> find(HashMap<String, String> params) {
        String sql = "select * from departments where ";

        String values = "";

        for (Map.Entry<String, String> entry :
                params.entrySet()) {
            String value = entry.getValue();

            value = " = " + "'" + value + "'";

            values = values.concat(entry.getKey() + value + " and ");
        }

        sql = sql + values.substring(0, values.length() - 4);

        ArrayList<Department> departments = new ArrayList<>();

        try {
            PreparedStatement statement = database.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String uniqueId = resultSet.getString("uniqueId");
                String name = resultSet.getString("name");
                String type = resultSet.getString("type");

                ArrayList<Patient> patients = findAllPatientsInDepartment(uniqueId);
                ArrayList<Staff> staff = findAllStaffInDepartment(uniqueId);
                int totalCapacity;
                int currentCapacity;
                HashMap<Patient, Bed> patientsInBeds;

                switch (type) {
                    case "InDepartment":
                        totalCapacity = resultSet.getInt("totalCapacity");
                        currentCapacity = resultSet.getInt("currentCapacity");
                        patientsInBeds = findPatientsInBeds(uniqueId);
                        departments.add(new InDepartment(uniqueId, name, totalCapacity, currentCapacity, patientsInBeds,
                                patients, staff));
                        break;
                    case "ERDepartment":
                        totalCapacity = resultSet.getInt("totalCapacity");
                        currentCapacity = resultSet.getInt("currentCapacity");
                        patientsInBeds = findPatientsInBeds(uniqueId);
                        departments.add(new ERDepartment(uniqueId, name, totalCapacity, currentCapacity, patientsInBeds,
                                patients, staff));
                        break;
                    case "OutDepartment":
                        Queue<Patient> waitingPatients = findWaitingPatientsInDepartment(uniqueId);
                        departments.add(new OutDepartment(uniqueId, name,
                                patients, staff, waitingPatients));
                        break;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        database.disconnectFromDB();

        return (ArrayList<T>) departments;
    }

    public T find(T department) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("uniqueId", department.getUniqueId());
        return find(hashMap).get(0);
    }

    public Queue<Patient> findWaitingPatientsInDepartment(String departmentId) {
        String sql = "select patientId from patients_in_departments p where p.departmentId = ? and p.isWaiting";
        return new LinkedList<>(getPatientsFromSql(departmentId, sql));
    }

    public HashMap<Patient, Bed> findPatientsInBeds(String departmentId) {
        String sql = "select patientId, bedId from patients_in_departments p where p.departmentId = ? and p.bedId is not null";

        DaoPatientImpl<Patient> daoPatient = new DaoPatientImpl<>();

        HashMap<Patient, Bed> patients = new HashMap<>();

        try {
            PreparedStatement statement = database.prepareStatement(sql);
            statement.setString(1, departmentId);

            ResultSet resultSet = statement.executeQuery();


            HashMap<String, String> hashMap = new HashMap<>();

            while (resultSet.next()) {
                hashMap.put("uniqueid", resultSet.getString("patientId"));
                patients.put(daoPatient.find(hashMap).get(0), new Bed(resultSet.getInt("bedId")));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return patients;
    }

    private ArrayList<Patient> getPatientsFromSql(String departmentId, String sql) {
        DaoPatientImpl<Patient> daoPatient = new DaoPatientImpl<>();

        ArrayList<Patient> patients = new ArrayList<>();

        try {
            PreparedStatement statement = database.prepareStatement(sql);
            statement.setString(1, departmentId);

            ResultSet resultSet = statement.executeQuery();


            HashMap<String, String> hashMap = new HashMap<>();

            while (resultSet.next()) {
                hashMap.put("uniqueid", resultSet.getString("patientId"));
                patients.add(daoPatient.find(hashMap).get(0));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return patients;
    }

    public ArrayList<Patient> findAllPatientsInDepartment(String departmentId) {
        String sql = "select patientId from patients_in_departments p where p.departmentId = ?";
        return getPatientsFromSql(departmentId, sql);

    }

    public ArrayList<Staff> findAllStaffInDepartment(String departmentId) {
        String sql = "select staffId from staff_in_departments s where s.departmentId = ?";
        DaoStaffImpl<Staff> daoStaff = new DaoStaffImpl<>();

        ArrayList<Staff> staff = new ArrayList<>();

        try {
            PreparedStatement statement = database.prepareStatement(sql);
            statement.setString(1, departmentId);

            ResultSet resultSet = statement.executeQuery();


            HashMap<String, String> hashMap = new HashMap<>();

            while (resultSet.next()) {
                hashMap.put("uniqueid", resultSet.getString("staffId"));
                staff.add(daoStaff.find(hashMap).get(0));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return staff;
    }

    public String findDepartmentIdOfPerson(Staff staff) {
        String sql = "select uniqueId from departments d inner join staff_in_departments s on s.departmentId = d.uniqueId " +
                "where s.staffId = ?";

        String departmentId = null;

        try {
            PreparedStatement statement = database.prepareStatement(sql);
            statement.setString(1, staff.getUniqueId());

            ResultSet resultSet = statement.executeQuery();

            System.out.println(statement.toString());
            if (resultSet.next()) {
                departmentId = resultSet.getString("uniqueId");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            database.disconnectFromDB();
        }

        return departmentId;
    }

    public String findDepartmentIdOfPerson(Patient patient) {
        String sql = "select uniqueId from departments d inner join patients_in_departments p on p.departmentId = d.uniqueId " +
                "where p.patientId = ?";
        String departmentId = null;

        try {
            PreparedStatement statement = database.prepareStatement(sql);
            statement.setString(1, patient.getUniqueId());

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                departmentId = resultSet.getString("uniqueId");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            database.disconnectFromDB();
        }

        return departmentId;
    }

    @Override
    public boolean delete(String uniqueId) {
        String sqlDeleteDepartment = "delete from departments where uniqueId = ?";
        String sqlDeleteStaff = "delete from staff_in_departments where departmentId = ?";
        String sqlDeletePatient = "delete from patients_in_departments where departmentId = ?";

        PreparedStatement preparedStatement;
        try {
            preparedStatement = database.prepareStatement(sqlDeleteDepartment, false);
            preparedStatement.setString(1, uniqueId);
            boolean b1 = database.executePreparedStatement(preparedStatement, false);

            preparedStatement = database.prepareStatement(sqlDeleteStaff, false);
            preparedStatement.setString(1, uniqueId);
            boolean b2 = database.executePreparedStatement(preparedStatement, false);

            preparedStatement = database.prepareStatement(sqlDeletePatient, false);
            preparedStatement.setString(1, uniqueId);
            boolean b3 = database.executePreparedStatement(preparedStatement, false);

            // no patients or staff, so b2 and b3 are false but it deletes
            database.commit();
            database.disconnectFromDB();
            return b1 && b2 && b3;

        } catch (SQLException e) {
            return false;
        }
    }

    public boolean delete(Staff staff, T department) {
        String sql = "delete from staff_in_departments where departmentId = ? and staffId = ?";
        try {
            PreparedStatement statement = database.prepareStatement(sql);
            statement.setString(1, department.getUniqueId());
            statement.setString(2, staff.getUniqueId());

            if (!database.executePreparedStatement(statement)) {
                return false;
            }
            uniqueids.remove(staff.getUniqueId());
            return true;

        } catch (SQLException e) {
            return false;
        }
    }

    public boolean delete(Patient patient, T department) {
        String sql = "delete from patients_in_departments where departmentId = ? and patientId = ?";
        try {
            PreparedStatement statement = database.prepareStatement(sql);
            statement.setString(1, department.getUniqueId());
            statement.setString(2, patient.getUniqueId());

            if (!database.executePreparedStatement(statement)) {
                return false;
            }
            uniqueids.remove(patient.getUniqueId());
            return true;

        } catch (SQLException e) {
            return false;
        }
    }

    public boolean delete(T department) {
        department.getPatients().forEach((patient) -> uniqueids.remove(patient.getUniqueId()));
        department.getStaff().forEach((staff) -> uniqueids.remove(staff.getUniqueId()));

        return delete(department.getUniqueId());
    }

    public boolean update(Staff staff, T department) {
        String sql = "update staff_in_departments set departmentId = ? where staffId = ?";

        try {
            PreparedStatement statement = database.prepareStatement(sql);

            statement.setString(1, department.getUniqueId());
            statement.setString(2, staff.getUniqueId());

            return (database.executePreparedStatement(statement));

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean update(Patient patient, T department) {
        String sql = "update patients_in_departments set patientId = ?, departmentId = ?, bedId = ?, isWaiting = ?" +
                " where patientId = ?";

        try {
            PreparedStatement statement = database.prepareStatement(sql);

            buildPatientStatement(department, statement, patient);
            statement.setString(5, patient.getUniqueId());

            return database.executePreparedStatement(statement);

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(T department) {
        String sql = "update departments set name = ?, totalCapacity = ?, currentCapacity = ? " +
                " where uniqueId = ?";

        try {
            PreparedStatement statement = database.prepareStatement(sql);
            statement.setString(1, department.getName());

            if (department instanceof DepartmentBeds) {
                statement.setInt(2, ((DepartmentBeds) department).getTotalCapacity());
                statement.setInt(3, ((DepartmentBeds) department).getCurrentCapacity());
            } else {
                statement.setNull(2, Types.INTEGER);
                statement.setNull(3, Types.INTEGER);
            }

            statement.setString(4, department.getUniqueId());

            return database.executePreparedStatement(statement);

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

}