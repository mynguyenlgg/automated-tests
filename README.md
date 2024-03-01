# RestAssuredTestNGFramework
REST Assured API test automation framework using Java + Maven + TestNG + Allure report

Technologies/Tools used in building the framework
=================================================
- Rest Assured
- TestNG
- Java
- Allure Reports
- Hamcrest
- Jackson API
- Lombok
- IntelliJ
- GitHub
- Jenkins

# Run Tests
```
mvn clean test -Dsuitefile=src/test/resources/suite.testng.xml
```

# Generate Allure Report
```
allure generate --single-file target/allure-results --clean
```
The report will be generated in allure-report folder with name is index.html file
