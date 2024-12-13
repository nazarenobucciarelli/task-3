package com.solvd.task.gui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CategoryEbayPage extends AbstractEbayPage {

    @FindBy(css = "nav[role='navigation']")
    private WebElement nav;

    public CategoryEbayPage(WebDriver driver) {
        super(driver);
    }

    public boolean isNavigationDisplayed() {
        return nav.isDisplayed();
    }

}
