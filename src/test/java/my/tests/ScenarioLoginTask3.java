package my.tests;

import org.junit.Test;
import org.junit.Assert;
import org.openqa.selenium.By;



import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;



    public class ScenarioLoginTask3 extends Drivers {

        @Test
        public void login1(){
            drv.navigate().to("http://localhost/litecart/admin/login.php");
            drv.findElement(By.name("username")).sendKeys("admin");
            drv.findElement(By.name("password")).sendKeys("admin");

            drv.findElement(By.name("login")).click();

            // get the "image1" element
            drv.findElement(By.className("logotype"));

           if (!drv.getCurrentUrl().equals("http://localhost/litecart/admin/")){
             AssertionError assertError = new AssertionError();
              System.out.println("FAILED: " +assertError.getMessage());
               System.out.println("Page title is " + drv.getCurrentUrl());
               Assert.fail();
            }
        }
    }
    //private static final String LOGIN = "admin";
   // private static final String PASSWORD = "admin";
   // private WebDriver driver;
   // private WebDriverWait wait;


   // @Before
    //public void start() {
        //System.setProperty("webdriver.gecko.driver", "C:\\Users\\user\\Documents\\Selenium WEBDriver\\geckodriver\\geckodriver.exe");
        //driver = new FirefoxDriver();
       // wait = new WebDriverWait(driver, 2000);
        //driver = new ChromeDriver();
        //wait = new WebDriverWait(driver, 2000);

   // }

   // @Test
   // public void LoginToAdmin() {
      //  driver.navigate().to("http://localhost/litecart/admin/login.php");
      //  driver.findElement(By.name("username")).sendKeys(LOGIN);
       // driver.findElement(By.name("password")).sendKeys(PASSWORD);
      //  driver.findElement(By.name("login")).sendKeys(Keys.ENTER);
     //   wait.until(titleIs("My Store"));

         // get the "image1" element
     //  driver.findElement(By.className("logotype"));


  //  }
//}



