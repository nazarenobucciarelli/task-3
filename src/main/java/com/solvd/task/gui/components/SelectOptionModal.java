package com.solvd.task.gui.components;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Random;

public class SelectOptionModal extends AbstractComponent {

    @FindBy(css = "div[role='listbox'] div[role='option']:not([aria-disabled])")
    private List<WebElement> availableOptions;

    public SelectOptionModal(WebElement root) {
        super(root);
    }

    public void selectRandomOption() {
        try {
            int randomIndex = new Random().nextInt(availableOptions.size());
            if (randomIndex == 0) {
                randomIndex = 1;
            }
            availableOptions.get(randomIndex).click();
            logger.info("Selected size " + availableOptions.get(randomIndex).getText());
        } catch (Exception e) {
            logger.error("Error while trying to select random option", e);
        }
    }
}