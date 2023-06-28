package com.epam.auto.selenium02;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class Exercise2Test extends WebBaseTests {
    private String differentElementsMenuText = "Different elements";
    private String differentElementsPageTitle = "Different Elements";
    private String yellowOptionText = "Yellow";
    private String[] checkboxNames = {"Water", "Wind", "Earth", "Fire"};
    private String[] radioNames = {"Gold", "Silver", "Bronze", "Selen"};
    private String[] dropdownOptions = {"Yellow", "Red", "Green", "Blue"};

    private String checkboxWaterTitle = "Water";
    private String checkboxWindTitle = "Wind";
    private String radiobuttonSelenTitle = "Selen";
    private String checkboxElementType = "checkbox";
    private String radiobuttonElementType = "radio";
    private String dropdownElementType = "dropdown";

    public WebElement findMenuItemByText(String menuItemText) {
        return webDriver.findElement(By.xpath("//ul[@role='menu']/li[contains(a, '"
                + menuItemText + "')]"));
    }

    public WebElement findButtonElementByText(String elementItemText) {
        return webDriver.findElement(By.xpath("//label[contains(., '"
                + elementItemText + "')]//input"));
    }

    public void findClickAssertElementByTitle(String elementTitle) {
        WebElement webElement = findButtonElementByText(elementTitle);
        webElement.click();
        assertTrue(webElement.isSelected());
    }

    @Test(testName = "Exercise 2")

    public void exercise2() {
        openUrl();
        login();
        //Step 5: Open through the header menu Service -> Different Elements Page
        WebElement menuDropdown = webDriver.findElement(By.cssSelector("ul.m-l8 > li.dropdown"));
        menuDropdown.click();
        WebElement differentElementMenu = findMenuItemByText(differentElementsMenuText);
        differentElementMenu.click();
        assertEquals(webDriver.getTitle(), differentElementsPageTitle);

        //Step 6: Select checkboxes: Water, Wind
        findClickAssertElementByTitle(checkboxWaterTitle);
        findClickAssertElementByTitle(checkboxWindTitle);

        //Step 7: Select radio: Selen
        findClickAssertElementByTitle(radiobuttonSelenTitle);

        //Step 8: Select in dropdown Yellow
        WebElement dropdown = webDriver.findElement(By.cssSelector("select.uui-form-element"));

        Select dropdownSelect = new Select(dropdown);
        dropdownSelect.selectByVisibleText(yellowOptionText);
        assertEquals(dropdownSelect.getFirstSelectedOption().getText(), yellowOptionText);

        //Step 9: Assert that
        // for each checkbox there is an individual log row and value is corresponded to the status of checkbox
        WebElement logBox = webDriver.findElement(By.cssSelector(".logs"));

        assertActionInLog(checkboxNames, checkboxElementType, logBox);

        // for radio button there is a log row and value is corresponded to the status of radio button
        assertActionInLog(radioNames, radiobuttonElementType, logBox);

        // for dropdown there is a log row and value is corresponded to the selected value
        assertActionInLog(dropdownOptions, dropdownElementType, logBox);
    }

    public void assertActionInLog(String[] items, String elementType, WebElement logBox) {
        for (String item : items) {
            String logEntryText = item;

            if (elementType.equals(checkboxElementType) || elementType.equals(radiobuttonElementType)) {
                WebElement element = findButtonElementByText(item);
                element.click();
                if (elementType.equals(checkboxElementType)) {
                    logEntryText += ": condition changed to " + (element.isSelected() ? "true" : "false");
                }
            } else if (elementType.equals(dropdownElementType)) {
                WebElement dropdown = webDriver.findElement(By.cssSelector("select.uui-form-element"));
                Select dropdownSelect = new Select(dropdown);
                dropdownSelect.selectByVisibleText(item);
            }

            WebElement logEntry = logBox.findElement(By.xpath("//li[contains(., '" + logEntryText + "')]"));
            assertTrue(logEntry.isDisplayed());
        }
    }
}
