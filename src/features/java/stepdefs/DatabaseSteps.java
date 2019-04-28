package stepdefs;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import management.*;
import exceptions.*;
import storage.Dao;
import storage.Database;
import storage.DaoStaffImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

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


    @When("changing a person's information")
    public void changingAPersonsInformation() {
        department = new Department("Mockdepartment", 10);
        patient = new Patient(
                "Simon",
                "Muuu",
                new Date(2019),
                0,
                "Homestreet 23",
                45231298);
        clerk.registerPerson(patient, department);
        clerk.setPersonSurname(patient, "Moe");


    }
    @Then("the user should not be able to change the unique ID of that person")
    public void notChangeUniqueID() {
        String UniqueIdbefore = patient.getUniqueId();
        assertEquals(UniqueIdbefore, patient.getUniqueId());
        //You can not change ID.
    }

    @When("the user need specific information")
    public void theUserNeedSpecificInformation() {
        clerk.registerPerson(new Patient(
                "Hilda",
                "Stol",
                new Date(1997),
                0,
                "Hildagade 1",
                45231298), department);
        }

    @Then("the user should be able to search by keywords or filters in the database")
    public void theUserShouldSearch() {
        Dao<Staff> DaoInt = new DaoStaffImpl<>();
        staff = new Staff("Emil", "Christensen", new Date(2019), 0, "Strandvejen 20", 30303030, "echristensen@hospital.dk", "EC");
        ict.registerPerson(staff,department);
        DaoInt.save(staff);

        assertEquals(DaoInt.find(staff),staff);
        HashMap<String,String> HMtest = new HashMap<String,String>();
        HMtest.put("name","Emil");
        assertEquals(DaoInt.find(HMtest),staff);




        }




    @Given("a user that can query the database")
    public void aUserThatCanQueryTheDatabase() {
        patient = new Patient(
                "NOT",
                "DATABASE",
                new Date(2019),
                0,
                "Homestreet 23",
                45231298);

        assertNotNull(clerk);
    }

    @When("the person is not found")
    public void thePersonIsNotFound() {
        HashMap<String, String> params = new HashMap<>();
        params.put("name", "'not'");

        try {
            assertNull(clerk.find(params));
        } catch (PersonNotFoundException e) {
//            e.printStackTrace();
        }
    }

    @Then("the user should be notified")
    public void theUserShouldBeNotified() {
        HashMap<String, String> params = new HashMap<>();
        params.put("name", "'not'");

        try {
            assertNull(clerk.find(params));
        } catch (PersonNotFoundException e) {
            assertEquals("exceptions.PersonNotFoundException: Person was not found with given parameters", e.toString());
        }
    }


    @Given("an ICT-officer")
    public void anIctOfficer() {
        //ict

    }
    @When("specific information is needed")
    public void specificInformation() {
        staff = new Staff(
                "NOT",
                "DATABASE",
                new Date(2019),
                0,
                "Homestreet 23",
                45231298);
        ict.registerPerson(staff,department);




    }
    @Then("the ICT officer should be able to search by keywords in the patient or staff database")
    public void theIctOfficerShould(){
        HashMap<String, String> params = new HashMap<>();
        params.put("uniqueId", staff.getUniqueId());
        try {
            assertEquals(ict.find(params),staff);
        } catch (PersonNotFoundException e) {
            assertEquals("exceptions.PersonNotFoundException: Person was not found with given parameters", e.toString());
        }




    }


}

