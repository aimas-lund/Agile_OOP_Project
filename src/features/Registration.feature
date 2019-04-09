Feature: Registration
  This feature will handle all patient and staff registrations to the hospital

  Scenario: A patient is registered
    Given a new patient
    When the patient walks into the department
    Then clerk should register patient information

  Scenario: A clerk should be able to check if a patient is already registered
    Given an already registered patient
    When the patient goes to the hospital
    Then the clerk should be able to check if the patient is registered

  Scenario: A patient gets registered, and also gets a unique ID
    Given a new patient
    When the patient walks into the department
    Then the patient should get a unique ID

  Scenario: A new staff member should be registered normally and get a work email
    Given a newly hired employee
    When walking in to the ICT officer's office
    Then the ICT officer should register the staff information
    And register a uniqueID
    And work email should be generated

  Scenario: A new staff member's initials overlap
    Given a newly hired employee
    When he is assigned a work email
    And his initials overlap with someone else's
    Then take next letter in his name

  Scenario Outline: A doctor has a specialization
    Given a new Doctor
    When being registered as a staff member
    Then their specialization "<specialization>" should be specified along other information
    Examples:
      | specialization                     |
      | Dermatology                        |
      | Allergy & immunology               |
      | Anesthesiology                     |
      | Dermatology                        |
      | Diagnostic radiology               |
      | Emergency Medicine                 |
      | Family Medicine                    |
      | Internal Medicine                  |
      | Medical Genetics                   |
      | Neurology                          |
      | Nuclear Medicine                   |
      | Obstetrics and gynecology          |
      | Ophthalmology                      |
      | Pathology                          |
      | Pediatrics                         |
      | Physical medicine & rehabilitation |
      | Preventive medicine                |
      | Radiation oncology                 |
      | Surgery                            |
      | Urology                            |