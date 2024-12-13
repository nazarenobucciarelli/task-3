package com.solvd.task.gui.components;

import com.solvd.task.gui.pages.SearchResultsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Header extends AbstractComponent {

    @FindBy(css = "#gh-ac")
    private WebElement searchBox;

    @FindBy(css = ".gh-spr")
    private WebElement searchButton;

    @FindBy(css = "#gh-shop-a")
    private WebElement shopByCategoryButton;

    @FindBy(id = "gh-cart-n")
    private WebElement cartNumber;

    @FindBy(css = "#gh-sbc-o")
    private WebElement modal;

    WebDriver driver;

    public Header(WebElement root, WebDriver driver) {
        super(root);
        this.driver = driver;
    }

    public void typeSearchBox(String text) {
        try {
            searchBox.sendKeys(text);
            logger.info("Search box entered: " + text);
        } catch (Exception e) {
            logger.error("Error while typing search box: " + e);
        }
    }

    public SearchResultsPage clickSearchButton() {
        try {
            searchButton.click();
            logger.info("Search button clicked");
            return new SearchResultsPage(driver);
        } catch (Exception e) {
            logger.error("Error trying to click search button", e);
            return null;
        }
    }

    public ShopByCategoryModal clickShopByCategoryButton() {
        try {
            shopByCategoryButton.click();
            logger.info("Shop button clicked");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10L));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#gh-sbc-o")));
            return new ShopByCategoryModal(modal, driver);
        } catch (Exception e) {
            logger.error("Error trying to click shop by category button", e);
            return null;
        }
    }

    public int getCartNumber() {
        try {
            int cartNum = Integer.parseInt(cartNumber.getText());
            logger.info("Cart number is: " + cartNum);
            return cartNum;
        } catch (Exception e) {
            logger.info("Cart number is: 0");
            return 0;
        }
    }

}
