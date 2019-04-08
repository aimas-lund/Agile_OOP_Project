Feature: departments
  This feature will manage the departments

  Scenario: A department is made
    Given a new department
    When the department is made
    Then the department needs to know how many patients they can host

  Scenario: Available beds
    Given a department
    When a patient needs care
    Then the department needs to know if there's an available bed