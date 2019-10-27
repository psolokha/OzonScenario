package ru.aplana.study.ozon.pages;


import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.aplana.study.ozon.utils.DriverController;

import java.util.function.Function;

public abstract class AbstractPage {

    private WebDriver driver;

    AbstractPage(){
        driver = DriverController.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@class = 'search-input']")
    private WebElement searchInput;
    @FindBy(xpath = "//button[@data-test-id = 'header-search-go']")
    private WebElement searchGo;
    @FindBy(xpath = "//a[@href='/cart']")
    private WebElement cartButton;


    void clearField(WebElement element) {
        new Actions(driver).moveToElement(element).doubleClick().sendKeys(Keys.BACK_SPACE).build().perform();
    }

    void moveToElement(WebElement element){
        new Actions(driver).moveToElement(element).pause(500).build().perform();
    }

    void waitElement(WebElement element) {
        new WebDriverWait(driver, 10).ignoring(WebDriverException.class).until(ExpectedConditions.visibilityOf(element));
    }

    void pause() {
        new Actions(driver).pause(1000).build().perform();
    }

    void smartClick(WebElement element) {
        new WebDriverWait(driver, 10).ignoring(
                WebDriverException.class)
                .until(driver -> {
                    moveToElement(element);
                    element.click();
                    return true;
                });
    }

    public void searchItem(String itemToSearch) {
        waitElement(searchInput);
        searchInput.sendKeys(itemToSearch);
        searchGo.click();
    }

    public void goToCart() {
        waitElement(cartButton);
        cartButton.click();
    }
}
