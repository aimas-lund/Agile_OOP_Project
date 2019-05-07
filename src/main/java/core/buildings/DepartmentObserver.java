package core.buildings;

import core.persons.Patient;
import core.persons.Staff;
import persistence.data_access_objects.DaoDepartmentImpl;

public class DepartmentObserver implements Observer {
    DaoDepartmentImpl<Department> daoDepartment = new DaoDepartmentImpl<>();

    @Override
    public void objectChanged(Object source, String action, Object oldValue, Object newValue) {
        if (oldValue instanceof Department || newValue instanceof Department) {
            objectChanged(source, action, (Department) oldValue, (Department) newValue);
        } else if (oldValue instanceof Staff || newValue instanceof Staff) {
            objectChanged(source, action, (Staff) oldValue, (Staff) newValue);
        } else if (oldValue instanceof Patient || newValue instanceof Patient) {
            objectChanged(source, action, oldValue, newValue);
        }
    }

    public void objectChanged(Object source, String action, Department oldValue, Department newValue) {
        switch (action) {
            case "ADD":
                break;
            case "UPDATE":
                break;
            case "REMOVE":
                break;

        }
    }

    public void objectChanged(Object source, String action, Staff oldValue, Staff newValue) {
        switch (action) {
            case "ADD":
                daoDepartment.save(newValue, source);
                break;
            case "UPDATE":
                break;
            case "REMOVE":
                break;

        }
    }

    public void objectChanged(Object source, String action, Patient oldValue, Patient newValue) {
        switch (action) {
            case "ADD":
                break;
            case "UPDATE":
                break;
            case "REMOVE":
                break;

        }
    }
}
