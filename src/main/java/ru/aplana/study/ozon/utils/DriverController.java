package ru.aplana.study.ozon.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class DriverController {

    private static DriverController instance;
    private static WebDriver driver;

    private DriverController() {
        Properties properties = ProjectProperties.getProperties();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Long.getLong(properties.getProperty("timeouts.implicityWait")), TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(Long.getLong(properties.getProperty("timeouts.pageLoad")), TimeUnit.SECONDS);

    }

    public static WebDriver getDriver() {
        if (instance == null) instance = new DriverController();
        return driver;
    }
}
