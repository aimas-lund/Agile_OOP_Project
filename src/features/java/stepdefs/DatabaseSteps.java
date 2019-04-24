package stepdefs;

import cucumber.api.java.en.*;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import management.Hospital;
import storage.Database;

import static org.junit.jupiter.api.Assertions.*;

public class DatabaseSteps {

    @Given("a user")
    public void a_user() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @When("a new patient is admitted to the hospital")
    public void a_new_patient_is_admitted_to_the_hospital() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Then("the user should add the patient to the database")
    public void the_user_should_add_the_patient_to_the_database() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @When("a new staff is hired to the hospital")
    public void aNewStaffIsHiredToTheHospital() {
    }

    @Then("the user should add the staff to the database")
    public void theUserShouldAddTheStaffToTheDatabase() {
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
