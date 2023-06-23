package com.epam.auto.selenium03.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.epam.auto.selenium03.page.DifferentElementsPage;
import com.epam.auto.selenium03.page.HeaderMenu;
import com.epam.auto.selenium03.page.LogBox;
import com.epam.auto.selenium03.page.ServiceMenu;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class Exercise2Test extends WebBaseTests {
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

    @Test(testName = "Exercise 2")

    public void exercise2() {
        initialize();
        //Step 5: Open through the header menu Service -> Different Elements Page
        HeaderMenu headerMenu = new HeaderMenu(webDriver);
        headerMenu.openServiceMenu();

        ServiceMenu serviceMenu = new ServiceMenu(webDriver);
        serviceMenu.openDifferentElementsPage();
        DifferentElementsPage differentElementsPage = new DifferentElementsPage(webDriver);
        assertEquals(differentElementsPage.getTitle(), differentElementsPageTitle);

        //Step 6: Select checkboxes: Water, Wind
        differentElementsPage.selectItem(checkboxWaterTitle);
        differentElementsPage.selectItem(checkboxWindTitle);
        assertTrue(differentElementsPage.isItemSelected(checkboxWaterTitle));
        assertTrue(differentElementsPage.isItemSelected(checkboxWindTitle));

        //Step 7: Select radio: Selen
        differentElementsPage.selectItem(radiobuttonSelenTitle);
        assertTrue(differentElementsPage.isItemSelected(radiobuttonSelenTitle));

        //Step 8: Select in dropdown Yellow
        differentElementsPage.selectDropdownOption(yellowOptionText);
        assertEquals(differentElementsPage.getSelectedDropdownOptionText(), yellowOptionText);

        //Step 9: Assert that
        // for each checkbox there is an individual log row and value is corresponded to the status of checkbox
        LogBox logBox = new LogBox(webDriver);

        assertActionInLogBox(checkboxNames, checkboxElementType, logBox, differentElementsPage);

        // for radio button there is a log row and value is corresponded to the status of radio button
        assertActionInLogBox(radioNames, radiobuttonElementType, logBox, differentElementsPage);

        // for dropdown there is a log row and value is corresponded to the selected value
        assertActionInLogBox(dropdownOptions, dropdownElementType, logBox, differentElementsPage);
    }

    public void assertActionInLogBox(String[] items, String elementType, LogBox logBox,
                                     DifferentElementsPage differentElementsPage) {
        for (String item : items) {
            if (elementType.equals(DifferentElementsPage.CHECKBOX_ELEMENT_TYPE)
                    || elementType.equals(DifferentElementsPage.RADIOBUTTON_ELEMENT_TYPE)) {
                differentElementsPage.selectItem(item);
            } else if (elementType.equals(DifferentElementsPage.DROPDOWN_ELEMENT_TYPE)) {
                differentElementsPage.selectDropdownOption(item);
            }
            WebElement logEntry = logBox.getLogRecord(item, elementType, differentElementsPage);
            assertTrue(logEntry.isDisplayed());
        }
    }
}
