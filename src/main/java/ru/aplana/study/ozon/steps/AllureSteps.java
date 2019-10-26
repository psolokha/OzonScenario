package ru.aplana.study.ozon.steps;

import io.qameta.allure.Step;
import ru.aplana.study.ozon.pages.MainPage;

class AllureSteps {

    @Step("Выполняем поиск {item} через строку поиска")
    void searchItem(String item) {
        MainPage main = new MainPage();
        main.serchItem(item);
    }

}
