package storage;

public class Query {
    @PreAuthorize("hasRole('doctor') or hasRole('ict')")
    public void getPatientsData(){
        //TODO: get all patients and their data (person and health).
    }
    @PreAuthorize("hasRole('doctor') or hasRole('clerk') or hasRole('ict')")
    public void getAdmissionData(){
        //TODO: get status (in/out) and linked departments.
    }
    @PreAuthorize("hasRole('clerk') or hasRole('ict')")
    public void getPatientPersonalData(){
        //TODO: get all patients regsitration information \ {health data}
    }
    @PreAuthorize("hasRole('ict')")
    public void getStaffData(){
        //TODO: get staff information
    }

}
