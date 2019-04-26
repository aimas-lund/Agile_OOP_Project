Feature: Database
  Handles everything related to the database, not querying.

  Scenario: a user adds a patient to the database
  Given a user
  When a new patient is admitted to the hospital
  Then the user should add the patient to the database

  Scenario: a user adds a staff to the database
    Given a user
    When a new staff is hired to the hospital
    Then the user should add the staff to the database

  Scenario: a user should not be able to change a persons ID
    Given a user
    When changing a person's information
    Then the user should not be able to change the unique ID of that person


  Scenario: a user searches for patients by keywords
    Given a user
    When the user need specific information
    Then the user should be able to search by keywords or filters in the database.