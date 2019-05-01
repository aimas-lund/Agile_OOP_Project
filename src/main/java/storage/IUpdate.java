package storage;

import management.Department;
import management.Person;

public interface IUpdate {
    <T extends Person> boolean registerPerson(T person, Department department);

    <T extends Person> boolean isPersonRegistered(T person, Department department);

    <T extends Person> boolean delete(T obj, Department department);

    <T extends Person> boolean update(T person);
}
