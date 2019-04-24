package tests;

import management.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class HospitalBedTest {

    private final Hospital hospital = new Hospital();
    private ArrayList<Department> depts = hospital.getDepartments();
    private final Department department = new Department("ER",10);
    private final Patient patient = new Patient();
    private final Staff staff = new Staff();
    private final Department department2 = new Department("ER2",10);
    private Bed bed;

    @BeforeEach
    void setUp() {
        bed = new Bed(10);
    }

    @Test
    void newBed() {
        assertSame (bed.getId (), 10);
    }

    @Test
    void fillBed() {
        assertTrue (bed.fill (new Patient()));
    }

    @Test
    void fillOccupiedBed() {
        Patient p = new Patient();
        bed.fill(p);
        assertFalse(bed.fill(p));

    }

    @Test
    void emptyBed() {
        assertFalse(bed.isOccupied());
    }

    @Test
    void isOccupiedBed() {
        bed.fill (new Patient ());
        assertTrue (bed.isOccupied());
    }

    @Test
    void getPatientFilledBed() {
        bed.fill (new Patient ());
        assertNotNull (bed.getPatient ());
    }

    @Test
    void getPatientEmptyBed() {
        bed.fill (new Patient ());
        bed.empty ();
        assertNull (bed.getPatient ());
    }

    @Test
    void assignPatient() {
        hospital.add (department);
        hospital.assign (patient, department);
    }

    @Test
    void assignPatientDeptNonexistant() {
        hospital.assign (patient, department);
        depts = hospital.getDepartments();
        assertTrue (depts.isEmpty ());
    }

    @Test
    void assignStaff() {
        hospital.add (department);
        hospital.assign (staff, department);
    }

    @Test
    void assignStaffDeptNonexistant() {
        hospital.assign (staff, department);
        ArrayList<Department> depts = hospital.getDepartments();
        assertTrue (depts.isEmpty ());
    }

    @Test
    void movePatient() {
        hospital.move (patient, department, department2);
    }

    @Test
    void moveStaff() {
        hospital.move (staff, department, department2);
    }

    @Test
    void removeDept() {
        hospital.add (department);
        hospital.remove (department);
        assertTrue (depts.isEmpty ());
    }
}

