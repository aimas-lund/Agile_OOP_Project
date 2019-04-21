package storage;

import management.Staff;

import javax.xml.crypto.Data;
import java.sql.Statement;

public class StaffUpdater implements IUpdate {
    Statement statement;

    StaffUpdater(Database database) {
        statement = database.createStatement();
    }

    public void updateStaffEmail(Staff staff, String email) {
//        statement.execute("")

        staff.setEmail(email);
    }
}
