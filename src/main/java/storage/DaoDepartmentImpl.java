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
    public void update(T Department) {
        database.connectToDB();

        String[] information = Department.getDepartmentInformation();
        String sql = "UPDATE patients set uniqueId = %s, name = %s, availablebeds = %s, capacity = %s where uniqueId = %s";

        for (String value :
                information) {
            sql = sql.replaceFirst("%s", value.replaceAll(" ", "_"));
        }

        sql = String.format(sql, Department.getUniqueId());

        executeStatement(sql);
    }

    @Override
    public void update(T obj, String[] params) {

    }
    @Override
    public void save(T patient) {
        database.connectToDB();
        String[] information = patient.getDepartmentInformation();
        String sql = "insert into patients values('%s','%s', '%s', '%s')";
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
        } finally {
            database.disconnectFromDB();
        }

    }

    @Override
    public boolean delete(Department department) {
        database.connectToDB();

        String sql = "delete from department where name = '%s'";
        sql = String.format(sql, department.getName());

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
    public T find(T department) {
        database.connectToDB();

        String sql = "select * from patients where uniqueid = '%s'";
        sql = String.format(sql, department.getName());

        Statement statement = database.createStatement();
        T foundDepartment = null;

        try {
            ResultSet set = statement.executeQuery(sql);

            if (set.next()) {
                foundDepartment = (T) new Department(
                        set.getString("uniqueId"),
                        set.getString("name"),
                        set.getInt("capacity"));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        database.disconnectFromDB();
        return foundDepartment;
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