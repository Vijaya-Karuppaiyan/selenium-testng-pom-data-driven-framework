# Data-Driven Selenium Automation Framework using TestNG, POM, and Apache POI
## Scalable Data-Driven Selenium Automation Framework Using TestNG and POM

## 📌 Project Overview
This project is a **Data-Driven Selenium Automation Framework** built using **Java, Selenium WebDriver, TestNG, and Page Object Model (POM)**.  
It supports executing automated test cases using test data from **Excel files** with the help of **Apache POI**, ensuring reusability, scalability, and maintainability.

The framework is designed following **industry best practices** and is suitable for **real-time automation projects**.

---

## 🛠️ Tools & Technologies Used
- **Java (JDK 11)**
- **Selenium WebDriver 4**
- **TestNG**
- **Apache POI (Excel handling)**
- **Maven**
- **WebDriverManager**
- **Eclipse IDE**
- **Git & GitHub**

---

## 🧱 Framework Design
- **Page Object Model (POM)** for UI interaction
- **Data-Driven Testing** using Excel files
- **TestNG DataProvider** for parameterized test execution
- **BaseTest** for common setup and teardown
- **Utility classes** for configuration and Excel handling

---

## 📂 Project Structure
Data-DrivenSeleniumAutomationFrameworkusingTestNGandPOM
│
├── src/main/java
│ ├── com.saucedemo.base
│ │ └── BaseTest.java
│ ├── com.saucedemo.factory
│ │ └── DriverFactory.java
│ ├── com.saucedemo.pages
│ │ └── LoginPage.java
│ └── com.saucedemo.utils
│ ├── ConfigReader.java
│ └── ExcelUtil.java
│
├── src/main/resources
│ └── config.properties
│
├── src/test/java
│ └── com.saucedemo.testcases
│ └── LoginTest.java
│
├── src/test/resources
│ └── testdata
│ └── LoginData.xlsx
│
├── testng.xml
├── pom.xml
├── .gitignore
└── README.md

---

## 📊 Excel Test Data Format
**Sheet Name:** `LoginTestData`

| TestCaseID | Email | Password | ExpectedResult |
|-----------|-------|----------|----------------|
| TC_01 | standard_user | secret_sauce | Success |
| TC_02 | locked_out_user | secret_sauce | Fail |
| TC_03 | problem_user | wrong_pwd | Fail |

---
## Key Features
- **Data-driven execution using Excel**
- **Page Object Model implementation**
- **Reusable WebDriver setup**
- **Easy maintenance and scalability**
- **Supports negative and positive test scenarios**
- **Git-friendly with .gitignore**
## Challenges & Solutions
- **Excel temporary file Git error → Solved using .gitignore**
- **Sheet not found issue → Ensured exact sheet name matching**
- **Code duplication → Solved using BaseTest and utility classes**
- **Locator maintenance → Solved using POM design**
## Future Enhancements
- **Cross-browser testing support**
- **Parallel execution using TestNG**
- **Extent / Allure Reporting**
- **Jenkins / CI-CD integration**
- **Cucumber (BDD) integration**
- **Database-driven testing**
## Author
- **Vijaya K**
- **Senior IT Trainer | Automation Testing | Selenium | TestNG | Java**
## License
- **This project is created for learning, training, and demonstration purposes.**
