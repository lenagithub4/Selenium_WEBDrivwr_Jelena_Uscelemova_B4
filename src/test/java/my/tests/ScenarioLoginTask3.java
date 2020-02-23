package my.tests;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebElement;


import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;


public class ScenarioLoginTask3 {


    private static final String LOGIN = "admin";
    private static final String PASSWORD = "admin";
    private WebDriver driver;
    private WebDriverWait wait;


    @Before
    public void start() {
        //System.setProperty("webdriver.gecko.driver", "C:\\Users\\user\\Documents\\Selenium WEBDriver\\geckodriver\\geckodriver.exe");
        //driver = new FirefoxDriver();
       // wait = new WebDriverWait(driver, 2000);
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 2000);

    }

    @Test
    public void LoginToAdmin() {
        driver.navigate().to("http://localhost/litecart/admin/login.php");
        driver.findElement(By.name("username")).sendKeys(LOGIN);
        driver.findElement(By.name("password")).sendKeys(PASSWORD);
        driver.findElement(By.name("login")).sendKeys(Keys.ENTER);
        wait.until(titleIs("My Store"));

         // get the "image1" element
       driver.findElement(By.className("logotype"));


    }
}



