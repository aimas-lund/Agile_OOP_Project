package management.tests;

import management.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

public class Hospital_Bed_Test {

    @Test
    void newBed() {
        Bed b = new Bed (10);
        assertSame (b.getId (), 10);
    }

    @Test
    void fillBed() {
        Bed b = new Bed (10);
        assertTrue (b.fill (new Patient()));
    }

    @Test
    void fillOccupiedBed() {
        Bed b = new Bed (10);
        b.fill (new Patient ());
        assertFalse (b.fill (new Patient ()));

    }

    @Test
    void emptyBed() {
        Bed b = new Bed (10);
        b.fill (new Patient ());

    }

    @Test
    void isOccupiedBed() {
        Bed b = new Bed (10);
        b.fill (new Patient ());
        assertTrue (b.isoccupied ());
    }

    @Test
    void getPatientFilledBed() {
        Bed b = new Bed (10);
        b.fill (new Patient ());
        assertNotNull (b.getPatient ());
    }

    @Test
    void getPatientEmptyBed() {
        Bed b = new Bed (10);
        b.fill (new Patient ());
        b.empty ();
        assertNull (b.getPatient ());
    }

    @Test
    void assignPatient() {
        Hospital h = new Hospital ();
        Department d = new Department ("ER",10);
        Patient p = new Patient ();
        h.add (d);
        h.assign (p, d);
    }

    @Test
    void assignPatientDeptNonexistant() {
        Hospital h = new Hospital ();
        Patient p = new Patient ();
        h.assign (p, new Department ("ER",10));
        assertTrue (h.depts.isEmpty ());
    }

    @Test
    void assignStaff() {
        Hospital h = new Hospital ();
        Department d = new Department ("ER",10);
        Staff s = new Staff ();
        h.add (d);
        h.assign (s, d);
    }

    @Test
    void assignStaffDeptNonexistant() {
        Hospital h = new Hospital ();
        Staff s = new Staff ();
        h.assign (s, new Department ("ER",10));
        assertTrue (h.depts.isEmpty ());
    }

    @Test
    void movePatient() {
        Hospital h = new Hospital ();
        Department d1 = new Department ("ER",10);
        Department d2 = new Department ("ER2",10);
        Patient p = new Patient ();
        h.move (p, d1, d2);
    }

    @Test
    void moveStaff() {
        Hospital h = new Hospital ();
        Department d1 = new Department ("ER",10);
        Department d2 = new Department ("ER2",10);
        Staff s = new Staff ();
        h.move (s, d1, d2);
    }

    @Test
    void removeDept() {
        Hospital h = new Hospital ();
        Department d = new Department ("ER",10);
        h.add (d);
        h.remove (d);
        assertTrue (h.depts.isEmpty ());
    }
}

