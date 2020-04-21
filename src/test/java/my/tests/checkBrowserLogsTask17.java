package my.tests;

import org.junit.Assert;
import org.junit.Test;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntry;


public class checkBrowserLogsTask17 extends Drivers {


            @Test
            public void CheckLogs(){

                ScenarioLoginTask3 test = new ScenarioLoginTask3();
                test.login2(); // логинимся

            drv. navigate().to("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");

            String productName = "Duck";

            List<WebElement> productList = drv.findElements(By.xpath(".//*[@id='content']/form/table//a[contains(text(),'" + productName +"')]"));


                for (int i = 1; i < productList.size(); i++) {
                productList = drv.findElements(By.xpath(".//*[@id='content']/form/table//a[contains(text(),'" + productName + "')]"));

                WebElement product = productList.get(i);
                product.click();
                System.out.println("Page is opened for item: " + drv.getTitle());

                List<LogEntry> browserLog = drv.manage().logs().get("browser").getAll();
                if (browserLog.size() > 0) {
                    AssertionError assertError = new AssertionError("There are the error messages in the Browser Console: ");
                    System.out.println(assertError.getMessage() + "" + browserLog);
                    Assert.fail();
                }

                drv.navigate().back();
            }
        }
    }


