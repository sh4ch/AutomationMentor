package com.epam.auto.selenium03.page;

import org.openqa.selenium.WebDriver;

public abstract class BasePage {
    protected WebDriver webDriver;

    public BasePage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void switchToDefault() {
        webDriver.switchTo().defaultContent();
    }

    public String getTitle() {
        return webDriver.getTitle();
    }
}
