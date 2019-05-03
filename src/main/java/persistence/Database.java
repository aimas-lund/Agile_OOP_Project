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

    public void deleteTable(String name) {
        Statement statement = createStatement();
        String sql = String.format("drop table if exists %s", name);
        try {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean executeStatement(String sql) {
        connectToDB();

        try {
            Statement statement = createStatement();
            statement.executeUpdate(sql);
            disconnectFromDB();
            return true;
        } catch (SQLException e) {
            disconnectFromDB();
            return false;
        }
    }
//
//    public static void main(String[] args) {
//        Connection connection = null;
//        try {
//            // create a database connection
//            connection = DriverManager.getConnection("jdbc:sqlite:sample.db");
//            Statement statement = connection.createStatement();
//            statement.setQueryTimeout(30);  // set timeout to 30 sec.
//
//            statement.executeUpdate("drop table if exists person");
//            statement.executeUpdate("create table person (id integer, name string)");
//            statement.executeUpdate("insert into person values(1, 'dsgfdg')");
//            statement.executeUpdate("insert into person values(2, 'yui')");
//            ResultSet rs = statement.executeQuery("select * from person");
//            while (rs.next()) {
//                // read the result set
//                System.out.println("name = " + rs.getString("name"));
//                System.out.println("id = " + rs.getInt("id"));
//            }
//        } catch (SQLException e) {
//            // if the error message is "out of memory",
//            // it probably means no database file is found
//            System.err.println(e.getMessage());
//        } finally {
//            try {
//                if (connection != null)
//                    connection.close();
//            } catch (SQLException e) {
//                // connection close failed.
//                System.err.println(e.getMessage());
//            }
//        }
//    }
}
