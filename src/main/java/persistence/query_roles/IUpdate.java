package persistence.query_roles;

import core.buildings.Department;
import core.persons.Person;

public interface IUpdate {
    <T extends Person> boolean registerPerson(T person, Department department);

    <T extends Person> boolean isPersonRegistered(T person, Department department);

    <T extends Person> boolean delete(T obj, Department department);

    <T extends Person> boolean update(T person);
}
