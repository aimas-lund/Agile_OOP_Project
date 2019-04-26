package storage;

import management.Patient;
import management.Staff;

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
    public void update(T patient) {
        database.connectToDB();

        String[] information = patient.getPersonInformation();
        String sql = "UPDATE patients set uniqueid = %s, name = %s, surname = %s, birthdate = %s, " +
                "gender = %s, homeaddress = %s, phonenumber = %s where uniqueId = %s";

        for (String value :
                information) {
            sql = sql.replaceFirst("%s", value.replaceAll(" ", "_"));
        }

        sql = String.format(sql, patient.getUniqueId());

        executeStatement(sql);
    }

    @Override
    public void update(T obj, String[] params) {

    }

    @Override
    public void save(T patient) {
        database.connectToDB();
        String[] information = patient.getPersonInformation();
        String sql = "insert into patients values('%s', '%s', '%s', '%s', '%s', '%s', '%s')";
        for (String value :
                information) {
            sql = sql.replaceFirst("%s", value.replaceAll(" ", "_"));
        }

        executeStatement(sql);
    }

    private void executeStatement(String sql) {
        try {
            Statement statement = database.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        database.disconnectFromDB();
    }

    @Override
    public void delete(T obj) {

    }

    @Override
    public T find(T patient) {
        database.connectToDB();

        String sql = "select * from patients where uniqueid = %s";
        sql = String.format(sql, patient.getUniqueId());

        Statement statement = database.createStatement();

        try {
            ResultSet set = statement.executeQuery(sql);

            if (set.next()) {
                return (T) new Patient(set.getString("name"),
                        set.getString("surname"),
                        stringToDate(set.getString("birthdate")),
                        Integer.parseInt(set.getString("gender")),
                        set.getString("homeaddress"),
                        Integer.parseInt(set.getString("phonenumber")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        database.disconnectFromDB();
        return null;
    }


    @Override
    public ArrayList<T> find(HashMap<String, String> params) {
        database.connectToDB();

        String sql = "select * from patients where ";
        String values = "";

        for (Map.Entry<String, String> entry :
                params.entrySet()) {
            String value = entry.getValue();

            if (!value.toLowerCase().startsWith("like") && !value.startsWith("=")) {
                value = " = " + value;
            }
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
