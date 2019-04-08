package stepdefs;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;



import static org.junit.Assert.assertTrue;

public class Departments {
    Department d = new Department();
    @Given("a new department")
    public void aNewDepartment() {
        assertTrue(d instanceof Department);
    }

    @When("the department is made")
    public void theDepartmentIsMade(int capacity, String name) {
        d = new Department(capacity,name);
    }

    @Then("the department needs to know how many patients they can host")
    public void theDepartmentNeedsToKnowHowManyPatientsTheyCanHost() {
        assertTrue(d.capacity instanceof int);
    }


    // Available beds
    @Given("a department")
    public void aDepartment() {

    }

    @When("a patient needs care")
    public void aPatientNeedsCare() {

    }

    @Then("the department needs to know if there's an available bed")
    public void theDepartmentNeedsToKnowIfThereSAnAvailableBed() {
        assertTrue(d.available_beds());
    }
}
