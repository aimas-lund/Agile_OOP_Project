package stepdefs;

import core.buildings.Department;
import core.buildings.InDepartment;
import core.persons.Clerk;
import core.persons.Hospital;
import core.persons.Patient;
import core.persons.Staff;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PatientStaffDistributionSteps {

    private Hospital hospital = new Hospital();
    private Staff staff = new Clerk("clerkyboiiiiveryunique");
    private Patient patient = new Patient("pat1");
    private Department department1 = new InDepartment("dep1", "ER", 10);
    private Department department2 = new InDepartment("dep2", "New", 5);

    @Before
    public void setUp() {
        hospital.add(department1);
        hospital.add(department2);
    }

    @After
    public void tearDown() {
        hospital.remove(department1);
        hospital.remove(department2);
    }

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
        hospital.move (staff, department1, department2);
        assertFalse (department1.getStaff ().contains (staff));
        assertTrue (department2.getStaff ().contains (staff));
    }

    @Given("a staff member in an existing department")
    public void aStaffMemberInAnExistingDepartment() {
        hospital.assign(staff, department1);
        assertTrue(department1.getStaff ().contains (staff));
    }

    @Given("a patient in an existing department")
    public void aPatientInAnExistingDepartment() {
        hospital.assign (patient, department1);
        assertTrue (department1.getPatients ().contains (patient));
    }
}
