package management;

public interface IRegistering<T extends Person> {
    boolean registerPerson(T person, Department department);

    boolean isPersonRegistered(T person, Department department);

}
