package my.tests;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;




public class openWindowsTask14 extends Drivers{


    public void NumberOfOpenedWindows(final int numberOpenedWindows) {
        new WebDriverWait(drv, 50) {
        }.until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver drv) {
                return (drv.getWindowHandles().size() == numberOpenedWindows);
            }
        });
    }




        @Test
        public void windows() {

            ScenarioLoginTask3 test = new ScenarioLoginTask3();
            test.login2(); // логинимся

            drv.navigate().to("http://localhost/litecart/admin/?app=countries&doc=countries");
            drv.findElement(By.xpath("//table[contains(@class, 'dataTable')]//tr[5]/td[5]/a")).click();  // click on American Samoa

            String windowMain = drv.getWindowHandle();  // ID of current Main page
             System.out.println("The Current Main Page '" + drv.getTitle() + "';    " + windowMain);  // The Current Main window ID
             System.out.println("________________________________");

            // Find all elements where are the target=blank
             List<WebElement> listOfTargetBlank = drv.findElements(By.cssSelector("td#content table a[target='_blank']"));
               for (int i = 0; i < listOfTargetBlank.size(); i++) {
                WebElement link = listOfTargetBlank.get(i);

                // find the URL of links
                String foundURLs = link.getAttribute("href");

                // will use Javascript executor
                ((JavascriptExecutor) drv).executeScript("window.open(\""+ foundURLs +"\");");
                  NumberOfOpenedWindows(2);

                for (String winHandle : drv.getWindowHandles()) {
                    drv.switchTo().window(winHandle);
                }
                String windowNew = drv.getWindowHandle();
                System.out.println("The New Opened Window is '" + drv.getTitle() + "'. Page URL is '" + drv.getCurrentUrl() + "'. " + windowNew);
                System.out.println("________________________________");

                  drv.close();
                  drv.switchTo().window(windowMain);
                System.out.println("Page title is " + drv.getTitle());
                   System.out.println("________________________________");

               }
        }


    }




