package tests.core.persons;

import core.persons.Gender;
import core.persons.Patient;
import org.junit.Test;

import java.util.Date;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class PatientTest {
    private Patient patient = new Patient();
    private Date birthdate = new Date(631152000000L);
    private Patient patientWithInformation = new Patient(
            "foo",
            "bar",
            birthdate,
            Gender.MALE,
            "Foo Bar Street 38",
            12345678);

    @Test
    public void isAlive() {
        assertTrue(patient.isAlive());
    }

    @Test
    public void getPersonInformationName() {
        assertEquals("foo", patientWithInformation.getPersonInformation()[1]);
    }

    @Test
    public void getPersonInformationSurname() {
        assertEquals("bar", patientWithInformation.getPersonInformation()[2]);
    }

    @Test
    public void getPersonInformationBirthdate() {
        assertEquals("1990_01_01", patientWithInformation.getPersonInformation()[3]);
    }

    @Test
    public void getPersonInformationGender() {
        assertEquals(Gender.MALE, Gender.valueOf(patientWithInformation.getPersonInformation()[4]));
    }

    @Test
    public void getPersonInformationHomeAddress() {
        assertEquals("Foo Bar Street 38", patientWithInformation.getPersonInformation()[5]);
    }

    @Test
    public void getPersonInformationPhoneNumber() {
        assertEquals("12345678", patientWithInformation.getPersonInformation()[6]);
    }

}