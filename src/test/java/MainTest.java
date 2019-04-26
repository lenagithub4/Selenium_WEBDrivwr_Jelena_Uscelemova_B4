import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class MainTest {

    private static final String SEARCH = "ryanair";
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 1000);

    }
    @Test
    public void myFirstTest() {
        driver.navigate().to("http://www.google.com");
        driver.findElement(By.name("q")).sendKeys(SEARCH);
        driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
        wait.until(titleIs(SEARCH + " - Google Search"));
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

}
