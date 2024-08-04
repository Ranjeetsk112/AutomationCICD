
@tag
Feature: Purchage the order from Ecommerce Website
  I want to use this template for my feature file
Background:
Given I landed on Ecommerce page

 @tag2
  Scenario Outline: Positive test of submitting the order
    Given Logged in with username <name> and password <password>
    When I add product <productName> to cart
    And Checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is display on ConfirmationPage

    Examples: 
      | name                     | password       | productName | 
      | ranjeetks123@gmail.com   | Ranjeet@123    | ZARA COAT 3 |
      
