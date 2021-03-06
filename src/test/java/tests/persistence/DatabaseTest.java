package tests.persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import persistence.Database;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;


public class DatabaseTest {
    private Database database;

    @Before
    public void setUp() {
        database = new Database();
    }

    @After
    public void tearDown() {
        database.disconnectFromDB();
    }

    @Test
    public void disconnectFromDB() {
        database.connectToDB();
        database.disconnectFromDB();
        assertFalse(database.hasConnection());
    }

    @Test
    public void hasConnection() {
        assertTrue(database.hasConnection());
    }


    @Test
    public void connectToDB() {
        database.connectToDB();
        assertTrue(database.hasConnection());
        database.disconnectFromDB();
    }

    @Test
    public void createTable() {
        PreparedStatement statement;
        String sql;

        // Delete table if currently exists
        try {
            sql = "drop table if exists test";
            statement = database.prepareStatement(sql);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        // create new table structure named "test"
        ArrayList<ArrayList<String>> list = new ArrayList<>(2);
        list.add(0, new ArrayList<>(Arrays.asList("key", "integer")));
        list.add(1, new ArrayList<>(Arrays.asList("name", "string")));
        assertTrue(database.createTable("test", list));

        // insert into table

        try {
            sql = "insert into test values (1, 'test 1')";
            statement = database.prepareStatement(sql);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // query from table
        try {
            sql = "SELECT * from test";
            statement = database.prepareStatement(sql);
            statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // will return false if you try to create a table that already exists
        assertFalse(database.createTable("test", list));

        try {
            sql = "DROP table IF exists test";
            statement = database.prepareStatement(sql);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deleteTable() {
        String sql;
        PreparedStatement statement;
        // Delete table if currently exists
        try {
            sql = "drop table if exists test";
            statement = database.prepareStatement(sql);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // use deleteTable method on a non-existing table
        assertFalse(database.deleteTable("test"));

        // create a table
        try {
            sql = "create table test (id integer, name string)";
            statement = database.prepareStatement(sql);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // use deleteTable method on an existing table
        assertTrue(database.deleteTable("test"));

    }

}