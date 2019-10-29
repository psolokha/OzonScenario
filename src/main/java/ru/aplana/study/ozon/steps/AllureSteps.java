package ru.aplana.study.ozon.steps;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.junit.Assert;
import ru.aplana.study.ozon.pages.CartPage;
import ru.aplana.study.ozon.pages.MainPage;
import ru.aplana.study.ozon.pages.SearchResultsPage;
import ru.aplana.study.ozon.utils.ItemsContainer;

import java.util.StringTokenizer;

class AllureSteps {

    private SearchResultsPage results = new SearchResultsPage();
    private CartPage cart = new CartPage();

    @Step("Выполняем поиск {item} через строку поиска")
    void searchItem(String item) {
        MainPage main = new MainPage();
        main.searchItem(item);
    }

    @Step("Выставить ограничение до {maxPrice}")
    void setMaximumPrice(String maxPrice) {
        results.setHighPrice(maxPrice);
    }

    @Step("Выбрать фильтр {filter}")
    void setFilter(String filter) {
        results.setFilter(filter);
    }

    @Step("Добавить в корзину {num} {odd} товаров")
    void addItemsToCart(String num, String odd) {
        for (int i = Integer.parseInt(num), n = odd.contains("нечет")? 0 : 1; i != 0; i--, n = n +2) {
            results.addToCart(n);
        }
    }

    @Step("Перейти в корзину и проверить наличие товаров")
    void checkItemsInCart() {
        cart.goToCart();
        Assert.assertEquals(cart.getNumberItems(), ItemsContainer.getInstance().getNumberItems());
    }

    @Step("Провеить, что появился текст: «{text}»")
    void checkText(String text) {
        Assert.assertEquals(cart.getTotalItemsMessage(), text);
    }

    @Step("Удалить все товары из корзины")
    void deleteAllItemsFromCart() {
        cart.deleteAllItems();
    }

    @Step("Проверить, что корзина пуста")
    void checkEmptyCart() {
        Assert.assertEquals(cart.getEmptyCartMessage(), "Корзина пуста");
    }

    @Step("Добавить файл с отчетом, указав товар с самой большой ценой")
    void addReport() {
        String resultReport = ItemsContainer.getInstance().toString() +
                "\nТовар с наибольшей ценой: \n" +
                ItemsContainer.getInstance().checkMostExpensive();
        Allure.addAttachment("testReport", resultReport);
        ItemsContainer.getInstance().removeAll();
    }

    @Step("Отфильтровать по брендам {brands}")
    void filterBrands(String brands) {
        StringTokenizer tokenizer = new StringTokenizer(brands, "., ;/\\\"'");
        String[] brandsList = new String[tokenizer.countTokens()];
        int i = 0;
        while (tokenizer.hasMoreElements()) brandsList[i++] = tokenizer.nextToken();
        results.filterBrands(brandsList);
    }

}
