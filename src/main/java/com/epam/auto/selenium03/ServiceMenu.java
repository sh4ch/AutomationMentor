package com.epam.auto.selenium03;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ServiceMenu extends BasePage {
    private String differentElementsMenuText = "Different elements";

    public ServiceMenu(WebDriver webDriver) {
        super(webDriver);
    }

    public WebElement findMenuItemByText(String menuItemText) {
        return webDriver.findElement(By.xpath("//ul[@role='menu']/li[contains(a, '"
                + menuItemText + "')]"));
    }

    public DifferentElementsPage openDifferentElementsPage() {
        WebElement differentElementMenu = findMenuItemByText(differentElementsMenuText);
        differentElementMenu.click();
        return new DifferentElementsPage(webDriver);
    }
}
