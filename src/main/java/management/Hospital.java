package management;

import java.util.ArrayList;

public class Hospital {

    private ArrayList<Department> depts = new ArrayList<Department> ();

    public void add(Department d) {
        depts.add (d);
    }

    public void remove(Department d) {
        depts.remove (d);
    }

    public void assign(Patient p, Department d) {
        if (depts.contains (d)) {
            d.add (p);
        }
    }

    public void assign(Staff s, Department d) {
        if (depts.contains (d)) {
            d.add (s);
        }
    }

    public void move(Patient p, Department d1, Department d2) {
        if (depts.contains (d1) && depts.contains (d2)) {
            d2.add (p);
            d1.remove (p);
        }
    }

    public void move(Staff s, Department d1, Department d2) {
        if (depts.contains (d1) && depts.contains (d2)) {
            d2.add (s);
            d1.remove (s);
        }
    }

    public ArrayList<Department> getDepts() {
        return depts;
    }

}
