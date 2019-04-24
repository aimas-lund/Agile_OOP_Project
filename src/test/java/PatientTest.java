import management.Patient;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class PatientTest {
    Patient patient = new Patient();

    @Test
    public void isAlive() {
        assertTrue(patient.isAlive());
    }


}