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
           List<WebElement> list = drv.findElements(By.className("hover-light"));
            System.out.println("Have found " + list.size() + " ducks on the page");

            for(int i=0;i<list.size();i++){
                WebElement item = list.get(i);
                List<WebElement> sticker = item.findElements(By.className("sticker"));

                if ((sticker.size()==1)) {
                    System.out.println("Duck N" + i + ": has a sticker " + item.getText()  + " count= " + sticker.size()  );
                }
                  else {
                    System.out.println("Duck N" + i + ": hasn't a sticker or has more that 1 sticker! ");
                }
             }

            }

        }


