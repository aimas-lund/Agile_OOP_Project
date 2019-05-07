package tests.core.buildings;

import core.buildings.Bed;
import core.buildings.Department;
import core.buildings.InDepartment;
import core.persons.Clerk;
import core.persons.Hospital;
import core.persons.Patient;
import core.persons.Staff;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.*;

public class HospitalBedTest {

    private final Hospital hospital = new Hospital();
    private ArrayList<Department> depts = hospital.getDepartments();
    private final Department department = new InDepartment("dep1", "ER", 10);
    private final Patient patient = new Patient("password123");
    private final Staff staff = new Clerk("password321");
    private final Department department2 = new InDepartment("dep2", "ER2", 10);
    private Bed bed;

    @Before
    public void setUp() {
        bed = new Bed(10);
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
    }

    @Test
    public void getPatientEmptyBed() {
        bed.fill (new Patient ());
        bed.empty ();
        assertNull (bed.getPatient ());
    }

    @Test
    public void assignPatient() {
        hospital.add (department);
        hospital.assign (patient, department);
    }

    @Test
    public void assignPatientDeptNonexistant() {
        hospital.assign (patient, department);
        depts = hospital.getDepartments();
        assertTrue (depts.isEmpty ());
    }

    @Test
    public void assignStaff() {
        hospital.add (department);
        hospital.assign (staff, department);
    }

    @Test
    public void assignStaffDeptNonexistant() {
        hospital.assign (staff, department);
        ArrayList<Department> depts = hospital.getDepartments();
        assertTrue (depts.isEmpty ());
    }

    @Test
    public void movePatient() {
        hospital.move (patient, department, department2);
    }

    @Test
    public void moveStaff() {
        hospital.move (staff, department, department2);
    }

    @Test
    public void removeDept() {
        hospital.add(department);
        hospital.remove(department);
        assertTrue(depts.isEmpty());
    }

    @Test
    public void moveStaffTest() {
        hospital.add(department);
        hospital.add(department2);
        department.add(staff);
        hospital.move(staff, department, department2);
        assertFalse(department.getStaff().contains(staff));
        assertTrue(department2.getStaff().contains(staff));
    }

    @Test
    public void movePatientTest() {
        hospital.add(department);
        hospital.add(department2);
        department.add(patient);
        hospital.move(patient, department, department2);
        assertFalse(department.getPatients().contains(patient));
        assertTrue(department2.getPatients().contains(patient));
    }

    @Test
    public void movePatientWhereItAlreadyExistsTest() {
        hospital.add(department);
        hospital.add(department2);
        department.add(patient);
        department2.add(patient);
        hospital.move(patient, department, department2);
        assertTrue(department.getPatients().contains(patient));
        assertTrue(department2.getPatients().contains(patient));
    }

    @Test
    public void moveStaffWhereItAlreadyExistsTest() {
        hospital.add(department);
        hospital.add(department2);
        department.add(staff);
        department2.add(staff);
        hospital.move(staff, department, department2);
        assertTrue(department.getStaff().contains(staff));
        assertTrue(department2.getStaff().contains(staff));
    }

    @Test
    public void getDepartmentsTest() {
        hospital.add(department);
        hospital.add(department2);
        assertTrue(hospital.getDepartments().contains(department));
        assertTrue(hospital.getDepartments().contains(department2));
    }
}

