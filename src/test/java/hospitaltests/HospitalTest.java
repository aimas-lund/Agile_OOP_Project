import org.junit.Test;

import static junit.framework.TestCase.*;

public class HospitalTest {

    // Bed Tests

    // New beds must be empty when instantiated
    @Test
    public void newBedAssignedTest() {
        Bed b = new Bed();
        assertTrue(!b.occupied());
    }

    @Test
    public void assignBedTest() {
        Bed b = new Bed();
        Patient p = new Patient(); // Instantiating a Patient still need parameters
        b.add(p);
        assertTrue(b.occupied());
    }

    @Test
    public void unassignBedTest() {
        Bed b = new Bed();
        Patient p = new Patient(); // Instantiating a Patient still need parameters
        b.add(p);
        b.remove();
        assertTrue(!b.occupied());
    }

    @Test
    public void getPatientInBedTest() {
        Bed b = new Bed();
        Patient p = new Patient(); // Instantiating a Patient still need parameters
        b.add(p);
        assertNotNull(b.patient());
    }

    @Test
    public void getPatientInBedTest2() {
        Bed b = new Bed();
        Patient p = new Patient(); // Instantiating a Patient still need parameters
        b.add(p);
        b.remove();
        assertNull(b.patient());
    }

    // Department Tests
    // Comment: Do we need to assign patients to beds in the dynamic bed array in the department? We need a method for that

    @Test
    public void initDepartmentTest() {
        Department d = new Department(10, "ER");
        assertSame("ER", d.name);
    }

    @Test
    public void capacityTest1() {
        Department d = new Department(10, "ER");
        assertEquals(10, d.available_beds());
    }

    @Test
    public void capacityTestAddPatient() {
        Department d = new Department(10, "ER");
        Patient p = new Patient();
        d.add(p);
        assertEquals(9, d.available_beds());
    }

    @Test
    public void capacityTestRemovePatient() {
        Department d = new Department(10, "ER");
        Patient p = new Patient();
        d.add(p);
        d.remove(p);
        assertEquals(10, d.available_beds());
    }

    @Test
    public void assignPatientToBedTest() {
        Department d = new Department(10, "ER");
        Patient p = new Patient("Paul"); // need patient constructor
        Bed b = new Bed();
        d.assign(p, b);
        assertSame("Paul", b.patient());
    }

    @Test
    public void movePatientBedOccupiedTest1() {
        Department d = new Department(10, "ER");
        Patient p = new Patient(); // need patient constructor
        Bed[] beds  = new Bed[2];
        d.assign(p, beds[0]);
        d.move(p, beds[1]);
        assertTrue(!beds[0].occupied() && beds[1].occupied());
    }

    @Test
    public void movePatientBedOccupiedTest2() {
        Department d = new Department(10, "ER");
        Patient p = new Patient(); // need patient constructor
        Bed[] beds  = new Bed[2];
        d.assign(p, beds[0]);
        d.move(p, beds[1]);
        assertTrue( beds[1].occupied());
    }

    @Test
    public void movePatientBedNameTest1() {
        Department d = new Department(10, "ER");
        Patient p = new Patient(); // need patient constructor
        Bed[] beds  = new Bed[2];
        d.assign(p, beds[0]);
        d.move(p, beds[1]);
        assertNull(beds[0].patient());
    }

    @Test
    public void movePatientBedNameTest2() {
        Department d = new Department(10, "ER");
        Patient p = new Patient(); // need patient constructor
        Bed[] beds  = new Bed[2];
        d.assign(p, beds[0]);
        d.move(p, beds[1]);
        assertSame(beds[1].patient(), p);
    }

    @Test
    public void removePatientBedTest() {
        Department d = new Department(10, "ER");
        Patient p = new Patient(); // need patient constructor
        Bed b = new Bed();
        d.assign(p, b);
        d.remove(p, b);
        assertNull(b.patient());
    }

    /*
    @Test
    public void capacityTestAddStaff() {
        Department d = new Department(10, "ER");
        Staff s = new Staff("Doctor Phil");
        d.add(s);
        assertTrue();
    }
    */

    // Hospital Tests

    @Test
    public void hospitalAssignPatientTest1() {
        Hospital h = new Hospital();
        Department d1 = new Department(10, "ER");
        Patient p = new Patient(); // need patient constructor
        h.assign(p, d1);
        assertTrue(d1.isPatientAssigned(p));
    }

    @Test
    public void hospitalMovePatientTest1() {
        Hospital h = new Hospital();
        Department d1 = new Department(10, "ER");
        Department d2 = new Department(5, "X-Ray");
        Patient p = new Patient(); // need patient constructor
        h.assign(p, d1);
        h.move(p, d1, d2);
        assertTrue(d2.isPatientAssigned(p));
    }

    @Test
    public void hospitalMovePatientTest2() {
        Hospital h = new Hospital();
        Department d1 = new Department(10, "ER");
        Department d2 = new Department(5, "X-Ray");
        Patient p = new Patient(); // need patient constructor
        h.assign(p, d1);
        h.move(p, d1, d2);
        assertTrue(!d1.isPatientAssigned(p));
    }

    @Test
    public void hospitalAssignStaffTest1() {
        Hospital h = new Hospital();
        Department d1 = new Department(10, "ER");
        Staff s = new Staff(); // need patient constructor
        h.assign(s, d1);
        assertTrue(d1.isStaffAssigned(s));
    }

    @Test
    public void hospitalMoveStaffTest1() {
        Hospital h = new Hospital();
        Department d1 = new Department(10, "ER");
        Department d2 = new Department(5, "X-Ray");
        Staff s = new Staff(); // need patient constructor
        h.assign(s, d1);
        h.move(s, d1, d2);
        assertTrue(!d1.isStaffAssigned(s));
    }

    @Test
    public void hospitalMoveStaffTest2() {
        Hospital h = new Hospital();
        Department d1 = new Department(10, "ER");
        Department d2 = new Department(5, "X-Ray");
        Staff s = new Staff(); // need patient constructor
        h.assign(s, d1);
        h.move(s, d1, d2);
        assertTrue(d2.isStaffAssigned(s));
    }

    @Test
    public void hospitalAssignDepartmentTest1() {
        Hospital h = new Hospital();
        Department d1 = new Department(10, "ER");
        h.add(d1);
        assertTrue(d1.isDepartmentAssigned(d1));
    }

    @Test
    public void hospitalRemoveDepartmentTest1() {
        Hospital h = new Hospital();
        Department d1 = new Department(10, "ER");
        h.add(d1);
        h.remove(d1);
        assertTrue(!d1.isDepartmentAssigned(d1));
    }
}
