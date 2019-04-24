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

    public void update(T staff) {
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
    public void update(T obj, String[] params) {

    }

    @Override
    public void save(T staff) {
        database.connectToDB();

        String[] information = staff.getPersonInformation();
        String sql = "insert into patients values('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')";
        executeStatement(information, sql);
    }

    @Override
    public void delete(T obj) {

    }

    @Override
    public T find(T obj) {

        return null;
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
                value += " = " + value;
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
