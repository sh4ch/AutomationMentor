package com.epam.auto.selenium04.tests;

import com.epam.auto.selenium04.AttachmentListener;
import com.epam.auto.selenium04.steps.Exercise1Steps;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(AttachmentListener.class)

public class Exercise1Test extends WebBaseTests {
    @Feature("Homework 4 Allure report and Steps design")
    @Test(testName = "Exercise 1")
    @Story("Home page tests")
    public void exercise1() {
        Exercise1Steps ex1Steps = new Exercise1Steps(webDriver);
        //Step 5: Assert that there are 4 items on the header section are displayed, and they have proper texts
        //"HOME", "CONTACT FORM", "SERVICE", "METALS & COLORS"
        ex1Steps.checkHeaderItems();

        //Step 6: Assert that there are 4 images on the Index Page, and they are displayed
        ex1Steps.checkImagesDisplayed();

        //Step 7: Assert that there are 4 texts on the Index Page under icons, and they have proper text
        ex1Steps.checkTextUnderIcons();

        //Step 8: Assert that there is the iframe with “Frame Button” exist
        ex1Steps.checkIframeIsDisplayed();

        //Step 9: Switch to the iframe and check that there is “Frame Button” in the iframe
        ex1Steps.checkFrameButtonInIFrame();

        //Step 10: Switch to original window back
        ex1Steps.switchToDefaultContent();

        //Step 11: Assert that there are 5 items in the Left Section are displayed, and they have proper text
        // “Home”, “Contact form”, “Service”, “Metals & Colors”, “Elements packs”
        ex1Steps.checkLeftSectionItems();
    }
}
