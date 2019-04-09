Feature: Patient_Staff_Distribution
  This feature will handle all patient and staff movement between departments and discharges

  Scenario: Staff

  Scenario: A patient moves departments
    Given a patient
    When their condition changes such that they need the attention of a different department
    Then the hospital should move the patient to the appropriate new department

