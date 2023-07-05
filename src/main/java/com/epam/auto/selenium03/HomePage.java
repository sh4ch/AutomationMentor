package com.epam.auto.selenium03;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage {
    @FindBy(id = "user-name")
    private WebElement userName;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    public String getUserName() {
        return userName.getText();
    }

    public LoginPage getLoginPage() {
        return new LoginPage(webDriver);
    }

}
