package hospital.objects.tests;

import org.junit.Test;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.TestCase.*;
import static org.junit.Assert.assertFalse;

import hospital.objects.*;

import java.util.ArrayList;

public class DepartmentTests {

    // Initiations
    private Department departmentWithCapacity10 = new Department(10, "ER");
    private Department departmentWithCapacity1 = new Department(1, "ER");
    private Department departmentWithNoCapacity = new Department(0, "ER");
    private Patient patient1 = new Patient();
    private Patient patient2= new Patient();
    private Staff staff = new Staff();

    // Test Getters
    @Test
    public void getNameTest() {
        assertSame("ER", departmentWithCapacity10.getName());
    }
    @Test
    public void getCapacityTest() {
        assertSame(10, departmentWithCapacity10.getCapacity());
    }
    @Test
    public void getBedsTest() {
        assertTrue(departmentWithCapacity10.getBeds() instanceof Bed[]);
    }
    @Test
    public void getAvailableBedsTest() {
        assertSame(departmentWithCapacity10.getAvailableBeds(), 10);
    }
    @Test
    public void getPatientsTest() {
        assertNotNull(departmentWithCapacity10.getPatients());
    }
    @Test
    public void getStaffTest() {
        assertNotNull(departmentWithCapacity10.getStaff());
    }
    @Test
    public void getBedTest() {
        assertTrue(departmentWithCapacity10.getPatientBed(patient1) instanceof Bed);
    }
    @Test
    public void patientInBedTest() {
        departmentWithCapacity10.assign(patient1);
        assertTrue(departmentWithCapacity10.patientInBed(patient1));
    }

    // Test functional methods
    @Test
    public void availableBedsTest1() {
        assertTrue(departmentWithCapacity10.available_beds());
    }
    @Test
    public void availableBedsTest2() {
        assertFalse(departmentWithNoCapacity.available_beds());
    }
    @Test
    public void addPatientTest() {
        departmentWithCapacity10.add(patient1);
        assertTrue(departmentWithCapacity10.getPatients().contains(patient1));
    }
    @Test
    public void removePatientTest() {
        departmentWithCapacity10.add(patient1);
        departmentWithCapacity10.remove(patient1);
        assertFalse(departmentWithCapacity10.getPatients().contains(patient1));
    }
    @Test
    public void addStaffTest() {
        departmentWithCapacity10.add(staff);
        assertTrue(departmentWithCapacity10.getPatients().contains(staff));
    }
    @Test
    public void removeStaffTest() {
        departmentWithCapacity10.add(staff);
        departmentWithCapacity10.remove(staff);
        assertFalse(departmentWithCapacity10.getPatients().contains(staff));
    }
    @Test
    public void assignPatientTest1() {
        departmentWithCapacity10.assign(patient1);
        assertTrue(departmentWithCapacity10.available_beds());
    }
    @Test
    public void assignPatientTest2() {
        departmentWithCapacity1.assign(patient1);
        assertFalse(departmentWithCapacity1.available_beds());
    }
    @Test
    public void assignPatientTest3() {
        departmentWithCapacity10.assign(patient1);
        assertTrue(departmentWithCapacity10.getBeds().contains(patient1));
    }
    @Test
    public void assignPatientTest4() { // It is expected that the patient is registered, once put in a bed
        departmentWithCapacity10.assign(patient1);
        assertTrue(departmentWithCapacity10.getPatients().contains(patient1));
    }
    @Test
    public void removePatientTest1() {
        departmentWithCapacity1.assign(patient1);
        departmentWithCapacity1.removeFromBed(patient1);
        assertTrue(departmentWithCapacity1.available_beds());
    }
    @Test
    public void removePatientTest2() {
        departmentWithCapacity1.assign(patient1);
        departmentWithCapacity1.remove(patient1);
        assertFalse(departmentWithCapacity1.getBeds().contains(patient1));
    }
    @Test
    public void assignPatientToBedTest() {
        departmentWithCapacity1.assign(patient1, 0);
        assertFalse(departmentWithCapacity1.available_beds());
    }
    @Test
    public void movePatientTest1() {
        departmentWithCapacity10.assign(patient1, 3);
        departmentWithCapacity10.move(patient1, 2);
        assertEquals(departmentWithCapacity10.getBeds()[2].getPatient(), patient1);
    }
    @Test
    public void movePatientTest2() {
        departmentWithCapacity10.assign(patient1, 3);
        departmentWithCapacity10.move(patient1, 2);
        assertNull(departmentWithCapacity10.getBeds()[3].getPatient());
    }

    // Test exceptions
    @Test (expected = ExceededCapacityException.class)
    public void filledCapacityTest() {
        departmentWithNoCapacity.assign(patient1);
    }
    @Test (expected = UnavailableBedException.class)
    public void unavailableBedTest() {
        departmentWithCapacity10.assign(patient1, 4);
        departmentWithCapacity10.assign(patient2, 4);
    }
    @Test (expected = BedNotFoundException.class)
    public void bedNotFoundTest() {
        departmentWithCapacity10.assign(patient1, 10);
    }
}
