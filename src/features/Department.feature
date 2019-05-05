Feature: departments
  This feature will manage the departments

  Scenario: A department is made
    Given a department
    When the department is made
    Then the department needs to know how many patients they can host

  Scenario: Add staff
    Given a department
    When I employ a staff
    Then I should add them to my system, such that I can easily look them up

  Scenario: Add patient
    Given a department
    When receiving a patient
    Then I should be able to add them to my system, such that I can easily look them up

  Scenario: Remove staff
    Given a department
    Then I should be able to remove them from my system

  Scenario: Remove patient
    Given a department
    When a patient's treatment is over
    Then I should be able to discharge them

  Scenario: Assign patient to available bed
    Given a department
    When receiving a patient
    Then I should assign them to an available bed

  Scenario: Assign patient to specific bed
    Given a department
    When receiving a patient
    Then I want to assign them to a specific bed

  Scenario: Move patient between beds
    Given a department
    When a patient needs to be relocated
    Then I should be able to move them to another available bed

  Scenario: Move patient to specific bed
    Given a department
    When a patient needs to be relocated
    Then I should be able to move them to a specific bed

  Scenario: Remove patient from bed
    Given a department
    When a patient doesnt need a bed anymore
    Then I should be able to remove them from the bed

  Scenario: Get name and capacity of department
    Given a department
    When looking at the department
    Then you should be able to retrieve name and capacity

  Scenario: Get number of available beds
    Given a department
    When I want an overview of the department
    Then I should be able to retrieve how many available beds there's left

  Scenario: Get staff list
    Given a department
    When you need to know the departments staff
    Then you should be able to get the staff list

  Scenario: Get patient list
    Given a department
    When you need to know patients under that department
    Then you should be able to get the patient list

  Scenario: Get patient's bed
    Given a department
    When looking up a patient
    And the patient is in a bed
    Then I should be able to find which bed the patient is in

  Scenario: Check if available bed
    Given a department
    When receiving a patient
    Then I should know if there's an available bed

  Scenario: Get an available beds
    Given a department
    When receiving a patient
    And there's available beds
    Then I should get an available bed

  Scenario: Patient in bed
    Given a department
    When looking up a patient
    Then I should know if the patient is assigned to a bed
