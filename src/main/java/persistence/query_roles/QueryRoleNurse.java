package persistence.query_roles;

import core.buildings.BedManager;
import core.buildings.InDepartment;
import core.buildings.OutDepartment;
import core.persons.Patient;
import exceptions.ExceededCapacityException;

public class QueryRoleNurse {
    public void assignPatientToWaitingRoom(Patient patient, OutDepartment outDepartment) {
        outDepartment.addWaitingPatient(patient);
        //TODO : add a dao implementation here
    }

    public boolean assignPatientToBed(Patient patient, InDepartment inDepartment) {
        BedManager bedManager = new BedManager(inDepartment);
        try {
            bedManager.assignToBed(patient);
            return true;
        } catch (ExceededCapacityException e) {
            return false;
        }
        //TODO: Store that patient has been added BOIIII
    }
}
