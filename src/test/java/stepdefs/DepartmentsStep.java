package stepdefs;


import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.jupiter.api.Assertions.*;

import management.*;


public class DepartmentsStep {

    Hospital h = new Hospital ();
    Department existingDept = new Department ();
    Department newDept = new Department ();
    Staff s = new Staff ();
    Patient p = new Patient ();
    Bed b = new Bed (1);

    @Then("the hospital should be able to assign the patient to a specific department.")
    public void theHospitalShouldBeAbleToAssignThePatientToASpecificDepartment() {
        h.assign (p, existingDept);
        existingDept.getPatients ().contains (p);
    }

    @When("there is a need to expand to include a new department")
    public void thereIsANeedToExpandToIncludeANewDepartment() {
        // cannot add code
    }

    @Then("the hospital should be able to create a new department")
    public void theHospitalShouldBeAbleToCreateANewDepartment() {
        h.add (newDept);
        assertTrue (h.depts.contains (newDept));
    }

    @When("there is no use for this department anymore")
    public void thereIsNoUseForThisDepartmentAnymore() {
        // cannot add code
    }

    @Then("the hospital should be able to remove the department")
    public void theHospitalShouldBeAbleToRemoveTheDepartment() {
        h.remove (existingDept);
        assertFalse (h.depts.contains (existingDept));
        
    }

    @Given("an empty bed")
    public void anEmptyBed() {
        assertFalse (b.isoccupied ());
    }

    @When("there is need for a bed")
    public void thereIsNeedForABed() {
        // cannot add code
    }


    @Given("a bed with a patient")
    public void aBedWithAPatient() {
        b.fill (p);
        assertNotNull (b.getPatient ());
    }

    @When("the patient no longer needs to be assigned the bed")
    public void thePatientNoLongerNeedsToBeAssignedTheBed() {
        // cannot add code
    }

    @Then("The bed should be unoccupied by the patient.")
    public void theBedShouldBeUnoccupiedByThePatient() {
        b.empty ();
        assertFalse (b.isoccupied ());
    }

    @Then("the bed should be assigned a patient.")
    public void theBedShouldBeAssignedAPatient() {
        b.fill (p);
        assertTrue (b.isoccupied ());
    }

    @Given("a new staff member")
    public void aNewStaffMember() {
        Staff s = new Staff ();
    }

    @When("a new staff gets a job at the hospital")
    public void aNewStaffGetsAJobAtTheHospital() {
        // cannot add code
    }

    @Then("the hospital should be able to assign them to the right department.")
    public void theHospitalShouldBeAbleToAssignThemToTheRightDepartment() {
        h.assign (s, existingDept);
    }

    @Given("an existing department")
    public void anExistingDepartment() {
        h.add (existingDept);
        assertTrue (h.depts.contains (existingDept));
    }

    @Given("a nonexisting department")
    public void aNonexistingDepartment() {
        assertFalse (h.depts.contains (newDept));
    }
}
