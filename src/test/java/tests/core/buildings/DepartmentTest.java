package tests.core.buildings;

import core.buildings.Department;
import core.buildings.OutDepartment;
import core.persons.Clerk;
import core.persons.Patient;
import core.persons.Staff;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.*;

public class DepartmentTest {
    // Initiations
    private Department outDepartment;
    private Patient patient1;
    private Staff staff;

    @Before
    public void setUp() {
        patient1 = new Patient("pat1");
        staff = new Clerk("staff1");
        outDepartment = new OutDepartment("outDepartment", "Saint John");
    }

    @Test
    public void getNameTest() {
        assertEquals("Saint John", outDepartment.getName());
    }

    @Test
    public void getPatientsTest() {
        assertNotNull(outDepartment.getPatients());
    }

    @Test
    public void getStaffTest() {
        assertNotNull(outDepartment.getStaff());
    }


    @Test
    public void addPatientTest() {
        outDepartment.add(patient1);
        assertTrue(outDepartment.getPatients().contains(patient1));
    }

    @Test
    public void removePatientTest() {
        outDepartment.add(patient1);
        outDepartment.remove(patient1);
        assertFalse(outDepartment.getPatients().contains(patient1));
    }

    @Test
    public void addStaffTest() {
        outDepartment.add(staff);
        assertTrue(outDepartment.getStaff().contains(staff));
    }

    @Test
    public void removeStaffTest() {
        outDepartment.add(staff);
        outDepartment.remove(staff);
        assertFalse(outDepartment.getStaff().contains(staff));
    }

    @Test
    public void isStaffInDepartment() {
        outDepartment.add(staff);
        assertTrue(outDepartment.isStaffInDepartment(staff));
    }

    @Test
    public void getDepartmentInformation() {
        String[] departmentInfo = outDepartment.getDepartmentInformation();

        assertEquals("outDepartment", departmentInfo[0]);
        assertEquals("Saint John", departmentInfo[1]);
        assertEquals("OutDepartment", departmentInfo[2]);
    }

}
