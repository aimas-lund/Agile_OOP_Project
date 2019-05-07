package core.buildings;

import persistence.data_access_objects.DaoDepartmentImpl;

public class DepartmentObserver implements Observer {
    DaoDepartmentImpl<Department> daoDepartment = new DaoDepartmentImpl<>();

    @Override
    public void objectChanged(Object oldValue, Object newValue) {
        daoDepartment.save((Department) newValue);
    }
}
