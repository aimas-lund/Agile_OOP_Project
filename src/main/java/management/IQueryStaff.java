package management;

import exceptions.PersonNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;

public interface IQueryStaff<Staff> {
    Staff findStaff(Staff obj) throws PersonNotFoundException;

    ArrayList<Staff> findStaff(HashMap<String, String> params) throws PersonNotFoundException;

}
