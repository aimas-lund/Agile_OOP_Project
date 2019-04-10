package management;

import java.util.ArrayList;

public class Hospital {
	private ArrayList<Department> depts = new ArrayList<Department>();
	
	public void move(Staff s, Department deptA, Department deptB) {
		deptA.remove(s);
		deptB.add(s);
	}
	public void move(Patient p, Department deptA, Department deptB) {
		deptA.remove(p);
		deptB.add(p);
	}
	public void add(Department d) {
		depts.add(d);
	}
	public void remove(Department d) {
		depts.remove(d);
	}
	public void assign(Staff s, Department d) {
		d.add(s);
	}
	public void assign(Patient p, Department d) {
		d.add(p);
	}
}
