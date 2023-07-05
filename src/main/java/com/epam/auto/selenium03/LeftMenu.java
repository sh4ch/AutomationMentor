package com.epam.auto.selenium03;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LeftMenu extends BasePage {
    @FindBy(css = "ul.sidebar-menu.left > li > a > span")
    private List<WebElement> leftMenuElements;

    public LeftMenu(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    public List<WebElement> getLeftMenuElements() {
        return leftMenuElements;
    }

    public List<String> getLeftMenuTexts() {
        List<String> menuTexts = new ArrayList<>();
        for (WebElement menuItem : getLeftMenuElements()) {
            menuTexts.add(menuItem.getText().toUpperCase());
        }
        return menuTexts;
    }

    public boolean isLeftMenuElementDisplayed(WebElement menuElement) {
        return menuElement.isDisplayed();
    }
}
