package management;

import exceptions.PersonNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;

public interface IQueryPatients<Patient> {
    Patient findPatient(Patient obj) throws PersonNotFoundException;

    ArrayList<Patient> findPatient(HashMap<String, String> params) throws PersonNotFoundException;

}
