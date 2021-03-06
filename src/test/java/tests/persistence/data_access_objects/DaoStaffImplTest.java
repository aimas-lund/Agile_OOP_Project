package tests.persistence.data_access_objects;

import core.persons.*;
import core.utility.Gender;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import persistence.data_access_objects.DaoStaffImpl;

import java.util.Date;
import java.util.HashMap;

import static org.junit.Assert.*;

public class DaoStaffImplTest {
    private Staff staff;
    private DaoStaffImpl<Staff> daoStaff = new DaoStaffImpl<>();

    @Before
    public void setUp() {
        staff = new Clerk(
                "testID",
                "Oline",
                "Fischersen",
                new Date(1556668800000L), // Date: 01_05_2019
                Gender.MALE,
                "DTUStreet 56",
                45231298,
                "OLFI@agile_hospital.com",
                "OLFI");

        assertTrue(daoStaff.save(staff));
    }

    @After
    public void tearDown() {
        daoStaff.delete(staff);
    }

    @Test
    public void updateFails() {
        Staff clerk = new Clerk(
                "testIDasdasdasd",
                "Oline",
                "Fischersen",
                new Date(1556668800000L), // Date: 01_05_2019
                Gender.MALE,
                "DTUStreet 56",
                45231298,
                "OLFI@agile_hospital.com",
                "OLFI");

        assertFalse(daoStaff.update(clerk));
    }

    @Test
    public void updateValuesFromObject() {
        String email = "PØLZ@agile_hospital.com";
        new PersonInformationFacade(staff).setStaffEmail(email);
        new PersonInformationFacade(staff).setPersonName("Hilda");

        assertTrue(daoStaff.update(staff));

        assertEquals(email, daoStaff.find(staff).getEmail());
        assertEquals("Hilda", daoStaff.find(staff).getName());
    }

    @Test
    public void save() {
        daoStaff.delete(staff);
        assertTrue(daoStaff.save(staff));

        assertEquals("Oline", daoStaff.find(staff).getName());
    }

    @Test
    public void saveFailsDuplicate() {
        assertFalse(daoStaff.save(staff));
    }

    @Test
    public void delete() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("uniqueid", "testID");

        assertFalse(daoStaff.find(hashMap).isEmpty());
        assertTrue(daoStaff.delete(staff));
        assertTrue(daoStaff.find(hashMap).isEmpty());
    }

    @Test
    public void delete1() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("uniqueid", "testID");

        assertFalse(daoStaff.find(hashMap).isEmpty());
        assertTrue(daoStaff.delete(staff.getUniqueId()));
        assertTrue(daoStaff.find(hashMap).isEmpty());

    }

    @Test
    public void findSingular() {
        assertEquals("Fischersen", daoStaff.find(staff).getSurname());
        assertEquals("DTUStreet 56", daoStaff.find(staff).getHomeAddress());
    }

    @Test
    public void findMultiples() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("gender", Gender.MALE.toString());

        assertTrue(daoStaff.find(hashMap).size() > 1);

    }

    @Test
    public void findNurse() {
        Staff staffNew = new Nurse(
                "testID2",
                "Stereotype",
                "Dabb",
                new Date(1556668800000L), // Date: 01_05_2019
                Gender.FEMALE,
                "DTUStreet 52",
                45231298,
                "AIOD@agile_hospital.com",
                "AIOD");

        daoStaff.save(staffNew);

        assertTrue(daoStaff.find(staffNew) instanceof Nurse);

        daoStaff.delete(staffNew);
    }

    @Test
    public void findICTOfficer() {
        Staff staffNew = new ICTOfficer(
                "testID2",
                "Stereotype",
                "Dabb",
                new Date(1556668800000L), // Date: 01_05_2019
                Gender.FEMALE,
                "DTUStreet 52",
                45231298,
                "AIOD@agile_hospital.com",
                "AIOD");

        daoStaff.save(staffNew);

        assertTrue(daoStaff.find(staffNew) instanceof ICTOfficer);

        daoStaff.delete(staffNew);
    }
}