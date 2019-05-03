package persistence.query_roles;

import core.persons.Person;
import exceptions.PersonNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;

interface IQuery {
    <T extends Person> ArrayList<T> find(HashMap<String, String> params, T table) throws PersonNotFoundException;
}
