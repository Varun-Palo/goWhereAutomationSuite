@benefitCalculator
Feature: As a user, I want you receive recommendations from Support For Your Calculator

  @AC1
  Scenario: AC 1 - Check calculation for 29 yrs old person having 3-room rented HDB flat
    Given User is on support calculator page
    Then User reads the important notice and start the assessment
    Then User provides his personal information
      | Fields               | Values                       |
      | Year of Birth        | 1994                         |
      | AssessableIncome     | Between $34,001 and $100,000 |
      | More than 1 property | No                           |
      | Property Ownership   | Rented from HDB              |
      | Housing Type         | 2-Room flat                  |
      | isAdditionalMembers  | No                           |
    And Validate the total estimate for the Individual and household benefits
      | Fields        | Values                             |
      | Individual    | $350                               |
      | Household     | $1,150                             |
      | TotalEstimate | $1,500 and 4 months of S&CC Rebate |


  @AC2
  Scenario: AC 2 - Check calculation for 28 yrs old person having 5-room house rented from Open Market
    Given User is on support calculator page
    Then User reads the important notice and start the assessment
    Then User provides his personal information
      | Fields                  | Values                       |
      | Year of Birth           | 1995                         |
      | AssessableIncome        | Above $100,000               |
      | More than 1 property    | Yes                          |
      | Property Ownership      | Rented from Open market      |
      | Housing Type            | 5-room flat                  |
      | isAdditionalMembers     | Yes                          |
      | Member Year of Birth    | 1996                         |
      | Member AssessableIncome | Between $34,001 and $100,000 |
    And Validate the total estimate for the Individual and household and member benefits
      | Fields        | Values |
      | Individual    | $200   |
      | Household     | $500   |
      | Member        | $350   |
      | TotalEstimate | $1,050 |

  @AC3-Negative
  Scenario: AC 3 - Check Web Element validation without any sending values
    Given User is on support calculator page
    Then User reads the important notice and start the assessment
    Then User provides no information and clicks the estimate button
    And Validate the error message "This is a required field." for each web elements

  @AC4
  Scenario: AC 4 - Check calculation for user less than 21 yrs old user
    Given User is on support calculator page
    Then User reads the important notice and start the assessment
    Then User provides his personal information
      | Fields               | Values                          |
      | Year of Birth        | 2010                            |
      | AssessableIncome     | NA                              |
      | More than 1 property | No                              |
      | Property Ownership   | Owned by me or household member |
      | Housing Type         | 3-room flat                     |
      | isAdditionalMembers  | No                              |
    And Validate the total estimate for the Individual and household benefits
      | Fields        | Values                             |
      | Individual    | $150                               |
      | Household     | $1,090                             |
      | TotalEstimate | $1,240 and 3 months of S&CC Rebate |

  @AC5-Edge-Case
  Scenario: AC 5 - Check calculation for user having 10 members
    Given User is on support calculator page
    Then User reads the important notice and start the assessment
    Then User provides his personal information and 10 other member details
      | Fields                  | Values                       |
      | Year of Birth           | 1995                         |
      | AssessableIncome        | Above $100,000               |
      | More than 1 property    | Yes                          |
      | Property Ownership      | Rented from Open market      |
      | Housing Type            | Private property             |
      | Estimated Annual Value  | Between $21,001 and $25,000  |
      | isAdditionalMembers     | Yes                          |
      | Member Year of Birth    | 1996                         |
      | Member AssessableIncome | Between $34,001 and $100,000 |
    Then Validate the total estimate for the Individual and household and 10 member benefits
      | Fields        | Values |
      | Individual    | $200   |
      | Household     | $500   |
      | Member        | $350   |
      | TotalEstimate | $4,200 |