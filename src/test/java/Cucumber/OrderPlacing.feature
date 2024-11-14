
@tag
Feature: Placing an Order for Ecommerece Website
  I want to use this template for my feature file

Background: I Landed on Ecommerce Application

  @tag2
  Scenario Outline: Test of Submitting Order
    Given Logged in with username <username> and password <password>
    When I add product <productname> to cart
    And checkout product and place the order
    Then "THANK YOU FOR THE ORDER." message is displayed on confirmation page

    Examples: 
      | username  									| 		password 			|			productname			|
      | techworld7982@gmail.com 		|     Jinjin@123 		| 		IPHONE 13 PRO		|

