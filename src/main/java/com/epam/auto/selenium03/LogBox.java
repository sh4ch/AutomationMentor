package com.epam.auto.selenium03;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogBox extends BasePage {
    @FindBy(css = ".logs")
    private WebElement logBoxElement;

    public LogBox(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    private WebElement getLogBoxElement() {
        return logBoxElement;
    }

    public WebElement getLogRecord(String item, ElementTypes elementType, DifferentElementsPage differentElementsPage) {
        String logEntryText = item;
        if (elementType.equals(ElementTypes.CHECKBOX)) {
            logEntryText += ": condition changed to "
                    + (differentElementsPage.isItemSelected(item) ? "true" : "false");
        }
        return getLogBoxElement().findElement(By.xpath("//li[contains(., '" + logEntryText + "')]"));
    }
}
