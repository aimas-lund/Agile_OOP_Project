package core.buildings;

import core.persons.Patient;
import core.persons.Staff;
import persistence.data_access_objects.DaoDepartmentImpl;

public class DepartmentObserver implements Observer {
    private DaoDepartmentImpl<Department> daoDepartment = new DaoDepartmentImpl<>();

    @Override
    public void objectChanged(Object source, Event event, Object oldValue, Object newValue) {
        if (oldValue instanceof Department || newValue instanceof Department) {
            objectChanged((Department) source, event);
        } else if (oldValue instanceof Staff || newValue instanceof Staff) {
            objectChanged((Department) source, event, (Staff) oldValue, (Staff) newValue);
        } else if (oldValue instanceof Patient || newValue instanceof Patient) {
            objectChanged((Department) source, event, (Patient) oldValue, (Patient) newValue);
        }
    }

    public void objectChanged(Department source, Event event) {
        switch (event) {
            case ADD:
                daoDepartment.save(source);
                break;
            case UPDATE:
                daoDepartment.update(source);
                break;
            case DELETE:
                daoDepartment.delete(source);
                break;

        }
    }

    public void objectChanged(Department source, Event event, Staff oldValue, Staff newValue) {
        switch (event) {
            case ADD:
                daoDepartment.save(newValue, source);
                break;
            case UPDATE:
                daoDepartment.update(newValue, source);
                break;
            case DELETE:
                daoDepartment.delete(oldValue, source);
                break;

        }
    }

    public void objectChanged(Department source, Event event, Patient oldValue, Patient newValue) {
        switch (event) {
            case ADD:
                daoDepartment.save(newValue, source);
                break;
            case UPDATE:
                daoDepartment.update(newValue, source);
                break;
            case DELETE:
                daoDepartment.delete(oldValue, source);
                break;
        }
    }
}
