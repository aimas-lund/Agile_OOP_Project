package tests.persistence.data_access_objects;

import core.buildings.*;
import core.persons.*;
import exceptions.ExceededCapacityException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import persistence.data_access_objects.DaoDepartmentImpl;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class DaoDepartmentImplTest {
    private DepartmentBeds inDepartment;
    private DepartmentBeds inDepartmentTest;
    private Department outDepartment;
    private Department outDepartmentTest;
    private DaoDepartmentImpl<Department> daoDepartment = new DaoDepartmentImpl<>();


    @Before
    public void setUp() {
        inDepartment = new InDepartment("id2", "Saint John", 6);
        outDepartment = new OutDepartment("id", "name");

        inDepartmentTest = new InDepartment("inTest", "Saint test", 6);
        outDepartmentTest = new OutDepartment("outTest", "name");

        inDepartmentTest.add(new Patient("patientTest1"));
        inDepartmentTest.add(new Clerk("clerkTest1"));
        outDepartmentTest.add(new Patient("patientTest2"));
        outDepartmentTest.add(new Clerk("clerkTest2"));

    }

    @After
    public void tearDown() {
        daoDepartment.delete(inDepartmentTest);
        daoDepartment.delete(outDepartmentTest);
    }

    @Test
    public void saveAllStaffSomeExists() {
        inDepartmentTest.add(new Clerk("clerkid1"));
        assertTrue(daoDepartment.saveAllStaff(inDepartmentTest));
        inDepartmentTest.add(new Clerk("clerkid1"));
        assertFalse(daoDepartment.saveAllStaff(inDepartmentTest));
    }

    @Test
    public void saveAllPatientsSomeExists() {
        inDepartmentTest.add(new Patient("patient1"));
        assertTrue(daoDepartment.saveAllPatients(inDepartmentTest));
        inDepartmentTest.add(new Patient("patient1"));
        assertFalse(daoDepartment.saveAllPatients(inDepartmentTest));
    }

    @Test
    public void saveNonBedDepartment() {
        assertTrue(daoDepartment.save(outDepartmentTest));
    }

    @Test
    public void saveAllStaffFails() {
        inDepartmentTest.add(new Clerk());
        assertFalse(daoDepartment.save(inDepartmentTest));

    }

    @Test
    public void saveAllPatientsFails() {
        inDepartmentTest.add(new Patient());
        assertFalse(daoDepartment.save(inDepartmentTest));

    }

    @Test
    public void saveDepartmentFails() {
        assertTrue(daoDepartment.save(inDepartmentTest));
        assertFalse(daoDepartment.save(inDepartmentTest));
    }

    @Test
    public void saveDeleteDepartment() throws ExceededCapacityException {
        inDepartmentTest.add(new Clerk("clerk"));
        inDepartmentTest.add(new Nurse("nurse"));
        inDepartmentTest.add(new ICTOfficer("ICTOfficer"));

        BedManager bedManager = new BedManager(inDepartmentTest);
        bedManager.assignToBed(new Patient("patient1"));
        bedManager.assignToBed(new Patient("patient2"));

        inDepartmentTest.add(new Patient("patient3"));
        assertTrue(daoDepartment.save(inDepartmentTest));
        assertTrue(daoDepartment.delete(inDepartmentTest));

    }

    @Test
    public void saveDeletePatient() {
        Patient patient = new Patient("etId");
        assertTrue(daoDepartment.save(patient, inDepartment));
        assertTrue(daoDepartment.delete(patient, inDepartment));
    }

    @Test
    public void saveDeleteStaff() {
        Clerk staff = new Clerk("etId2");
        assertTrue(daoDepartment.save(staff, inDepartment));
        assertTrue(daoDepartment.delete(staff, inDepartment));
    }

    @Test
    public void updateDepartment() {
        assertTrue(daoDepartment.save(inDepartmentTest));
        new DepartmentInformationFacade(inDepartmentTest).setDepartmentName("new name");
        assertTrue(daoDepartment.update(inDepartmentTest));
        new DepartmentInformationFacade(inDepartmentTest).setDepartmentName("Saint John");
        assertTrue(daoDepartment.update(inDepartmentTest));
    }

    @Test
    public void updatePatient() {
        Patient patient = new Patient("patientsomething");

        try {
            new BedManager(inDepartment).assignToBed(patient);
        } catch (ExceededCapacityException e) {
            e.printStackTrace();
        }

        assertTrue(daoDepartment.save(patient, inDepartment));

        try {
            new BedManager(inDepartment).changeBed(patient);
        } catch (ExceededCapacityException e) {
            e.printStackTrace();
        }

        assertTrue(daoDepartment.update(patient, inDepartment));

        assertTrue(daoDepartment.delete(patient, inDepartment));
    }

    @Test
    public void updateStaff() {
        Staff staff = new Clerk("someid");
        assertTrue(daoDepartment.save(staff, inDepartment));
        assertTrue(daoDepartment.update(staff, outDepartment));
        assertTrue(daoDepartment.delete(staff, outDepartment));
    }

    @Test
    public void findDepartmentIdOfStaff() {

    }

}