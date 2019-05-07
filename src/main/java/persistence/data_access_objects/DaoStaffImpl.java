package persistence.data_access_objects;


import core.persons.*;
import core.utility.Speciality;
import persistence.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DaoStaffImpl<T extends Staff> implements IDao<T> {

    private final Database database = new Database();

    @Override
    public boolean save(T staff) {
        String sql = "insert into staff values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement statement = database.prepareStatement(sql);
            buildStaffInformationSql(staff, statement);

            return database.executePreparedStatement(statement);

        } catch (SQLException | NullPointerException e) {
            return false;
        }
    }

    @Override
    public ArrayList<T> find(HashMap<String, String> params) {
        database.connectToDB();

        String sql = "select * from staff where ";
        String values = "";

        for (Map.Entry<String, String> entry :
                params.entrySet()) {
            String value = entry.getValue();

            value = " = " + "'" + value + "'";

            values = values.concat(entry.getKey() + value + " and ");
        }

        sql = sql + values.substring(0, values.length() - 4);


        ArrayList<T> staff = new ArrayList<>();

        try {
            PreparedStatement statement = database.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String uniqueId = resultSet.getString("uniqueId");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                Date birthdate = resultSet.getDate("birthdate");
                Gender gender = Gender.valueOf(resultSet.getString("gender"));
                String homeaddress = resultSet.getString("homeaddress"); //.replaceAll("_", " ")
                int phonenumber = Integer.parseInt(resultSet.getString("phonenumber"));
                String email = resultSet.getString("email");
                String initials = resultSet.getString("initials");

                Staff foundStaff = null;

                switch (resultSet.getString("role")) {
                    case "Clerk":
                        foundStaff = new Clerk(uniqueId, name, surname, birthdate, gender, homeaddress, phonenumber, email, initials);
                        break;
                    case "Nurse":
                        foundStaff = new Nurse(uniqueId, name, surname, birthdate, gender, homeaddress, phonenumber, email, initials);
                        break;
                    case "ICTOfficer":
                        foundStaff = new ICTOfficer(uniqueId, name, surname, birthdate, gender, homeaddress, phonenumber, email, initials);
                        break;
                    case "Doctor":
                        Speciality speciality = Speciality.valueOf(resultSet.getString("speciality"));
                        foundStaff = new Doctor(uniqueId, name, surname, birthdate, gender, homeaddress, phonenumber, email, initials, speciality);
                        break;
                }

                staff.add((T) foundStaff);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        database.disconnectFromDB();
        return staff;
    }

    public <T extends Staff> T find(T staff) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("uniqueid", staff.getUniqueId());
        return (T) find(hashMap).get(0);
    }

    public boolean delete(Staff staff) {
        return delete(staff.getUniqueId());
    }

    @Override
    public boolean delete(String uniqueId) {
        String sql = "delete from staff where uniqueid = ?";

        try {
            PreparedStatement statement = database.prepareStatement(sql);
            statement.setString(1, uniqueId);
            return database.executePreparedStatement(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(T staff) {
        String sql = "UPDATE staff set uniqueid = ?, name = ?, surname = ?, birthdate = ?, " +
                "gender = ?, homeaddress = ?, phonenumber = ?, email = ?, initials = ?, " +
                "role = ?, speciality = ? where uniqueid = ?";

        try {
            PreparedStatement statement = database.prepareStatement(sql);
            buildStaffInformationSql(staff, statement);
            statement.setString(12, staff.getUniqueId());
            return database.executePreparedStatement(statement);

        } catch (SQLException | NullPointerException e) {
            return false;
        }
    }

    private void buildStaffInformationSql(T staff, PreparedStatement statement) throws SQLException, NullPointerException {
        statement.setString(1, staff.getUniqueId());
        statement.setString(2, staff.getName());
        statement.setString(3, staff.getSurname());
        statement.setDate(4, new java.sql.Date(staff.getBirthdate().getTime()));
        statement.setString(5, staff.getGender().toString());
        statement.setString(6, staff.getHomeAddress()); //.replaceAll(" ", "_")
        statement.setInt(7, staff.getPhoneNumber());

        statement.setString(8, staff.getEmail());
        statement.setString(9, staff.getInitials());
        statement.setString(10, staff.getClass().getSimpleName());
        if (staff instanceof Doctor && ((Doctor) staff).getSpeciality() != null) {
            statement.setString(11, ((Doctor) staff).getSpeciality().toString());
        } else {
            statement.setNull(11, Types.VARCHAR);
        }
    }

}
