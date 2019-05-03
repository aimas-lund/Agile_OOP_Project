package tests;

import exceptions.PersonNotFoundException;
import management.Department;
import management.Patient;
import management.PersonInformationFacade;
import management.Staff;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import storage.QueryRoleICT;

import java.util.Date;
import java.util.HashMap;

import static junit.framework.TestCase.*;


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
    public void findPatient() {
        try {
            queryRoleICT.find(patient);
        } catch (PersonNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findStaff() {
        try {
            queryRoleICT.find(staff);
        } catch (PersonNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test(expected = PersonNotFoundException.class)
    public void findStaffThrows() throws PersonNotFoundException {
        queryRoleICT.find(new Staff("name", "surname"));
    }

    @Test(expected = PersonNotFoundException.class)
    public void findPatientThrows() throws PersonNotFoundException {
        queryRoleICT.find(new Patient("name", "surname"));
    }

    @Test(expected = PersonNotFoundException.class)
    public void findEmptyArray() throws PersonNotFoundException {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("uniqueid", "nonexistent");

        queryRoleICT.find(hashMap, new Patient());
    }

    @Test
    public void deleteStaffFails() {
        queryRoleICT.delete(new Staff("name", "surname"), department);
    }

    @Test
    public void deletePatientFails() {
        queryRoleICT.delete(new Staff("name", "surname"), department);
    }

    @Test
    public void updateStaff() throws PersonNotFoundException {
        new PersonInformationFacade(staff).setStaffInitials("HjNO");
        new PersonInformationFacade(staff).setPersonName("Hjalte");
        new PersonInformationFacade(staff).setPersonSurname("Nordgaard");

        assertTrue(queryRoleICT.update(staff));
        Staff actual = queryRoleICT.find(staff);
        assertEquals("Hjalte", actual.getName());
        assertEquals("Nordgaard", actual.getSurname());
        assertEquals("HJNO", actual.getInitials());
        assertEquals("HJNO@agile_hospital.com", actual.getEmail());
    }

    @Test
    public void updatePatient() throws PersonNotFoundException {
        new PersonInformationFacade(patient).setPersonName("Hjalte");
        new PersonInformationFacade(patient).setPersonSurname("Nordgaard");

        assertTrue(queryRoleICT.update(patient));
        Patient actual = queryRoleICT.find(patient);
        assertEquals("Hjalte", actual.getName());
        assertEquals("Nordgaard", actual.getSurname());
    }

    @Test
    public void updateFails() {
        assertFalse(queryRoleICT.update(new Staff("name", "surname")));
    }

}