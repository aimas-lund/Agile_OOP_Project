Feature: Database
  Handles everything related to the database, not querying.

  Scenario: a user adds a patient to the database
  Given a user
  When a new patient is admitted to the hospital
  Then the user should add the patient to the database
