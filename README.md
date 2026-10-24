# Selenium Slider & Progress Bar Testing

## Project Overview
This project automates the testing of slider controls and progress bar functionality using Selenium WebDriver, Java, and TestNG. Maven manages the project dependencies and test execution, while Jenkins runs the tests as part of a CI workflow.

## Technologies Used
- Java 21
- Selenium WebDriver 4.38.0
- TestNG 7.11.0
- Apache Maven 3.10.0
- Jenkins
- Git and GitHub

## Automated Test Cases
1. Verify the slider minimum value is 0.
2. Verify the slider maximum value is 100.
3. Verify the slider middle value is 50.
4. Verify the progress bar reaches 100%.
5. Verify the progress bar can be stopped.

## Project Structure
```text
SeleniumSliderTesting/
├── src/
│   └── test/
│       └── java/
│           └── tests/
│               └── SliderProgressBarTest.java
├── testng.xml
├── pom.xml
└── README.md
```

## How to Run
### Prerequisites
Install Java JDK, Apache Maven, and Google Chrome.

### Execute the tests
Open a terminal in the project directory and run:

```bash
mvn clean test
```

## Test Results
The five automated test cases passed in the verified Jenkins build. Jenkins also publishes an HTML TestNG report.

## Continuous Integration
The project is integrated with Jenkins to retrieve source code from GitHub, execute Maven tests, and publish the test report.

## Repository
GitHub: https://github.com/druvi1609/Selenium-Slider-ProgressBar-Testing
