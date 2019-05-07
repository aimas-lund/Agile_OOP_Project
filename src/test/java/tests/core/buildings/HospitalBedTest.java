package tests.core.buildings;

import core.buildings.Bed;
import core.buildings.Department;
import core.buildings.InDepartment;
import core.buildings.OutDepartment;
import core.persons.Clerk;
import core.persons.Hospital;
import core.persons.Patient;
import core.persons.Staff;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.*;

public class HospitalBedTest {

    private final Hospital hospital = new Hospital();
    private ArrayList<Department> depts = hospital.getDepartments();
    private Department department;
    private final Patient patient = new Patient("password123");
    private final Staff staff = new Clerk("password321");
    private Department department2;
    private Bed bed;

    @Before
    public void setUp() {
        department = new InDepartment("dep1", "ER", 10);
        department2 = new InDepartment("dep2", "ER2", 10);

        bed = new Bed(10);
        hospital.add(department);
        hospital.add(department2);
    }

    @After
    public void tearDown() {
        hospital.remove(department);
        hospital.remove(department2);
    }

    @Test
    public void newBed() {
        assertSame (bed.getId (), 10);
    }

    @Test
    public void fillBed() {
        assertTrue (bed.fill (new Patient()));
    }

    @Test
    public void fillOccupiedBed() {
        bed.fill (new Patient ());
        assertFalse (bed.fill (new Patient ()));
        bed.empty();
    }

    @Test
    public void emptyBed() {
        assertFalse(bed.isOccupied());
    }

    @Test
    public void isOccupiedBed() {
        bed.fill (new Patient ());
        assertTrue (bed.isOccupied());
    }

    @Test
    public void getPatientFilledBed() {
        bed.fill (new Patient ());
        assertNotNull (bed.getPatient ());
        bed.empty();
    }

    @Test
    public void getPatientEmptyBed() {
        bed.fill (new Patient ());
        bed.empty ();
        assertNull (bed.getPatient ());
    }

    @Test
    public void assignPatient() {
        assertTrue(hospital.assign(patient, department));
        assertTrue(hospital.remove(patient, department));
    }

    @Test
    public void assignPatientDeptNonexistant() {
        assertFalse(hospital.assign(patient, new OutDepartment("depTest123", "Saint Outz")));
    }

    @Test
    public void addExistingDepartmentsFails() {
        assertFalse(hospital.add(department));
        assertFalse(hospital.add(department2));
    }

    @Test
    public void assignStaff() {
        assertTrue(hospital.assign(staff, department));
        assertTrue(hospital.remove(staff, department));
    }

    @Test
    public void assignStaffDeptNonexistant() {
        assertFalse(hospital.assign(staff, new OutDepartment("depTest123", "Saint Outz")));
    }

    @Test
    public void movePatient() {
        assertTrue(hospital.assign(patient, department));
        assertTrue(department.isPatientInDepartment(patient));
        hospital.move (patient, department, department2);
        assertFalse(department.isPatientInDepartment(patient));
        assertTrue(department2.isPatientInDepartment(patient));
    }

    @Test
    public void moveStaff() {
        hospital.assign(staff, department);
        assertTrue(department.isStaffInDepartment(staff));
        hospital.move (staff, department, department2);
        assertFalse(department.isStaffInDepartment(staff));
        assertTrue(department2.isStaffInDepartment(staff));
    }

    @Test
    public void removeDept() {
        assertFalse(depts.isEmpty());
        assertTrue(hospital.remove(department));
    }

    @Test
    public void moveStaffTest() {
        assertTrue(hospital.assign(staff, department));
        hospital.move(staff, department, department2);
        assertFalse(department.isStaffInDepartment(staff));
        assertTrue(department2.isStaffInDepartment(staff));
    }

    @Test
    public void movePatientTest() {
        assertTrue(hospital.assign(patient, department));
        assertTrue(hospital.move(patient, department, department2));
        assertFalse(department.getPatients().contains(patient));
        assertTrue(department2.getPatients().contains(patient));
    }

    @Test
    public void movePatientWhereItAlreadyExistsTest() {
        assertTrue(hospital.assign(patient, department));
        assertTrue(hospital.assign(patient, department2));
        assertFalse(hospital.move(patient, department, department2));
        assertTrue(department.getPatients().contains(patient));
        assertTrue(department2.getPatients().contains(patient));
    }

    @Test
    public void moveStaffWhereItAlreadyExistsTest() {
        assertTrue(hospital.assign(staff, department));
        assertTrue(hospital.assign(staff, department2));
        assertFalse(hospital.move(staff, department, department2));
        assertTrue(department.getStaff().contains(staff));
        assertTrue(department2.getStaff().contains(staff));
    }

    @Test
    public void getDepartmentsTest() {
        assertTrue(hospital.getDepartments().contains(department));
        assertTrue(hospital.getDepartments().contains(department2));
    }
}

