package my.tests;
import my.tests.Drivers;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;
import java.util.List;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.*;


public class AddItemToChartTask13b extends Drivers {


        @Test
        public void addItemsToCart() throws InterruptedException {
            drv.get("http://localhost/litecart/en/");
           // drv.navigate().to("http://localhost/litecart/en/");
            int ducksInTheCart = 0;

            // add 3 first Ducks
            while (ducksInTheCart < 3) {
                selectTheFirstDuck();
                ducksInTheCart = addToCart(ducksInTheCart);
                backToMainPage();
            }

            // count of added ducks comparison
            final int countItemsCart = checkOfCart();
            System.out.println("There are " + countItemsCart + " ducks in the Cart");

            assertEquals(ducksInTheCart, countItemsCart);

            // Process in the Cart
            inTheCart(); // go to Cart

            removeItemsInCart(countItemsCart);

            backToMainPage();

            assertEquals(0, checkOfCart());


        }

        // select the first duck on the main page.
        private void selectTheFirstDuck() {
            //wait.until(drv.findElements(By.cssSelector("div.content li.product")));
            final List<WebElement> products = drv.findElements(By.cssSelector("div.content li.product"));
            products.get(0).click();
             }

         //  add the ducks to Cart
       private int addToCart(int itemsValueInCart) throws InterruptedException {
             itemsValueInCart = itemsValueInCart + 1;
             WebElement quantityInCart = drv.findElement(By.cssSelector("div#cart span"));
             if (isElementsPresent(By.cssSelector("select[required='required']"))) {
              fillRequiredSelector();
            }
            //wait.until(visibilityOf(drv.findElement(By.cssSelector("div#box-product button"))));
             drv.findElement(By.cssSelector("div#box-product button")).click();
             wait.until(ExpectedConditions.attributeToBe(quantityInCart, "textContent", String.valueOf(itemsValueInCart)));
             return itemsValueInCart;
             }

             // go to Main Page
      private void backToMainPage() {
            drv.navigate().to("http://localhost/litecart/en/");
           WebElement firstDuck = drv.findElement(By.cssSelector("header div#logotype-wrapper"));
           // wait.until(visibilityOf(firstDuck));
           // wait.until(elementToBeClickable(firstDuck));
          firstDuck.click();
         }

         // count of items in the Cart
      private int checkOfCart() {
          WebElement checkcart = drv.findElement(By.cssSelector("div#cart"));

         return Integer.parseInt(checkcart.findElement(By.cssSelector("span.quantity")).getText());
        }

         // go to Cart
        private void inTheCart() {
            //drv.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
            drv.findElement(By.cssSelector("div#cart a.link")).click();
        }

        //Delete the added Ducks from the Cart
        private void removeItemsInCart (int quantity) {
            List<WebElement> dataTableRows = drv.findElements(By.cssSelector("div#box-checkout-summary table.dataTable tr"));
            //List<WebElement> dataTableRows = drv.findElements(By.cssSelector("ul.shortcuts li"));
            System.out.println("There are " + dataTableRows.size() + " ducks in the Order Summary");

           while (dataTableRows.size() > 0) {
                wait.until (ExpectedConditions.presenceOfElementLocated(By.cssSelector("button[value='Remove']")));
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[value='Remove']")));
                wait.until (ExpectedConditions.elementToBeClickable(By.cssSelector("button[value='Remove']"))).click();
               //drv.findElement(By.cssSelector("button[value='Remove']")).click();
                wait.until(ExpectedConditions.stalenessOf(dataTableRows.get(0)));

                dataTableRows  = drv.findElements(By.cssSelector("div#box-checkout-summary table.dataTable tr"));
               //dataTableRows  = drv.findElements(By.cssSelector("ul.shortcuts li"));
            }
            WebElement noItems = drv.findElement(By.cssSelector("div#checkout-cart-wrapper p em"));
            String noItemsText = noItems.getText();
            System.out.println(noItemsText);

        }



          // is elements present by locator
        private boolean isElementsPresent(By locator) {
            final List<WebElement> elements = drv.findElements(locator);
            return elements.size() > 0;
        }
         // select 1st element from Select list
        private void fillRequiredSelector() {
            final List<WebElement> selectors = drv.findElements(By.cssSelector("select[required='required']"));
            for (WebElement selector : selectors) {
                Select select = new Select(selector);
                select.selectByIndex(1);
            }
        }



    }



