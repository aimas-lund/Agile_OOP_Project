package tests;

import management.Patient;
import management.Staff;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import storage.QueryRoleICT;

import java.util.Date;

public class QueryRoleICTTest {
    private Patient patient;
    private Staff staff;
    private QueryRoleICT queryRoleICT = new QueryRoleICT();

    @Before
    public void setUp() {
        patient = new Patient(
                "testID",
                "Oline",
                "Fischersen",
                new Date(1556668800000L), // Date: 01_05_2019
                1,
                "DTUStreet 56",
                45231298);

        staff = new Staff(
                "testID",
                "Oline",
                "Fischersen",
                new Date(1556668800000L), // Date: 01_05_2019
                1,
                "DTUStreet 56",
                45231298,
                "OLFI@saxobank.com",
                "OLFI");

    }

    @After
    public void tearDown() {
        patient = new Patient();
        staff = new Staff();
    }

    @Test
    public void find() {
    }

    @Test
    public void find1() {
    }

    @Test
    public void findStaff() {
    }

    @Test
    public void findPatient() {
    }

    @Test
    public void find2() {
    }

    @Test
    public void registerPerson() {
    }

    @Test
    public void isPersonRegistered() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void update() {
    }

    @Test
    public void delete1() {
    }

    @Test
    public void registerPerson1() {
    }

    @Test
    public void isPersonRegistered1() {
    }
}