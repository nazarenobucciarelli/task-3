package com.solvd.task.gui.components;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractComponent {

    protected WebElement root;

    protected static final Logger logger = LoggerFactory.getLogger(AbstractComponent.class);

    public AbstractComponent(WebElement root) {
        this.root = root;
        PageFactory.initElements(root, this);
    }

}
