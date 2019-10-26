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
        System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Integer.parseInt(properties.getProperty("timeouts.implicitlyWait")), TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(Integer.parseInt(properties.getProperty("timeouts.pageLoad")), TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(properties.getProperty("url"));
    }

    public static WebDriver getDriver() {
        if (instance == null) instance = new DriverController();
        return driver;
    }
}
