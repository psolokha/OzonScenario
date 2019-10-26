package ru.aplana.study.ozon.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.aplana.study.ozon.utils.DriverController;

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


    void waitElement(WebElement element) {
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOf(element));
    }

    public void serchItem(String itemToSearch) {
        waitElement(searchInput);
        searchInput.sendKeys(itemToSearch);
        searchGo.click();
    }
}
