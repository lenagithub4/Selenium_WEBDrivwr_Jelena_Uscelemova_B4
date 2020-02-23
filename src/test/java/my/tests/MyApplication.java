package my.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import sel.course.project.pageobjects.CartPage;
import sel.course.project.pageobjects.ItemPage;
import sel.course.project.pageobjects.MainPage;

/**
 * Created by Olga on 12/20/2016.
 */
public class MyApplication {
    private WebDriver wd;
    private WebDriverWait wait;

    public MainPage mainPage;
    public ItemPage itemPage;
    public CartPage cartPage;


    public MyApplication(){
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setBrowserName("chrome");
        wd = new ChromeDriver(caps);
        wait = new WebDriverWait(wd, 30);

        mainPage = new MainPage(wd, wait);
        itemPage = new ItemPage(wd, wait);
        cartPage = new CartPage(wd, wait);
    }

    public void goToPage(String string){
        wd.navigate().to(string);
    }


    public void quit(){
        wd.quit();
    }
}
