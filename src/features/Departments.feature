
Feature: Patient_Staff_Distribution
  Patient, Staff, and Department management, beds in use.


  Scenario: Occupied bed to be emptied
    Given a bed with a patient
    When the patient no longer needs to be assigned the bed
    Then The bed should be unoccupied by the patient.

  Scenario: Empty bed to be occupied
    Given an empty bed
    When there is need for a bed
    Then the bed should be assigned a patient.

  Scenario: An obsolete department
    Given an existing department
    When there is no use for this department anymore
    Then the hospital should be able to remove the department

  Scenario: A new department
    Given a nonexisting department
    When there is a need to expand to include a new department
    Then the hospital should be able to create a new department

  Scenario: A patient is registered
    Given a new patient
    When the patient walks into the department
    Then the hospital should be able to assign the patient to a specific department.

  Scenario: A new staff
    Given a new staff member
    When a new staff gets a job at the hospital
    Then the hospital should be able to assign them to the right department.

