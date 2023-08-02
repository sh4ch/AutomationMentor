package com.epam.auto.selenium04.tests;

import com.epam.auto.selenium04.AttachmentListener;
import com.epam.auto.selenium04.steps.Exercise2Steps;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(AttachmentListener.class)

public class Exercise2Test extends WebBaseTests {
    @Feature("Homework 4 Allure report and Steps design")
    @Test(testName = "Exercise 2")
    @Story("Different Elements page tests")
    public void exercise2() {
        Exercise2Steps ex2Steps = new Exercise2Steps(webDriver);
        //Step 5: Open through the header menu Service -> Different Elements Page
        ex2Steps.openDiffElPageCheckTitle();

        //Step 6: Select checkboxes: Water, Wind
        ex2Steps.selectWaterWindCheckSelection();

        //Step 7: Select radio: Selen
        ex2Steps.selectSelenCheckSelection();

        //Step 8: Select in dropdown Yellow
        ex2Steps.selectYellowCheckSelection();

        //Step 9: Assert that
        // for each checkbox there is an individual log row and value is corresponded to the status of checkbox
        // for radio button there is a log row and value is corresponded to the status of radio button
        // for dropdown there is a log row and value is corresponded to the selected value
        ex2Steps.performDropRadioBoxActionsCheckLogs();
    }

}
