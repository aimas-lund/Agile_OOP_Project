package tests;

import management.Clerk;
import management.Patient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import storage.DaoPatientImpl;

import java.util.Date;
import java.util.HashMap;

import static org.junit.Assert.*;

public class DaoPatientImplTest {
    private final DaoPatientImpl<Patient> dao = new DaoPatientImpl<>();
    private Patient patient;

    @Before
    public void setUp() {
        patient = new Patient(
                "testID",
                "Oline",
                "Fischersen",
                new Date(1556668800000L), // Date: 01_05_2019
                1,
                "DTUStreet 56",
                45231298);

        dao.save(patient);
    }

    @After
    public void tearDown() {
        dao.delete(patient);
        patient = new Patient();
    }


    @Test
    public void updateAllValuesFromObject() {
        new Clerk().setPersonName(patient,
                "Aimas");

        assertTrue(dao.update(patient));
        assertEquals("Aimas", dao.find(patient).getName());

    }

    @Test
    public void updateSomeValuesFromHashMap() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("name", "Simon");
        hashMap.put("surname", "Simonsen");
        hashMap.put("gender", "0");

        assertTrue(dao.update(patient, hashMap));
        assertEquals("Simon", dao.find(patient).getName());
        assertEquals("Simonsen", dao.find(patient).getSurname());
        assertEquals(0, dao.find(patient).getGender());
    }

    @Test
    public void updateMissingColumnsFails() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("dabbering", "Simon");

        assertFalse(dao.update(patient, hashMap));
    }

    @Test
    public void save() {
        assertEquals(patient.getUniqueId(), dao.find(patient).getUniqueId());
    }

    @Test
    public void saveMultipleFails() {
        assertFalse(dao.save(patient));
    }

    @Test
    public void find() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("name", "Simon");
        hashMap.put("surname", "Simonsen;'");
        hashMap.put("gender", "0");

        assertTrue(dao.find(hashMap).isEmpty());
    }
}