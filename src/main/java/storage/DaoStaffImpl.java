package storage;

import management.Staff;

import java.sql.Statement;

public class DaoStaffImpl {
    private Statement statement;

    DaoStaffImpl(Database database) {
        statement = database.createStatement();
    }

    public void updateStaffEmail(Staff staff, String email) {
//        statement.execute("")
//        staff.setEmail(email);
    }
}
