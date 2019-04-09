package management;

import java.util.Date;

public class ICTOfficer extends Staff implements IRegistering {


    public <T extends Person> void registerPerson(T person, Department department) {
        // Add person to database
        department.getStaff().add(person);
        // Give unique id
        addUniqueIdToPerson(person);
    }

    public void addUniqueIdToPerson(Person person) {
        person.setUniqueId(InformationGenerator.generateUniqueID());
    }

    public void setDoctorInformation(Doctor doctor, Speciality speciality, String name, String surname, Date birthdate, int gender,
                                     String homeAddress, int phoneNumber) {
        doctor.setSpeciality(speciality);
        setPersonInformation(doctor, name, surname, birthdate, gender, homeAddress, phoneNumber);
    }

    @SuppressWarnings("Duplicates")
    public <T extends Person> void setPersonInformation(T person, String name, String surname, Date birthdate, int gender,
                                                        String homeAddress, int phoneNumber) {
        person.setName(name);
        person.setSurname(surname);
        person.setBirthdate(birthdate);
        person.setGender(gender);
        person.setHomeAddress(homeAddress);
        person.setPhoneNumber(phoneNumber);
    }
}
