package tests;

import management.Patient;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class PatientTest {
    Patient patient = new Patient();

    @Test
    void isAlive() {
        assertTrue(patient.isAlive());
    }


}