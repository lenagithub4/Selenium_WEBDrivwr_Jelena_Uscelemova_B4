package my.tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class FindStickerTask8 extends Drivers {


        @Test
        public void sticker(){
            drv.navigate().to("http://localhost/litecart/en/");

            drv.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
           List<WebElement> list = drv.findElements(By.cssSelector("div.content li.product.column.shadow.hover-light"));
            System.out.println("Have found " + list.size() + " ducks on the page");

            for(int i=0;i<list.size();i++){
                WebElement item = list.get(i);
                List<WebElement> sale = item.findElements(By.cssSelector("div[class='sticker sale']"));
                List<WebElement> stnew = item.findElements(By.cssSelector("div[class='sticker new']"));
                System.out.println("Duck N "+ i);

                if ((sale.size()==1 && stnew.size()==0))  {

                    System.out.println("SUCCESS. Found an item with a sticker. " + item.getAttribute("outerText")  + " count= " + sale.size()  + " Item number is " + i );


                }
                 else {
                     if ((sale.size()==0 && stnew.size()==1)) {
                         System.out.println("SUCCESS. Found an item with a sticker. " + item.getAttribute("outerText")  + " count= " + stnew.size()  + " Item number is " + i );

                     }
                }



                }

            }

        }


