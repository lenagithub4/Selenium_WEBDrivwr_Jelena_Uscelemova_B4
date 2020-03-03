package my.tests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import sel.course.LoginTest;
import sel.course.SetDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class FindPageElementsTask4 extends Drivers {

    @Test
    public void adminMenu() {
        ScenarioLoginTask3 test = new ScenarioLoginTask3();
        test.login1();

        drv.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        List<WebElement> menuList = drv.findElements(By.cssSelector("ul#box-apps-menu li#app-"));

        for (int i = 0; i < menuList.size(); i++) {
            menuList = drv.findElements(By.cssSelector("ul#box-apps-menu li#app-"));
            WebElement menuItem = menuList.get(i);

            menuItem.click();

            if (drv.getTitle().equals("")){
                AssertionError assertError = new AssertionError();
                System.out.println("FAILED. Found page without title. Page URL is " + drv.getCurrentUrl()+ " ." +assertError.getMessage());
                Assert.fail();
            }
            System.out.println("Page title is " + drv.getTitle());

            drv.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            if ((drv.findElements(By.cssSelector("ul.docs li")).size() > 0)) {
                List<WebElement> subMenuList = drv.findElements(By.cssSelector("ul#box-apps-menu li li"));

                for (int j = 1; j < subMenuList.size(); j++) {
                    subMenuList = drv.findElements(By.cssSelector("ul#box-apps-menu li li"));
                    WebElement subMenuItem = subMenuList.get(j);

                    subMenuItem.click();

                    if (drv.getTitle().equals("")){
                        AssertionError assertError = new AssertionError();
                        System.out.println("FAILED. Found page without title. Page URL is " + drv.getCurrentUrl()+ " ." +assertError.getMessage());
                        Assert.fail();
                    }
                    System.out.println("Page title is " + drv.getTitle());

                    drv.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                }
            }
        }
    }
}
