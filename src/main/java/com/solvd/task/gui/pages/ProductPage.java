package com.solvd.task.gui.pages;

import com.solvd.task.gui.components.Dialog;
import com.solvd.task.gui.components.SelectOptionModal;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ProductPage extends AbstractPage {

    @FindBy(xpath = "//*[@data-testid='x-atc-action']/a")
    private WebElement addToCartButton;

    @FindBy(xpath = "//div[@data-testid='x-msku-evo']/div[not(@hidden)]")
    private List<WebElement> selectOptionButtons;

    @FindBy(css = "h1.x-item-title__mainTitle span")
    private WebElement productName;

    @FindBy(css = "span.listbox-button--expanded")
    private WebElement selectOptionModal;

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public void selectRandomOptions() {
        try {
            for (WebElement button : selectOptionButtons) {
                SelectOptionModal selectOptionModal = clickOptionButton(button);
                selectOptionModal.selectRandomOption();
                logger.info("Option button clicked");
            }
        } catch (Exception e) {
            logger.error("Error occurred while clicking option button", e);
        }
    }

    public SelectOptionModal clickOptionButton(WebElement button) {
        try {
            button.click();
            logger.info("Click option button clicked");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10L));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.listbox-button--expanded")));
            return new SelectOptionModal(selectOptionModal);
        } catch (Exception e) {
            logger.error("Error occurred while clicking option button", e);
            return null;
        }
    }

    public ShoppingCartPage clickAddToCartButton() {
        try {
            addToCartButton.click();
            logger.info("Add to cart button clicked");
            return new ShoppingCartPage(driver);
        } catch (Exception e) {
            logger.error("Error occurred while clicking add to cart button", e);
            return null;
        }
    }

    public boolean isAddToCartButtonPresent() {
        try {
            if (addToCartButton.isDisplayed()) {
                logger.info("Add to cart button present");
            }
            return true;
        } catch (Exception e) {
            logger.error("Add to cart button is not present");
            return false;
        }
    }

    public String getProductName() {
        try {
            logger.info("Product name displayed: {}", productName.getText());
            return productName.getText();
        } catch (Exception e) {
            logger.error("Error getting product name", e);
            return null;
        }
    }
}
