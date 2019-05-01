package management;

import exceptions.PersonNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;

public interface IQueryPatients<Patient> {
    Patient findpatient(Patient obj) throws PersonNotFoundException;
    ArrayList<Patient> findpatient(HashMap<String, String> params) throws PersonNotFoundException;


}

