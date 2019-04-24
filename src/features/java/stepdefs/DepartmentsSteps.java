package stepdefs;


import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import management.*;

import java.util.ArrayList;

import static junit.framework.TestCase.*;


public class DepartmentsSteps {

    Hospital h = new Hospital ();
    Department existingDept = new Department ();
    Department newDept = new Department ();
    Staff s = new Staff ();
    Patient p = new Patient ();
    Bed b = new Bed (1);

    @Then("the hospital should be able to assign the patient to a specific department.")
    public void theHospitalShouldBeAbleToAssignThePatientToASpecificDepartment() {
        h.add(existingDept);
        h.assign (p, existingDept);
        assertTrue(existingDept.getPatients().contains(p));
    }

    @When("there is a need to expand to include a new department")
    public void thereIsANeedToExpandToIncludeANewDepartment() {
        // cannot add code
    }

    @Then("the hospital should be able to create a new department")
    public void theHospitalShouldBeAbleToCreateANewDepartment() {
        h.add (newDept);
        ArrayList<Department> depts = h.getDepartments();
        assertTrue (depts.contains (newDept));
    }

    @When("there is no use for this department anymore")
    public void thereIsNoUseForThisDepartmentAnymore() {
        // cannot add code
    }

    @Then("the hospital should be able to remove the department")
    public void theHospitalShouldBeAbleToRemoveTheDepartment() {
        h.remove (existingDept);
        ArrayList<Department> depts = h.getDepartments();
        assertFalse (depts.contains (existingDept));
        
    }

    @Given("an empty bed")
    public void anEmptyBed() {
        assertFalse (b.isOccupied());
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
        assertFalse (b.isOccupied ());
    }

    @Then("the bed should be assigned a patient.")
    public void theBedShouldBeAssignedAPatient() {
        b.fill (p);
        assertTrue (b.isOccupied ());
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
        ArrayList<Department> depts = h.getDepartments();
        assertTrue (depts.contains (existingDept));
    }

    @Given("a nonexisting department")
    public void aNonexistingDepartment() {
        ArrayList<Department> depts = h.getDepartments();
        assertFalse (depts.contains (newDept));
    }
}
