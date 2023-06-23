package com.epam.auto.selenium03.page;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class IndexPage extends BasePage {
    @FindBy(css = ".icons-benefit")
    private List<WebElement> indexImages;
    @FindBy(css = ".benefit-txt")
    private List<WebElement> indexImagesTexts;

    public IndexPage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    public List<WebElement> getIndexImages() {
        return indexImages;
    }

    public List<WebElement> getIndexImagesTextElements() {
        return indexImagesTexts;
    }

    public List<String> getIndexImagesTexts() {
        List<String> imagesTexts = new ArrayList<>();
        for (WebElement menuItem : getIndexImagesTextElements()) {
            imagesTexts.add(menuItem.getText());
        }
        return imagesTexts;
    }

    public boolean isIndexImageDisplayed(WebElement indexImage) {
        return indexImage.isDisplayed();
    }
}
