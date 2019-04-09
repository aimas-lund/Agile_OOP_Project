package management;

import java.util.Date;

public interface IRegistering {
    // TODO: Instead of having an access level, why not just dedicate needed methods through interfaces?
    // TODO: Maybe this as an abstract class
    <T extends Person> void registerPerson(T person, Department department);

    void addUniqueIdToPerson(Person person);

    <T extends Person> void setPersonInformation(T person, String name, String surname, Date birthdate, int gender,
                                                 String homeAddress, int phoneNumber);
}
