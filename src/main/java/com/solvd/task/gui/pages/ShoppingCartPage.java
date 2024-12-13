package com.solvd.task.gui.pages;

import com.solvd.task.gui.components.CartProduct;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class ShoppingCartPage extends AbstractPage {

    @FindBy(css = "div.cart-bucket")
    private List<WebElement> cartProducts;


    private WebDriver driver;

    public ShoppingCartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public List<CartProduct> getCartProducts() {
        return cartProducts.stream().map(product -> new CartProduct(product, driver)).collect(Collectors.toList());
    }

    public List<String> getProductTitles() {
        return getCartProducts().stream().map(CartProduct::getTitle).collect(Collectors.toList());
    }
}
