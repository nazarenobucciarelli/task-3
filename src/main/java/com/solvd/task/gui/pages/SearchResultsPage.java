package com.solvd.task.gui.pages;

import com.solvd.task.gui.components.Product;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
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
}
