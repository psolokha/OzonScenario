package ru.aplana.study.ozon;

import org.junit.Ignore;
import org.junit.Test;
import ru.aplana.study.ozon.pages.CartPage;
import ru.aplana.study.ozon.pages.SearchResultsPage;
import ru.aplana.study.ozon.steps.AllureSteps;
import ru.aplana.study.ozon.utils.DriverController;
import ru.aplana.study.ozon.utils.ItemsContainer;

public class SimpleTest {

    @Test
    @Ignore
    public void brandTest() {
        DriverController.getDriver().get("https://www.ozon.ru/category/naushniki-i-garnitury-15546/?text=%D0%B1%D0%B5%D1%81%D0%BF%D1%80%D0%BE%D0%B2%D0%BE%D0%B4%D0%BD%D1%8B%D0%B5+%D0%BD%D0%B0%D1%83%D1%88%D0%BD%D0%B8%D0%BA%D0%B8&from_global=true");
        AllureSteps allure = new AllureSteps();
        allure.filterBrands("Beats, Samsung");
    }

    @Test
    @Ignore
    public void simpleTest() {
        DriverController.getDriver().get("https://www.ozon.ru/category/naushniki-i-garnitury-15546/?text=%D0%B1%D0%B5%D1%81%D0%BF%D1%80%D0%BE%D0%B2%D0%BE%D0%B4%D0%BD%D1%8B%D0%B5+%D0%BD%D0%B0%D1%83%D1%88%D0%BD%D0%B8%D0%BA%D0%B8&from_global=true");
        SearchResultsPage result = new SearchResultsPage();

        result.setHighPrice("10 000");
        result.filterBrands("Beats", "Samsung");
        result.addToCart(1);
        result.addToCart(3);
        result.addToCart(5);
        result.addToCart(7);





        result.goToCart();

        CartPage cart = new CartPage();

        System.out.println(cart.getTotalItemsMessage());

        System.out.println(ItemsContainer.getInstance().toString());

        cart.deleteAllItems();

        System.out.println(cart.getEmptyCartMessage());





    }
}
