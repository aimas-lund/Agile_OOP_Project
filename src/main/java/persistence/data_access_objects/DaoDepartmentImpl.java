package persistence.data_access_objects;

import core.buildings.Department;
import core.buildings.DepartmentBeds;
import core.buildings.OutDepartment;
import core.persons.Bed;
import core.persons.Patient;
import core.persons.Staff;
import persistence.Database;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;

public class DaoDepartmentImpl<T extends Department> implements IDao<T> {
    private static ArrayList<String> uniqueids;
    private final Database database = new Database();

    static {
        uniqueids = new ArrayList<>();
    }

    @Override
    public boolean save(T department) {
        if (!saveDepartment(department)) {
            return false;
        } else if (!saveAllPatients(department)) {
            return false;
        } else if (!saveAllStaff(department)) {
            return false;
        }
        database.commit();
        database.disconnectFromDB();
        return true;
    }

    public boolean saveDepartment(T department) {
        String sql = "insert into departments values(?, ?, ?, ?, ?)";

        try {
            PreparedStatement statement = database.prepareStatement(sql);
            statement.setString(1, department.getUniqueId());
            statement.setString(2, department.getName());
            statement.setString(5, department.getClass().getSimpleName());

            if (department instanceof DepartmentBeds) {
                statement.setInt(3, ((DepartmentBeds) department).getTotalCapacity());
                statement.setInt(4, ((DepartmentBeds) department).getCurrentCapacity());
            } else {
                statement.setNull(3, Types.INTEGER);
                statement.setNull(4, Types.INTEGER);
            }
            statement.addBatch();
            return database.executePreparedStatement(statement, false);

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean saveAllStaff(T department) {
        String sql = "insert into staff_in_departments values(?, ?)";
        ArrayList<String> tempUniqueIds = new ArrayList<>();

        try {
            PreparedStatement statement = database.prepareStatement(sql);

            for (Staff staff :
                    department.getStaff()) {
                if (uniqueids.contains(staff.getUniqueId())) {
                    continue;
                }
                statement.setString(1, staff.getUniqueId());
                statement.setString(2, department.getUniqueId());
                statement.addBatch();
            }

            if (database.executePreparedStatement(statement, false)) {
                uniqueids.addAll(tempUniqueIds);
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean saveAllPatients(T department) {
        String sql = "insert into patients_in_departments values(?, ?, ?, ?)";
        ArrayList<String> tempUniqueIds = new ArrayList<>();

        try {
            PreparedStatement statement = database.prepareStatement(sql);

            for (Patient patient :
                    department.getPatients()) {
                if (uniqueids.contains(patient.getUniqueId())) {
                    continue;
                }

                statement.setString(1, patient.getUniqueId());
                statement.setString(2, department.getUniqueId());

                if (department instanceof DepartmentBeds) {
                    Bed bed = ((DepartmentBeds) department).getBedWithPatient(patient);
                    if (bed == null) {
                        statement.setNull(3, Types.INTEGER);
                    } else {
                        statement.setInt(3, bed.getId());
                    }
                    statement.setBoolean(4, false);
                } else if (department instanceof OutDepartment) {
                    statement.setNull(3, Types.INTEGER);
                    statement.setBoolean(4, ((OutDepartment) department).isPatientWaiting(patient));
                }
                statement.addBatch();
                tempUniqueIds.add(patient.getUniqueId());
            }

            if (database.executePreparedStatement(statement, false)) {
                uniqueids.addAll(tempUniqueIds);
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean save(Patient patient, T Department) {
        return false;
    }

    public boolean save(Staff staff, T department) {
        return false;
    }

    @Override
    public ArrayList<T> find(HashMap<String, String> params) {
        return null;
    }

    public ArrayList<Patient> findAllPatientsInDepartment(T department) {
        return null;
    }

    public ArrayList<Staff> findAllStaffInDepartment(T department) {
        return null;
    }

    public T find(T department) {
        return null;
    }

    public Department findDepartmentOfPerson(Staff staff) {
        return null;
    }

    public Department findDepartmentOfPerson(Patient patient) {
        return null;
    }

    @Override
    public boolean delete(String uniqueId) {
        String sqlDeleteDepartment = "delete from departments where uniqueId = ?";
        String sqlDeleteStaff = "delete from patients_in_departments where departmentId = ?";
        String sqlDeletePatients = "delete from staff_in_departments where departmentId = ?";

        PreparedStatement preparedStatement;
        try {
            preparedStatement = database.prepareStatement(sqlDeleteDepartment);
            preparedStatement.setString(1, uniqueId);
            preparedStatement.addBatch();
            boolean b1 = database.executePreparedStatement(preparedStatement, false);

            preparedStatement = database.prepareStatement(sqlDeleteStaff);
            preparedStatement.setString(1, uniqueId);
            preparedStatement.addBatch();
            boolean b2 = database.executePreparedStatement(preparedStatement, false);

            preparedStatement = database.prepareStatement(sqlDeletePatients);
            preparedStatement.setString(1, uniqueId);
            preparedStatement.addBatch();
            boolean b3 = database.executePreparedStatement(preparedStatement, false);

            database.commit();
            database.disconnectFromDB();
            return b1 && b2 && b3;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean delete(Staff staff, T department) {
        return false;
    }

    public boolean delete(Patient patient, T department) {
        return false;
    }

    public boolean delete(T department) {
        return delete(department.getUniqueId());
    }

    public boolean update(Staff staff, T department) {
        return false;
    }

    public boolean update(Patient patient, T department) {
        return false;
    }

    @Override
    public boolean update(T department) {
        return false;
    }

}