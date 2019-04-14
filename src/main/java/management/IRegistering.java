package management;

import java.util.Date;

public interface IRegistering {
    // TODO: Instead of having an access level, why not just dedicate needed methods through interfaces?
    // TODO: Maybe this as an abstract class
    // Returns true if succeeded
    <T extends Person> boolean registerPerson(T person, Department department);

    <T extends Person> void isPersonRegistered(T person, Department department) throws PersonAlreadyRegisteredException;

    void addUniqueIdToPerson(Person person);

    <T extends Person> void setPersonInformation(T person, String name, String surname, Date birthdate, int gender,
                                                 String homeAddress, int phoneNumber);
}
