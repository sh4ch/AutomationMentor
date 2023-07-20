package com.epam.auto.selenium03.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.epam.auto.selenium03.DifferentElementsPage;
import com.epam.auto.selenium03.HomePage;
import com.epam.auto.selenium03.enums.CheckboxesTexts;
import com.epam.auto.selenium03.enums.DropdownOptionsTexts;
import com.epam.auto.selenium03.enums.ElementTypes;
import com.epam.auto.selenium03.enums.RadioButtonsTexts;
import java.util.List;
import org.testng.annotations.Test;

public class Exercise2Test extends WebBaseTests {
    private String expectedDifferentElementsMenuText = "Different elements";
    private HomePage homePage;
    private DifferentElementsPage differentElementsPage;

    @Test(testName = "Exercise 2")
    public void exercise2() {
        //Step 5: Open through the header menu Service -> Different Elements Page
        homePage = new HomePage(webDriver);
        differentElementsPage = new DifferentElementsPage(webDriver);
        homePage.openDifferentElementsPage();

        assertEquals(differentElementsPage.getTitle().toLowerCase(), expectedDifferentElementsMenuText.toLowerCase());

        //Step 6: Select checkboxes: Water, Wind
        selectElementAndVerifySelection(CheckboxesTexts.WATER.getText());
        selectElementAndVerifySelection(CheckboxesTexts.WIND.getText());

        //Step 7: Select radio: Selen
        selectElementAndVerifySelection(RadioButtonsTexts.SELEN.getText());

        //Step 8: Select in dropdown Yellow
        differentElementsPage.selectDropdownOption(DropdownOptionsTexts.YELLOW.getText());
        assertEquals(differentElementsPage.getSelectedDropdownOptionText(), DropdownOptionsTexts.YELLOW.getText());

        //Step 9: Assert that
        // for each checkbox there is an individual log row and value is corresponded to the status of checkbox
        getLogsAndVerifyActions(CheckboxesTexts.getCheckboxesTexts(), ElementTypes.CHECKBOX);

        // for radio button there is a log row and value is corresponded to the status of radio button
        getLogsAndVerifyActions(RadioButtonsTexts.getRadioButtonsTexts(), ElementTypes.RADIOBUTTON);

        // for dropdown there is a log row and value is corresponded to the selected value
        getLogsAndVerifyActions(DropdownOptionsTexts.getDropdownOptionsTexts(), ElementTypes.DROPDOWN);
    }

    public void getLogsAndVerifyActions(List<String> items, ElementTypes elementType) {

        for (String item : items) {
            StringBuilder logEntryText = new StringBuilder(item);

            switch (elementType) {
                case CHECKBOX -> {
                    differentElementsPage.selectItem(item);
                    logEntryText.append(": condition changed to ")
                            .append(differentElementsPage
                                    .findButtonElementByText(item)
                                    .isSelected() ? "true" : "false");
                }
                case RADIOBUTTON -> differentElementsPage.selectItem(item);
                case DROPDOWN -> differentElementsPage.selectDropdownOption(item);
                default -> throw new IllegalArgumentException("Unsupported element type: " + elementType);
            }
            assertTrue(differentElementsPage.getLogRecord().stream()
                    .anyMatch(record -> record.getText().contains(logEntryText)));
        }
    }

    public void selectElementAndVerifySelection(String elementTitle) {
        differentElementsPage.selectItem(elementTitle);
        assertTrue(differentElementsPage.findButtonElementByText(elementTitle).isSelected());
    }
}
