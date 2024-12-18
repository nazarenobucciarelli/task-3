package com.solvd.task.gui.pages;

import com.solvd.task.gui.components.SelectOptionModal;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ProductEbayPage extends AbstractEbayPage {

    @FindBy(xpath = "//*[@data-testid='x-atc-action']/a")
    private WebElement addToCartButton;

    @FindBy(xpath = "//div[@data-testid='x-msku-evo']/div[not(@hidden)]")
    private List<WebElement> selectOptionButtons;

    @FindBy(css = "h1.x-item-title__mainTitle span")
    private WebElement productName;

    @FindBy(css = "span.listbox-button--expanded")
    private WebElement selectOptionModal;

    public ProductEbayPage(WebDriver driver) {
        super(driver);
    }

    public void selectRandomOptions() {
        try {
            for (WebElement button : selectOptionButtons) {
                SelectOptionModal selectModal = clickOptionButton(button);
                selectModal.selectRandomOption();
                logger.info("Random option selected");
            }
        } catch (Exception e) {
            logger.error("Error occurred while clicking random option", e);
        }
    }

    public SelectOptionModal clickOptionButton(WebElement button) {
        try {
            button.click();
            logger.info("Options button clicked");
            wait.until(ExpectedConditions.visibilityOf(selectOptionModal));
            return new SelectOptionModal(selectOptionModal, driver);
        } catch (Exception e) {
            logger.error("Error occurred while clicking option button", e);
            return null;
        }
    }

    public ShoppingCartEbayPage clickAddToCartButton() {
        try {
            addToCartButton.click();
            logger.info("Add to cart button clicked");
            return new ShoppingCartEbayPage(driver);
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
