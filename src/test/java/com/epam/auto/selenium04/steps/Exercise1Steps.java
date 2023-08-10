package com.epam.auto.selenium04.steps;

import com.epam.auto.selenium03.HomePage;
import io.qameta.allure.Step;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

public class Exercise1Steps extends WebBaseSteps {
    private HomePage homePage;
    private SoftAssert softAssert;

    public Exercise1Steps(WebDriver driver) {
        super.setUp(driver);
        homePage = new HomePage(driver);
        this.softAssert = new SoftAssert();
    }

    @Step("Step 5: Assert that there are 4 items on the header section are displayed, and they have proper texts"
            + "\n'HOME', 'CONTACT FORM', 'SERVICE', 'METALS & COLORS'")
    public void checkHeaderItems(int itemsNumber, List<String> upperMenuTexts) {
        softAssert.assertEquals(homePage.getMenuElements().size(), itemsNumber);

        for (WebElement menuItem : homePage.getMenuElements()) {
            softAssert.assertTrue(menuItem.isDisplayed());
        }
        softAssert.assertEquals(homePage.getMenuTexts(), upperMenuTexts);
    }

    @Step("Step 6: Assert that there are 4 images on the Index Page, and they are displayed")
    public void checkImagesDisplayed(int imagesNumber) {
        softAssert.assertEquals(homePage.getIndexImages().size(), imagesNumber);

        for (WebElement indexImage : homePage.getIndexImages()) {
            softAssert.assertTrue(indexImage.isDisplayed());
        }
    }

    @Step("Step 7: Assert that there are 4 texts on the Index Page under icons, and they have proper text")
    public void checkTextUnderIcons(int textNumber, List<String> expectedIconTexts) {
        softAssert.assertEquals(homePage.getIndexImagesTextElements().size(), textNumber);

        softAssert.assertEquals(homePage.getIndexImagesTexts(), expectedIconTexts);
    }

    @Step("Step 8: Assert that there is the iframe with “Frame Button” exist")
    public void checkIframeIsDisplayed() {
        softAssert.assertTrue(homePage.getFirstFrame().isDisplayed());
    }

    @Step("Step 9: Switch to the iframe and check that there is “Frame Button” in the iframe")
    public void checkFrameButtonInIFrame(String btnName) {
        homePage.switchToFrame();
        softAssert.assertEquals(homePage.getFrameButtonValue(), btnName);
    }

    @Step("Step 10: Switch to original window back")
    public void switchToDefaultContent() {
        homePage.switchToDefault();
    }

    @Step("Step 11: Assert that there are 5 items in the Left Section are displayed, and they have proper text"
            + "\n“Home”, “Contact form”, “Service”, “Metals & Colors”, “Elements packs”")
    public void checkLeftSectionItems(int itemsNumber, List<String> menuText) {
        softAssert.assertEquals(homePage.getLeftMenuElements().size(), itemsNumber);

        for (WebElement leftMenuItem : homePage.getLeftMenuElements()) {
            softAssert.assertTrue(leftMenuItem.isDisplayed());
        }
        softAssert.assertEquals(homePage.getLeftMenuTexts(), menuText);

        softAssert.assertAll();
    }

    public SoftAssert getSoftAssert() {
        return softAssert;
    }
}
