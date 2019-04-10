package management;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Hospital_Bed_Test {

    @Test
    public void newBed() {
        Bed b = new Bed (10);
        assertSame (b.getId (), 10);
    }

    @Test
    public void fillBed() {
        Bed b = new Bed (10);
        assertTrue (b.fill (new Patient ()));
    }

    @Test
    public void fillOccupiedBed() {
        Bed b = new Bed (10);
        b.fill (new Patient ());
        assertFalse (b.fill (new Patient ()));

    }

    @Test
    public void emptyBed() {
        Bed b = new Bed (10);
        b.fill (new Patient ());

    }

    @Test
    public void isOccupiedBed() {
        Bed b = new Bed (10);
        b.fill (new Patient ());
        assertTrue (b.isoccupied ());
    }

    @Test
    public void getPatientFilledBed() {
        Bed b = new Bed (10);
        b.fill (new Patient ());
        assertNotNull (b.getPatient ());
    }

    @Test
    public void getPatientEmptyBed() {
        Bed b = new Bed (10);
        b.fill (new Patient ());
        b.empty ();
        assertNull (b.getPatient ());
    }

    @Test
    public void assignPatient() {
        Hospital h = new Hospital ();
        Department d = new Department ();
        Patient p = new Patient ();
        h.add (d);
        h.assign (p, d);
    }

    @Test
    public void assignPatientDeptNonexistant() {
        Hospital h = new Hospital ();
        Patient p = new Patient ();
        h.assign (p, new Department ());
        assertTrue (h.depts.isEmpty ());
    }

    @Test
    public void assignStaff() {
        Hospital h = new Hospital ();
        Department d = new Department ();
        Staff s = new Staff ();
        h.add (d);
        h.assign (s, d);
    }

    @Test
    public void assignStaffDeptNonexistant() {
        Hospital h = new Hospital ();
        Staff s = new Staff ();
        h.assign (s, new Department ());
        assertTrue (h.depts.isEmpty ());
    }

    @Test
    public void movePatient() {
        Hospital h = new Hospital ();
        Department d1 = new Department ();
        Department d2 = new Department ();
        Patient p = new Patient ();
        h.move (p, d1, d2);
    }

    @Test
    public void moveStaff() {
        Hospital h = new Hospital ();
        Department d1 = new Department ();
        Department d2 = new Department ();
        Staff s = new Staff ();
        h.move (s, d1, d2);
    }

    @Test
    public void removeDept() {
        Hospital h = new Hospital ();
        Department d = new Department ();
        h.add (d);
        h.remove (d);
        assertTrue (h.depts.isEmpty ());
    }
}

