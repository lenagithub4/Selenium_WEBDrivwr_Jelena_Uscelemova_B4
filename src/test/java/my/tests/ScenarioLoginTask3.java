package my.tests;

import org.junit.Test;
import org.junit.Assert;
import org.openqa.selenium.By;



import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;


// my 2nd login file
    public class ScenarioLoginTask3 extends Drivers {

        @Test
        public void login1(){
            drv.navigate().to("http://localhost/litecart/admin/login.php");
            drv.findElement(By.name("username")).sendKeys("admin");
            drv.findElement(By.name("password")).sendKeys("admin");

            drv.findElement(By.name("login")).click();

            // get the "image1" element
            drv.findElement(By.className("logotype"));

           if (!drv.getCurrentUrl().equals("http://localhost/litecart/admin/")){
             AssertionError assertError = new AssertionError();
              System.out.println("FAILED: " +assertError.getMessage());
               System.out.println("Page title is " + drv.getCurrentUrl());
               Assert.fail();
            }
        }
    }
