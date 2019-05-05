package persistence.data_access_objects;


import core.persons.Gender;
import core.persons.Staff;
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

public class DaoStaffImpl<T extends Staff> implements IDao<T> {

    private final Database database = new Database();

    @Override
    public boolean save(T staff) {
        String[] information = staff.getPersonInformation();
        String sql = "insert into staff values('%s', '%s', '%s', date('%s'), '%s', '%s', '%s', '%s', '%s')";

        for (String value :
                information) {
            sql = sql.replaceFirst("%s", value.replaceAll(" ", "_"));
        }

        return database.executeStatement(sql);
    }

    @Override
    public ArrayList<T> find(HashMap<String, String> params) {
        database.connectToDB();

        String sql = "select * from staff where ";
        String values = "";

        for (Map.Entry<String, String> entry :
                params.entrySet()) {
            String value = entry.getValue();

            value = " = " + "'" + value + "'";

            values = values.concat(entry.getKey() + value + " and ");
        }

        sql = sql + values.substring(0, values.length() - 4);

        Statement statement = database.createStatement();

        ArrayList<T> staff = new ArrayList<>();

        try {
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Date birthdate = new SimpleDateFormat("yyyy-MM-dd").parse(resultSet.getString("birthdate"));

                staff.add((T) new Staff(
                        resultSet.getString("uniqueId"),
                        resultSet.getString("name"),
                        resultSet.getString("surname"),
                        birthdate,
                        Gender.valueOf(resultSet.getString("gender")),
                        resultSet.getString("homeaddress").replaceAll("_", " "),
                        Integer.parseInt(resultSet.getString("phonenumber")),
                        resultSet.getString("email"),
                        resultSet.getString("initials")
                ));
            }

        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        database.disconnectFromDB();
        return staff;
    }

    public <T extends Staff> T find(T staff) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("uniqueid", staff.getUniqueId());
        return (T) find(hashMap).get(0);
    }

    public boolean delete(Staff staff) {
        return delete(staff.getUniqueId());
    }

    @Override
    public boolean delete(String uniqueId) {
        String sql = "delete from staff where uniqueid = '%s'";
        sql = String.format(sql, uniqueId);

        return database.executeStatement(sql);
    }

    public boolean update(T staff) {
        String[] information = staff.getPersonInformation();
        String sql = "UPDATE staff set uniqueid = '%s', name = '%s', surname = '%s', birthdate = date('%s'), " +
                "gender = '%s', homeaddress = '%s', phonenumber = '%s', email = '%s', initials = '%s'";
        String sqlWhere = String.format(" where uniqueId = '%s'", staff.getUniqueId());

        for (String value :
                information) {
            if (value == null) return false;

            sql = sql.replaceFirst("%s", value.replaceAll(" ", "_"));
        }

        return database.executeStatement(sql + sqlWhere);
    }

}
