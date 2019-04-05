Feature: Registration
  This feature handles all registration of both staff and patients

  Scenario: Register a new person
    Given A patient
    And the patient is not already registered
    When he walks in the reception
    Then the clerk should register the patient