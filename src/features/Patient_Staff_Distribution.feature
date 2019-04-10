Feature: Patient_Staff_Distribution
  This feature will handle all patient and staff movement between departments and discharges

  Scenario: A staff moves departments
    Given a staff member in an existing department
    When their area of work changes
    Then the hospital should be able to move them to another department.

  Scenario: A patient moves departments
    Given a patient in an existing department
    When their condition changes such that they need the attention of a different department
    Then the hospital should move the patient to the appropriate new department
