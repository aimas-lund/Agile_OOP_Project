package tests;

import management.Patient;
import org.junit.jupiter.api.Test;
import storage.Dao;
import storage.DaoPatientImpl;

import java.util.Date;

class DaoPatientImplTest {
    private Dao<Patient> dao = new DaoPatientImpl<>();

    @Test
    void update() {
        // TODO: Finish this

        Patient patient = new Patient(
                "Bobby",
                "Fischer",
                new Date(2019),
                0,
                "Homestreet 23",
                45231298);

        dao.save(patient);
        dao.update(patient);
    }
}