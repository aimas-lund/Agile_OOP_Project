package tests;

import management.Department;
import management.Staff;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static junit.framework.TestCase.assertEquals;

public class StaffTest {

    private Staff staff;
    private Department department;

    @Before
    public void setup() {
        staff = new Staff(
                "Beth",
                "McMuffin",
                new Date(631152000000L),
                0,
                "Internet Blvd. 4",
                87654321);
        staff.setEmail("bemc@hospital.com");
    }

    @Test
    public void getPersonInformationName() {
        assertEquals("Beth", staff.getPersonInformation()[1]);
    }

    @Test
    public void getPersonInformationSurname() {
        assertEquals("McMuffin", staff.getPersonInformation()[2]);
    }

    @Test
    public void getPersonInformationBirthdate() {
        assertEquals("1990_01_01", staff.getPersonInformation()[3]);
    }

    @Test
    public void getPersonInformationGender() {
        assertEquals("0", staff.getPersonInformation()[4]);
    }

    @Test
    public void getPersonInformationHomeAddress() {
        assertEquals("Internet Blvd. 4", staff.getPersonInformation()[5]);
    }

    @Test
    public void getPersonInformationPhoneNumber() {
        assertEquals("87654321", staff.getPersonInformation()[6]);
    }

    @Test
    public void getEmailTest() {
        assertEquals("bemc@hospital.com", staff.getEmail());
    }

    @Test
    public void getInitialsFromEmailTest() {
        assertEquals("bemc", staff.getInitials());
    }

    @Test
    public void getInitialsTest() {
        staff.setInitials("bemu");
        assertEquals("bemu", staff.getInitials());
    }
}
