import management.Patient;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class PatientTest {
    Patient patient = new Patient();

    @Test
    public void isAlive() {
        assertTrue(patient.isAlive());
    }
}