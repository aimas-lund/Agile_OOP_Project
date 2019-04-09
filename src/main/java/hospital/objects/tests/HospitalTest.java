package hospital.objects.tests;

import org.junit.Test;
import org.testng.annotations.ITestAnnotation;
import static junit.framework.TestCase.*;
import hospital.objects.*;

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

    // Hospital Tests

    @Test
    public void hospitalAssignPatientTest1() {
        Hospital h = new Hospital();
        Department d1 = new Department(10, "ER");
        Patient p = new Patient(); // need patient constructor
        h.assign(p, d1);
        assertTrue(d1.contains(p));
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
