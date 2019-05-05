package core.persons;

import core.buildings.OutDepartment;
import core.utility.Speciality;

import java.util.Date;

public class Doctor extends Staff {
    private Speciality speciality;
    private Patient treatedPatient;

    public Doctor() {
    }

    public Doctor(String name, String surname, Date birthdate, int gender, String homeaddress, int phonenumber) {
        super(name, surname, birthdate, gender, homeaddress, phonenumber);
    }

    public Doctor(Speciality speciality, String name, String surname, Date birthdate, int gender, String homeaddress, int phonenumber) {
        super(name, surname, birthdate, gender, homeaddress, phonenumber);
        this.speciality = speciality;
    }

    public Doctor(String uniqueId) {
        super(uniqueId);
    }

    public Speciality getSpeciality() {
        return speciality;
    }

    void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }

    public void callWaitingPatient(OutDepartment outDepartment) {
        treatedPatient = outDepartment.getNextWaitingPatient();
    }

    public void dischargeTreatedPatient() {
        treatedPatient = null;
    }

    public Patient getTreatedPatient() {
        return treatedPatient;
    }
}
