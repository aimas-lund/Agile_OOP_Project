package management;

import exceptions.PersonNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;

public interface IQueryStaff<Staff> {
    Staff findstaff(Staff obj) throws PersonNotFoundException;

    ArrayList<Staff> findstaff(HashMap<String, String> params) throws PersonNotFoundException;

}
