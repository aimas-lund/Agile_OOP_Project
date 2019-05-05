Feature: Admission
  This feature describes the admission of patients to the hospital and each departments functionality


  Scenario: A doctor calls a waiting patient
    Given a doctor
    When there is waiting patients
    Then he should call a patient

  Scenario: A nurse assigns a patient to the waiting room
    Given an out department
    And a nurse
    And a clerk
    And a patient
    When the clerk registers the patient to the system
    Then the nurse should assign the patient to the waiting room