package core.buildings;

import core.persons.Patient;
import core.persons.Staff;
import persistence.data_access_objects.DaoDepartmentImpl;

import static core.buildings.Event.*;

public class DepartmentObserver implements Observer {
    DaoDepartmentImpl<Department> daoDepartment = new DaoDepartmentImpl<>();

    @Override
    public void objectChanged(Object source, Event action, Object oldValue, Object newValue) {
        if (oldValue instanceof Department || newValue instanceof Department) {
            objectChanged(source, action, (Department) oldValue, (Department) newValue);
        } else if (oldValue instanceof Staff || newValue instanceof Staff) {
            objectChanged(source, action, (Staff) oldValue, (Staff) newValue);
        } else if (oldValue instanceof Patient || newValue instanceof Patient) {
            objectChanged(source, action, (Patient) oldValue, (Patient) newValue);
        }
    }

    public void objectChanged(Object source, Event action, Department oldValue, Department newValue) {
        switch (action) {
            case ADD:
                break;
            case UPDATE:
                break;
            case DELETE:
                break;

        }
    }

    public void objectChanged(Object source, Event action, Staff oldValue, Staff newValue) {
        switch (action) {
            case ADD:
                //daoDepartment.save(newValue, source);
                break;
            case UPDATE:
                break;
            case DELETE:
                break;

        }
    }

    public void objectChanged(Object source, Event action, Patient oldValue, Patient newValue) {
        switch (action) {
            case ADD:
                break;
            case UPDATE:
                break;
            case DELETE:
                break;
        }
    }
}
