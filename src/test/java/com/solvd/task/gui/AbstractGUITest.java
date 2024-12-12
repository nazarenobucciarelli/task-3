package com.solvd.task.gui;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class AbstractGUITest {

    private static final ThreadLocal<WebDriver> driverThreadLocal = ThreadLocal.withInitial(() -> {
        String driverPath = System.getProperty("user.dir") + "/src/test/resources/chromedriver";
        System.setProperty("webdriver.chrome.driver", driverPath);
        return new ChromeDriver();
    });

    protected static final Logger logger = LoggerFactory.getLogger(AbstractGUITest.class);

    protected WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    @BeforeMethod
    public void setUp() {
        WebDriver driver = getDriver();
        driverThreadLocal.set(driver);
        getDriver().get("https://www.ebay.com");
    }

    @AfterMethod
    public void tearDown() {
        WebDriver driver = getDriver();
        if (driver != null) {
            driver.quit();
            driverThreadLocal.remove();
        }
    }

}
