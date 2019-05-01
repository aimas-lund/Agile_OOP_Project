package storage;

import exceptions.PersonNotFoundException;
import management.Person;

import java.util.ArrayList;
import java.util.HashMap;

interface IQuery {
    <T extends Person> ArrayList<T> find(HashMap<String, String> params, T table) throws PersonNotFoundException;
}
