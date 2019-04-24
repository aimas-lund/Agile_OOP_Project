package storage;


import management.Staff;

import java.sql.SQLException;
import java.sql.Statement;

public class DaoStaffImpl<T extends Staff> implements Dao<T> {

    private final Database database = new Database();
    private Statement statement = database.createStatement();


    public void update(T staff) {
        String[] information = staff.getPersonInformation();
        String sql = "UPDATE test set uniqueid = %s, name = %s, surname = %s, birthdate = %s, " +
                "gender = %s, homeaddress = %s, phonenumber = %s, email = %s, initials = %s";

        for (String value :
                information) {
            sql = sql.replaceFirst("%s", value.replaceAll(" ", "_"));
        }

        try {
            Statement statement = database.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }


    @Override
    public void update(T obj, String[] params) {

    }

    @Override
    public void save(T staff) {
        String[] information = staff.getPersonInformation();
        String sql = "insert into patients values('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')";
        for (String value :
                information) {
            sql = sql.replaceFirst("%s", value.replaceAll(" ", "_"));
        }

        try {
            Statement statement = database.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(T obj) {

    }

    @Override
    public T find(T obj) {
        return null;
    }
}
