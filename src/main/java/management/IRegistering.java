package management;

public interface IRegistering {
    // TODO: Instead of having an access level, why not just dedicate needed methods through interfaces?
    // TODO: Maybe this as an abstract class
    // Returns true if succeeded
    <T extends Person> boolean registerPerson(T person, Department department);

    <T extends Person> boolean isPersonRegistered(T person, Department department);

}
