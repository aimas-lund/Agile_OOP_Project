package tests.core.persons;

import core.persons.Doctor;
import core.persons.PersonInformationFacade;
import core.utility.Speciality;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DoctorTest {

    private Doctor doctor;

    @Before
    public void setup() {
        doctor = new Doctor();
        new PersonInformationFacade(doctor).setDoctorSpeciality(Speciality.NEUROLOGY);
    }

    @Test
    public void getSpecialtyTest() {
        assertEquals(doctor.getSpeciality(), Speciality.NEUROLOGY);
    }
}
