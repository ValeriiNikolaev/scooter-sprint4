package ru.yandex.qa.scooter;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class mainPageLocator {
    private final WebDriver driver;

    //список вопросов
    private final By questList = By.xpath(".//div[@class='accordion']");

    // кнопка "да все привыкли" закрыть
    private final By cookieCloseButton = By.className("App_CookieButton__3cvqF");

    // кнопка первая Заказать
    private final By firstOrderButton = By.xpath(".//button[@class='Button_Button__ra12g']");

    //вторая кнопка Заказать
    private final By secondOrderButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    //параметрезируем вопросы и ответы
    private String questLocator (int questNumber){
        return String.format("accordion__heading-%s", questNumber);
    }

    private String answerLocator (int questNumber){
        return String.format("accordion__panel-%s", questNumber);
    }

    //конструктор
    public mainPageLocator(WebDriver driver) {
        this.driver = driver;
    }

    public void clickQuest(int questNumber) {
        //вопрос
        By quest = By.id(questLocator(questNumber));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(quest));
        driver.findElement(quest).click();
    }
    public String answerToQuest(int questNumber) {
        //ответ
        By answer = By.id(answerLocator(questNumber));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(answer));
        return driver.findElement(answer).getText();
    }

    public void clickFirstOrderButton(){
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(firstOrderButton));
        new WebDriverWait(driver, 2).until(ExpectedConditions.elementToBeClickable(firstOrderButton));
        driver.findElement(firstOrderButton).click();
    }

    public void clickSecondOrderButton(){
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(secondOrderButton));
        new WebDriverWait(driver, 2).until(ExpectedConditions.elementToBeClickable(secondOrderButton));
        driver.findElement(secondOrderButton).click();
    }

}

