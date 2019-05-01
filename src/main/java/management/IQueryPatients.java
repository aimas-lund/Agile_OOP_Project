package management;

import exceptions.PersonNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;

public interface IQueryPatients<T extends Patient> {
    T findPatient(T obj) throws PersonNotFoundException;
    ArrayList<T> findPatient(HashMap<String, String> params) throws PersonNotFoundException;


}

