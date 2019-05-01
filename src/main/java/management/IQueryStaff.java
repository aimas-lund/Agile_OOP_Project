package management;

import exceptions.PersonNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;

public interface IQueryStaff<T extends Staff> {
    T findStaff(T obj) throws PersonNotFoundException;

    ArrayList<T> findStaff(HashMap<String, String> params) throws PersonNotFoundException;

}
