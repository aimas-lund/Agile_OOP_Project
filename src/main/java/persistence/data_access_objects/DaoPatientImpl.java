package persistence.data_access_objects;

import core.persons.Gender;
import core.persons.Patient;
import persistence.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DaoPatientImpl<T extends Patient> implements IDao<T> {
    private final Database database = new Database();

    @Override
    public boolean save(T patient) {
        String[] information = patient.getPersonInformation();
        String sql = "insert into patients values('%s', '%s', '%s', date('%s'), '%s', '%s', '%s')";
        for (String value :
                information) {
            sql = sql.replaceFirst("%s", value.replaceAll(" ", "_"));
        }

        return database.executeStatement(sql);
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
