# GoWhere Digital Service- Support Calculator Automation

# Overview
This project is a sample implementation of a Selenium WebDriver automation framework using Java, Cucumber, and the Page Object Model design pattern. It is designed to facilitate easy creation and maintenance of automated test scripts for web applications.

# Features
Selenium WebDriver: Utilizes Selenium WebDriver for browser automation.
Cucumber: Implements behavior-driven development (BDD) using Cucumber for test scenario representation.
Page Object Model (POM): Follows the POM design pattern for organizing and maintaining web elements and actions.

# Prerequisites
Before running the tests, make sure you have the following installed:
Java JDK 8 or later
WebDriver (ChromeDriver)
Cucumber

# Getting Started
* **Download the Framework:**
Obtain the .zip file containing the Selenium Java Cucumber framework.

* **Extract the Framework:**
Extract the contents of the .zip file to a directory of your choice.

* **Project Structure:**
Review the project structure. It typically includes directories for source code, resources, test cases, and configuration files.

* **Open Project in an IDE:**
Import the project into your preferred Integrated Development Environment (IDE) like IntelliJ IDEA, Eclipse, or Visual Studio Code.

* **Review Configuration Files:**
Check for configuration files such as pom.xml (Maven configuration) and any other configuration files specific to the framework.

* **Run the Tests:**
Execute a sample test to ensure that everything is set up correctly. You can run tests using the following Maven command: mvn test
Right click on TestRunner.java Run as -> mvn test

* **Writing Tests**
Add feature files in the src/test/resources/features directory.
Implement step definitions in the com.example.framework.stepdefinitions package.
Create page classes in the com.example.framework.pages package using the Page Object Model.

* **Reporting**
Cucumber HTML reports generates test reports in the target/cucumber-html-reports directory.