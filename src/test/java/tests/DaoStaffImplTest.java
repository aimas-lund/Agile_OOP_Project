package tests;

import management.Staff;
import org.junit.jupiter.api.Test;
import storage.Dao;
import storage.DaoStaffImpl;

import java.util.Date;

class DaoStaffImplTest {
        Dao dao = new DaoStaffImpl ();

        @Test
        void update() {


            Staff staff = new Staff(1, "Dr.", "Bobby", new Date (2019), 0, "Homestreet 23", 45231298, "Bobbyemail@email.com", "DB");

            dao.save(staff);
            dao.update(staff);
        }
    }
