package com.epam.auto.selenium04.tests;

import com.epam.auto.selenium04.AttachmentListener;
import com.epam.auto.selenium04.steps.Exercise1Steps;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(AttachmentListener.class)

public class TestFail extends WebBaseTests {
    @Feature("Homework 4 Allure report and Steps design")
    @Test(testName = "Test Fail")
    @Story("Screenshot listener test")
    public void testFail() {
        Exercise1Steps ex1Steps = new Exercise1Steps(webDriver);
        //Step Fail: Assert that there are 6 images on the Index Page
        ex1Steps.checkTestFail();
        ex1Steps.getSoftAssert().assertAll();
    }
}
