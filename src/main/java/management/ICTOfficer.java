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
