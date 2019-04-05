Feature: Registration
  This feature will handle all patient and staff registrations to the hospital

  Scenario: A patient is registered
    Given a new patient
    When the patient walks into the department
    Then clerk should register patient information
