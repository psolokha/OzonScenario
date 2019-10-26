package ru.aplana.study.ozon.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.aplana.study.ozon.utils.ItemsContainer;

import java.util.List;

public class SearchResultsPage extends AbstractPage {

    private String itemName = ".//span[@data-test-id = 'tile-name']";
    String itemPrice = ".//span[@data-test-id = 'tile-price']";
    String itemAdd = ".//button[contains(@class, 'buy-text-button')]/div";

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

    public void filterRAM(String ram) {
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

    public void filterRating() {
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

    public String getItemName(WebElement element) {return element.findElement(By.xpath(itemName)).getText().replaceAll("\"\\s", "");}
    public String getItemName(Integer index) {return getItemByIndex(index).findElement(By.xpath(itemName)).getText().replaceAll("\"\\s", "");}
    public String getItemName(String index) {return getItemByIndex(Integer.parseInt(index)).findElement(By.xpath(itemName)).getText().replaceAll("\"\\s", "");}

    public Integer getItemPrice(WebElement element) {return Integer.parseInt(element.findElement(By.xpath(itemPrice)).getText().replaceAll("\\D", ""));}
    public Integer getItemPrice(Integer index) {return Integer.parseInt(getItemByIndex(index).findElement(By.xpath(itemPrice)).getText().replaceAll("\\D", ""));}
    public Integer getItemPrice(String index) {return Integer.parseInt(getItemByIndex(Integer.parseInt(index)).findElement(By.xpath(itemPrice)).getText().replaceAll("\\D", ""));}

    public void addToCart(Integer index) {
        smartClick(getItemByIndex(index).findElement(By.xpath(itemAdd)));
        ItemsContainer.putItem(getItemName(index), getItemPrice(index));
    }
}
