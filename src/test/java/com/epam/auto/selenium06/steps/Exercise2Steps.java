package com.epam.auto.selenium06.steps;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.epam.auto.selenium06.DifferentElementsPage;
import com.epam.auto.selenium06.HomePage;
import com.epam.auto.selenium06.enums.CheckboxesTexts;
import com.epam.auto.selenium06.enums.DropdownOptionsTexts;
import com.epam.auto.selenium06.enums.ElementTypes;
import com.epam.auto.selenium06.enums.RadioButtonsTexts;
import io.qameta.allure.Step;
import java.util.List;

public class Exercise2Steps extends WebBaseSteps {
    private HomePage homePage;
    private DifferentElementsPage differentElementsPage;

    public Exercise2Steps() {
        homePage = new HomePage(webDriver);
        differentElementsPage = new DifferentElementsPage(webDriver);
    }

    @Step("Step 5: Open through the header menu Service -> Different Elements Page")
    public void openDiffElPageCheckTitle(String expectDiffElMenu) {
        homePage.openDifferentElementsPage();
        assertEquals(differentElementsPage.getTitle().toLowerCase(), expectDiffElMenu.toLowerCase());
    }

    @Step("Step 6: Select checkboxes: Water, Wind")
    public void selectWaterWindCheckSelection() {
        selectElementAndVerifySelection(CheckboxesTexts.WATER.getText());
        selectElementAndVerifySelection(CheckboxesTexts.WIND.getText());
    }

    @Step("Step 7: Select radio: Selen")
    public void selectSelenCheckSelection() {
        selectElementAndVerifySelection(RadioButtonsTexts.SELEN.getText());
    }

    @Step("Step 8: Select in dropdown Yellow")
    public void selectYellowCheckSelection(String dropdownText) {
        differentElementsPage.selectDropdownOption(DropdownOptionsTexts.YELLOW.getText());
        assertEquals(differentElementsPage.getSelectedDropdownOptionText(), dropdownText);
    }

    @Step("Step 9: Assert that")
            public void performDropRadioBoxActionsCheckLogs() {
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
