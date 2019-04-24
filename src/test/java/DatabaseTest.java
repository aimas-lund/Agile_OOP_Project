
import org.junit.Before;
import org.junit.Test;
import storage.Database;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;


public class DatabaseTest {
    private Database database;

    @Before
    public void setUp() {
        database = new Database();
    }

    @Test
    public void disconnectFromDB() {
        database.disconnectFromDB();
        assertFalse(database.hasConnection());
    }

    @Test
    public void hasConnection() {
        assertTrue(database.hasConnection());
    }

    @Test
    public void createStatement() {
        Statement statement = database.createStatement();
        assertNotNull(statement);
    }

    @Test
    public void connectToDB() {
        database.connectToDB();
        assertTrue(database.hasConnection());
    }

    @Test
    public void createTable() {
        Database database = new Database();
        Statement statement = database.createStatement();

        // Delete table if currently exists
        try {
            statement.executeUpdate("drop table if exists test");
        } catch (SQLException e) {
            e.printStackTrace();
        }


        // create new table structure named "test"
        ArrayList<ArrayList<String>> list = new ArrayList<>(2);
        list.add(0, new ArrayList<String>(Arrays.asList("key", "integer")));
        list.add(1, new ArrayList<String>(Arrays.asList("name", "string")));
        assertTrue(database.createTable("test", list));

        // insert into table
        try {
            statement.executeUpdate("insert into test values (1, 'test 1')");
        } catch (SQLException e) {
            e.printStackTrace();
        };

        // query from table
        try {
            statement.executeQuery("SELECT * from test");
        } catch (SQLException e) {
            e.printStackTrace();
        };

        // will return false if you try to create a table that already exists
        assertFalse(database.createTable("test", list));
    }

    // TODO: Fix this test

    @Test
    public void deleteTable() {

        Statement statement = database.createStatement();

        // Delete table if currently exists
        try {
            statement.executeUpdate("drop table if exists test");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // use deleteTable method on a non-existing table
        database.deleteTable("test");

        // create a table
        try {
            statement.executeUpdate("create table test (id integer, name string)");
        } catch (SQLException e) {
            e.printStackTrace();
        };

        // use deleteTable method on an existing table
        database.deleteTable("test");

    }

}