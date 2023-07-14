package com.epam.auto.selenium03;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FirstFramePage extends BasePage {
    @FindBy(id = "frame")
    private WebElement firstFrame;
    @FindBy(id = "frame-button")
    private WebElement frameButton;

    public FirstFramePage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    public WebElement getFirstFrame() {
        return firstFrame;
    }

    public String getFrameButtonValue() {
        return frameButton.getAttribute("value");
    }
}
