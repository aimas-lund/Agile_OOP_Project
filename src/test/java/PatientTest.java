import management.Patient;
import java.util.Date;
import org.junit.Test;

import static junit.framework.TestCase.assertSame;
import static junit.framework.TestCase.assertTrue;

public class PatientTest {
    Patient patient = new Patient();
    Date birthdate = new Date(631148400000L);
    Patient patientWithInformation = new Patient(
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
        assertSame("1990_01_01", patientWithInformation.getPersonInformation()[3]);
    }
    @Test
    public void getPersonInformationGender() {
        assertSame("0", patientWithInformation.getPersonInformation()[4]);
    }
    @Test
    public void getPersonInformationHomeAddress() {
        assertSame("Foo Bar Street 38", patientWithInformation.getPersonInformation()[5]);
    }
    @Test
    public void getPersonInformationPhoneNumber() {
        assertSame("12345678", patientWithInformation.getPersonInformation()[6]);
    }

}