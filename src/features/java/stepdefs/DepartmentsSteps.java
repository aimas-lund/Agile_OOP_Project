package stepdefs;


import core.buildings.Bed;
import core.buildings.Department;
import core.buildings.OutDepartment;
import core.persons.Clerk;
import core.buildings.Hospital;
import core.persons.Patient;
import core.persons.Staff;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.ArrayList;

import static junit.framework.TestCase.*;


public class DepartmentsSteps {

    Hospital hospital = new Hospital();
    Department existingDept = new OutDepartment("adambeillinstavetforkert", "adam");
    Department newDept = new OutDepartment("adambellinogsåstavetforkert", "adamnt");
    Staff staff = new Clerk("staff1");
    Patient patient = new Patient("pat1");
    Bed bed = new Bed(1);

    @After
    public void tearDown() {
        hospital.remove(newDept);
        hospital.remove(existingDept);
    }

    @Then("the hospital should be able to assign the patient to a specific department.")
    public void theHospitalShouldBeAbleToAssignThePatientToASpecificDepartment() {
        hospital.add(existingDept);
        hospital.assign(patient, existingDept);
        assertTrue(existingDept.isPatientInDepartment(patient));
    }

    @When("there is a need to expand to include a new department")
    public void thereIsANeedToExpandToIncludeANewDepartment() {
        // cannot add code
    }

    @Then("the hospital should be able to create a new department")
    public void theHospitalShouldBeAbleToCreateANewDepartment() {
        hospital.add(newDept);
        ArrayList<Department> depts = hospital.getDepartments();
        assertTrue (depts.contains (newDept));
    }

    @When("there is no use for this department anymore")
    public void thereIsNoUseForThisDepartmentAnymore() {
        // cannot add code
    }

    @Then("the hospital should be able to remove the department")
    public void theHospitalShouldBeAbleToRemoveTheDepartment() {
        hospital.remove(existingDept);
        ArrayList<Department> depts = hospital.getDepartments();
        assertFalse (depts.contains (existingDept));
        
    }

    @Given("an empty bed")
    public void anEmptyBed() {
        assertFalse(bed.isOccupied());
    }

    @When("there is need for a bed")
    public void thereIsNeedForABed() {
        // cannot add code
    }


    @Given("a bed with a patient")
    public void aBedWithAPatient() {
        bed.fill(patient);
        assertNotNull(bed.getPatient());
    }

    @When("the patient no longer needs to be assigned the bed")
    public void thePatientNoLongerNeedsToBeAssignedTheBed() {
        // cannot add code
    }

    @Then("The bed should be unoccupied by the patient.")
    public void theBedShouldBeUnoccupiedByThePatient() {
        bed.empty();
        assertFalse(bed.isOccupied());
    }

    @Then("the bed should be assigned a patient.")
    public void theBedShouldBeAssignedAPatient() {
        bed.fill(patient);
        assertTrue(bed.isOccupied());
    }

    @Given("a new staff member")
    public void aNewStaffMember() {
        Staff s = new Clerk();
    }

    @When("a new staff gets a job at the hospital")
    public void aNewStaffGetsAJobAtTheHospital() {
        // cannot add code
    }

    @Then("the hospital should be able to assign them to the right department.")
    public void theHospitalShouldBeAbleToAssignThemToTheRightDepartment() {
        hospital.assign(staff, existingDept);
    }

    @Given("an existing department")
    public void anExistingDepartment() {
        hospital.add(existingDept);
        ArrayList<Department> depts = hospital.getDepartments();
        assertTrue (depts.contains (existingDept));
    }

    @Given("a nonexisting department")
    public void aNonexistingDepartment() {
        ArrayList<Department> depts = hospital.getDepartments();
        assertFalse (depts.contains (newDept));
    }
}
