package storage;

import management.Staff;

import java.sql.Statement;

public class StaffUpdater implements IUpdate {
    private Statement statement;

    StaffUpdater(Database database) {
        statement = database.createStatement();
    }

    public void updateStaffEmail(Staff staff, String email) {
//        statement.execute("")
//        staff.setEmail(email);
    }
}
