package tests;

import management.Clerk;
import management.Department;
import management.Patient;
import management.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ClerkTest {
    private Clerk clerk = new Clerk();
    private Person patient;
    private Department department;


    @BeforeEach
    void setUp() {
        patient = new Patient();
        department = new Department();
    }

    @Test
    void registerPerson() {
        assertTrue(clerk.registerPerson(patient, department));
        assertFalse(clerk.registerPerson(patient, department));
    }

    @Test
    void setPersonPhoneNumber() {
        int correct_phonenumber = 42042069;
        int incorrect_phonenumber = 420420693;

        assertTrue(clerk.setPersonPhoneNumber(patient, correct_phonenumber));
        assertFalse(clerk.setPersonPhoneNumber(patient, incorrect_phonenumber));
    }
}