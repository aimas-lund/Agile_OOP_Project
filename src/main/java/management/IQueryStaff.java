package management;

import exceptions.PersonNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;

public interface IQuery<T> {
    T find(T obj) throws PersonNotFoundException;

    ArrayList<T> find(HashMap<String, String> params) throws PersonNotFoundException;

}
