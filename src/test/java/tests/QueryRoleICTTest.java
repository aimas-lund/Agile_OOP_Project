package tests;

import management.Department;
import management.Patient;
import management.Staff;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import storage.QueryRoleICT;

import java.util.Date;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;


public class QueryRoleICTTest {
    private Patient patient;
    private Staff staff;
    private QueryRoleICT queryRoleICT = new QueryRoleICT();
    private Department department;

    @Before
    public void setUp() {
        department = new Department("test", 1);

        patient = new Patient(
                "testID",
                "Jorgen",
                "Vlad",
                new Date(1556668800000L), // Date: 01_05_2019
                1,
                "DTUStreet 51",
                45231298);

        assertTrue(queryRoleICT.registerPerson(patient, department));

        staff = new Staff(
                "testID2",
                "Oline",
                "Fischersen",
                new Date(1556668800000L), // Date: 01_05_2019
                1,
                "DTUStreet 56",
                45231298,
                "OLFI@saxobank.com",
                "OLFI");

        assertTrue(queryRoleICT.registerPerson(staff, department));

    }

    @After
    public void tearDown() {
        assertTrue(queryRoleICT.delete(staff, department));
        assertTrue(queryRoleICT.delete(patient, department));

        patient = new Patient();
        staff = new Staff();
    }

    @Test
    public void duplicateRegisterPersonStaffFails() {
        assertFalse(queryRoleICT.registerPerson(staff, department));
    }

    @Test
    public void duplicateRegisterPersonPatientFails() {
        assertFalse(queryRoleICT.registerPerson(patient, department));
    }

    @Test
    public void isPersonRegistered() {

    }

    @Test
    public void findPatient() {
//        queryRoleICT.find(patient);
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
    public void find2() {
    }

    @Test
    public void registerPerson1() {
    }

    @Test
    public void isPersonRegistered1() {
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


}