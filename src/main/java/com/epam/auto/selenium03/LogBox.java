package com.epam.auto.selenium03;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogBox extends BasePage {
    @FindBy(css = ".logs")
    private WebElement logBoxElement;

    @FindBy(css = ".logs li")
    private List<WebElement> logRecords;

    public LogBox(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    public List<WebElement> getLogRecord() {
        return this.logRecords;
    }
}
