Feature: ChangeInformation
  This feature covers the change of patient and staff information

  Scenario: An ICT officer changes person information
    Given an ICT officer
    And a person with information
    When he gets new PI about a person
    Then he should change the information
