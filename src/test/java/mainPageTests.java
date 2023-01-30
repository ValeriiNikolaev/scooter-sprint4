import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.yandex.qa.scooter.mainPageLocator;

@RunWith(Parameterized.class)
public class mainPageTests {
    private WebDriver driver;
    private final int questNumber;
    private final String expectedAnswer;

    public mainPageTests(int questNumber, String expectedAnswer){
        this.questNumber = questNumber;
        this.expectedAnswer = expectedAnswer;
    }

    @Parameterized.Parameters
    public static Object[][] answerText() {
        return new Object[][] {
                {0, "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {1, "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {2, "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {3, "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {4, "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {5, "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {6, "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {7, "Да, обязательно. Всем самокатов! И Москве, и Московской области."},
        };
    }

    public void openWeb() {
        //driver = new ChromeDriver();
        driver = new FirefoxDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }
    @Test
    public void checkQuestListTests(){
        openWeb();
        mainPageLocator objMainPage = new mainPageLocator(driver);
        objMainPage.clickQuest(questNumber);
        String actual = objMainPage.answerToQuest(questNumber);
        Assert.assertEquals(actual, expectedAnswer);
    }

    @After
    public void closeWeb() {
        // Закрыть браузер
        driver.quit();
    }
}
