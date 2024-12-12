package com.solvd.task.gui.pages;

import com.solvd.task.gui.components.Header;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractPage {

    WebDriver driver;

    protected static final Logger logger = LoggerFactory.getLogger(AbstractPage.class);

    @FindBy(css = "#globalHeaderWrapper")
    protected WebElement header;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public Header getHeader() {
        return new Header(header, driver);
    }
}
