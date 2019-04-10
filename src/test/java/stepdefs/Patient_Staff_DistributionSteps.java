package stepdefs;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import management.Department;
import management.Hospital;
import management.Patient;
import management.Staff;

import static org.junit.jupiter.api.Assertions.*;

public class Patient_Staff_DistributionSteps {

    Hospital h = new Hospital ();
    Staff s = new Staff ();
    Patient p = new Patient ();
    Department d1 = new Department ();
    Department d2 = new Department ();


    @When("their condition changes such that they need the attention of a different department")
    public void theirConditionChangesSuchThatTheyNeedTheAttentionOfADifferentDepartment() {
        // cannot add code
    }

    @Then("the hospital should move the patient to the appropriate new department")
    public void theHospitalShouldMoveThePatientToTheAppropriateNewDepartment() {
        h.move (p, d1, d2);
        assertFalse (d1.getPatients ().contains (p));
        assertTrue (d2.getPatients ().contains (p));
    }

    @When("their area of work changes")
    public void theirAreaOfWorkChanges() {
        // cannot add code
    }

    @Then("the hospital should be able to move them to another department.")
    public void theHospitalShouldBeAbleToMoveThemToAnotherDepartment() {
        h.move (s, d1, d2);
        assertFalse (d1.getStaff ().contains (s));
        assertTrue (d2.getStaff ().contains (s));
    }

    @Given("a staff member in an existing department")
    public void aStaffMemberInAnExistingDepartment() {
        h.assign (s, d1);
        assertTrue (d1.getStaff ().contains (s));
    }

    @Given("a patient in an existing department")
    public void aPatientInAnExistingDepartment() {
        h.assign (p, d1);
        assertTrue (d1.getPatients ().contains (p));
    }
}
