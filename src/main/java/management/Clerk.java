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

    public <T extends Person> void registerPerson(T person, Department department) {
        department.getPatients().add(person);
        addUniqueIdToPerson(person);
    }

    public void addUniqueIdToPerson(Person person) {
        person.setUniqueId(InformationGenerator.generateUniqueID());
    }

    public boolean checkPatientRegistrationStatus(Patient patient, Department department) {
        // TODO: Can also be made with exceptions..

        return department.getPatients().contains(patient);
    }
}
