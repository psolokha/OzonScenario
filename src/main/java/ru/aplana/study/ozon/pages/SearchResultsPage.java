package ru.aplana.study.ozon.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.aplana.study.ozon.utils.ItemsContainer;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static ru.aplana.study.ozon.utils.DriverController.getDriver;

public class SearchResultsPage extends AbstractPage {

    private String itemName = ".//span[@data-test-id = 'tile-name']";
    private String itemPrice = ".//span[@data-test-id = 'tile-price']";
    private String itemAdd = ".//button[contains(@class, 'buy-text-button')]/div";
    private String brandName = ".//span[@class = 'label-text']";

    @FindBy(xpath = "//div[contains(@class, 'paginator column__item_remove-margin')]")
    private WebElement resultBlock;

    @FindBy(xpath = "//div[contains(@class, 'search-result')]/div/div")
    private List<WebElement> resultSet;

    @FindBy(xpath = "//div[@data-test-id = 'filter-block-price']//input[@data-test-id = 'range-filter-to-input']")
    private WebElement fieldTo;

    @FindBy(xpath = "//div[@data-test-id = 'filter-block-price']//input[@data-test-id = 'range-filter-from-input']")
    private WebElement fieldFrom;

    @FindBy(xpath = "//div[@class = 'rating-filter']/label/span[@class = 'checkmark']")
    private WebElement checkboxRating;

    @FindBy(xpath = "//div[@data-test-id = 'filter-block-ram']//div[@class = 'items']/label")
    private List<WebElement> listCheackboxRAM;

    @FindBy(xpath = "//div[@data-test-id = 'filter-block-ram']")
    private WebElement filterRamBlock;

    @FindBy(xpath = "//div[@data-test-id = 'filter-block-brand']")
    private WebElement filterBrandsBlock;

    @FindBy(xpath = "//div[@data-test-id = 'filter-block-brand']//span[@class = 'show-text']")
    private  WebElement showAllBrands;

    @FindBy(xpath = "//div[@data-test-id = 'filter-block-brand']//input[@class = 'input']")
    private WebElement filterBrandsInput;

    @FindBy(xpath = "//div[@data-test-id = 'filter-block-brand']//label[@class = 'label']")
    private List<WebElement> listBrands;

    @FindBy(xpath = "//div[@data-test-id = 'filter-block-brand']//label[@class = 'label']//span[@class = 'checkmark']")
    private WebElement checkboxBrand;

    @FindBy(xpath = "//div[@class='search-show-container']/label")
    private WebElement brandItem;

    public void filterBrands(String... brands) {
        moveToElement(filterBrandsBlock);
        smartClick(showAllBrands);
        waitElement(filterBrandsInput);
        for (String str : brands) {
            waitElement(filterBrandsInput);
            waitElement(brandItem);
            smartClick(filterBrandsInput);
            filterBrandsInput.clear();
            filterBrandsInput.sendKeys(str);
            for (WebElement element : listBrands) {
                if (element.findElement(By.xpath(brandName)).getText()
                        .replaceAll("\\W", "")
                        .equalsIgnoreCase(str)) {
                    smartClick(checkboxBrand);
                    waitElement(resultBlock);
                }
            }
        }
    }

    private WebElement getItemByIndex(Integer index) {
        for (WebElement element: resultSet) {
            if (element.getAttribute("data-index").equals(String.valueOf(index))) {
                moveToElement(element);
                return element;
            }
        }
        System.out.println("Wrong index value");
        return null;
    }

    public void setFilter(String filter) {
        if ("Высокий рейтинг".equalsIgnoreCase(filter)) filterRating();
        else filterRAM(filter);
    }

    private void filterRAM(String ram) {
        moveToElement(filterRamBlock);
        for (WebElement element: listCheackboxRAM) {
            if (element.findElement(By.xpath(".//span[@class = 'label-text']")).getText().replaceAll("\\D", "").equalsIgnoreCase(ram.replaceAll("\\D", ""))) {
                element.findElement(By.xpath(".//span[@class = 'checkmark']")).click();
                waitElement(resultBlock);
                return;
            }
        }
        System.out.println("No such RAM value");
    }

    private void filterRating() {
        smartClick(checkboxRating);
        waitElement(resultBlock);
    }

    public void setHighPrice(Integer price) {
        clearField(fieldTo);
        fieldTo.sendKeys(String.valueOf(price));
    }
    public void setHighPrice(String price) {
        clearField(fieldTo);
        fieldTo.sendKeys(price.replaceAll("\\D", ""));
    }

    public void setLowPrice(Integer price) {
        clearField(fieldFrom);
        fieldFrom.sendKeys(String.valueOf(price));
    }
    public void setLowPrice(String price) {
        clearField(fieldFrom);
        fieldFrom.sendKeys(price.replaceAll("\\D", ""));
    }

    private String getItemName(Integer index) {
        return getItemByIndex(index).findElement(By.xpath(itemName))
                .getText().replaceAll("\"\\s", "");
    }

    private Integer getItemPrice(Integer index) {
        return Integer.parseInt(getItemByIndex(index).findElement(By.xpath(itemPrice))
                .getText().replaceAll("\\D", ""));
    }

    public void addToCart(Integer index) {
        waitPageLoaded();
        ((JavascriptExecutor) getDriver())
                .executeScript("return arguments[0].scrollIntoView(false)",  getItemByIndex(index));
        smartClick(getItemByIndex(index).findElement(By.xpath(itemAdd)));
        ItemsContainer.getInstance().putItem(getItemName(index), getItemPrice(index));
    }


    private boolean isElementPresent(By locator) {
        try{
            driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
            WebElement element = driver.findElement(locator);
            return element.isDisplayed();
        } catch (Exception e) {
            driver.manage().timeouts().implicitlyWait(45,TimeUnit.SECONDS);
            return false;
        }
    }

    private void waitPageLoaded() {
        new WebDriverWait(driver, 45)
                .until((ExpectedCondition<Boolean>) webDriver -> !isElementPresent(By.xpath("//div[contains(@class , 'parandja')]")));
    }
}
