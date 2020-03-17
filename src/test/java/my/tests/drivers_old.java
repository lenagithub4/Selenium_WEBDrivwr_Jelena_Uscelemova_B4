package my.tests;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

public class drivers_old {


        public static WebDriver drv;
        public WebDriverWait wait;


        @Before
        public void start() {
            //chrome
            //if (drv == null) {
            //    DesiredCapabilities caps = new DesiredCapabilities();
            //  caps.setBrowserName("chrome");
            //   drv = new ChromeDriver(caps);
            //  wait = new WebDriverWait(drv, 2000);
            // }

            //firefox
            // if (drv==null){
            // System.setProperty("webdriver.gecko.driver", "C:/Users/user/Documents/Selenium WEBDriver/geckodriver/geckodriver.exe");
            // drv = new FirefoxDriver();
            //  wait = new WebDriverWait(drv, 2000);
            // }

            //ie
            if (drv==null) {
                DesiredCapabilities caps = new DesiredCapabilities();
                // caps.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);
                //caps.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
                //caps.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
                drv = new InternetExplorerDriver();
                drv.manage().window().maximize();
                wait = new WebDriverWait(drv, 10);
            }

            // System.out.println(((HasCapabilities) drv).getCapabilities());

            // drv.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            // wait = new WebDriverWait(drv, 10);
        }

        @After
        public void quit(){
            drv.quit();}
    }


