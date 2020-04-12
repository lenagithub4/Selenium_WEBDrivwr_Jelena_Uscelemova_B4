package my.tests;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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

            // 1st Duck
            selectTheFirstDuck();
            ducksInTheCart = addToCart(ducksInTheCart);
            backToMainPage();

            // 2nd Duck
            selectTheFirstDuck();
            ducksInTheCart = addToCart(ducksInTheCart);
            backToMainPage();

            // 3rd Duck
            selectTheFirstDuck();
            ducksInTheCart = addToCart(ducksInTheCart);
            backToMainPage();

            final int countItemsCart = checkOfCart();

            assertEquals(ducksInTheCart, countItemsCart);

            inTheCart();
            removeItemsInCart(countItemsCart);
            backToMainPage();

            assertEquals(0, checkOfCart());
        }

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

        private void inTheCart() {
           drv.findElement(By.cssSelector("div#cart a.link")).click();
        }

        private void removeItemsInCart (int quantity) {
            List<WebElement> dataTablerows = drv.findElements(By.cssSelector("table.dataTable tr"));

            while (dataTablerows .size() > 0) {
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("form[name='cart_form'] button[value='Remove']")));
                drv.findElement(By.cssSelector("form[name='cart_form'] button[value='Remove']")).click();
                wait.until(ExpectedConditions.stalenessOf(dataTablerows .get(0)));
                dataTablerows  = drv.findElements(By.cssSelector("table.dataTable tr"));
            }

        }

        private int checkOfCart() {
            WebElement checkcart = drv.findElement(By.cssSelector("div#cart"));
            return Integer.parseInt(checkcart.findElement(By.cssSelector("span.quantity")).getText());
        }

        private void backToMainPage() {
            drv.navigate().to("http://localhost/litecart/en/");
            WebElement firstDuck = drv.findElement(By.cssSelector("header div#logotype-wrapper"));
           // wait.until(visibilityOf(firstDuck));
          // wait.until(elementToBeClickable(firstDuck));
            firstDuck.click();
        }



        private boolean isElementsPresent(By locator) {
            final List<WebElement> elements = drv.findElements(locator);
            return elements.size() > 0;
        }

        private void fillRequiredSelector() {
            final List<WebElement> selectors = drv.findElements(By.cssSelector("select[required='required']"));
            for (WebElement selector : selectors) {
                Select select = new Select(selector);
                select.selectByIndex(1);
            }
        }

        private void selectTheFirstDuck() {
           //wait.until(drv.findElements(By.cssSelector("div.content li.product")));
            final List<WebElement> products = drv.findElements(By.cssSelector("div.content li.product"));
            products.get(0).click();
        }

    }



