import management.Clerk;
import management.Department;
import management.Patient;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class ClerkTest {
    private Clerk clerk = new Clerk();
    private Patient patient;
    private Department department;


    @Before
    public void setUp() {
        patient = new Patient(
                "Oline",
                "Fischersen",
                new Date(2019),
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
    public void setPersonPhoneNumber() {
        int correct_phonenumber = 42042069;
        int incorrect_phonenumber = 420420693;

        assertTrue(clerk.setPersonPhoneNumber(patient, correct_phonenumber));
        assertFalse(clerk.setPersonPhoneNumber(patient, incorrect_phonenumber));
    }
}