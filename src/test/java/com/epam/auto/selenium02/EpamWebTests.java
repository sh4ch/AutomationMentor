package com.epam.auto.selenium02;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class EpamWebTests {
    private WebDriver webDriver;
    private SoftAssert softAssert;
    private String uri;
    private String username;
    private String password;
    private String homePageTitle = "Home Page";
    private String userIconId = "user-icon";
    private String nameId = "name";
    private String passwordId = "password";
    private String loginButtonId = "login-button";
    private String userNameId = "user-name";
    private String userNameText = "ROMAN IOVLEV";
    private List<String> upperMenuTexts;
    private List<String> iconTexts;
    private String firstFrameId = "frame";
    private String frameButtonId = "frame-button";
    private String frameButtonText = "Frame Button";
    private List<String> leftMenuTexts;
    private String differentElementsMenuText = "Different Elements";
    private String yellowOptionText = "Yellow";
    private String[] checkboxNames = {"Water", "Wind", "Earth", "Fire"};
    private String[] radioNames = {"Gold", "Silver", "Bronze", "Selen"};
    private String[] dropdownOptions = {"Yellow", "Red", "Green", "Blue"};

    @BeforeClass
    public void setup() {

        WebDriverManager.chromedriver().setup();
        PropertiesService propertiesService = new PropertiesService();
        Properties properties = propertiesService.getProperties();
        uri = properties.get("uri").toString();
        username = properties.get("username").toString();
        password = properties.get("password").toString();

        softAssert = new SoftAssert();
        upperMenuTexts = Arrays.asList("HOME", "CONTACT FORM", "SERVICE", "METALS & COLORS");
        iconTexts = Arrays.asList("To include good practices\nand ideas from successful\nEPAM project",
                "To be flexible and\ncustomizable",
                "To be multiplatform",
                "Already have good base\n(about 20 internal and\nsome external projects),\nwish to get more…");
        leftMenuTexts = Arrays.asList("Home", "Contact form", "Service", "Metals & Colors", "Elements packs");
    }

    @BeforeMethod
    public void launchWebDriver() {
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test(testName = "Exercise 1")
    public void exercise1() {

        //Step 1: Open test site by URL
        webDriver.navigate().to(uri);
        softAssert.assertEquals(webDriver.getCurrentUrl(), uri);

        //Step 2: Assert Browser title "Home Page"
        softAssert.assertEquals(webDriver.getTitle(), homePageTitle);

        //Step 3: Perform login
        webDriver.findElement(By.id(userIconId)).click();
        webDriver.findElement(By.id(nameId)).sendKeys(username);
        webDriver.findElement(By.id(passwordId)).sendKeys(password);
        webDriver.findElement(By.id(loginButtonId)).click();

        //Step 4: Assert Username is loggined
        WebElement userName = webDriver.findElement(By.id(userNameId));
        softAssert.assertEquals(userName.getText(), userNameText);

        //Step 5: Assert that there are 4 items on the header section are displayed, and they have proper texts
        //"HOME", "CONTACT FORM", "SERVICE", "METALS & COLORS"
        webDriver.switchTo().defaultContent();
        List<WebElement> menuElements = webDriver.findElements(By.cssSelector("ul.m-l8 > li > a"));
        softAssert.assertEquals(menuElements.size(), 4);

        List<String> actualMenuTexts = new ArrayList<>();
        for (WebElement menuItem : menuElements) {
            actualMenuTexts.add(menuItem.getText());
        }
        softAssert.assertEquals(actualMenuTexts, upperMenuTexts);

        //Step 6: Assert that there are 4 images on the Index Page, and they are displayed
        List<WebElement> indexImages = webDriver.findElements(By.cssSelector(".icons-benefit"));
        softAssert.assertEquals(indexImages.size(), 4);

        for (WebElement indexImage : indexImages) {
            softAssert.assertTrue(indexImage.isDisplayed());
        }

        //Step 7: Assert that there are 4 texts on the Index Page under icons, and they have proper text
        List<WebElement> indexImagesTexts = webDriver.findElements(By.cssSelector(".benefit-txt"));
        softAssert.assertEquals(indexImagesTexts.size(), 4);

        List<String> actualIconTexts = new ArrayList<>();
        for (WebElement indexImageText : indexImagesTexts) {
            actualIconTexts.add(indexImageText.getText());
        }
        softAssert.assertEquals(actualIconTexts, iconTexts);

        //Step 8: Assert that there is the iframe with “Frame Button” exist
        List<WebElement> frameElements = webDriver.findElements(By.id(firstFrameId));
        softAssert.assertTrue(frameElements.size() > 0);

        //Step 9: Switch to the iframe and check that there is “Frame Button” in the iframe
        WebElement iframe = webDriver.findElement(By.id(firstFrameId));
        webDriver.switchTo().frame(iframe);
        String buttonName = webDriver.findElement(By.id(frameButtonId)).getAttribute("value");
        softAssert.assertEquals(buttonName, frameButtonText);

        //Step 10: Switch to original window back
        webDriver.switchTo().defaultContent();

        //Step 11: Assert that there are 5 items in the Left Section are displayed, and they have proper text
        // “Home”, “Contact form”, “Service”, “Metals & Colors”, “Elements packs”
        List<WebElement> leftMenuElements = webDriver
                .findElements(By.cssSelector("ul.sidebar-menu.left > li > a > span"));
        softAssert.assertEquals(leftMenuElements.size(), 5);

        List<String> actualLeftMenuTexts = new ArrayList<>();
        for (WebElement leftMenuItem : leftMenuElements) {
            actualLeftMenuTexts.add(leftMenuItem.getText());
        }
        softAssert.assertEquals(actualLeftMenuTexts, leftMenuTexts);

        softAssert.assertAll();
    }

    @Test(testName = "Exercise 2")
    public void exercise2() {
        //Step 1: Open test site by URL
        webDriver.navigate().to(uri);
        assertEquals(webDriver.getCurrentUrl(), uri);

        //Step 2: Assert Browser title "Home Page"
        assertEquals(webDriver.getTitle(), homePageTitle);

        //Step 3: Perform login
        webDriver.findElement(By.id(userIconId)).click();
        webDriver.findElement(By.id(nameId)).sendKeys(username);
        webDriver.findElement(By.id(passwordId)).sendKeys(password);
        webDriver.findElement(By.id(loginButtonId)).click();

        //Step 4: Assert Username is loggined
        WebElement userName = webDriver.findElement(By.id(userNameId));
        assertEquals(userName.getText(), userNameText);

        //Step 5: Open through the header menu Service -> Different Elements Page
        WebElement menuDropdown = webDriver.findElement(By.cssSelector("ul.m-l8 > li.dropdown"));
        menuDropdown.click();
        WebElement differentElementMenu = webDriver
                .findElement(By.xpath("//ul[@role='menu']/li[contains(a, 'Different elements')]"));
        differentElementMenu.click();
        assertEquals(webDriver.getTitle(), differentElementsMenuText);

        //Step 6: Select checkboxes: Water, Wind
        WebElement waterCheckbox = webDriver.findElement(By.xpath("//label[contains(., 'Water')]//input"));
        waterCheckbox.click();
        WebElement windCheckbox = webDriver.findElement(By.xpath("//label[contains(., 'Wind')]//input"));
        windCheckbox.click();
        assertTrue(waterCheckbox.isSelected());
        assertTrue(windCheckbox.isSelected());

        //Step 7: Select radio: Selen
        WebElement selenRadio = webDriver.findElement(By.xpath("//label[contains(., 'Selen')]//input"));
        selenRadio.click();
        assertTrue(selenRadio.isSelected());

        //Step 8: Select in dropdown Yellow
        WebElement dropdown = webDriver.findElement(By.cssSelector("select.uui-form-element"));

        Select dropdownSelect = new Select(dropdown);
        dropdownSelect.selectByVisibleText(yellowOptionText);
        assertEquals(dropdownSelect.getFirstSelectedOption().getText(), yellowOptionText);

        //Step 9: Assert that
        // for each checkbox there is an individual log row and value is corresponded to the status of checkbox
        WebElement logBox = webDriver.findElement(By.cssSelector(".logs"));

        for (String checkboxName : checkboxNames) {
            WebElement checkbox = webDriver
                    .findElement(By.xpath("//label[contains(., '" + checkboxName + "')]//input"));
            checkbox.click();

            WebElement logEntry = logBox
                    .findElement(By.xpath("//li[contains(., '"
                            + checkboxName + ": condition changed to "
                            + (checkbox.isSelected() ? "true" : "false") + "')]"));
            assertTrue(logEntry.isDisplayed());
        }

        // for radio button there is a log row and value is corresponded to the status of radio button
        for (String radioName : radioNames) {
            WebElement radioButton = webDriver
                    .findElement(By.xpath("//label[contains(., '" + radioName + "')]//input"));
            radioButton.click();

            WebElement logEntry = logBox.findElement(By.xpath("//li[contains(., '" + radioName + "')]"));
            assertTrue(logEntry.isDisplayed());
        }

        // for dropdown there is a log row and value is corresponded to the selected value
        for (String option : dropdownOptions) {
            dropdownSelect.selectByVisibleText(option);

            WebElement logEntry = logBox.findElement(By.xpath("//li[contains(., '" + option + "')]"));
            assertTrue(logEntry.isDisplayed());
        }
    }

    @AfterMethod
    public void teardown() {
        //Step: Close Browser
        webDriver.quit();
    }
}
