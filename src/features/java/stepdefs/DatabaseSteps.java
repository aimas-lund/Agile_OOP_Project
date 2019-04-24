package stepdefs;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import management.*;
import storage.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import static junit.framework.TestCase.*;

public class DatabaseSteps {

    private Clerk clerk;
    private Patient patient;
    private Department department;
    private ICTOfficer ict;
    private Staff staff;

    @Before
    public void setUp() {
        department = new Department("Mockdepartment", 10);
        clerk = new Clerk();
        patient = new Patient();
        ict = new ICTOfficer();
    }
    @Given("a user")
    public void a_user() {
        assertNotNull(clerk);
    }

    @When("a new patient is admitted to the hospital")
    public void a_new_patient_is_admitted_to_the_hospital() {
        patient = new Patient(
                "Bobby",
                "Fischer",
                new Date(2019),
                0,
                "Homestreet 23",
                45231298);

        clerk.registerPerson(patient, department);
    }

    @Then("the user should add the patient to the database")
    public void the_user_should_add_the_patient_to_the_database() {
        Database database = new Database();
        Statement statement = database.createStatement();

        String sql = "select * from patients where uniqueId = '%s'";
        sql = String.format(sql, patient.getUniqueId());
        ResultSet rs;

        try {
            rs = statement.executeQuery(sql);

            assertTrue(rs.next());
            while (rs.next()) {
                assertEquals("Bobby", rs.getString("name"));
                assertEquals("Fischer", rs.getString("surname"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @When("a new staff is hired to the hospital")
    public void aNewStaffIsHiredToTheHospital() {
        staff = new Staff("Emil", "Christensen", new Date(2019), 0, "Strandvejen 20", 30303030, "echristensen@hospital.dk", "EC");
        ict.registerPerson(staff, department);
    }

    @Then("the user should add the staff to the database")
    public void theUserShouldAddTheStaffToTheDatabase() {
        Database database = new Database();
        Statement statement = database.createStatement();

        String sql = "select * from staff where uniqueId = '%s'";
        sql = String.format(sql, staff.getUniqueId());
        ResultSet rs;

        try {
            rs = statement.executeQuery(sql);

            assertTrue(rs.next());
            while (rs.next()) {
                assertEquals("Emil", rs.getString("name"));
                assertEquals("Christensen", rs.getString("surname"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
//
//    @Given("a hospital")
//    public void aHospital() {
//        assertTrue(hospital instanceof Hospital);
//    }
//
//    @And("the hospital does not have connection to the database")
//    public void theHospitalDoesNotHaveConnectionToTheDatabase() {
//        hospital.getDatabase().disconnectFromDB();
//        assertFalse(hospital.getDatabase().hasConnection());
//    }
//
//    @When("a hospital is instantiated")
//    public void aHospitalIsInstantiated() {
//        assertNotNull(hospital);
//    }
//
//    @Then("a hospital is connected to the database")
//    public void aHospitalIsConnectedToTheDatabase() {
//        Hospital hospital_new = new Hospital();
//        assertTrue(hospital_new.getDatabase().hasConnection());
//        assertFalse(hospital.getDatabase().hasConnection());
//    }
}