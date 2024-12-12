package com.solvd.task.gui;

import com.solvd.task.gui.components.Header;
import com.solvd.task.gui.components.Product;
import com.solvd.task.gui.components.ShopByCategoryModal;
import com.solvd.task.gui.pages.CategoryPage;
import com.solvd.task.gui.pages.HomePage;
import com.solvd.task.gui.pages.SearchResultsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class SearchTest extends AbstractGUITest {

    @Test(enabled = true)
    public void testSearchResults() {
        HomePage homePage = new HomePage(getDriver());
        Header header = homePage.getHeader();
        header.typeSearchBox("something");
        SearchResultsPage searchResultsPage = header.clickSearchButton();
        List<Product> products = searchResultsPage.getProducts();
        Assert.assertFalse(products.isEmpty());
        products.forEach(product -> {
            logger.info(product.getTitle());
        });
    }

    @Test(enabled = true)
    public void testEnterCategory() {
        HomePage homePage = new HomePage(getDriver());
        Header header = homePage.getHeader();
        ShopByCategoryModal shopByCategoryModal = header.clickShopByCategoryButton();
        CategoryPage categoryPage = shopByCategoryModal.clickRandomCategory();
        Assert.assertTrue(categoryPage.isNavigationDisplayed());
    }

}
