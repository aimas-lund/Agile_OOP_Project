package management;

import java.util.Date;

public class Clerk extends Staff implements IRegistering {

    @SuppressWarnings("Duplicates")  // don't mind this
    public <T extends Person> void setPersonInformation(T person, String name, String surname, Date birthdate, int gender,
                                                        String homeAddress, int phoneNumber) {
        person.setName(name);
        person.setSurname(surname);
        person.setBirthdate(birthdate);
        person.setGender(gender);
        person.setHomeAddress(homeAddress);
        person.setPhoneNumber(phoneNumber);
    }

    public <T extends Person> boolean registerPerson(T person, Department department) {
        // Check that the person is not registered
        try {
            isPersonRegistered(person, department);
        } catch (PersonAlreadyRegisteredException e) {
            return false;
        }

        department.getPatients().add(person);
        addUniqueIdToPerson(person);

        return true;
    }

    public <T extends Person> void isPersonRegistered(T person, Department department) throws PersonAlreadyRegisteredException {
        // Search for same Unique ID
        for (Person patient : department.getStaff()) {
            if (patient.getUniqueId() == person.getUniqueId()) {
                throw new PersonAlreadyRegisteredException("Patient is already registered in the system");
            }
        }
    }
    public void addUniqueIdToPerson(Person person) {
        person.setUniqueId(InformationGenerator.generateUniqueID());
    }

    public boolean checkPatientRegistrationStatus(Patient patient, Department department) {
        // TODO: Can also be made with exceptions..

        return department.getPatients().contains(patient);
    }
}
