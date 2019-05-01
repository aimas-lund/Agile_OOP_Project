package storage;

import management.Department;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DaoDepartmentImpl<T extends Department> implements Dao<T> {

    private final Database database = new Database();

    @Override
    public boolean update(T Department) {
        database.connectToDB();

        String[] information = Department.getDepartmentInformation();
        String sql = "UPDATE patients set uniqueId = %s, name = %s, availablebeds = %s, capacity = %s where uniqueId = %s";

        for (String value :
                information) {
            sql = sql.replaceFirst("%s", value.replaceAll(" ", "_"));
        }

        sql = String.format(sql, Department.getUniqueId());

        return database.executeStatement(sql);
    }

    @Override
    public boolean update(T obj, HashMap<String, String> params) {
        return false;
    }

    @Override
    public boolean save(T patient) {
        database.connectToDB();
        String[] information = patient.getDepartmentInformation();
        String sql = "insert into patients values('%s','%s', '%s', '%s')";
        for (String value :
                information) {
            sql = sql.replaceFirst("%s", value.replaceAll(" ", "_"));
        }

        return database.executeStatement(sql);
    }

    public boolean delete(Department department) {
        return delete(department.getUniqueId());
    }

    @Override
    public boolean delete(String uniqueid) {
        database.connectToDB();

        String sql = "delete from department where name = '%s'";
        sql = String.format(sql, uniqueid);

        return database.executeStatement(sql);
    }

    public T find(T department) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("uniqueid", department.getUniqueId());
        return find(hashMap).get(0);
    }


    @Override
    public ArrayList<T> find(HashMap<String, String> params) {
        database.connectToDB();

        String sql = "select * from departments where ";
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

        ArrayList<T> departments = new ArrayList<>();

        try {
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                departments.add((T) new Department(
                        resultSet.getString("uniqueId"),
                        resultSet.getString("name"),
                        resultSet.getInt("capacity"))
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        database.disconnectFromDB();
        return departments;
    }

}