package tests.core.buildings;

import core.buildings.BedManager;
import core.buildings.InDepartment;
import core.persons.Bed;
import core.persons.Clerk;
import core.persons.Patient;
import core.persons.Staff;
import exceptions.ExceededCapacityException;
import exceptions.UnavailableBedException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.*;

public class BedManagerTest {
    private BedManager bedManager10;
    private BedManager bedManager1;
    private BedManager bedManager0;
    private InDepartment departmentWithCapacity10;
    private InDepartment departmentWithCapacity1;
    private InDepartment departmentWithCapacity0;
    private Patient patient2;
    private Patient patient1;
    private Staff staff;

    @Before
    public void setUp() {
        patient2 = new Patient("pat2");
        patient1 = new Patient("pat1");
        staff = new Clerk("staff1");
        departmentWithCapacity10 = new InDepartment("departmentWithCapacity10", "Saint John", 10);
        departmentWithCapacity1 = new InDepartment("departmentWithCapacity1", "Saint Alice", 1);
        departmentWithCapacity0 = new InDepartment("departmentWithCapacity0", "Saint Knuckles");

        bedManager10 = new BedManager(departmentWithCapacity10);
        bedManager1 = new BedManager(departmentWithCapacity1);
        bedManager0 = new BedManager(departmentWithCapacity0);
    }

    @After
    public void tearDown() {
    }


    @Test
    public void changeBedWithoutId() throws ExceededCapacityException {
        bedManager10.assignToBed(patient1);
        Bed bed = departmentWithCapacity10.getBedWithPatient(patient1);
        assertTrue(bed.getPatient().equals(patient1));
        bedManager10.changeBed(patient1);
        Bed bed2 = departmentWithCapacity10.getBedWithPatient(patient1);
        assertTrue(bed2.getPatient().equals(patient1));
        assertFalse(bed.isOccupied());

    }

    @Test
    public void changeBedWithNonBeddedPatient() throws ExceededCapacityException {
        bedManager10.changeBed(patient1);

    }

    @Test
    public void changeBedIdWithNonBeddedPatient() throws UnavailableBedException {
        bedManager10.changeBed(patient1, 2);
    }

    @Test
    public void getBedTest() throws ExceededCapacityException {
        bedManager1.assignToBed(patient1);
        assertNotNull(departmentWithCapacity1.getBedWithPatient(patient1));
    }

    @Test
    public void patientInBedTest() throws ExceededCapacityException {
        bedManager1.assignToBed(patient1);
        assertTrue(departmentWithCapacity1.isPatientInBed(patient1));
    }


    @Test
    public void assignPatientTest1() throws ExceededCapacityException {
        bedManager1.assignToBed(patient1);
        assertTrue(departmentWithCapacity10.hasAvailableBeds());
    }

    @Test
    public void assignPatientTest4() throws ExceededCapacityException { // It is expected that the patient is registered, once put in a bed
        bedManager10.assignToBed(patient1);
        assertTrue(departmentWithCapacity10.isPatientInDepartment(patient1));
    }

    @Test
    public void removePatientTest1() throws ExceededCapacityException {
        bedManager1.assignToBed(patient1);
        bedManager1.removeFromBed(patient1);
        assertTrue(departmentWithCapacity1.hasAvailableBeds());
    }

    @Test
    public void removePatientTest2() throws ExceededCapacityException {
        bedManager1.assignToBed(patient1);
        bedManager1.discharge(patient1);
        assertFalse(departmentWithCapacity1.isPatientInBed(patient1));
    }

    @Test
    public void assignPatientToBedTest() throws UnavailableBedException {
        bedManager1.assignToBed(patient1, 0);
        assertFalse(departmentWithCapacity1.hasAvailableBeds());
    }

    @Test
    public void movePatientTest1() throws UnavailableBedException {
        bedManager10.assignToBed(patient1, 3);
        bedManager10.changeBed(patient1, 2);
        assertEquals(departmentWithCapacity10.getBeds()[2].getPatient(), patient1);
    }

    @Test
    public void movePatientTest2() throws UnavailableBedException {
        bedManager10.assignToBed(patient1, 3);
        bedManager10.changeBed(patient1, 2);
        assertNull(departmentWithCapacity10.getBeds()[3].getPatient());
    }

    @Test (expected = ExceededCapacityException.class)
    public void filledCapacityTest() throws ExceededCapacityException {
        bedManager0.assignToBed(patient1);
    }

    @Test (expected = UnavailableBedException.class)
    public void unavailableBedTest() throws UnavailableBedException {
        bedManager1.assignToBed(patient1, 4);
        bedManager1.assignToBed(patient2, 4);
    }

    @Test(expected = UnavailableBedException.class)
    public void bedNotFoundTest() throws UnavailableBedException {
        bedManager1.assignToBed(patient1, 10);
    }

    @Test (expected = ExceededCapacityException.class)
    public void patientInBedWithoutBedID() throws ExceededCapacityException {
        bedManager1.assignToBed(patient1);
        bedManager1.changeBed(patient1);

    }

    @Test (expected = ExceededCapacityException.class)
    public void getAvailableBedExceptionTest() throws ExceededCapacityException {
        bedManager1.assignToBed(patient1);
        departmentWithCapacity1.getAvailableBeds();
    }
}