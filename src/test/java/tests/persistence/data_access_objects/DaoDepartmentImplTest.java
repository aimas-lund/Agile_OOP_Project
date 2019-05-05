package tests.persistence.data_access_objects;

import core.buildings.Department;
import core.buildings.InDepartment;
import core.buildings.OutDepartment;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import persistence.data_access_objects.DaoDepartmentImpl;

public class DaoDepartmentImplTest {
    private Department inDepartment;
    private Department outDepartment;
    private DaoDepartmentImpl<Department> daoDepartment = new DaoDepartmentImpl<>();


    @Before
    public void setUp() {
        inDepartment = new InDepartment("dep2", "ER", 1);
        outDepartment = new OutDepartment();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void save() {
    }

    @Test
    public void find() {
    }

    @Test
    public void find1() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void delete1() {
    }

    @Test
    public void update() {
    }

    @Test
    public void update1() {
    }

    @Test
    public void save1() {
    }

    @Test
    public void find2() {
    }

    @Test
    public void find3() {
    }

    @Test
    public void delete2() {
    }

    @Test
    public void delete3() {
    }

    @Test
    public void update2() {
    }
}