package stepdefs;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import management.Hospital;
import storage.Database;

import static org.junit.jupiter.api.Assertions.*;

public class DatabaseSteps {
    private Hospital hospital = new Hospital();
    private Database database = new Database();

    @Given("a hospital")
    public void aHospital() {
        assertNotNull(hospital);
    }

    @And("the hospital does not have connection to the database")
    public void theHospitalDoesNotHaveConnectionToTheDatabase() {

    }

    @When("a hospital is instantiated")
    public void aHospitalIsInstantiated() {
    }

    @Then("a hospital is connected to the database")
    public void aHospitalIsConnectedToTheDatabase() {
    }
}
