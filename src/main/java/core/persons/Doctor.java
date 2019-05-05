package core.persons;

import core.buildings.OutDepartment;
import core.utility.Speciality;

import java.util.Date;

public class Doctor extends Staff {
    private Speciality speciality;
    private Patient treatedPatient;

    public Doctor() {
    }

    public Doctor(String uniqueId, String name, String surname, Date birthdate, Gender gender, String homeaddress,
                  int phonenumber, String email, String initials, Speciality speciality) {
        super(uniqueId, name, surname, birthdate, gender, homeaddress, phonenumber, email, initials);
        this.speciality = speciality;
    }

    public Doctor(Speciality speciality, String name, String surname, Date birthdate, Gender gender, String homeaddress, int phonenumber) {
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
