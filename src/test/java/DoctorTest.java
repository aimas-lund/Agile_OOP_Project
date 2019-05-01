import management.Doctor;
import management.Speciality;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;

import static org.junit.Assert.*;

public class DoctorTest {

    private Doctor doctor;

    @Before
    public void setup() {
        doctor = new Doctor();
        doctor.setSpeciality(Speciality.NEUROLOGY);
    }

    @Test
    public void getSpecialtyTest() {
        assertEquals(doctor.getSpeciality(), Speciality.NEUROLOGY);
    }
}
