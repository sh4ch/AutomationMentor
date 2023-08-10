package com.epam.auto.selenium04.tests;

import com.epam.auto.selenium03.PropertiesService;
import com.epam.auto.selenium04.AttachmentListener;
import com.epam.auto.selenium04.steps.WebBaseSteps;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

@Listeners(AttachmentListener.class)
public abstract class WebBaseTests {
    protected WebDriver webDriver;
    private String uri;
    private String username;
    private String userPassword;

    @BeforeClass(description = "Initial setup for environment")
    public void initialSetup() {
        if (webDriver == null) {
            WebDriverManager.chromedriver().setup();
            PropertiesService propertiesService = new PropertiesService();
            Properties properties = propertiesService.getProperties();
            uri = properties.get("uriJDI").toString();
            username = System.getenv("username");
            userPassword = System.getenv("userPassword");

            webDriver = new ChromeDriver();
            webDriver.manage().window().maximize();
            webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        }
        WebBaseSteps baseSteps = new WebBaseSteps();
        baseSteps.setUp(webDriver);
        //Step 1: Open test site by URL
        baseSteps.openSiteCheckUrl(uri);
        //Step 2: Assert Browser title \"Home Page\"
        baseSteps.checkBrowserTitle();
        //Step 3: Perform login
        baseSteps.login(username, userPassword);
        //Step 4: Assert Username is logged in
        baseSteps.checkUsername();
    }

    @AfterClass(description = "Final Step: Close Browser")
    public void classTeardown() {
        webDriver.close();
    }

    @AfterSuite
    public void suitTeardown() {
        webDriver.quit();
        webDriver = null;
    }

    public WebDriver getWebDriver() {
        return webDriver;
    }
}
