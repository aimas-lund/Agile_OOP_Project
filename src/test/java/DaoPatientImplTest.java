package tests;

import management.Patient;
import org.junit.jupiter.api.Test;
import storage.Dao;
import storage.DaoPatientImpl;

import java.util.Date;

class DaoPatientImplTest {
    Dao dao = new DaoPatientImpl();

    @Test
    void update() {


        Patient patient = new Patient(1,
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