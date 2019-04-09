Feature: ChangeInformation
  This feature covers the change of patient and staff information

  Scenario: An ICT officer changes person information
    Given an ICT officer
    And a person with information
    When he gets new PI about a person
    Then he should change the information

  Scenario: A clerk changes person information
    Given a clerk
    And a person with information
    When the information is wrong
    Then the clerk should be able to change this information


