package com.epam.auto.selenium03.tests;

import com.epam.auto.selenium03.PropertiesService;
import com.epam.auto.selenium03.page.HomePage;
import com.epam.auto.selenium03.page.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.asserts.SoftAssert;

public abstract class WebBaseTests {
    protected WebDriver webDriver;
    protected SoftAssert softAssert;
    private String uri;
    private String username;
    private String userPassword;
    private String homePageTitle = "Home Page";
    private String userNameText = "ROMAN IOVLEV";

    public void initialize() {
        if (webDriver == null) {
            WebDriverManager.chromedriver().setup();
            PropertiesService propertiesService = new PropertiesService();
            Properties properties = propertiesService.getProperties();
            uri = properties.get("uri").toString();
            username = properties.get("username").toString();
            userPassword = properties.get("userPassword").toString();

            softAssert = new SoftAssert();
            webDriver = new ChromeDriver();
            webDriver.manage().window().maximize();
            webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

            //Step 1: Open test site by URL
            webDriver.navigate().to(uri);
            softAssert.assertEquals(webDriver.getCurrentUrl(), uri);

            //Step 2: Assert Browser title "Home Page"
            HomePage homePage = new HomePage(webDriver);
            softAssert.assertEquals(homePage.getTitle(), homePageTitle);

            //Step 3: Perform login
            LoginPage loginPage = new LoginPage(webDriver);
            loginPage.login(username, userPassword);

            //Step 4: Assert Username is loggined
            softAssert.assertEquals(homePage.getUserName(), userNameText);
        }
    }

    @AfterClass
    public void classTeardown() {
        //Step: Close Browser
        webDriver.close();
    }

    @AfterSuite
    public void suitTeardown() {
        webDriver.quit();
        webDriver = null;
    }
}
