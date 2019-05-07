package controller;

import core.buildings.Department;
import core.buildings.Hospital;
import persistence.data_access_objects.DaoDepartmentImpl;

import java.util.ArrayList;
import java.util.HashMap;

public class LoadHospital {
    Hospital hospital = new Hospital();
    DaoDepartmentImpl<Department> DAO = new DaoDepartmentImpl<>();


    LoadHospital() {
        this.hospital = loadHospital();
    }

    public Hospital getHospital() {
        return this.hospital;
    }

    private Hospital loadHospital() {
        Hospital hospital = getHospital();

        HashMap<String, String> outDepts = new HashMap<>();
        HashMap<String, String> inDepts = new HashMap<>();
        HashMap<String, String> erDepts = new HashMap<>();
        outDepts.put("type", "OutDepartment");
        inDepts.put("type", "InDepartment");
        erDepts.put("type", "ERDepartment");

        ArrayList<HashMap<String,String>> depts = new ArrayList<HashMap<String, String>>();
        depts.add(outDepts);
        depts.add(inDepts);
        depts.add(erDepts);

        for (HashMap<String, String> dept : depts) {
            ArrayList<Department> result = DAO.find(dept);
            for (Department department : result) {
                hospital.add(department);
            }
        }
        return hospital;
    }
}
