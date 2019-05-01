package tests;

import exceptions.PersonNotFoundException;
import management.Department;
import management.Patient;
import org.junit.Before;
import org.junit.Test;
import storage.QueryRoleClerk;

import java.util.Date;

import static junit.framework.TestCase.*;

public class ClerkTest {
    private QueryRoleClerk clerk = new QueryRoleClerk();
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
        department = new Department();
    }

    @Test
    public void registerPerson() {
        assertTrue(clerk.registerPerson(patient, department));
        clerk.delete(patient, department);

    }

    @Test
    public void registerPersonToMultipleDepartmentsFails() {
        clerk.registerPerson(patient, department);
        assertFalse(clerk.registerPerson(patient, department));

        clerk.delete(patient, department);
    }

    @Test
    public void findPatientTestPass() throws PersonNotFoundException {
        clerk.registerPerson(patient, department);
        assertEquals(clerk.find(patient).getUniqueId(), patient.getUniqueId());
        clerk.delete(patient, department);
    }

    @Test(expected = PersonNotFoundException.class)
    public void findPatientTestFail() throws PersonNotFoundException {
        clerk.find(patient);
    }

    @Test(expected = PersonNotFoundException.class)
    public void findPatientTestFailDeletedPatient() throws PersonNotFoundException {
        clerk.registerPerson(patient, department);
        clerk.delete(patient, department);
        clerk.find(patient);
    }

    @Test
    public void checkPatientRegisteredTestTrue() {
        department.add(patient);
        assertTrue(clerk.checkPatientRegistrationStatus(patient, department));
    }

    @Test
    public void checkPatientRegisteredTestFalsePatient() {
        department.add(patient);
        Patient falsePatient = new Patient();
        assertFalse(clerk.checkPatientRegistrationStatus(falsePatient, department));
    }

    @Test
    public void checkPatientRegisteredTestFalseDepartment() {
        department.add(patient);
        Department falseDepartment = new Department();
        assertFalse(clerk.checkPatientRegistrationStatus(patient, falseDepartment));
    }
}