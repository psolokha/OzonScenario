package ru.aplana.study.ozon.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends AbstractPage {

    @FindBy(xpath = "//button[@class = 'close']")
    private WebElement closeCookiesWarning;

    public MainPage(){
        waitElement(closeCookiesWarning);
        closeCookiesWarning.click();
    }

}
