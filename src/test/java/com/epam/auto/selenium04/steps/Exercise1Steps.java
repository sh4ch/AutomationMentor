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

    private void assertAndReset() {
        softAssert.assertAll();
        softAssert = new SoftAssert();
    }

    @Step("Step 5: Assert that there are 4 items on the header section are displayed, and they have proper texts"
            + "\n'HOME', 'CONTACT FORM', 'SERVICE', 'METALS & COLORS'")
    public void checkHeaderItems(int expItemsNumber, List<String> expUpperMenuTexts) {
        softAssert.assertEquals(homePage.getMenuElements().size(), expItemsNumber);

        for (WebElement menuItem : homePage.getMenuElements()) {
            softAssert.assertTrue(menuItem.isDisplayed());
        }
        softAssert.assertEquals(homePage.getMenuTexts(), expUpperMenuTexts);
        softAssert.assertAll();
    }

    @Step("Step 6: Assert that there are 4 images on the Index Page, and they are displayed")
    public void checkImagesDisplayed(int expectedImagesNumber) {
        softAssert = new SoftAssert();
        softAssert.assertEquals(homePage.getIndexImages().size(), expectedImagesNumber);

        for (WebElement indexImage : homePage.getIndexImages()) {
            softAssert.assertTrue(indexImage.isDisplayed());
        }
        softAssert.assertAll();
    }

    @Step("Step 7: Assert that there are 4 texts on the Index Page under icons, and they have proper text")
    public void checkTextUnderIcons(int textNumber, List<String> expectedIconTexts) {
        softAssert = new SoftAssert();
        softAssert.assertEquals(homePage.getIndexImagesTextElements().size(), textNumber);

        softAssert.assertEquals(homePage.getIndexImagesTexts(), expectedIconTexts);
        softAssert.assertAll();
    }

    @Step("Step 8: Assert that there is the iframe with “Frame Button” exist")
    public void checkIframeIsDisplayed() {
        softAssert = new SoftAssert();
        softAssert.assertTrue(homePage.getFirstFrame().isDisplayed());
        softAssert.assertAll();
    }

    @Step("Step 9: Switch to the iframe and check that there is “Frame Button” in the iframe")
    public void checkFrameButtonInIFrame(String expectedBtnName) {
        softAssert = new SoftAssert();
        homePage.switchToFrame();
        softAssert.assertEquals(homePage.getFrameButtonValue(), expectedBtnName);
        softAssert.assertAll();
    }

    @Step("Step 10: Switch to original window back")
    public void switchToDefaultContent() {
        softAssert = new SoftAssert();
        homePage.switchToDefault();
        softAssert.assertAll();
    }

    @Step("Step 11: Assert that there are 5 items in the Left Section are displayed, and they have proper text"
            + "\n“Home”, “Contact form”, “Service”, “Metals & Colors”, “Elements packs”")
    public void checkLeftSectionItems(int expectedItemsNumber, List<String> expectedMenuText) {
        softAssert = new SoftAssert();
        softAssert.assertEquals(homePage.getLeftMenuElements().size(), expectedItemsNumber);

        for (WebElement leftMenuItem : homePage.getLeftMenuElements()) {
            softAssert.assertTrue(leftMenuItem.isDisplayed());
        }
        softAssert.assertEquals(homePage.getLeftMenuTexts(), expectedMenuText);
        softAssert.assertAll();
    }

    public SoftAssert getSoftAssert() {
        return softAssert;
    }
}
