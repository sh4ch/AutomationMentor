package com.epam.auto.selenium03.page;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HeaderMenu extends BasePage {
    @FindBy(css = "ul.m-l8 > li > a")
    private List<WebElement> menuElements;
    @FindBy(css = "ul.m-l8 > li.dropdown")
    private WebElement serviceMenuDropdown;

    public HeaderMenu(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    public List<WebElement> getMenuElements() {
        return menuElements;
    }

    public List<String> getMenuTexts() {
        List<String> menuTexts = new ArrayList<>();
        for (WebElement menuItem : getMenuElements()) {
            menuTexts.add(menuItem.getText());
        }
        return menuTexts;
    }

    public boolean isMenuElementDisplayed(WebElement menuElement) {
        return menuElement.isDisplayed();
    }

    public void openServiceMenu() {
        serviceMenuDropdown.click();
    }
}
