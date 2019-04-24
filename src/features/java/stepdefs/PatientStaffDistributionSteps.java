package stepdefs;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import management.Department;
import management.Hospital;
import management.Patient;
import management.Staff;

import static org.junit.Assert.*;

public class PatientStaffDistributionSteps {

    private Hospital hospital = new Hospital ();
    private Staff staff = new Staff ();
    private Patient patient = new Patient ();
    private Department department1 = new Department ("ER", 10);
    private Department department2 = new Department ("New", 5);


    @When("their condition changes such that they need the attention of a different department")
    public void theirConditionChangesSuchThatTheyNeedTheAttentionOfADifferentDepartment() {
        // cannot add code
    }

    @Then("the hospital should move the patient to the appropriate new department")
    public void theHospitalShouldMoveThePatientToTheAppropriateNewDepartment() {
        hospital.move (patient, department1, department2);
        assertFalse (department1.getPatients ().contains (patient));
        assertTrue (department2.getPatients ().contains (patient));
    }

    @When("their area of work changes")
    public void theirAreaOfWorkChanges() {
        // cannot add code
    }

    @Then("the hospital should be able to move them to another department.")
    public void theHospitalShouldBeAbleToMoveThemToAnotherDepartment() {
        hospital.add(department2);
        hospital.move (staff, department1, department2);
        assertFalse (department1.getStaff ().contains (staff));
        assertTrue (department2.getStaff ().contains (staff));
    }

    @Given("a staff member in an existing department")
    public void aStaffMemberInAnExistingDepartment() {
        hospital.add(department1);
        hospital.assign(staff, department1);
        assertTrue(department1.getStaff ().contains (staff));
    }

    @Given("a patient in an existing department")
    public void aPatientInAnExistingDepartment() {
        hospital.add(department1);
        hospital.assign (patient, department1);
        assertTrue (department1.getPatients ().contains (patient));
    }
}
