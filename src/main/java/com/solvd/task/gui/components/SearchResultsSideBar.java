package com.solvd.task.gui.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Random;

public class SearchResultsSideBar extends AbstractComponent {

    @FindBy(css = "ul.x-refine__left__nav li ul.x-refine__left__nav > li")
    private List<WebElement> filterGroups;

    public SearchResultsSideBar(WebElement root, WebDriver driver) {
        super(root, driver);
    }

    public String selectRandomBrand() {
        try{
            List<WebElement> checkBoxes = filterGroups.get(0).findElements(By.cssSelector(".checkbox__control"));
            int randomIndex = new Random().nextInt(checkBoxes.size());
            WebElement checkBox = checkBoxes.get(randomIndex);
            String value = checkBox.getAttribute("aria-label");
            while (value.equals("Unbranded")) {
                checkBox = checkBoxes.get(randomIndex);
                value = checkBox.getAttribute("aria-label");
            }
            checkBox.click();
            logger.info("Selected brand: " + value);
            return value;
        } catch (Exception e) {
            logger.error("Error while selecting brand", e);
            return null;
        }
    }
}
