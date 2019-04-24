package storage;

import management.Patient;

import java.sql.SQLException;
import java.sql.Statement;

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
//        sql = String.format(sql, information);

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
    public T find(T obj) {
        return null;
    }
}
