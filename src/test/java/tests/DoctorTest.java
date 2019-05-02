package tests;

import management.Doctor;
import management.PersonInformationFacade;
import management.Speciality;
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
