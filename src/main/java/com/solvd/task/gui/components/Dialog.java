package com.solvd.task.gui.components;

import com.solvd.task.gui.pages.ShoppingCartPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Dialog extends AbstractComponent {

    @FindBy(css = ".confirm-dialog__confirm")
    private WebElement confirmButton;

    private WebDriver driver;

    public Dialog(WebElement root, WebDriver driver) {
        super(root);
        this.driver = driver;
    }

    public ShoppingCartPage clickConfirmButton() {
        try {
            confirmButton.click();
            logger.info("Confirm button clicked");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cart-summary-call-to-action")));
            return new ShoppingCartPage(driver);
        } catch (Exception e) {
            logger.error("Error occurred while clicking confirm button", e);
            return null;
        }
    }
}
