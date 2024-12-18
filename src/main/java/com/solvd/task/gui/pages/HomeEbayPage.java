package com.solvd.task.gui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;


public class HomeEbayPage extends AbstractEbayPage {

    @FindBy(css = ".vl-flyout-nav__container li a")
    private List<WebElement> categories;

    public HomeEbayPage(WebDriver driver) {
        super(driver);
    }

    public List<WebElement> getCategories() {
        if (categories == null) {
            logger.info("No header categories found");
            return null;
        }
        List<WebElement> categoriesFiltered = categories.stream().filter(category -> category.isDisplayed()
                && !category.getText().equals("Explore (New!)")).toList();
        logger.info("Header categories found");
        logger.info("Header categories size: " + categoriesFiltered.size());
        return categoriesFiltered;
    }
}
