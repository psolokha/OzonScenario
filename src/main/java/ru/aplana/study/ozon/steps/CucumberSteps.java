package ru.aplana.study.ozon.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.ru.Дано;
import ru.aplana.study.ozon.utils.DriverController;

public class CucumberSteps {

    private AllureSteps allure;

    @Before
    public void driverInit(){
        DriverController.getDriver();
        allure = new AllureSteps();
    }

    @After
    public void driverQuit(){
        DriverController.quitDriver();
    }

    @Дано("Выполните поиск по «(.*)»")
    public void searchItem(String item) {
        allure.searchItem(item);
    }

    @Дано("Ограничить цену до (.*)")
    public void setMaxPrice(String price) {
        allure.setMaximumPrice(price);
    }

    @Дано("Отметить чекбокс – (.*)")
    public void checkboxClick(String checkbox) {
        allure.setFilter(checkbox);
    }

    @Дано("Из результатов поиска добавьте в корзину первые (.*) (.*) товаров.")
    public void addItemsToCart(String number, String odd) {
        allure.addItemsToCart(number, odd);
    }

    @Дано("Перейдите в корзину, убедитесь, что все добавленные ранее товары находятся в корзине")
    public void checkItemsInCart() {
        allure.checkItemsInCart();
    }

    @Дано("Проверить, что отображается текст «(.*)»")
    public void checkTextInCart(String text) {
        allure.checkText(text);
    }

    @Дано("Удалите все товары из корзины")
    public void deleteAllItems() {
        allure.deleteAllItemsFromCart();
    }

    @Дано("Проверьте, что корзина не содержит никаких товаров")
    public void checkCartIsEmpty() {
        allure.checkEmptyCart();
    }

    @Дано("Добавить отчет с максимальной ценой")
    public void getAllureReport() {
        allure.addReport();
    }

    @Дано("Бренды : (.*)")
    public void filterBrands(String brands) {
        allure.filterBrands(brands);
    }

}
