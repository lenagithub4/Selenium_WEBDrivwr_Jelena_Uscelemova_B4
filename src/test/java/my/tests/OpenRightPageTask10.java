package my.tests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import sel.course.SetDriver;
import java.util.concurrent.TimeUnit;

public class OpenRightPageTask10  extends Drivers {

        @Test
        public void openRightPage(){
            drv.navigate().to("http://localhost/litecart/en/");
            System.out.println("The current page: " + drv.getCurrentUrl());


            drv.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

            // find 1st Duck in the div Campaigns
                WebElement firstDuck = drv.findElement(By.cssSelector("#box-campaigns .products li:nth-of-type(1)"));

            // find properties textContent of Duck's title
                 WebElement titleDuck = firstDuck.findElement(By.className("name"));
                 String nameDuck = titleDuck.getAttribute("textContent");

            // price Usual
               WebElement priceUsualDuck = firstDuck.findElement(By.className("regular-price"));
                 String priceUsual = priceUsualDuck.getAttribute("textContent"); //text
                   String priceUsualColor = priceUsualDuck.getCssValue("color"); //color
                      String priceUsualFontSize = priceUsualDuck.getCssValue("font-size"); //font-size


            // price New
                WebElement priceNewDuck = firstDuck.findElement(By.className("campaign-price"));
                  String priceNew =  priceNewDuck.getAttribute("textContent"); //text
                     String priceNewColor = priceNewDuck.getCssValue("color"); //color
                       String priceNewFontSize = priceNewDuck.getCssValue("font-size"); //font-size



            firstDuck.click();

            System.out.println("Duck opened Page: " + drv.getCurrentUrl());
            System.out.println("Title of page: " + drv.getTitle());

            // Duck°s opened page attributes

            WebElement nameDuckWindow = drv.findElement(By.cssSelector("div#box-product h1"));
            String nameDuckWin = nameDuckWindow.getAttribute("textContent"); //name of Duck in the opened window

              WebElement infoDuckWindow = drv.findElement(By.cssSelector("div#box-product"));

                WebElement priceUsualWindow = infoDuckWindow.findElement(By.className("regular-price"));
                    String priceUsualW = priceUsualWindow .getAttribute("textContent");  //price value
                     String priceUsualWColor = priceUsualWindow.getCssValue("color");  // color
                          String priceUsualFontSizeW = priceUsualWindow.getCssValue("font-size"); //font-size


            WebElement priceNewWindowDuck = infoDuckWindow.findElement(By.className("campaign-price"));
                 String priceNewWDuck = priceNewWindowDuck.getAttribute("textContent");
                     String priceNewColorW = priceNewWindowDuck.getCssValue("color");  // color
                         String priceNewFontSizeW = priceNewWindowDuck.getCssValue("font-size"); //font-size


            if (!(nameDuck.equals(nameDuckWin)) ) {
                AssertionError assertError = new AssertionError();
                System.out.println("ERROR: " + assertError.getMessage());
                System.out.println("The Title of Duck on the main page ("+ nameDuck +") is different than at the opened window ("+nameDuckWin+")" );
                Assert.fail();
            }
            System.out.println("The Titles are identical on the main page ("+ nameDuck +") with the opened window ("+nameDuckWin+")" );
            System.out.println("----------------------- ");



           // The Usual price attributes compare


            if (!(priceUsual.equals(priceUsualW)) ) {
                AssertionError assertError = new AssertionError();
                System.out.println("ERROR: " + assertError.getMessage());
                System.out.println("The Usual price on the main page ("+ priceUsual +") is different with the opened window ("+priceUsualW+")" );
                Assert.fail();
            }
            System.out.println("The Usual price on the main page ("+ priceUsual +") is the same with the opened window ("+priceUsualW+")" );
            System.out.println("The Color of Usual price on main page is : "+ priceUsualColor );
            System.out.println("The Color of Usual price on the Duck's page is : "+ priceUsualWColor );
            System.out.println("The Font-size of Usual price on main page is : "+ priceUsualFontSize);
            System.out.println("The Font-size of Usual price on the Duck's page is : "+ priceUsualFontSizeW);
            System.out.println("----------------------- ");


            // The Discount (New) price attributes comparition
            if (!(priceNew.equals(priceNewWDuck)) ) {
                AssertionError assertError = new AssertionError();
                System.out.println("FAILED: " + assertError.getMessage());
                System.out.println("The Discount price on the main page ("+ priceNew +") is different with the opened window ("+priceNewWDuck+")" );
                Assert.fail();
            }
            System.out.println("The Discount price on the main page  ("+ priceNew +") is the same with the opened window ("+priceNewWDuck+")" );
            System.out.println("The Color of Discount price on the main page is : "+ priceNewColor );
            System.out.println("The Color of Discount price on the Duck's page is : "+ priceNewColorW );
            System.out.println("The Font-size of Discount price on the main page is : "+ priceNewFontSize);
            System.out.println("The Font-size of Discount price  on the Duck's page is : "+ priceNewFontSizeW);
            System.out.println("----------------------- ");

        }
    }


