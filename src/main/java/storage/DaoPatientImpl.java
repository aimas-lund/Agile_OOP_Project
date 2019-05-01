package storage;

import management.Patient;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DaoPatientImpl<T extends Patient> implements Dao<T> {
    private final Database database = new Database();

    @Override
    public boolean update(Patient patient) {
        String[] information = patient.getPersonInformation();
        String sql = "UPDATE patients set uniqueid = '%s', name = %s, surname = %s, birthdate = %s, " +
                "gender = %s, homeaddress = %s, phonenumber = %s where uniqueId = %s";

        for (String value :
                information) {
            sql = sql.replaceFirst("%s", value.replaceAll(" ", "_"));
        }

        sql = String.format(sql, patient.getUniqueId());

        return executeStatement(sql);
    }

    @Override
    public boolean update(T obj, HashMap<String, String> params) {
        return false;
    }

    @Override
    public boolean save(T patient) {
        database.connectToDB();
        String[] information = patient.getPersonInformation();
        String sql = "insert into patients values('%s', '%s', '%s', '%s', '%s', '%s', '%s')";
        for (String value :
                information) {
            sql = sql.replaceFirst("%s", value.replaceAll(" ", "_"));
        }

        return executeStatement(sql);
    }

    private boolean executeStatement(String sql) {
        database.connectToDB();

        try {
            Statement statement = database.createStatement();
            statement.executeUpdate(sql);
            database.disconnectFromDB();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            database.disconnectFromDB();
            return false;
        }
    }

    public boolean delete(Patient patient) {
        return delete(patient.getUniqueId());
    }

    @Override
    public boolean delete(String uniqueId) {
        database.connectToDB();

        String sql = "delete from patients where uniqueid = '%s'";
        sql = String.format(sql, uniqueId);

        Statement statement = database.createStatement();

        try {
            statement.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public T find(T patient) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("uniqueid", patient.getUniqueId());
        return find(hashMap).get(0); // Returns arrayList of length 1
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
                patients.add((T) new Patient(
                        resultSet.getString("uniqueId"),
                        resultSet.getString("name"),
                        resultSet.getString("surname"),
                        stringToDate(resultSet.getString("birthdate")),
                        Integer.parseInt(resultSet.getString("gender")),
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

    private Date stringToDate(String birthdate) {
        try {
            return new SimpleDateFormat("yyyy_MM_DD").parse(birthdate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;

    }
}
