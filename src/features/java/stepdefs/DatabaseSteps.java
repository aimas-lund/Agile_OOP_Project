package stepdefs;

import core.buildings.Department;
import core.buildings.InDepartment;
import core.persons.Clerk;
import core.persons.Gender;
import core.persons.Patient;
import core.persons.Staff;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import exceptions.PersonNotFoundException;
import persistence.Database;
import persistence.data_access_objects.DaoPatientImpl;
import persistence.data_access_objects.DaoStaffImpl;
import persistence.query_roles.QueryRoleClerk;
import persistence.query_roles.QueryRoleICT;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;

import static junit.framework.TestCase.*;

public class DatabaseSteps {
    private QueryRoleClerk clerk;
    private Patient patient;
    private Department department;
    private QueryRoleICT ict;
    private Staff staff;

    @Before
    public void setUp() {
        department = new InDepartment("Mockdepartment", "name", 10);
        clerk = new QueryRoleClerk();
        patient = new Patient();
        ict = new QueryRoleICT();
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
                Gender.FEMALE,
                "Homestreet 23",
                45231298);

        clerk.registerPerson(patient, department);
    }

    @Then("the user should add the patient to the database")
    public void the_user_should_add_the_patient_to_the_database() {
        Database database = new Database();

        String sql = "select * from patients where uniqueId = ?";

        ResultSet rs;
        try {
            PreparedStatement statement = database.prepareStatement(sql);
            statement.setString(1, patient.getUniqueId());
            rs = statement.executeQuery();

            assertTrue(rs.next());
            while (rs.next()) {
                assertEquals("Bobby", rs.getString("name"));
                assertEquals("Fischer", rs.getString("surname"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            database.disconnectFromDB();
            clerk.delete(patient, department);
        }
    }

    @When("a new staff is hired to the hospital")
    public void aNewStaffIsHiredToTheHospital() {
        staff = new Clerk("Emil", "Christensen", new Date(2019), Gender.MALE, "Strandvejen 20", 30303030, "echristensen@hospital.dk", "EC");
        assertTrue(ict.registerPerson(staff, department));
    }

    @Then("the user should add the staff to the database")
    public void theUserShouldAddTheStaffToTheDatabase() {
        Database database = new Database();

        String sql = "select * from staff where uniqueId = ?";
        ResultSet rs;

        try {
            PreparedStatement statement = database.prepareStatement(sql);
            statement.setString(1, staff.getUniqueId());
            rs = statement.executeQuery();

            assertTrue(rs.next());
            while (rs.next()) {
                assertEquals("Emil", rs.getString("name"));
                assertEquals("Christensen", rs.getString("surname"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            database.disconnectFromDB();
            ict.delete(staff, department);
        }
    }


    @When("changing a person's information")
    public void changingAPersonsInformation() {
        department = new InDepartment("Mockdepartment", "Name", 10);
        patient = new Patient(
                "Simon",
                "Muuu",
                new Date(2019),
                Gender.MALE,
                "Homestreet 23",
                45231298);

        clerk.registerPerson(patient, department);
        clerk.update(patient);

    }
    @Then("the user should not be able to change the unique ID of that person")
    public void notChangeUniqueID() {
        String UniqueIdbefore = patient.getUniqueId();
        assertEquals(UniqueIdbefore, patient.getUniqueId());
        //You can not change ID.
        clerk.delete(patient, department);
    }

    @When("the user need specific information")
    public void theUserNeedSpecificInformation() {
        patient = new Patient(
                "Hilda",
                "Stol",
                new Date(1997),
                Gender.FEMALE,
                "Hildagade 1",
                45231298);
        clerk.registerPerson(patient, department);

        staff = new Clerk(
                "Emil",
                "Christensen",
                new Date(2019),
                Gender.FEMALE,
                "Strandvejen 20",
                30303030,
                "echristensen@hospital.dk",
                "EMCH");
        ict.registerPerson(staff, department);


    }

    @Then("the user should be able to search by keywords or filters in the database.")
    public void theUserShouldSearch() {
        DaoStaffImpl<Staff> daoStaff = new DaoStaffImpl<>();
        DaoPatientImpl<Patient> daoPatient = new DaoPatientImpl<>();

        assertEquals(daoStaff.find(staff).getUniqueId(), staff.getUniqueId());
        assertEquals(daoPatient.find(patient).getUniqueId(),patient.getUniqueId());

        HashMap<String, String> HMtest = new HashMap<>();
        HMtest.put("name", "Emil");
        for (Staff staff : daoStaff.find(HMtest)) {
            if (staff.getUniqueId().equals(this.staff.getUniqueId())) {
                assertEquals(staff.getUniqueId(), this.staff.getUniqueId());
            }
        }

        HMtest.remove("name");
        HMtest.put("name", "Hilda");
        for (Patient patient : daoPatient.find(HMtest)) {
            if (patient.getUniqueId().equals(this.patient.getUniqueId())) {
                assertEquals(patient.getUniqueId(), this.patient.getUniqueId());
            }
        }

        ict.delete(staff, department);
        clerk.delete(patient, department);

    }

    @Given("a user that can query the database")
    public void aUserThatCanQueryTheDatabase() {
        patient = new Patient(
                "NOT",
                "DATABASE",
                new Date(2019),
                Gender.MALE,
                "Homestreet 23",
                45231298);

        assertNotNull(clerk);
    }

    @When("the person is not found")
    public void thePersonIsNotFound() {
        HashMap<String, String> params = new HashMap<>();
        params.put("name", "not");

        try {
            assertNull(clerk.find(params));
        } catch (PersonNotFoundException e) {
//            e.printStackTrace();
        }
    }

    @Then("the user should be notified")
    public void theUserShouldBeNotified() {
        HashMap<String, String> params = new HashMap<>();
        params.put("name", "not");

        try {
            assertNull(clerk.find(params));
        } catch (PersonNotFoundException e) {
            assertEquals("exceptions.PersonNotFoundException: No patients were found with given parameters",
                    e.toString());
        }
    }

    @Given("an ICT-officer")
    public void anICTofficer(){
        assertNotNull(ict);

    }
    @When("specific information is needed")
    public void specificInformation(){
        patient = new Patient(
                "Hilda",
                "Stol",
                new Date(1997),
                Gender.FEMALE,
                "Hildagade 1",
                45231298);
        clerk.registerPerson(patient, department);

        staff = new Clerk(
                "Emil",
                "Christensen",
                new Date(2019),
                Gender.MALE,
                "Strandvejen 20",
                30303030,
                "echristensen@hospital.dk",
                "EMCH");
        ict.registerPerson(staff, department);



    }
    @Then("the ICT-officer should be able to search by keywords in the patient or staff database")
    public void ShouldBeAbleToSearch(){

        HashMap<String, String> paramspatient = new HashMap<>();
        paramspatient.put("name", "Hilda");

        HashMap<String, String> paramsstaff = new HashMap<>();
        paramsstaff.put("name", "Emil");

        try {
            assertNotNull(ict.findPatient(paramspatient));
        } catch (PersonNotFoundException e) {
            assertEquals("exceptions.PersonNotFoundException: No patients were found with given parameters",
                    e.toString());
        }

        ict.delete(patient, department);

        try {
            assertNotNull(ict.findStaff(paramsstaff));
        } catch (PersonNotFoundException e) {
            assertEquals("exceptions.PersonNotFoundException: No patients were found with given parameters",
                    e.toString());
        }

        ict.delete(staff, department);


    }
}
