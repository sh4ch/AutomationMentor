package com.epam.auto.selenium04.steps;

import com.epam.auto.selenium03.HomePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

public class WebBaseSteps {
    protected WebDriver webDriver;
    protected SoftAssert softAssert;
    private String expectedHomePageTitle = "Home Page";
    private String expectedUserNameText = "ROMAN IOVLEV";
    private HomePage homePage;

    public void setUp(WebDriver driver) {
        softAssert = new SoftAssert();
        homePage = new HomePage(driver);
        webDriver = driver;
    }

    @Step("Step 1: Open test site by URL")
    public void openSiteCheckUrl(String uri) {
        webDriver.navigate().to(uri);
        softAssert.assertEquals(webDriver.getCurrentUrl(), uri);
    }

    @Step("Step 2: Assert Browser title \"Home Page\"")
    public void checkBrowserTitle() {
        softAssert.assertEquals(homePage.getTitle(), expectedHomePageTitle);
    }

    @Step("Step 3: Perform login")
    public void login(String username, String userPassword) {
        homePage.login(username, userPassword);
    }

    @Step("Step 4: Assert Username is logged in")
    public void checkUsername() {
        softAssert.assertEquals(homePage.getUserName(), expectedUserNameText);
    }
}
