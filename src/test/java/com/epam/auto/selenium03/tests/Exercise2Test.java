package com.epam.auto.selenium03.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.epam.auto.selenium03.CheckboxesTexts;
import com.epam.auto.selenium03.DifferentElementsPage;
import com.epam.auto.selenium03.DropdownOptionsTexts;
import com.epam.auto.selenium03.ElementTypes;
import com.epam.auto.selenium03.HeaderMenu;
import com.epam.auto.selenium03.LogBox;
import com.epam.auto.selenium03.RadioButtonsTexts;
import com.epam.auto.selenium03.ServiceMenu;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class Exercise2Test extends WebBaseTests {
    private String differentElementsMenuText = "Different elements";
    private DifferentElementsPage differentElementsPage;
    private LogBox logBox;

    @Test(testName = "Exercise 2")

    public void exercise2() {
        //Step 5: Open through the header menu Service -> Different Elements Page
        HeaderMenu headerMenu = indexPage.getHeaderMenu();

        ServiceMenu serviceMenu = headerMenu.openServiceMenu();
        differentElementsPage = serviceMenu.openDifferentElementsPage();
        assertEquals(differentElementsPage.getTitle().toLowerCase(), differentElementsMenuText.toLowerCase());

        //Step 6: Select checkboxes: Water, Wind
        selectElementAndAssert(CheckboxesTexts.WATER.getText());
        selectElementAndAssert(CheckboxesTexts.WIND.getText());

        //Step 7: Select radio: Selen
        selectElementAndAssert(RadioButtonsTexts.SELEN.getText());

        //Step 8: Select in dropdown Yellow
        differentElementsPage.selectDropdownOption(DropdownOptionsTexts.YELLOW.getText());
        assertEquals(differentElementsPage.getSelectedDropdownOptionText(), DropdownOptionsTexts.YELLOW.getText());

        //Step 9: Assert that
        // for each checkbox there is an individual log row and value is corresponded to the status of checkbox
        logBox = differentElementsPage.getLogBox();

        assertActionInLogBox(CheckboxesTexts.getCheckboxesTexts(), ElementTypes.CHECKBOX);

        // for radio button there is a log row and value is corresponded to the status of radio button
        assertActionInLogBox(RadioButtonsTexts.getRadioButtonsTexts(), ElementTypes.RADIOBUTTON);

        // for dropdown there is a log row and value is corresponded to the selected value
        assertActionInLogBox(DropdownOptionsTexts.getDropdownOptionsTexts(), ElementTypes.DROPDOWN);
    }

    public void assertActionInLogBox(List<String> items, ElementTypes elementType) {
        for (String item : items) {
            switch (elementType) {
                case CHECKBOX, RADIOBUTTON -> differentElementsPage.selectItem(item);
                case DROPDOWN -> differentElementsPage.selectDropdownOption(item);
                default -> throw new IllegalArgumentException("Unsupported element type: " + elementType);
            }
            WebElement logEntry = logBox.getLogRecord(item, elementType, differentElementsPage);
            assertTrue(logEntry.isDisplayed());
        }
    }

    public void selectElementAndAssert(String elementTitle) {
        differentElementsPage.selectItem(elementTitle);
        assertTrue(differentElementsPage.isItemSelected(elementTitle));
    }
}
