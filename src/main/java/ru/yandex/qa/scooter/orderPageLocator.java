package ru.yandex.qa.scooter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class orderPageLocator {
    private final WebDriver driver;

    //поле Имя
    private final By fieldName = By.xpath(".//input[@placeholder='* Имя']");

    //поле Фамилия
    private final By fieldSurname = By.xpath(".//input[@placeholder='* Фамилия']");

    //поле Адрес
    private final By fieldAddress = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");

    //поле Станция метро
    private final By fieldStationMetro = By.className("select-search__input");

    //поле Телефон
    private final By fieldPhone = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");

    //кнопка Далее
    private final By nextButton = By.xpath(".//div[@class='Order_NextButton__1_rCA']/button");

    //поле Дата привоза
    private final By dateOfSubmission = By.xpath(".//input[@placeholder='* Когда привезти самокат']");

    // Срок аренды
    private final By rentList = By.xpath(".//span[@class='Dropdown-arrow']");

    //поле Коммент
    private final By fieldComment = By.xpath(".//input[@placeholder='Комментарий для курьера']");

    //кнопка Заказать
    private final By orderButton = By.xpath(".//div[@class='Order_Buttons__1xGrp']/button[@class='Button_Button__ra12g Button_Middle__1CSJM']");


    //кнопка подтверждения заказа
    private final By buttonYes = By.xpath(".//div[@class='Order_Modal__YZ-d3']//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    //отменить заказ
    private final By buttonNo = By.xpath(".//div[@class='Order_Modal__YZ-d3']//button[@class='Button_Button__ra12g Button_Middle__1CSJM Button_Inverted__3IF-i']");

    //ПОПАП об успешном заказе
    private final By orderSuccess = By.className("Order_Modal__YZ-d3");

    public orderPageLocator(WebDriver driver) {
        this.driver = driver;
    }

    //параметризуем станции метро
    public void fillStationMetro (String stationMetro){
        driver.findElement(fieldStationMetro).sendKeys(stationMetro);
        new WebDriverWait(driver, 2).until(ExpectedConditions.visibilityOfElementLocated(By.className("select-search__select")));
        driver.findElement(By.className("select-search__select")).click();
    }

    public void fillName(String name) {

        driver.findElement(fieldName).sendKeys(name);
    }

    public void fillSurname(String surname) {
        driver.findElement(fieldSurname).sendKeys(surname);
    }

    public void fillAddress(String address) {
        driver.findElement(fieldAddress).sendKeys(address);
    }

    public void fillPhone(String phone) {

        driver.findElement(fieldPhone).sendKeys(phone);
    }

    public void clickNextButton() {
        driver.findElement(nextButton).click();
    }

    public void dateOfSubmission(String rentdate){
        driver.findElement(dateOfSubmission).sendKeys(rentdate);
    }

    public void selectRentDuration(int duration){
        driver.findElement(rentList).click();
        String selectRent = String.format(".//div[@class='Dropdown-menu']/div[%s]", duration);
        driver.findElement(By.xpath(selectRent)).click();
    }

    public void selectColors(String color){
        //поле Цвет
        String selectColor = ".//label/input[@id='%s']";
        String colorLocator = String.format(selectColor, color);
        driver.findElement(By.xpath(colorLocator)).click();
    }

    public void fillComment (String comment){
        driver.findElement(fieldComment).sendKeys(comment);
    }

    public void clickConfirmOrderButton(){
        driver.findElement(orderButton).click();
    }

    public void clickYesConfirmOrderButton(){
        driver.findElement(buttonYes).click();
    }

    public void clickNoConfirmOrderButton(){
        driver.findElement(buttonNo).click();
    }
    public void fillUserDetailsForm(String name, String surname, String address, String stationMetro, String phone){
        fillName(name);
        fillSurname(surname);
        fillAddress(address);
        fillStationMetro(stationMetro);
        fillPhone(phone);
    }

    public boolean checkRentFormIsDisplayed() {
        new WebDriverWait(driver, 4).until(ExpectedConditions.visibilityOfElementLocated(rentList));
        return driver.findElement(rentList).isDisplayed();
    }
    public void fillRentForm(String rentDate, int duration, String color, String comment){
        dateOfSubmission(rentDate);
        selectRentDuration(duration);
        selectColors(color);
        fillComment(comment);
    }

    public boolean checkOrderSuccessIsDisplayed() {
        new WebDriverWait(driver, 4).until(ExpectedConditions.visibilityOfElementLocated(orderSuccess));
        return driver.findElement(orderSuccess).isDisplayed();
    }

}
