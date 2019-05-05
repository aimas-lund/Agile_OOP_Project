package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Database {
    private Connection connection = null;

    public Database() {
        connectToDB();
    }

    public void connectToDB() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:sample.db");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void disconnectFromDB() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean hasConnection() {
        if (connection == null) return false;

        try {
            return (!connection.isClosed());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public Statement createStatement() {
        // Connect to database if null
        if (!(hasConnection())) {
            connectToDB();
        }
        try {
            return connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean createTable(String name, ArrayList<ArrayList<String>> fields) {
        Statement statement = createStatement();
        String query = "create table %s (%s";
        String values = "";

        for (ArrayList<String> field:
                fields) {
            values = values.concat(field.get(0) + " " + field.get(1) + ", ");
        }

        values = values.substring(0,values.length() - 2);
        values = String.format(values, name);
        query = String.format(query + ")", name, values);

        try {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            return false;
        }

        return true;
    }

    public boolean deleteTable(String name) {
        Statement statement = createStatement();
        String sql = String.format("drop table %s", name);
        try {
            statement.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean executeStatement(String sql) {
        connectToDB();

        try {
            Statement statement = createStatement();
            int i = statement.executeUpdate(sql);
            if (i > 0) {
                disconnectFromDB();
                return true;
            } else {
                disconnectFromDB();
                return false;
            }
        } catch (SQLException e) {
            disconnectFromDB();
            return false;
        }
    }
}
