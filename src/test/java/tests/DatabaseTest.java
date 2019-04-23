package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storage.Database;

import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseTest {
    private Database database;

    @BeforeEach
    void setUp() {
        database = new Database();
    }

    @Test
    void disconnectFromDB() {
        database.disconnectFromDB();
        assertFalse(database.hasConnection());
    }

    @Test
    void hasConnection() {
        assertFalse(database.hasConnection());
        database.connectToDB();
        assertTrue(database.hasConnection());
    }

    @Test
    void createStatement() {
        Statement statement = database.createStatement();
        assertNotNull(statement);
    }
}