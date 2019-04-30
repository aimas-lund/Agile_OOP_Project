import management.Patient;
import java.util.Date;
import org.junit.Test;

import static junit.framework.TestCase.*;

public class PatientTest {
    private Patient patient = new Patient();
    private Date birthdate = new Date(631148400000L);
    private Patient patientWithInformation = new Patient(
            "foo",
            "bar",
            birthdate,
            0,
            "Foo Bar Street 38",
            12345678);

    @Test
    public void isAlive() {
        assertTrue(patient.isAlive());
    }
    @Test
    public void getPersonInformationName() {
        assertSame("foo", patientWithInformation.getPersonInformation()[1]);
    }
    @Test
    public void getPersonInformationSurname() {
        assertSame("bar", patientWithInformation.getPersonInformation()[2]);
    }
    @Test
    public void getPersonInformationBirthdate() {
        assertEquals("1990_01_01", patientWithInformation.getPersonInformation()[3]);
    }
    @Test
    public void getPersonInformationGender() {
        assertEquals("0", patientWithInformation.getPersonInformation()[4]);
    }
    @Test
    public void getPersonInformationHomeAddress() {
        assertSame("Foo Bar Street 38", patientWithInformation.getPersonInformation()[5]);
    }
    @Test
    public void getPersonInformationPhoneNumber() {
        assertEquals("12345678", patientWithInformation.getPersonInformation()[6]);
    }

}