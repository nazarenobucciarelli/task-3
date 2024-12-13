package com.solvd.task.gui.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartProduct extends AbstractComponent{

    @FindBy(css = "button[data-test-id='cart-remove-item']")
    private WebElement removeButton;

    @FindBy(css = ".item-title a")
    private WebElement title;

    private WebDriver driver;

    public CartProduct(WebElement root, WebDriver driver) {
        super(root);
        this.driver = driver;
    }

    public void clickRemoveButton() {
        try {
            removeButton.click();
            logger.info("Remove button clicked");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.invisibilityOf(title));
        } catch (Exception e) {
            logger.error("Error while clicking remove button", e);
        }
    }

    public String getTitle() {
        try{
            String titleText = title.getText();
            logger.info("Title text is: " + titleText);
            return titleText;
        } catch (Exception e) {
            logger.error("Error while getting title", e);
            return null;
        }
    }
}
