import exceptions.ExceededCapacityException;
import exceptions.UnavailableBedException;
import management.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class TestDepartment {

    // Initiations
    private Department departmentWithCapacity10;
    private Department departmentWithCapacity1;
    private Department departmentWithNoCapacity;
    private Patient patient1;
    private Patient patient2;
    private Staff staff;

    @BeforeEach
    void setUp() {
        patient2 = new Patient();
        patient1 = new Patient();
        staff = new Staff();
        departmentWithCapacity10 = new Department("ER", 10);
        departmentWithCapacity1 = new Department("ER", 1);
        departmentWithNoCapacity = new Department("ER", 0);
    }

    // Test Getters
    @Test
    void getNameTest() {
        assertSame("ER", departmentWithCapacity10.getName());
    }
    @Test
    void getCapacityTest() {
        assertSame(10, departmentWithCapacity10.getCapacity());
    }
    @Test
    void getBedsTest() {
        assertTrue(departmentWithCapacity10.getBeds() instanceof Bed[]);
    }
    @Test
    void getAvailableBedsTest() {
        assertSame(departmentWithCapacity10.getAvailableBeds(), 10);
    }
    @Test
    void getPatientsTest() {
        assertNotNull(departmentWithCapacity10.getPatients());
    }
    @Test
    void getStaffTest() {
        assertNotNull(departmentWithCapacity10.getStaff());
    }
    @Test
    void getBedTest() throws ExceededCapacityException {
        departmentWithCapacity10.assign(patient1);
        assertTrue(departmentWithCapacity10.getPatientBed(patient1) instanceof Bed);
    }
    @Test
    void patientInBedTest() throws ExceededCapacityException {
        departmentWithCapacity10.assign(patient1);
        assertTrue(departmentWithCapacity10.patientInBed(patient1));
    }

    // Test functional methods
    @Test
    void availableBedsTest1() {
        assertTrue(departmentWithCapacity10.availableBeds());
    }
    @Test
    void availableBedsTest2() {
        assertFalse(departmentWithNoCapacity.availableBeds());
    }
    @Test
    void addPatientTest() {
        departmentWithCapacity10.add(patient1);
        assertTrue(departmentWithCapacity10.getPatients().contains(patient1));
    }
    @Test
    void removePatientTest() {
        departmentWithCapacity10.add(patient1);
        departmentWithCapacity10.remove(patient1);
        assertFalse(departmentWithCapacity10.getPatients().contains(patient1));
    }
    @Test
    void addStaffTest() {
        departmentWithCapacity10.add(staff);
        assertTrue(departmentWithCapacity10.getStaff().contains(staff));
    }
    @Test
    void removeStaffTest() {
        departmentWithCapacity10.add(staff);
        departmentWithCapacity10.remove(staff);
        assertFalse(departmentWithCapacity10.getStaff().contains(staff));
    }
    @Test
    void assignPatientTest1() throws ExceededCapacityException {
        departmentWithCapacity10.assign(patient1);
        assertTrue(departmentWithCapacity10.availableBeds());
    }
    @Test
    void assignPatientTest2() throws ExceededCapacityException {
        departmentWithCapacity1.assign(patient1);
        assertTrue(true);
    }
    @Test
    void assignPatientTest3() throws ExceededCapacityException {
        departmentWithCapacity10.assign(patient1);
        assertTrue(departmentWithCapacity10.patientInBed(patient1));
    }
    @Test
    void assignPatientTest4() throws ExceededCapacityException { // It is expected that the patient is registered, once put in a bed
        departmentWithCapacity10.assign(patient1);
        assertTrue(departmentWithCapacity10.getPatients().contains(patient1));
    }
    @Test
    void removePatientTest1() throws ExceededCapacityException {
        departmentWithCapacity1.assign(patient1);
        departmentWithCapacity1.removeFromBed(patient1);
        assertTrue(departmentWithCapacity1.availableBeds());
    }
    @Test
    void removePatientTest2() throws ExceededCapacityException {
        departmentWithCapacity1.assign(patient1);
        departmentWithCapacity1.remove(patient1);
        assertFalse(departmentWithCapacity1.patientInBed(patient1));
    }
    @Test
    void assignPatientToBedTest() throws UnavailableBedException {
        departmentWithCapacity1.assign(patient1, 0);
        assertFalse(departmentWithCapacity1.availableBeds());
    }
    @Test
    void movePatientTest1() throws UnavailableBedException {
        departmentWithCapacity10.assign(patient1, 3);
        departmentWithCapacity10.move(patient1, 2);
        assertEquals(departmentWithCapacity10.getBeds()[2].getPatient(), patient1);
    }
    @Test
    void movePatientTest2() throws UnavailableBedException {
        departmentWithCapacity10.assign(patient1, 3);
        departmentWithCapacity10.move(patient1, 2);
        assertNull(departmentWithCapacity10.getBeds()[3].getPatient());
    }
    @Test
    void filledCapacityTest() {
        assertThrows(ExceededCapacityException.class, () -> departmentWithNoCapacity.assign(patient1));
    }
    @Test
    void unavailableBedTest() throws UnavailableBedException {
        departmentWithCapacity10.assign(patient1, 4);
        assertThrows(UnavailableBedException.class, () -> departmentWithCapacity10.assign(patient2, 4));
    }
    @Test
    void bedNotFoundTest() {
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> departmentWithCapacity10.assign(patient1, 10));
    }
}
