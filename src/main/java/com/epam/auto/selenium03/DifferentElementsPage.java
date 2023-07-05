package com.epam.auto.selenium03;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;


public class DifferentElementsPage extends BasePage {
    @FindBy(css = "select.uui-form-element")
    private WebElement dropdown;
    public static final String CHECKBOX_ELEMENT_TYPE = "checkbox";
    public static final String RADIOBUTTON_ELEMENT_TYPE = "radio";
    public static final String DROPDOWN_ELEMENT_TYPE = "dropdown";

    public DifferentElementsPage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    public WebElement findButtonElementByText(String itemTitle) {
        return webDriver.findElement(By.xpath("//label[contains(., '"
                + itemTitle + "')]//input"));
    }

    public void selectItem(String itemTitle) {
        findButtonElementByText(itemTitle).click();
    }

    public boolean isItemSelected(String itemTitle) {
        return findButtonElementByText(itemTitle).isSelected();
    }

    private Select getDropdownSelect() {
        return new Select(dropdown);
    }

    public void selectDropdownOption(String optionText) {
        getDropdownSelect().selectByVisibleText(optionText);
    }

    public String getSelectedDropdownOptionText() {
        return getDropdownSelect().getFirstSelectedOption().getText();
    }
}
