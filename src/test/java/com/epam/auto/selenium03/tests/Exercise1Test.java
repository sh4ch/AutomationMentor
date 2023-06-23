package com.epam.auto.selenium03.tests;

import com.epam.auto.selenium03.page.FirstFramePage;
import com.epam.auto.selenium03.page.HeaderMenu;
import com.epam.auto.selenium03.page.IndexPage;
import com.epam.auto.selenium03.page.LeftMenu;
import java.util.Arrays;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class Exercise1Test extends WebBaseTests {
    private List<String> upperMenuTexts;
    private List<String> iconTexts;
    private List<String> leftMenuTexts;
    private String frameButtonText = "Frame Button";

    public void setup() {
        upperMenuTexts = Arrays.asList("HOME", "CONTACT FORM", "SERVICE", "METALS & COLORS");
        iconTexts = Arrays.asList("To include good practices\nand ideas from successful\nEPAM project",
                "To be flexible and\ncustomizable",
                "To be multiplatform",
                "Already have good base\n(about 20 internal and\nsome external projects),\nwish to get more…");
        leftMenuTexts = Arrays.asList("Home", "Contact form", "Service", "Metals & Colors", "Elements packs");
    }

    @Test(testName = "Exercise 1")

    public void exercise1() {
        initialize();
        setup();
        //Step 5: Assert that there are 4 items on the header section are displayed, and they have proper texts
        //"HOME", "CONTACT FORM", "SERVICE", "METALS & COLORS"
        //webDriver.switchTo().defaultContent();
        HeaderMenu headerMenu = new HeaderMenu(webDriver);

        List<WebElement> menuElements = headerMenu.getMenuElements();
        softAssert.assertEquals(menuElements.size(), 4);

        List<String> actualMenuTexts = headerMenu.getMenuTexts();
        for (WebElement menuItem : menuElements) {
            softAssert.assertTrue(headerMenu.isMenuElementDisplayed(menuItem));
        }
        softAssert.assertEquals(actualMenuTexts, upperMenuTexts);

        //Step 6: Assert that there are 4 images on the Index Page, and they are displayed
        IndexPage indexPage = new IndexPage(webDriver);

        List<WebElement> indexImages = indexPage.getIndexImages();
        softAssert.assertEquals(indexImages.size(), 4);

        for (WebElement indexImage : indexImages) {
            softAssert.assertTrue(indexPage.isIndexImageDisplayed(indexImage));
        }

        //Step 7: Assert that there are 4 texts on the Index Page under icons, and they have proper text
        List<WebElement> indexImagesTexts = indexPage.getIndexImagesTextElements();
        softAssert.assertEquals(indexImagesTexts.size(), 4);

        List<String> actualIconTexts = indexPage.getIndexImagesTexts();
        softAssert.assertEquals(actualIconTexts, iconTexts);

        //Step 8: Assert that there is the iframe with “Frame Button” exist
        FirstFramePage firstFramePage = new FirstFramePage(webDriver);
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
        LeftMenu leftMenu = new LeftMenu(webDriver);

        List<WebElement> leftMenuElements = leftMenu.getLeftMenuElements();
        softAssert.assertEquals(leftMenuElements.size(), 5);

        for (WebElement leftMenuItem : leftMenuElements) {
            softAssert.assertTrue(leftMenu.isLeftMenuElementDisplayed(leftMenuItem));
        }
        List<String> actualLeftMenuTexts = leftMenu.getLeftMenuTexts();
        softAssert.assertEquals(actualLeftMenuTexts, leftMenuTexts);

        softAssert.assertAll();
    }
}
