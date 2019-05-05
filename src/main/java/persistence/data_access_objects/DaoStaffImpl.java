package persistence.data_access_objects;


import core.persons.*;
import core.utility.Speciality;
import persistence.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DaoStaffImpl<T extends Staff> implements IDao<T> {

    private final Database database = new Database();

    @Override
    public boolean save(T staff) {
        String[] information = staff.getPersonInformation();

        String sql = "insert into staff values('%s', '%s', '%s', date('%s'), '%s', '%s', '%s', '%s', '%s', '%s', ";
        String sqlSuffix;
        if (staff instanceof Doctor && ((Doctor) staff).getSpeciality() != null) {
            String speciality = ((Doctor) staff).getSpeciality().toString();
            sqlSuffix = String.format("'%s')", speciality);
        } else {
            sqlSuffix = "null)";
        }

        sql += sqlSuffix;

        for (String value :
                information) {
            sql = sql.replaceFirst("%s", value.replaceAll(" ", "_"));
        }

        sql = sql.replaceFirst("%s", staff.getClass().getSimpleName());

        return database.executeStatement(sql);
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

        Statement statement = database.createStatement();

        ArrayList<T> staff = new ArrayList<>();

        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String uniqueId = resultSet.getString("uniqueId");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                Date birthdate = new SimpleDateFormat("yyyy-MM-dd").parse(resultSet.getString("birthdate"));
                Gender gender = Gender.valueOf(resultSet.getString("gender"));
                String homeaddress = resultSet.getString("homeaddress").replaceAll("_", " ");
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
        } catch (SQLException | ParseException e) {
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
        String sql = "delete from staff where uniqueid = '%s'";
        sql = String.format(sql, uniqueId);

        return database.executeStatement(sql);
    }

    public boolean update(T staff) {
        String[] information = staff.getPersonInformation();
        String sql = "UPDATE staff set uniqueid = '%s', name = '%s', surname = '%s', birthdate = date('%s'), " +
                "gender = '%s', homeaddress = '%s', phonenumber = '%s', email = '%s', initials = '%s'";
        String sqlWhere = String.format(" where uniqueId = '%s'", staff.getUniqueId());

        for (String value :
                information) {
            if (value == null) return false;

            sql = sql.replaceFirst("%s", value.replaceAll(" ", "_"));
        }

        return database.executeStatement(sql + sqlWhere);
    }

}
