package storage;


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

public class DaoStaffImpl<T extends Staff> implements Dao<T> {

    private final Database database = new Database();

    public boolean update(T staff) {
        database.connectToDB();

        String[] information = staff.getPersonInformation();
        String sql = "UPDATE staff set uniqueid = %s, name = %s, surname = %s, birthdate = %s, " +
                "gender = %s, homeaddress = %s, phonenumber = %s, email = %s, initials = %s";
        String sqlWhere = String.format(" where uniqueId = %s", staff.getUniqueId());

        executeStatement(information, sql + sqlWhere);
    }

    @SuppressWarnings("Duplicates")
    private void executeStatement(String[] information, String sql) {
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

        database.disconnectFromDB();
    }


    @Override
    public boolean update(T obj, HashMap<String, String> params) {

    }

    @Override
    public boolean save(T staff) {
        database.connectToDB();

        String[] information = staff.getPersonInformation();
        String sql = "insert into staff values('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')";
        executeStatement(information, sql);
    }

    @Override
    public boolean delete(String staff) {
        database.connectToDB();

        String sql = "delete from staff where uniqueid = '%s'";
        sql = String.format(sql, staff.getUniqueId());

        Statement statement = database.createStatement();

        try {
            statement.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public T find(T staff) {
        database.connectToDB();

        String sql = "select * from staff where uniqueid = '%s'";
        sql = String.format(sql, staff.getUniqueId());

        Statement statement = database.createStatement();
        T foundStaff = null;

        try {
            ResultSet set = statement.executeQuery(sql);

            if (set.next()) {
                foundStaff = (T) new Staff(
                        set.getString("uniqueid"),
                        set.getString("name"),
                        set.getString("surname"),
                        stringToDate(set.getString("birthdate")),
                        Integer.parseInt(set.getString("gender")),
                        set.getString("homeaddress"),
                        Integer.parseInt(set.getString("phonenumber")),
                        set.getString("email"),
                        set.getString("initials"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        database.disconnectFromDB();
        return foundStaff;
    }

    @Override
    public ArrayList<T> find(HashMap<String, String> params) {
        database.connectToDB();

        String sql = "select * from staff where ";
        String values = "";

        for (Map.Entry<String, String> entry :
                params.entrySet()) {
            String value = entry.getValue();

            if (!value.toLowerCase().startsWith("like") && !value.startsWith("=")) {
                value = " = " + value;
            }
            values = values.concat(entry.getKey() + value + "and ");
        }

        sql = sql + values.substring(0, values.length() - 4);

        Statement statement = database.createStatement();

        ArrayList<T> staff = new ArrayList<>();

        try {
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                staff.add((T) new Staff(
                        resultSet.getString("uniqueId"),
                        resultSet.getString("name"),
                        resultSet.getString("surname"),
                        stringToDate(resultSet.getString("birthdate")),
                        Integer.parseInt(resultSet.getString("gender")),
                        resultSet.getString("homeaddress"),
                        Integer.parseInt(resultSet.getString("phonenumber")),
                        resultSet.getString("email"),
                        resultSet.getString("initials")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        database.disconnectFromDB();
        return staff;
    }

    private Date stringToDate(String birthdate) {
        try {
            return new SimpleDateFormat("yyyy_mm_dd").parse(birthdate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;

    }
}
