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
        if (oldValue instanceof Staff || newValue instanceof Staff) {
            objectChanged(source, action, (Staff) oldValue, (Staff) newValue);
        } else if (oldValue instanceof Patient || newValue instanceof Patient) {
            objectChanged(source, action, (Patient) oldValue, (Patient) newValue);
        }
    }

    public void objectChanged(Object source, Event action, Staff oldValue, Staff newValue) {
        switch (action) {
            case ADD:
                daoStaff.save(newValue);
                break;
            case UPDATE:
                daoStaff.update(newValue);
                break;
            case DELETE:
                daoStaff.delete(oldValue);
                break;
        }
    }

    public void objectChanged(Object source, Event action, Patient oldValue, Patient newValue) {
        switch (action) {
            case ADD:
                daoPatient.save(newValue);
                break;
            case UPDATE:
                daoPatient.update(newValue);
                break;
            case DELETE:
                daoPatient.delete(oldValue);
                break;
        }
    }
}
