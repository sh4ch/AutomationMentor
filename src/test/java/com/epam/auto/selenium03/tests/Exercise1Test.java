package com.epam.auto.selenium03.tests;

import com.epam.auto.selenium03.FirstFramePage;
import com.epam.auto.selenium03.HeaderMenu;
import com.epam.auto.selenium03.LeftMenu;
import com.epam.auto.selenium03.MenuTexts;
import java.util.Arrays;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class Exercise1Test extends WebBaseTests {
    private List<String> iconTexts;
    private String frameButtonText = "Frame Button";

    public void setup() {
        iconTexts = Arrays.asList("To include good practices\nand ideas from successful\nEPAM project",
                "To be flexible and\ncustomizable",
                "To be multiplatform",
                "Already have good base\n(about 20 internal and\nsome external projects),\nwish to get more…");
    }

    @Test(testName = "Exercise 1")

    public void exercise1() {
        setup();
        //Step 5: Assert that there are 4 items on the header section are displayed, and they have proper texts
        //"HOME", "CONTACT FORM", "SERVICE", "METALS & COLORS"
        HeaderMenu headerMenu = indexPage.getHeaderMenu();
        softAssert.assertEquals(headerMenu.getMenuElements().size(), 4);

        for (WebElement menuItem : headerMenu.getMenuElements()) {
            softAssert.assertTrue(headerMenu.isMenuElementDisplayed(menuItem));
        }
        softAssert.assertEquals(headerMenu.getMenuTexts(), MenuTexts.getUpperMenuTexts());

        //Step 6: Assert that there are 4 images on the Index Page, and they are displayed
        softAssert.assertEquals(indexPage.getIndexImages().size(), 4);

        for (WebElement indexImage : indexPage.getIndexImages()) {
            softAssert.assertTrue(indexPage.isIndexImageDisplayed(indexImage));
        }

        //Step 7: Assert that there are 4 texts on the Index Page under icons, and they have proper text
        softAssert.assertEquals(indexPage.getIndexImagesTextElements().size(), 4);

        softAssert.assertEquals(indexPage.getIndexImagesTexts(), iconTexts);

        //Step 8: Assert that there is the iframe with “Frame Button” exist
        FirstFramePage firstFramePage = indexPage.getFirstFramePage();
        WebElement frameElement = firstFramePage.getFirstFrame();
        softAssert.assertTrue(frameElement != null);

        //Step 9: Switch to the iframe and check that there is “Frame Button” in the iframe
        firstFramePage.switchToFrame();
        String buttonName = firstFramePage.getFrameButtonValue();
        softAssert.assertEquals(buttonName, frameButtonText);

        //Step 10: Switch to original window back
        indexPage.switchToDefault();

        //Step 11: Assert that there are 5 items in the Left Section are displayed, and they have proper text
        // “Home”, “Contact form”, “Service”, “Metals & Colors”, “Elements packs”
        LeftMenu leftMenu = indexPage.getLeftMenu();
        softAssert.assertEquals(leftMenu.getLeftMenuElements().size(), 5);

        for (WebElement leftMenuItem : leftMenu.getLeftMenuElements()) {
            softAssert.assertTrue(leftMenu.isLeftMenuElementDisplayed(leftMenuItem));
        }
        softAssert.assertEquals(leftMenu.getLeftMenuTexts(), MenuTexts.getLeftMenuTexts());

        softAssert.assertAll();
    }
}
