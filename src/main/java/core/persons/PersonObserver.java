package core.persons;

import core.buildings.Department;
import core.buildings.Event;
import core.buildings.Observer;
import persistence.data_access_objects.DaoPatientImpl;
import persistence.data_access_objects.DaoStaffImpl;

import javax.print.Doc;

public class PersonObserver implements Observer {
    DaoStaffImpl<Staff> daoStaff = new DaoStaffImpl<>();
    DaoPatientImpl<Patient> daoPatient = new DaoPatientImpl<>();

    @Override
    public void objectChanged(Object source, Event action, Object oldValue, Object newValue) {
        if (source instanceof Staff) {
            objectChanged((Staff) source, action, oldValue, newValue);
        } else if (source instanceof Patient) {
            objectChanged((Patient) source, action, oldValue, newValue);
        }
    }

    public void objectChanged(Staff source, Event action, Object oldValue, Object newValue) {
        switch (action) {
            case UPDATE:
                daoStaff.update(source);
                break;
            default:
                break;
        }
    }

    public void objectChanged(Patient source, Event action, Object oldValue, Object newValue) {
        switch (action) {
            case UPDATE:
                daoPatient.update(source);
                break;
            default:
                break;
        }
    }
}
