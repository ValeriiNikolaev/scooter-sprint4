import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.yandex.qa.scooter.mainPageLocator;
import ru.yandex.qa.scooter.orderPageLocator;


@RunWith(Parameterized.class)
public class OrderTests {

    private WebDriver driver;
    private final String name;
    private final String surname;
    private final String address;
    private final String stationMetro;
    private final String phone;
    private final String rentDate;
    private final int duration;
    private final String color;
    private final String comment;
    private  final  String orderButtons;

    public OrderTests(String orderButtons, String name, String surname, String address, String stationMetro, String phone, String rentDate, int duration, String color, String comment){
        this.orderButtons = orderButtons;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.stationMetro = stationMetro;
        this.phone = phone;
        this.rentDate = rentDate;
        this.duration = duration;
        this.color = color;
        this.comment = comment;
    }
    @Parameterized.Parameters
    public static Object[][] userData() {
        return new Object[][]  {
                {".//button[@class='Button_Button__ra12g']", "Марина", "Чайкина", "Тайланд на море", "Митино", "88007775556", "02.02.2023", 4, "black", "упал, ничего стращного"},
                {".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']", "Колобок", "Сказочный", "Сингапур чайна таун", "Чистые пруды", "+79095057889", "06.02.2023", 2, "grey", "тили траливали"},
        };
    }
    @Before
    public  void openWeb() {
        driver = new ChromeDriver();
        //driver = new FirefoxDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    @Test
    public void orderFirstButtonTest() {
        mainPageLocator objMainPage = new mainPageLocator(driver);
        orderPageLocator objOrderPage = new orderPageLocator(driver);
        objMainPage.clickOrderButton(orderButtons);
        objOrderPage.fillUserDetailsForm(name, surname, address, stationMetro, phone);
        objOrderPage.clickNextButton();
        Assert.assertTrue(objOrderPage.checkRentFormIsDisplayed());
        objOrderPage.fillRentForm(rentDate, duration, color, comment);
        objOrderPage.clickConfirmOrderButton();
        objOrderPage.clickYesConfirmOrderButton();
        Assert.assertTrue(objOrderPage.checkOrderSuccessIsDisplayed());
    }


    @After
    public void closeWeb(){
        driver.quit();
    }

}
