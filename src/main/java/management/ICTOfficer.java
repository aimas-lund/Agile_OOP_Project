package management;

import java.util.Date;

public class ICTOfficer extends Staff implements IRegistering {


    public <T extends Person> boolean registerPerson(T person, Department department) {
        // Check that the person is not registered
        try {
            isPersonRegistered(person, department);
        } catch (PersonAlreadyRegisteredException e) {
            System.out.println(e.getMessage());
            return false;
        }

        // Add person to database
        department.getStaff().add(person);
        // Give unique id
        addUniqueIdToPerson(person);
        return true;
    }

    public <T extends Person> void isPersonRegistered(T person, Department department) throws PersonAlreadyRegisteredException {
        // Search for same Unique ID
        for (Person staff : department.getStaff()) {
            // TODO: Optimize find functionality, now is O(n)
            if (staff.getUniqueId() == person.getUniqueId()) {
                throw new PersonAlreadyRegisteredException("Staff member is already registered in the system");
            }
        }
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
