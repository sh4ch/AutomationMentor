package com.epam.auto.selenium05.cucumber.steps;

import com.epam.auto.selenium05.DifferentElementsPage;
import com.epam.auto.selenium05.DriverSingleton;
import com.epam.auto.selenium05.HomePage;
import com.epam.auto.selenium05.UserTablePage;
import org.openqa.selenium.WebDriver;

public abstract class AbstractBaseStep {
    protected HomePage homePage;
    protected DifferentElementsPage differentElementsPage;
    protected UserTablePage userTablePage;
    protected WebDriver driver = DriverSingleton.getDriver();

    public AbstractBaseStep() {
        homePage = new HomePage(driver);
        differentElementsPage = new DifferentElementsPage(driver);
        userTablePage = new UserTablePage(driver);
    }
}
