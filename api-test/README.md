# RestAssuredTestNGFramework
REST Assured API test automation framework using Java + Maven + TestNG + Allure report

Technologies/Tools used in building the framework
=================================================
- Java 11
- Rest Assured
- Maven
- TestNG
- Allure Reports

# Run API Tests
```
cd api-test/
mvn clean test -DsuiteFile="src/test/resources/suites/regression.xml"
```

# Clean locally existing allure-results and allure-report before running tests
```
rm -rf allure-* && mvn clean test -DsuiteFile="src/test/resources/suites/regression.xml"
```

# Generate Allure Report
```
allure generate --single-file allure-results --clean
```
if the allure command does not work locally, please use another way with Node Package execute:
```
npx allure-commandline generate --single-file allure-results --clean
```

The report will be generated in allure-report folder with name is index.html file

![Screenshot](allure-report.png)
