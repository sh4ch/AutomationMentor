package com.epam.auto.selenium04.steps;

import com.epam.auto.selenium03.HomePage;
import com.epam.auto.selenium03.enums.MenuTexts;
import io.qameta.allure.Step;
import java.util.Arrays;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

public class Exercise1Steps extends WebBaseSteps {
    private HomePage homePage;
    private SoftAssert softAssert;
    private List<String> expectedIconTexts = Arrays
            .asList("To include good practices\nand ideas from successful\nEPAM project",
            "To be flexible and\ncustomizable",
            "To be multiplatform",
            "Already have good base\n(about 20 internal and\nsome external projects),\nwish to get more…");
    private String expectedFrameButtonText = "Frame Button";

    public Exercise1Steps(WebDriver driver) {
        super.setUp(driver);
        homePage = new HomePage(driver);
        this.softAssert = new SoftAssert();
    }

    @Step("Step 5: Assert that there are 4 items on the header section are displayed, and they have proper texts"
            + "\n'HOME', 'CONTACT FORM', 'SERVICE', 'METALS & COLORS'")
    public void checkHeaderItems() {
        softAssert.assertEquals(homePage.getMenuElements().size(), 4);

        for (WebElement menuItem : homePage.getMenuElements()) {
            softAssert.assertTrue(menuItem.isDisplayed());
        }
        softAssert.assertEquals(homePage.getMenuTexts(), MenuTexts.getUpperMenuTexts());
    }

    @Step("Step 6: Assert that there are 4 images on the Index Page, and they are displayed")
    public void checkImagesDisplayed() {
        softAssert.assertEquals(homePage.getIndexImages().size(), 4);

        for (WebElement indexImage : homePage.getIndexImages()) {
            softAssert.assertTrue(indexImage.isDisplayed());
        }
    }

    @Step("Step Fail: Assert that there are 6 images on the Index Page, and they are displayed")
    public void checkTestFail() {
        softAssert.assertEquals(homePage.getIndexImages().size(), 6);
    }

    @Step("Step 7: Assert that there are 4 texts on the Index Page under icons, and they have proper text")
    public void checkTextUnderIcons() {
        softAssert.assertEquals(homePage.getIndexImagesTextElements().size(), 4);

        softAssert.assertEquals(homePage.getIndexImagesTexts(), expectedIconTexts);
    }

    @Step("Step 8: Assert that there is the iframe with “Frame Button” exist")
    public void checkIframeIsDisplayed() {
        softAssert.assertTrue(homePage.getFirstFrame().isDisplayed());
    }

    @Step("Step 9: Switch to the iframe and check that there is “Frame Button” in the iframe")
    public void checkFrameButtonInIFrame() {
        homePage.switchToFrame();
        softAssert.assertEquals(homePage.getFrameButtonValue(), expectedFrameButtonText);
    }

    @Step("Step 10: Switch to original window back")
    public void switchToDefaultContent() {
        homePage.switchToDefault();
    }

    @Step("Step 11: Assert that there are 5 items in the Left Section are displayed, and they have proper text"
            + "\n“Home”, “Contact form”, “Service”, “Metals & Colors”, “Elements packs”")
    public void checkLeftSectionItems() {
        softAssert.assertEquals(homePage.getLeftMenuElements().size(), 5);

        for (WebElement leftMenuItem : homePage.getLeftMenuElements()) {
            softAssert.assertTrue(leftMenuItem.isDisplayed());
        }
        softAssert.assertEquals(homePage.getLeftMenuTexts(), MenuTexts.getLeftMenuTexts());

        softAssert.assertAll();
    }

    public SoftAssert getSoftAssert() {
        return softAssert;
    }
}
