package com.solvd.task.gui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class ShoppingCartPage extends AbstractPage {

    @FindBy(css = "h3.item-title")
    private List<WebElement> productTitles;

    public ShoppingCartPage(WebDriver driver) {
        super(driver);
    }

    public List<String> getProductTitles() {
        return productTitles.stream().map(WebElement::getText).collect(Collectors.toList());
    }
}
