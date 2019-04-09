package hospital.objects.tests;

import org.junit.Test;
import static junit.framework.TestCase.*;
import hospital.objects.*;

public class departmentTests {

    // Test Getters

    @Test
    public void getNameTest() {
        Department d = new Department(10, "ER");
        assertSame("ER", d.getName());
    }

    @Test
    public void getCapacityTest() {
        Department d = new Department(10, "ER");
        assertSame(10, d.getCapacity());
    }

    @Test
    public void getBedsTest1() {
        Department d = new Department(10, "ER");
        assertNotNull(d.getBeds());
    }

    @Test
    public void getBedsTest2() {
        Department d = new Department(10, "ER");
        assertTrue(d.getBeds()[0] instanceof Bed);
    }

    @Test
    public void getAvailableBedsTest1() {
        Department d = new Department(10, "ER");
        assertTrue(d.getAvailableBeds() instanceof int);
    }

    @Test
    public void getAvailableBedsTest2() {
        Department d = new Department(10, "ER");
        assertSame(d.getAvailableBeds(), 10);
    }

    @Test
    public void getPatientsTest() {
        Department d = new Department(10, "ER");
        assertNotNull(d.getPatients());
    }

    @Test
    public void getStaffTest() {
        Department d = new Department(10, "ER");
        assertNotNull(d.getStaff());
    }


    // Test functional methods

    @Test
    public void availableBedsTest1() {
        Department d = new Department(10, "ER");
        assertTrue(d.available_beds());
    }

    @Test
    public void availableBedsTest2() {
        Department d = new Department(0, "ER");
        assertTrue(!d.available_beds());
    }

    @Test
    public void addPatientTest() {
        Department d = new Department(10, "ER");
        Patient p = new Patient();
        d.add(p);
        assertTrue(d.getPatients().contains(p));
    }

    @Test
    public void removePatientTest() {
        Department d = new Department(10, "ER");
        Patient p = new Patient();
        d.add(p);
        d.remove(p);
        assertTrue(!d.getPatients().contains(p));
    }

    @Test
    public void addStaffTest() {
        Department d = new Department(10, "ER");
        Staff s = new Staff();
        d.add(s);
        assertTrue(d.getPatients().contains(s));
    }

    @Test
    public void removeStaffTest() {
        Department d = new Department(10, "ER");
        Staff s = new Staff();
        d.add(s);
        d.remove(s);
        assertTrue(!d.getPatients().contains(s));
    }

    @Test
    public void assignPatientTest1() {
        Department d = new Department(10, "ER");
        Patient p = new Patient();
        d.add(p);
        d.assign(p);
        assertTrue(d.available_beds());
    }

    @Test
    public void assignPatientTest2() {
        Department d = new Department(1, "ER");
        Patient p = new Patient();
        d.add(p);
        d.assign(p);
        assertTrue(!d.available_beds());
    }

    @Test
    public void assignPatientTest3() {
        Department d = new Department(10, "ER");
        Patient p = new Patient();
        d.add(p);
        d.assign(p);
        assertTrue(d.getBeds.contains(p));
    }

    @Test
    public void assignPatientTest4() { // It is expected that the patient is registered, once put in a bed
        Department d = new Department(10, "ER");
        Patient p = new Patient();
        d.assign(p);
        assertTrue(d.getPatients().contains(p));
    }

    @Test
    public void removePatientTest1() {
        Department d = new Department(1, "ER");
        Patient p = new Patient();
        d.add(p);
        d.assign(p);
        d.removeFromBed(p);
        assertTrue(d.available_beds());
    }

    @Test
    public void removePatientTest2() {
        Department d = new Department(1, "ER");
        Patient p = new Patient();
        d.add(p);
        d.assign(p);
        d.remove(p);
        assertTrue(!d.getBeds.contains(p));
    }

    @Test
    public void assignPatientToBedTest() {
        Department d = new Department(1, "ER");
        Patient p = new Patient(); // need patient constructor
        d.assign(p, 0);
        assertTrue(!d.available_beds());
    }

    @Test
    public void movePatientTest1() {
        Department d = new Department(10, "ER");
        Patient p = new Patient(); // need patient constructor
        d.add(p);
        d.assign(p, 3);
        d.move(p, 2);
        assertEqual(d.getBeds()[3].getPatient, p);
    }

    @Test
    public void movePatientTest2() {
        Department d = new Department(10, "ER");
        Patient p = new Patient(); // need patient constructor
        d.add(p);
        d.assign(p, 3);
        d.move(p, 2);
        assertNull(d.getBeds()[4].getPatient);
    }

    @Test (expected = ExceededCapacityException.class)
    public void filledCapacityTest() {
        Department d = new Department(0, "ER");
        Patient p = new Patient(); // need patient constructor
        d.assign(p);
        assertTrue(!d.available_beds());
    }

    @Test (expected = UnavailableBedException.class)
    public void unavailableBedTest() {
        Department d = new Department(10, "ER");
        Patient p1 = new Patient(); // need patient constructor
        Patient p2 = new Patient();
        d.assign(p1, 4);
        d.assign(p2, 4);
    }

    @Test (expected = BedNotFoundException.class)
    public void bedNotFoundTest() {
        Department d = new Department(10, "ER");
        Patient p1 = new Patient(); // need patient constructor
        d.assign(p1, 10);
    }

    @Test (expected = SameBedException.class)
    public void sameBedTest() {
        Department d = new Department(10, "ER");
        Patient p1 = new Patient(); // need patient constructor
        d.assign(p1, 5);
        d.move(p1, 5);
    }

    @Test (expected = PatientNotFoundException.class)
    public void sameBedTest() {
        Department d = new Department(10, "ER");
        Patient p = new Patient(); // need patient constructor
        d.move(p, 5);
    }
}
