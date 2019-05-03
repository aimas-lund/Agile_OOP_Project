package tests;

import exceptions.PersonNotFoundException;
import management.Department;
import management.InDepartment;
import management.Patient;
import org.junit.Before;
import org.junit.Test;
import storage.QueryRoleClerk;

import java.util.Date;

import static junit.framework.TestCase.*;

public class QueryRoleClerkTest {
    private QueryRoleClerk queryRoleClerk = new QueryRoleClerk();
    private Patient patient;
    private Department department;


    @Before
    public void setup() {
        patient = new Patient(
                "Oline",
                "Fischersen",
                new Date(1556668800000L), // Date: 01_05_2019
                1,
                "DTUStreet 56",
                45231298);
        department = new InDepartment();
    }

    @Test
    public void registerPerson() {
        assertTrue(queryRoleClerk.registerPerson(patient, department));
        queryRoleClerk.delete(patient, department);

    }

    @Test
    public void registerPersonToMultipleDepartmentsFails() {
        queryRoleClerk.registerPerson(patient, department);
        assertFalse(queryRoleClerk.registerPerson(patient, department));

        queryRoleClerk.delete(patient, department);
    }

    @Test
    public void findPatientTestPass() throws PersonNotFoundException {
        queryRoleClerk.registerPerson(patient, department);
        assertEquals(queryRoleClerk.find(patient).getUniqueId(), patient.getUniqueId());
        queryRoleClerk.delete(patient, department);
    }

    @Test(expected = PersonNotFoundException.class)
    public void findPatientTestFail() throws PersonNotFoundException {
        queryRoleClerk.find(patient);
    }

    @Test(expected = PersonNotFoundException.class)
    public void findPatientTestFailDeletedPatient() throws PersonNotFoundException {
        queryRoleClerk.registerPerson(patient, department);
        queryRoleClerk.delete(patient, department);
        queryRoleClerk.find(patient);
    }

    @Test
    public void deleteFails() {
        queryRoleClerk.delete(new Patient("name", "surname"), department);
    }
}