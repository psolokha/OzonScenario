package ru.aplana.study.ozon;

import org.junit.Test;
import org.openqa.selenium.By;
import ru.aplana.study.ozon.pages.CartPage;
import ru.aplana.study.ozon.pages.SearchResultsPage;
import ru.aplana.study.ozon.utils.DriverController;
import ru.aplana.study.ozon.utils.ItemsContainer;

public class SimpleTest {

    @Test
    public void simpleTest() {
        DriverController.getDriver().get("https://www.ozon.ru/category/smartfony-15502/?text=iphone&from_global=true");
        SearchResultsPage result = new SearchResultsPage();

        result.setHighPrice("60 000");
        result.filterRAM("3");
        result.filterRating();
        result.addToCart(0);
        result.addToCart(2);
        result.addToCart(4);
        result.goToCart();

        CartPage cart = new CartPage();

        System.out.println(cart.getTotalItemsMessage());

        cart.deleteAllItems();

        System.out.println(cart.getEmptyCartMessage());


//        DriverController.getDriver().findElement(By.xpath("//button[contains(@class, 'buy-text-button')]/div")).click();


//        for (int i = 5, n = 0; i != 0; i--, n = n +2) {
//            result.addToCart(n);
//        }


    }
}
