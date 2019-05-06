package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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

    public PreparedStatement prepareStatement(String sql) throws SQLException {
        return prepareStatement(sql, true);
    }

    public PreparedStatement prepareStatement(String sql, boolean autoCommit) throws SQLException {
        // Connect to database if null
        if (!(hasConnection())) {
            connectToDB();
        }
        connection.setAutoCommit(autoCommit);
        return connection.prepareStatement(sql);

    }

    public boolean createTable(String name, ArrayList<ArrayList<String>> fields) {
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
            PreparedStatement statement = prepareStatement(query);
            statement.executeUpdate();
            disconnectFromDB();
        } catch (SQLException e) {
            disconnectFromDB();
            return false;
        }

        return true;
    }

    public boolean deleteTable(String name) {
        String sql = String.format("drop table %s", name);
        try {
            PreparedStatement statement = prepareStatement(sql);
            statement.executeUpdate();
            disconnectFromDB();
            return true;
        } catch (SQLException e) {
            disconnectFromDB();
            return false;
        }
    }

    public boolean executePreparedStatementBatch(PreparedStatement statement) {
        return executePreparedStatementBatch(statement, true);
    }

    public boolean executePreparedStatementBatch(PreparedStatement statement, boolean shouldCommit) {
        boolean success;
        try {
            try {
                int[] updateCount = statement.executeBatch();

                for (int update :
                        updateCount) {
                    if (update < 1) {
                        return false;
                    }
                }
                if (shouldCommit) {
                    connection.commit();
                }
                success = true;
            } catch (SQLException e) {
                connection.rollback();
                success = false;
            } finally {
                if (shouldCommit) {
                    connection.close();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            success = false;
        }
        return success;
    }

    public boolean executePreparedStatement(PreparedStatement statement) {
        return executePreparedStatement(statement, true);
    }

    public boolean executePreparedStatement(PreparedStatement statement, boolean shouldCommit) {
        boolean success;
        try {
            try {
                int update = statement.executeUpdate();

                if (update <= 0) {
                    return false;
                }

                if (shouldCommit && !connection.getAutoCommit()) {
                    connection.commit();
                }

                success = true;
            } catch (SQLException e) {
                if (!connection.getAutoCommit()) {
                    connection.rollback();
                }
                success = false;
            } finally {
                if (shouldCommit) {
                    connection.close();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            success = false;
        }
        return success;
    }


    public void commit() {
        try {
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
