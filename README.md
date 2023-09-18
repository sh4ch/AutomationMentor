# AutomationMentor

On my project there are two Automation Engineers.
We work according to Scrum, developing new features for the People Managing application.
Each sprint is two weeks
Automation is applied for:
* new features we implement (creating new Tests),
* run regression for the App's area that was affected by the new feature,
* support Jenkins jobs for Development/Testing Environment.

Tech stack: Java, TestNG, Jenkins, ALM

## Selenium 01
**Task:** Create maven project, write simple tests for calculator-1.0.jar (at least 4 operations)

**Prompts for maven**
```
mvn clean test -P all-calculator
mvn clean test -P multsum-calculator
mvn clean test -P pow-calculator
```

## Selenium 02
**Task:**  Implement tests for the given test cases. Work with web locators, test ng, maven.

**Prompts for maven**
```
mvn clean test -P all-exercises-hw2
mvn clean test -P exercise1-hw2
mvn clean test -P exercise2-hw2
```


## Selenium 03
**Task:**  Refactor tests form HW2 in ==Void Page Object== design pattern.

**Prompts for maven**
```
mvn clean test -P all-exercises-hw3
```

## Selenium 04
**Task:** Add Step design pattern to HW3, configure Allure report plugin, configure Jenkins job.

Jenkins UI: http://localhost:8080/

**Prompts for CLI**
```shell
java -jar jenkins.war --enable-future-java
```
**Prompts for maven**
```
mvn clean test -P all-exercises-hw4
mvn allure:serve
```

## Selenium 05
**Task:** Apply BDD methodology to test cases and implement in tests Cucumber framework.

**Prompts for maven**
```
mvn clean test -P all-exercises-hw5
```

## Selenium 06

**Task:** Set up Selenium Grid with two nodes with different browsers and run all your tests from the HW4 on the Grid

**Prompts for CLI**
```shell
java -jar selenium-server-4.12.1.jar hub
java -jar selenium-server-4.12.1.jar node --config /<path-to-config>/chromeNode.toml --hub 192.168.100.3
java -jar selenium-server-4.12.1.jar node --config /<path-to-config>/firefoxNode.toml --hub 192.168.100.3
```
**Prompts for maven**
```
mvn clean test -P all-exercises-hw6 -D launch.type=remote -D browser.name=chrome
mvn clean test -P all-exercises-hw6 -D launch.type=remote -D browser.name=firefox
```