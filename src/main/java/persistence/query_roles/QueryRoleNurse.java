package persistence.query_roles;

import core.buildings.OutDepartment;
import core.persons.Patient;

public class QueryRoleNurse {
    public void assignPatientToWaitingRoom(Patient patient, OutDepartment outDepartment) {
        outDepartment.addWaitingPatient(patient);
        //TODO : add a dao implementation here
    }
}
