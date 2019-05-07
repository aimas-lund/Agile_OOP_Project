package tests.persistence.data_access_objects;

import core.buildings.*;
import core.persons.*;
import exceptions.ExceededCapacityException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import persistence.data_access_objects.DaoDepartmentImpl;
import persistence.data_access_objects.DaoStaffImpl;
import persistence.query_roles.QueryRoleICT;

import java.util.Date;
import java.util.HashMap;

import static junit.framework.TestCase.*;

public class DaoDepartmentImplTest {
    private DepartmentBeds inDepartment;
    private DepartmentBeds inDepartmentTest;
    private Department outDepartment;
    private Department outDepartmentTest;
    private DaoDepartmentImpl<Department> daoDepartment = new DaoDepartmentImpl<>();


    @Before
    public void setUp() {
        // Make sure the test sql script has been run before testing
        inDepartment = new InDepartment("id", "Saint John", 6);
        outDepartment = new OutDepartment("id2", "Saint Anders");

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
    public void updateNotBedDepartment() {
        assertTrue(daoDepartment.update(outDepartment));
    }

    @Test
    public void updateFails() {
        assertFalse(daoDepartment.update(new Clerk("notanid"), inDepartment));
        assertFalse(daoDepartment.update(new Patient("notanid"), inDepartment));
    }

    @Test
    public void deleteStaffFails() {
        assertFalse(daoDepartment.delete(new Clerk("notanid"), inDepartment));
    }

    @Test
    public void findDepartmentIdOfPersonStaff() {
        Nurse n = new Nurse("Oline", "Stark", new Date(1337), Gender.FEMALE, "Hey", 12345678);
        DaoStaffImpl<Staff> staff = new DaoStaffImpl<>();
        QueryRoleICT QRICT = new QueryRoleICT();
        QRICT.registerPerson(n,inDepartmentTest);
        String depId = daoDepartment.findDepartmentIdOfPerson(n);
        System.out.println(depId);
//        assertEquals("id", depId);
    }

    @Test
    public void findDepartmentIdOfPersonPatient() {
        String depId = daoDepartment.findDepartmentIdOfPerson(new Patient("patient3"));
        assertEquals("id3", depId);
    }

    @Test
    public void findDepartmentByObject() {
        Department findDepartment = new OutDepartment("id2", "Saint Anders");

        OutDepartment department = (OutDepartment) daoDepartment.find(findDepartment);
        assertNotNull(department);
        assertTrue(department.hasWaitingPatients());
        assertFalse(department.getStaff().isEmpty());
        assertFalse(department.getPatients().isEmpty());
    }


    @Test
    public void findERDepartmentByUniqueId() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("uniqueId", "id3");

        ERDepartment department = (ERDepartment) daoDepartment.find(hashMap).get(0);
        assertNotNull(department);
        assertEquals(0, department.getCurrentCapacity());
        assertEquals(1, department.getTotalCapacity());
        assertFalse(department.getPatients().isEmpty());
        assertFalse(department.getPatientsInBeds().isEmpty());
        assertFalse(department.getStaff().isEmpty());
        assertFalse(department.hasAvailableBeds());

        assertTrue(department.isPatientInBed(department.getPatients().get(0)));

    }

    @Test
    public void findOutDepartmentByUniqueId() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("uniqueId", "id2");

        OutDepartment department = (OutDepartment) daoDepartment.find(hashMap).get(0);
        assertNotNull(department);
        assertTrue(department.hasWaitingPatients());
        assertFalse(department.getStaff().isEmpty());
        assertFalse(department.getPatients().isEmpty());
    }

    @Test
    public void findInDepartmentByUniqueId() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("uniqueId", "id");

        InDepartment department = (InDepartment) daoDepartment.find(hashMap).get(0);
        assertNotNull(department);
        assertEquals(4, department.getCurrentCapacity());
        assertEquals(5, department.getTotalCapacity());
        assertFalse(department.getPatients().isEmpty());
        assertFalse(department.getPatientsInBeds().isEmpty());
        assertFalse(department.getStaff().isEmpty());
        assertTrue(department.hasAvailableBeds());

        assertTrue(department.isPatientInBed(department.getPatients().get(0)));

    }

    @Test
    public void saveEmptyStaffFails() {
        Clerk clerk = new Clerk();
        assertFalse(daoDepartment.save(clerk, inDepartmentTest));
    }

    @Test
    public void saveStaffDuplicateFails() {
        Clerk clerk = new Clerk("patienttest1");
        assertTrue(daoDepartment.save(clerk, inDepartmentTest));
        assertFalse(daoDepartment.save(clerk, inDepartmentTest));
    }

    @Test
    public void saveEmptyPatientFails() {
        Patient patient = new Patient();
        assertFalse(daoDepartment.save(patient, inDepartmentTest));
    }

    @Test
    public void savePatientDuplicateFails() {
        Patient patient = new Patient("patienttest1");
        assertTrue(daoDepartment.save(patient, inDepartmentTest));
        assertFalse(daoDepartment.save(patient, inDepartmentTest));
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
        bedManager.assignToBed(new Patient("patienttest1"));
        bedManager.assignToBed(new Patient("patienttest2"));

        inDepartmentTest.add(new Patient("patienttest3"));
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

        daoDepartment.delete(patient, inDepartment); // Precautionary delete
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


}