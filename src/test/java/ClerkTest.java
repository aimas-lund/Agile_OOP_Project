import management.Clerk;
import management.Department;
import management.Patient;
import management.Person;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.*;

public class ClerkTest {
    private Clerk clerk = new Clerk();
    private Person patient;
    private Department department;


    @Before
    public void setUp() {
        patient = new Patient();
        department = new Department();
    }

    @Test
    public void registerPerson() {
        assertTrue(clerk.registerPerson(patient, department));

    }
    @Test
    public void registerPersonToMultipleDepartments() {
        clerk.registerPerson(patient, department);
        assertFalse(clerk.registerPerson(patient, department));
    }

    @Test
    public void setPersonPhoneNumber() {
        int correct_phonenumber = 42042069;
        int incorrect_phonenumber = 420420693;

        assertTrue(clerk.setPersonPhoneNumber(patient, correct_phonenumber));
        assertFalse(clerk.setPersonPhoneNumber(patient, incorrect_phonenumber));
    }
}