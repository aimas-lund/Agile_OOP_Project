package persistence.data_access_objects;

import core.persons.Gender;
import core.persons.Patient;
import persistence.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DaoPatientImpl<T extends Patient> implements IDao<T> {
    private final Database database = new Database();

    @Override
    public boolean save(T patient) {
        String sql = "insert into patients values(?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement statement = database.prepareStatement(sql);
            buildPatientInformationSql(patient, statement);

            return database.executePreparedStatement(statement);
        } catch (SQLException e) {
            return false;
        }

    }

    private void buildPatientInformationSql(T patient, PreparedStatement statement) throws SQLException {
        statement.setString(1, patient.getUniqueId());
        statement.setString(2, patient.getName());
        statement.setString(3, patient.getSurname());
        statement.setDate(4, new java.sql.Date(patient.getBirthdate().getTime()));
        statement.setString(5, patient.getGender().toString());
        statement.setString(6, patient.getHomeAddress()); //.replaceAll(" ", "_")
        statement.setInt(7, patient.getPhoneNumber());
    }

    @Override
    public ArrayList<T> find(HashMap<String, String> params) {
        String sql = "select * from patients where ";
        String values = "";

        for (Map.Entry<String, String> entry :
                params.entrySet()) {
            String value = entry.getValue();

            value = " = " + "'" + value + "'";

            values = values.concat(entry.getKey() + value + " and ");
        }

        sql = sql + values.substring(0, values.length() - 4);


        ArrayList<T> patients = new ArrayList<>();

        try {
            PreparedStatement statement = database.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                patients.add((T) new Patient(
                        resultSet.getString("uniqueId"),
                        resultSet.getString("name"),
                        resultSet.getString("surname"),
                        resultSet.getDate("birthdate"),
                        Gender.valueOf(resultSet.getString("gender")),
                        resultSet.getString("homeaddress"),
                        Integer.parseInt(resultSet.getString("phonenumber"))
                ));
            }

        } catch (SQLException e) {
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
        String sql = "delete from patients where uniqueid = ?";

        try {
            PreparedStatement statement = database.prepareStatement(sql);
            statement.setString(1, uniqueId);
            return database.executePreparedStatement(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean update(T patient) {
        String sql = "UPDATE patients set uniqueid = ?, name = ?, surname = ?, birthdate = ?, " +
                "gender = ?, homeaddress = ?, phonenumber = ? where uniqueId = ?";

        try {
            PreparedStatement statement = database.prepareStatement(sql);
            buildPatientInformationSql(patient, statement);
            statement.setString(8, patient.getUniqueId());
            return database.executePreparedStatement(statement);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

}
