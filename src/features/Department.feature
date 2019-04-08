Feature: departments
  This feature will manage the departments

  Scenario: A department is made
    Given a department
    When the department is made
    Then the department needs to know how many patients they can host

  Scenario: Available beds
    Given a department
    When a patient needs care
    Then the department needs to know if there's an available bed

  Scenario: Remove staff
    Given a department
    When a staff no longer works here
    Then I should be able to remove them from my system

  Scenario: Add staff
    Given a department
    When I employ a staff
    Then I should add them to my system, such that I can easily look them up

  Scenario: Assign patient to bed
    Given a department
    When receiving a patient
    Then I want to assign them to a specific bed, such that all patients are accounted for

  Scenario: Discharge patient
    Given a department
    When a patient's treatment is over
    Then I should be able to discharge them, thus removing my responsibility

  Scenario: Moving patients
    Given a department
    When a patient needs to be moved to another department
    Then I should be able to remove the patient from my system

  Scenario: Move patient between beds
    Given a department
    When a patient needs to be relocated
    Then I should be able to move them between beds