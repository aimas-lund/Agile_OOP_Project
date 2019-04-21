Feature: Database
  Handles everything related to the database, not querying.

  Scenario: A hospital is connected to the database
    Given a hospital
    And the hospital does not have connection to the database
    When a hospital is instantiated
    Then a hospital is connected to the database