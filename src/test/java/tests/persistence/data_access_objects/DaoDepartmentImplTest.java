package tests.persistence.data_access_objects;

import core.buildings.*;
import core.persons.Clerk;
import core.persons.ICTOfficer;
import core.persons.Nurse;
import core.persons.Patient;
import exceptions.ExceededCapacityException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import persistence.data_access_objects.DaoDepartmentImpl;

import static junit.framework.TestCase.assertTrue;

public class DaoDepartmentImplTest {
    private DepartmentBeds inDepartment;
    private Department outDepartment;
    private DaoDepartmentImpl<Department> daoDepartment = new DaoDepartmentImpl<>();


    @Before
    public void setUp() {
        inDepartment = new InDepartment("dep2", "ER", 3);
        outDepartment = new OutDepartment();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void saveDelete() throws ExceededCapacityException {
        inDepartment.add(new Clerk("clerk"));
        inDepartment.add(new Nurse("nurse"));
        inDepartment.add(new ICTOfficer("ICTOfficer"));

        BedManager bedManager = new BedManager(inDepartment);
        bedManager.assignToBed(new Patient("patient1"));
        bedManager.assignToBed(new Patient("patient2"));

        inDepartment.add(new Patient("patient3"));
        assertTrue(daoDepartment.save(inDepartment));
        assertTrue(daoDepartment.delete(inDepartment));

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

}