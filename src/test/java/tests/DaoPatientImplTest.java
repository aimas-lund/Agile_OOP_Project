package tests;

import management.Clerk;
import management.Patient;
import org.junit.Test;
import storage.DaoPatientImpl;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DaoPatientImplTest {
    private final DaoPatientImpl<Patient> dao = new DaoPatientImpl<>();

    @Test
    public void update() {
        Patient patient = new Patient(
                "testID",
                "Oline",
                "Fischersen",
                new Date(1556668800000L), // Date: 01_05_2019
                1,
                "DTUStreet 56",
                45231298);
        String oldName = patient.getName();
        assertTrue(dao.save(patient));

        new Clerk().setPersonName(patient,
                "Aimas");

        assertTrue(dao.update(patient));

        assertEquals("Aimas", dao.find(patient).getName());
    }

    @Test
    public void update1() {
    }

    @Test
    public void save() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void delete1() {
    }

    @Test
    public void find() {
    }
}