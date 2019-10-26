package ru.aplana.study.ozon.steps;

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

//    @After
//    public void driverQuit(){
//        DriverController.getDriver().quit();
//    }

    @Дано("Выполните поиск по (.*)")
    public void searchItem(String item) {
        allure.searchItem(item);
    }


}
