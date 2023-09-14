package com.epam.auto.selenium06.tests;

import com.epam.auto.selenium06.AttachmentListener;
import com.epam.auto.selenium06.PropertiesService;
import com.epam.auto.selenium06.steps.WebBaseSteps;
import com.epam.auto.selenium06.webdriver.WebDriverProvider;
import java.time.Duration;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

@Listeners(AttachmentListener.class)
public abstract class WebBaseTests {
    protected static WebDriver webDriver;
    private String uri;
    private String username;
    private String userPassword;

    @BeforeClass(description = "Initial setup for environment")
    public void initialSetup(ITestContext context) {
        Properties properties = PropertiesService.getProperties();
        uri = properties.get("uriJDI").toString();
        username = properties.get("username").toString();
        userPassword = properties.get("password").toString();

        if (username.isEmpty() || username.equals("[MASKED]")) {
            username = System.getenv("username");
        }
        if (userPassword.isEmpty() || userPassword.equals("[MASKED]")) {
            userPassword = System.getenv("userPassword");
        }
        webDriver = WebDriverProvider.getDriver();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        context.setAttribute("driver", webDriver);

        WebBaseSteps baseSteps = new WebBaseSteps();
        baseSteps.setUp();
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
        WebDriverProvider.closeDriver();
    }

}
