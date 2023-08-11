package com.epam.auto.selenium04;

import com.epam.auto.selenium04.tests.WebBaseTests;
import io.qameta.allure.Attachment;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class AttachmentListener implements ITestListener {

    public void onTestFailure(ITestResult itestresult) {
        Object testClassInstance = itestresult.getInstance();
        if (testClassInstance instanceof WebBaseTests) {
            WebDriver driver = ((WebBaseTests) testClassInstance).getWebDriver();
            saveScreenshot(driver, itestresult.getName());
        }
    }

    @Attachment(type = "image/png", fileExtension = ".png")
    public byte[] saveScreenshot(WebDriver driver, String testName) {
        File screenCapture = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenCapture, new File(".//target/screenshots/" + testName + "_Screenshot.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
