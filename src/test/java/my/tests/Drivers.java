package my.tests;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Drivers  {
    public static WebDriver drv;
    public WebDriverWait wait;


    @Before
    public void start() {

       //Chrome
    // if (drv == null) {
    // DesiredCapabilities caps = new DesiredCapabilities();
    // caps.setBrowserName("chrome");
    // drv = new ChromeDriver(caps);
    // wait = new WebDriverWait(drv, 2000);
     //}


        //IE
      // if (drv==null) {
      //DesiredCapabilities caps = new DesiredCapabilities();
      // drv = new InternetExplorerDriver(caps);
      //drv.manage().window().maximize();
    // wait = new WebDriverWait(drv, 30);
     // }

        //Firefox
      if (drv==null){
      System.setProperty("webdriver.gecko.driver", "C:/Users/user/Documents/Selenium WEBDriver/geckodriver/geckodriver.exe");
      drv = new FirefoxDriver();
      wait = new WebDriverWait(drv, 2000);
      }


    }

    @After
    public void quit(){
        drv.quit();}
}

