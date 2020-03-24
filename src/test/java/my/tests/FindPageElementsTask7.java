package my.tests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import java.util.concurrent.TimeUnit;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class FindPageElementsTask7 extends Drivers {

    @Test
    public void MenuAdminka() {
        ScenarioLoginTask3 test = new ScenarioLoginTask3();
        test.login2(); // логинимся

        drv.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS); //вызовы элементов driver.findElement(), пока элемент не будет найден или будет достигунта граница времени ожидания.
        List<WebElement> menuAdminkaList = drv.findElements(By.cssSelector("ul#box-apps-menu li#app-"));
       // List<WebElement> menuHeader = drv.findElements(By.cssSelector("h1"));

        for (int i = 0; i < menuAdminkaList.size(); i++) {
            menuAdminkaList = drv.findElements(By.cssSelector("ul#box-apps-menu li#app-")); // находим меню Админки по селектору
            WebElement menuAdminkaElement = menuAdminkaList.get(i);
           // menuHeader = drv.findElements(By.id("text"));

            // print of menu element on what I will click
            System.out.println("Element of menu: " + menuAdminkaList.get(i).getAttribute("outerText") );

            //click on menu Element
            menuAdminkaElement.click();

                    if (drv.getTitle().equals("")){
                        AssertionError assertError = new AssertionError();
                        System.out.println("WARNING! No Title for Page: " + drv.getCurrentUrl()+ " ." +assertError.getMessage());
                        Assert.fail();
                    }

                // что я просмотрела / список

                System.out.println("    Page Title: " + drv.getTitle());
                System.out.println(" --------------------------- " );
                drv.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);



                     // submenu
                    if ((drv.findElements(By.cssSelector("ul#box-apps-menu li#app-.selected ul.docs li")).size() > 0)) {
                        List<WebElement> subMenuAdminkaList = drv.findElements(By.cssSelector("ul#box-apps-menu li#app- ul.docs li"));

                        for (int j = 0; j < subMenuAdminkaList.size(); j++) {
                            subMenuAdminkaList = drv.findElements(By.cssSelector("ul#box-apps-menu li#app- ul.docs li"));
                            WebElement subMenuAdminkaElement = subMenuAdminkaList.get(j);
                           // menuHeader = drv.findElements(By.id("text"));
                            System.out.println("   Element of subMenu: " + subMenuAdminkaList.get(j).getAttribute("outerText") );
                            subMenuAdminkaElement.click();

                            if (drv.getTitle().equals("")){
                                AssertionError assertError = new AssertionError();
                                System.out.println("WARNING! No Title for Page:" + drv.getCurrentUrl()+ " ." +assertError.getMessage());
                                Assert.fail();
                            }

                            System.out.println("       subPage Title: " + drv.getTitle());
                            System.out.println(" --------------------------- " );
                            drv.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS); // вызываю, пока не появится элемент
                        }
                        drv.navigate().to("http://localhost/litecart/admin");
                        menuAdminkaList = drv.findElements(By.cssSelector("ul#box-apps-menu li#app-"));
                    }

        }
    }
}
