package ru.aplana.study.ozon.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

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
        new WebDriverWait(driver, 20).ignoring(WebDriverException.class).until( new Function<WebDriver, Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                driver.findElement(By.xpath("//button[@class = 'close']")).click();
                return true;
            }
        });
    }


    public static WebDriver getDriver() {
        if (instance == null) instance = new DriverController();
        return driver;
    }
}
