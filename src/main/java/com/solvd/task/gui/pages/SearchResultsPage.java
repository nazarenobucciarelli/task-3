package com.solvd.task.gui.pages;

import com.solvd.task.gui.components.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class SearchResultsPage extends AbstractPage {

    @FindBy(css = ".srp-results .s-item")
    List<WebElement> productElements;

    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }

    public List<Product> getProducts() {
        return productElements.stream()
                .map(Product::new)
                .collect(Collectors.toList());
    }

    public ProductPage clickOnRandomProduct() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10L));
            wait.until(webDriver -> !productElements.isEmpty() && productElements.get(0).isDisplayed());
            int randomIndex = new Random().nextInt(productElements.size());
            productElements.get(randomIndex).findElement(By.cssSelector(".s-item__title")).click();
            logger.info("Clicked on Random Product");

            Set<String> windowHandles = driver.getWindowHandles();
            String currentWindowHandle = driver.getWindowHandle();

            for (String windowHandle : windowHandles) {
                if (!windowHandle.equals(currentWindowHandle)) {
                    driver.switchTo().window(windowHandle);
                    break;
                }
            }
            return new ProductPage(driver);
        } catch (Exception e) {
            logger.error("Error while clicking random product", e);
            return null;
        }
    }
}
