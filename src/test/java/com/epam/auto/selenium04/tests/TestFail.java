package com.epam.auto.selenium04.tests;

import com.epam.auto.selenium04.AttachmentListener;
import com.epam.auto.selenium04.enums.MenuTexts;
import com.epam.auto.selenium04.steps.Exercise1Steps;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(AttachmentListener.class)

public class TestFail extends WebBaseTests {
    private int expectedImagesNumber = 6;
    private int expectedLeftSectionItems = 5;

    @Feature("Homework 4 Allure report and Steps design")
    @Test(testName = "Test Fail")
    @Story("Screenshot listener test")
    public void testFail() {
        Exercise1Steps ex1Steps = new Exercise1Steps(webDriver);
        //Step Fail: Assert that there are 6 images on the Index Page
        ex1Steps.checkImagesDisplayed(expectedImagesNumber);

        //Step 11: Assert that there are 5 items in the Left Section are displayed, and they have proper text
        // “Home”, “Contact form”, “Service”, “Metals & Colors”, “Elements packs”
        ex1Steps.checkLeftSectionItems(expectedLeftSectionItems, MenuTexts.getLeftMenuTexts());
    }
}
