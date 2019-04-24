import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import storage.Database;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseTest {
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
        ArrayList<ArrayList<String>> list = new ArrayList<>(2);
        list.add(0, new ArrayList<String>(Arrays.asList("key", "integer")));
        list.add(0, new ArrayList<String>(Arrays.asList("name", "string")));
        assertTrue(database.createTable("test", list));

        Statement statement = database.createStatement();

        assertDoesNotThrow(() -> statement.executeUpdate("insert into test values (1, '420blazin')"));
        assertDoesNotThrow(() -> statement.executeQuery("SELECT * from test"));
        assertFalse(database.createTable("test", list));
    }

    @Test
    public void deleteTable() {
        database.deleteTable("test");

        Statement statement = database.createStatement();
        assertDoesNotThrow(() -> statement.executeUpdate("create table test (id integer, name string)"));
        assertDoesNotThrow(() -> statement.executeUpdate("drop table test"));

    }

//    @AfterAll
//    public void deleteMockTables() {
//    }
}