package tests.persistence.data_access_objects;

import core.buildings.Department;
import core.buildings.InDepartment;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import persistence.data_access_objects.DaoDepartmentImpl;

public class DaoDepartmentImplTest {
    private Department department;
    private DaoDepartmentImpl<Department> daoDepartment = new DaoDepartmentImpl<>();


    @Before
    public void setUp() {
        department = new InDepartment("dep2", "ER", 1);

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
}