package tests;

import management.Department;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import storage.DaoDepartmentImpl;

public class DaoDepartmentImplTest {
    private Department department;
    private DaoDepartmentImpl<Department> daoDepartment = new DaoDepartmentImpl<>();


    @Before
    public void setUp() {
        department = new Department("ER", 1);

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