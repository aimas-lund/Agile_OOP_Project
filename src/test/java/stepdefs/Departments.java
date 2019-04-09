package stepdefs;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import exceptions.exceededCapacityException;
import management.Bed;
import management.Department;
import management.Patient;
import management.Staff;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class Departments {
    Department d = new Department("ER",80);
    Patient p;
    Staff s;
    Bed b;
    @Given("a department")
    public void aDepartment() {
        assertTrue(d instanceof Department);
    }
    //[7] Capacity

    @When("the department is made")
    public void theDepartmentIsMade() {
    }

    @Then("the department needs to know how many patients they can host")
    public void theDepartmentNeedsToKnowHowManyPatientsTheyCanHost() {
        assertTrue(d.getCapacity() > 0);
    }


    // [8] Available beds

    @When("a patient needs care")
    public void aPatientNeedsCare() {

    }

    @Then("the department needs to know if there's an available bed")
    public void theDepartmentNeedsToKnowIfThereSAnAvailableBed() {
        assertTrue(d.available_beds());
    }

    // [9] Remove staff
    @When("a staff no longer works here")
    public void aStaffNoLongerWorksHere() {

    }

    @Then("I should be able to remove them from my system")
    public void iShouldBeAbleToRemoveThemFromMySystem() {
        s = new Staff();
        d = new Department("test",10);
        ArrayList staff = d.getStaff();
        d.add(s);
        assertTrue(staff.contains(s));
        d.remove(s);
        assertFalse(staff.contains(s));
    }

    // [9] Add staff
    @When("I employ a staff")
    public void iEmployAStaff() {
        s = new Staff();
    }

    @Then("I should add them to my system, such that I can easily look them up")
    public void iShouldAddThemToMySystemSuchThatICanEasilyLookThemUp() {
        d.add(s);
        ArrayList staff = d.getStaff();
        assertTrue(staff.contains(s));
    }

    // [10] Assign to bed
    @When("receiving a patient")
    public void receivingAPatient() {
        p = new Patient();
    }

    @Then("I want to assign them to a specific bed, such that all patients are accounted for")
    public void iWantToAssignThemToASpecificBedSuchThatAllPatientsAreAccountedFor() {
        d.assign(p,1);
        assertTrue(d.getBeds()[1].occupied());
    }
    // [10] Assign any bed
    @Then("I should check for available beds and assign them to one if there is room")
    public void iShouldCheckForAvailableBedsAndAssignThemToOneIfThereIsRoom() throws exceededCapacityException {
        assertTrue(d.available_beds());
        d.assign(p);
        for (Bed bed : d.getBeds()) {
            if (bed.getPatient() == p) assertTrue(bed.getPatient() == p);
        }
    }

    // [11] Discharge patients
    @When("a patient's treatment is over")
    public void aPatientSTreatmentIsOver() {
        p = new Patient();
    }

    @Then("I should be able to discharge them, thus removing my responsibility")
    public void iShouldBeAbleToDischargeThemThusRemovingMyResponsibility() {
        d.add(p);
        ArrayList patients = d.getPatients();
        assertTrue(patients.contains(p));
        d.remove(p);
        assertFalse(patients.contains(p));
    }

    // [11] Move patients
    @When("a patient needs to be moved to another department")
    public void aPatientNeedsToBeMovedToAnotherDepartment() {
        p = new Patient();
    }

    @Then("I should be able to remove the patient from my system")
    public void iShouldBeAbleToRemoveThePatientFromMySystem() {
        d.add(p);
        ArrayList patients = d.getPatients();
        assertTrue(patients.contains(p));
        d.remove(p);
        assertFalse(patients.contains(p));
    }

    // [12] Move patient between beds
    @When("a patient needs to be relocated")
    public void aPatientNeedsToBeRelocated() {
        p = new Patient();
        d.assign(p,1);
        assertTrue(d.getBeds()[1].getPatient() == p);

    }

    @Then("I should be able to move them between beds")
    public void iShouldBeAbleToMoveThemBetweenBeds() {
        assertEquals(b.getPatient(),p);
        d.move(p,1);
        assertEquals(d.getBeds()[1].getPatient(),p);
        assertFalse(b.occupied());
    }
}

