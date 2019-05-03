package stepdefs;

import core.buildings.InDepartment;
import core.persons.Patient;
import core.persons.Staff;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import exceptions.ExceededCapacityException;
import exceptions.UnavailableBedException;

import static org.junit.Assert.*;

public class DepartmentSteps {
    private InDepartment validDept = new InDepartment("dep1", "ER", 80);
    private InDepartment emptyDept = new InDepartment("dep2", "empty", 0);
    private Patient patient1 = new Patient("patient1");
    private Patient patient2 = new Patient("patient2");
    private Staff staff = new Staff("staff1");

    // A department is made (capacity)
    @Given("a department")
    public void aDepartment() {
        assertNotNull(validDept);
    }
    @When("the department is made")
    public void theDepartmentIsMade() {
    }
    @Then("the department needs to know how many patients they can host")
    public void theDepartmentNeedsToKnowHowManyPatientsTheyCanHost() {
        assertEquals(validDept.getCurrentCapacity(), 80);
    }

    // Add staff
    @When("I employ a staff")
    public void iEmployAStaff() {
        validDept.add(staff);
    }
    @Then("I should add them to my system, such that I can easily look them up")
    public void iShouldAddThemToMySystemSuchThatICanEasilyLookThemUp() {
        assertTrue(validDept.getStaff().contains(staff));
    }

    // Add patient
    @When("receiving a patient")
    public void receivingAPatient() {
    }
    @Then("I should be able to add them to my system, such that I can easily look them up")
    public void iShouldBeAbleToAddThemToMySystemSuchThatICanEasilyLookThemUp() {
        validDept.add(patient1);
        assertTrue(validDept.getPatients().contains(patient1));
    }

    // Remove staff
    @When("a staff no longer works here")
    public void aStaffNoLongerWorksHere() {
    }
    @Then("I should be able to remove them from my system")
    public void iShouldBeAbleToRemoveThemFromMySystem() {
        validDept.remove(staff);
        assertFalse(validDept.getStaff().contains(staff));
    }

    // Remove patient
    @When("a patient's treatment is over")
    public void aPatientSTreatmentIsOver() {
    }
    @Then("I should be able to discharge them")
    public void iShouldBeAbleToDischargeThem() {
        validDept.remove(patient1);
        assertFalse(validDept.getPatients().contains(patient1));
    }

    // Assign patient to available bed
    @Then("I should assign them to an available bed")
    public void iShouldAssignThemToAnAvailableBed() throws ExceededCapacityException {
        validDept.assign(patient1);
        assertTrue(validDept.patientInBed(patient1));
    }

    // Assign patient to specific bed
    @Then("I want to assign them to a specific bed")
    public void iWantToAssignThemToASpecificBed() throws UnavailableBedException {
        validDept.assign(patient1,69);
        assertEquals(validDept.getBeds()[69].getPatient(),patient1);
    }

    // Move patient between beds
    @When("a patient needs to be relocated")
    public void aPatientNeedsToBeRelocated() {
    }
    @Then("I should be able to move them to another available bed")
    public void iShouldBeAbleToMoveThemToAnotherAvailableBed() throws ExceededCapacityException {
        validDept.move(patient2);
        assertEquals(validDept.getBedWithPatient(patient2).getPatient(), patient2);
    }

    // Move patient to specific bed
    @Then("I should be able to move them to a specific bed")
    public void iShouldBeAbleToMoveThemToASpecificBed() throws UnavailableBedException {
        validDept.move(patient2,70);
        assertEquals((validDept.getBeds())[70].getPatient(),patient2);
    }

    // Remove patient from bed
    @When("a patient doesnt need a bed anymore")
    public void aPatientDoesntNeedABedAnymore() {
    }
    @Then("I should be able to remove them from the bed")
    public void iShouldBeAbleToRemoveThemFromTheBed() throws ExceededCapacityException {
        validDept.assign(patient1);
        validDept.removeFromBed(patient1);
        assertFalse(validDept.patientInBed(patient1));
    }

    // Get name and capacity of department
    @When("looking at the department")
    public void lookingAtTheDepartment() {
    }
    @Then("you should be able to retrieve name and capacity")
    public void youShouldBeAbleToRetrieveNameAndCapacity() {
        assertEquals(validDept.getName(),"ER");
        assertEquals(validDept.getCurrentCapacity(), 80);
        assertNotEquals(emptyDept.getName(),"wrong");
    }

    // Get number of available beds
    @When("I want an overview of the department")
    public void iWantAnOverviewOfTheDepartment() {
    }
    @Then("I should be able to retrieve how many available beds there's left")
    public void iShouldBeAbleToRetrieveHowManyAvailableBedsThereSLeft() {
        assertEquals(emptyDept.getCurrentCapacity(), 0);
        validDept.removeFromBed(patient1);
        validDept.removeFromBed(patient2);
        assertEquals(validDept.getCurrentCapacity(), 80);
    }

    // Get staff list
    @When("you need to know the departments staff")
    public void youNeedToKnowTheDepartmentsStaff() {
    }
    @Then("you should be able to get the staff list")
    public void youShouldBeAbleToGetTheStaffList() {
        assertNotNull(validDept.getStaff());
    }

    // Get patient list
    @When("you need to know patients under that department")
    public void youNeedToKnowPatientsUnderThatDepartment() {
    }
    @Then("you should be able to get the patient list")
    public void youShouldBeAbleToGetThePatientList() {
        assertNotNull(validDept.getPatients());
    }

    // Get patient's bed
    @And("the patient is in a bed")
    public void thePatientIsInABed() throws UnavailableBedException {
        validDept.assign(patient2,0);
        assertTrue(validDept.patientInBed(patient2));
        assertEquals(validDept.getBedWithPatient(patient2), validDept.getBeds()[0]);
    }
    @Then("I should be able to find which bed the patient is in")
    public void iShouldBeAbleToFindWhichBedThePatientIsIn() {
    }

    // Check if available beds
    @Then("I should know if there's an available bed")
    public void iShouldKnowIfThereSAnAvailableBed() {
        assertTrue(validDept.hasAvailableBeds());
        assertFalse(emptyDept.hasAvailableBeds());

    }

    // Get an available bed
    @And("there's available beds")
    public void thereSAvailableBeds() {
    }
    @Then("I should get an available bed")
    public void iShouldGetAnAvailableBed() throws ExceededCapacityException {
        assertNotNull(validDept.getAvailableBeds());
    }

    // Patient in bed
    @When("looking up a patient")
    public void lookingUpAPatient() {
        
    }
    @Then("I should know if the patient is assigned to a bed")
    public void iShouldKnowIfThePatientIsAssignedToABed() throws ExceededCapacityException {
        validDept.assign(patient1);
        validDept.removeFromBed(patient2);
        assertTrue(validDept.patientInBed(patient1));
        assertFalse(validDept.patientInBed(patient2));
    }
}

