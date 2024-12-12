package com.solvd.task.gui.components;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Product extends AbstractComponent {

    @FindBy(css = ".s-item__title span")
    private WebElement title;

    public Product(WebElement root) {
        super(root);
    }

    public String getTitle() {
        return this.title.getText();
    }

}
