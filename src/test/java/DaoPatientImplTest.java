import management.Patient;
import org.junit.Test;
import storage.Dao;
import storage.DaoPatientImpl;

import java.util.Date;

public class DaoPatientImplTest {
    private Dao<Patient> dao = new DaoPatientImpl<>();

    @Test
    public void update() {
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