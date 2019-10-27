package ru.aplana.study.ozon.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CartPage extends AbstractPage {

    @FindBy(xpath = "//div[@class = 'cart-item']")
    private List<WebElement> listCartItems;

    @FindBy(xpath = "//button[@class='button button blue']")
    private WebElement confirmDeleteButton;

    @FindBy(xpath = "//h1[contains(text(),'Корзина пуста')]")
    private WebElement emptyCartMessage;

    @FindBy(xpath = "//span[@class='total-middle-header-title']")
    private WebElement yourCartMessage;

    @FindBy(xpath = "//span[@class='total-middle-header-text']")
    private WebElement numberItemsMessage;

    @FindBy(xpath = "//div[@delete_button_name='Удалить выбранные']/span")
    private WebElement clickDeleteAllItems;

    public Integer getNumberItems() {
        return listCartItems.size();
    }

    public String getTotalItemsMessage() {
        waitElement(numberItemsMessage);
        String tmpText = numberItemsMessage.getText().replaceAll("[^•]+$", "");
        return yourCartMessage.getText() + " - " + tmpText.substring(0,tmpText.length()-2);
    }

    public void deleteAllItems() {
        clickDeleteAllItems.click();
        smartClick(confirmDeleteButton);
    }

    public String getEmptyCartMessage() {
        waitElement(emptyCartMessage);
        return emptyCartMessage.getText();
    }

}
