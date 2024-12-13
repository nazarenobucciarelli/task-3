package com.solvd.task.gui;

import com.solvd.task.gui.components.*;
import com.solvd.task.gui.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class EbayTests extends AbstractGUITest {

    @Test(enabled = false)
    public void testSearchResults() {
        HomePage homePage = new HomePage(getDriver());
        Header header = homePage.getHeader();
        header.typeSearchBox("football jerseys");
        SearchResultsPage searchResultsPage = header.clickSearchButton();
        List<Product> products = searchResultsPage.getProducts();
        products.forEach(product -> {
            Assert.assertTrue(!product.getTitle().isEmpty() && !product.getPrice().isEmpty());
        });
    }

    @Test(enabled = false)
    public void testShoppingCartAdd() {
        List<String> productTitles = new ArrayList<>();

        ShoppingCartPage shoppingCartPage = addProductToShoppingCart(productTitles, "t-shirts");
        Header shoppingCartHeader = shoppingCartPage.getHeader();

        Assert.assertEquals(shoppingCartHeader.getCartNumber(), 1);
        shoppingCartPage.getProductTitles().forEach(productTitle -> {
            Assert.assertTrue(productTitles.contains(productTitle));
        });
    }

    public ShoppingCartPage addProductToShoppingCart(List<String> productTitles, String search) {
        HomePage homePage = new HomePage(getDriver());
        Header header = homePage.getHeader();
        header.typeSearchBox(search);
        SearchResultsPage searchResultsPage = header.clickSearchButton();
        ProductPage productPage = searchResultsPage.clickOnRandomProduct();
        boolean isAddToCartButtonPresent = productPage.isAddToCartButtonPresent();
        while(!isAddToCartButtonPresent){
            getDriver().close();

            Set<String> windowHandles = getDriver().getWindowHandles();
            String mainWindowHandle = windowHandles.iterator().next();
            getDriver().switchTo().window(mainWindowHandle);

            productPage = searchResultsPage.clickOnRandomProduct();
            isAddToCartButtonPresent = productPage.isAddToCartButtonPresent();
        }
        productTitles.add(productPage.getProductName());

        productPage.selectRandomOptions();

        if (productPage.isConfirmDialogDisplayed()) {
            Dialog dialog = productPage.getConfirmDialog();
            dialog.clickConfirmButton();
        }

        return productPage.clickAddToCartButton();
    }

    @Test(enabled = true)
    public void testShoppingCartRemove() {
        List<String> productTitles = new ArrayList<>();

        ShoppingCartPage shoppingCartPage = addProductToShoppingCart(productTitles, "t-shirt");
        Header shoppingCartHeader = shoppingCartPage.getHeader();
        shoppingCartPage.getCartProducts().forEach(CartProduct::clickRemoveButton);

        Assert.assertEquals(shoppingCartHeader.getCartNumber(), 0);
        shoppingCartPage.getProductTitles().forEach(productTitle -> {
            Assert.assertFalse(productTitles.contains(productTitle));
        });
    }

    @Test(enabled = true)
    public void testWrongLoginAttempt() {
        List<String> productTitles = new ArrayList<>();

        ShoppingCartPage shoppingCartPage = addProductToShoppingCart(productTitles, "t-shirt");
        Header shoppingCartHeader = shoppingCartPage.getHeader();
        shoppingCartPage.getCartProducts().forEach(CartProduct::clickRemoveButton);

        Assert.assertEquals(shoppingCartHeader.getCartNumber(), 0);
        shoppingCartPage.getProductTitles().forEach(productTitle -> {
            Assert.assertFalse(productTitles.contains(productTitle));
        });
    }

}
