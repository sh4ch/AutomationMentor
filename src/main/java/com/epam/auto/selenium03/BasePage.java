package com.epam.auto.selenium03;

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

    public HeaderMenu getHeaderMenu() {
        return new HeaderMenu(webDriver);
    }

    public LeftMenu getLeftMenu() {
        return new LeftMenu(webDriver);
    }

    public LogBox getLogBox() {
        return new LogBox(webDriver);
    }
}
