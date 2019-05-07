package tests.core.buildings;

import core.buildings.*;
import core.persons.Patient;
import exceptions.ExceededCapacityException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.*;

public class DepartmentBedsTest {
    private DepartmentBeds inDepartment = new InDepartment();
    private DepartmentBeds erDepartment = new ERDepartment();

    @Before
    public void setUp() {
        inDepartment = new InDepartment("dep1","Saint Java", 1);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void constructSaturatedDepartment() {
        String uniqueid = "pølz";
        String name = "Some name";
        int totalCapacity = 10;
        int currentCapacity = 9;
        HashMap<Patient, Bed> patientsInBed = new HashMap<>();
        Patient patient = new Patient("id1");
        patientsInBed.put(patient, new Bed(0));

        DepartmentBeds fullDepartment = new InDepartment(uniqueid, name, totalCapacity, currentCapacity, patientsInBed);

        patientsInBed.put(patient, new Bed(5));
        DepartmentBeds fullDepartment2 = new ERDepartment(uniqueid, name, totalCapacity, currentCapacity, patientsInBed);

        assertTrue(fullDepartment.isPatientInBed(patient));
        assertTrue(fullDepartment.getBed(0).isOccupied());
        assertFalse(fullDepartment.getBed(1).isOccupied());
        assertTrue(fullDepartment2.getBed(5).isOccupied());

    }

    @Test
    public void getPatientsInBeds() throws ExceededCapacityException {
        BedManager bedManager = new BedManager(inDepartment);
        Patient patient = new Patient("id");
        bedManager.assignToBed(patient);
        for (Map.Entry<Patient, Bed> entry : inDepartment.getPatientsInBeds().entrySet()) {
            assertEquals(entry.getKey(), patient);
            assertEquals(entry.getValue(), inDepartment.getBed(0));
        }
    }

    @Test
    public void getBedExceedsCapacity() {
        assertNull(inDepartment.getBed(10));
    }

    @Test
    public void getTotalCapacity() {
        assertEquals(inDepartment.getTotalCapacity(), 1);
    }

    @Test
    public void getDepartmentInformation() {
        String[] departmentInfo = inDepartment.getDepartmentInformation();
        assertEquals("dep1", departmentInfo[0]);
        assertEquals("Saint Java", departmentInfo[1]);
        assertEquals("InDepartment", departmentInfo[2]);
        assertEquals("1", departmentInfo[3]);
        assertEquals("1", departmentInfo[4]);
    }

    @Test
    public void getCurrentCapacity() {
        assertEquals(1, inDepartment.getCurrentCapacity());
    }
}
