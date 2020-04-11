package my.tests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

import java.io.FileNotFoundException;
import java.util.concurrent.TimeUnit;

public class NewItemAddTask12 extends Drivers {

    // new Item data:
    String newItemName = "Canvasback NEW";
    String newCode = "CB001";
    String newQuantity = "11";
    String newSoldOut = "1";
    String newValidFrom = "01.01.2020";
    String newValidTo = "31.12.2020";
    String newManufacturer = "1";
    String newShortDescr = "The canvasback is a species of diving duck";
    String newDescription = "The breeding habitat of the canvasback is in North America prairie potholes. The bulky nest is built from vegetation in a marsh and lined with down.";
    String newDescription2 = "The bulky nest is built from vegetation in a marsh and lined with down. Loss of nesting habitat has caused populations to decline.";
    String newPrice1 = "55";
    String newPrice2 = "55";
    String filePath = "/src/test/resources/NewDuck.jpg";


    @Test
    public void NewItemAdd() throws FileNotFoundException {
        ScenarioLoginTask3 test = new ScenarioLoginTask3();
        test.login2(); // логинимся

        //going to catalog
        drv.findElement(By.cssSelector("ul#box-apps-menu a[href$='doc=catalog']")).click();
        drv.manage().timeouts().implicitlyWait(300, TimeUnit.SECONDS);
        if (!drv.getTitle().equals("Catalog | My Store")){
            AssertionError assertError = new AssertionError();
            System.out.println("ERROR: " +assertError.getMessage());
            System.out.println("Title of page is: " + drv.getTitle());
            Assert.fail();
        }
        System.out.println("Title of page is: " + drv.getTitle());



        //Click on " Add New Product "
        // tab "General"

        drv.findElement(By.cssSelector("td#content a[href$='doc=edit_product']")).click();
        drv.manage().timeouts().implicitlyWait(300, TimeUnit.SECONDS);
        if (!drv.getTitle().equals("Add New Product | My Store")){
            AssertionError assertError = new AssertionError();
            System.out.println("ERROR: " +assertError.getMessage());
            System.out.println("Title of page is: " + drv.getTitle());
            Assert.fail();
        }
        System.out.println("Title of page is: " + drv.getTitle());

        // set Radio Button to Status 'Enabled'
            WebElement radioButtonStatus = drv.findElement(By.cssSelector("div#tab-general input[value='1'][type='radio']"));
            radioButtonStatus.click();
            drv.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        // add new Item Name
            WebElement Name = drv.findElement(By.cssSelector("div#tab-general input[name='name[en]'][type='text']"));
            Name.sendKeys(newItemName);

        // add new code
            WebElement Ncode = drv.findElement(By.cssSelector("div#tab-general input[name='code'][type='text']"));
            Ncode.sendKeys(newCode);

        //Checkbox 'Categories - Main'
            drv.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            WebElement mainCategory = drv.findElement(By.cssSelector("div#tab-general input[value='0'][type='checkbox']"));
            String mainCategoryStatus = mainCategory.getAttribute("checked");
            if (mainCategoryStatus!=null){
            mainCategory.click();
        }
        // Checkbox 'Categories - Rubber Duck'
            WebElement rubberDuck = drv.findElement(By.cssSelector("div#tab-general input[value='1'][type='checkbox']"));
            String rubberDuckStatus = rubberDuck.getAttribute("checked");
            if (rubberDuckStatus==null){
             rubberDuck.click();
            }


        //Product Groups, Gender
            drv.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            drv.findElement(By.cssSelector("div#tab-general input[value='1-3'][type='checkbox']")).click();

        // Quantity
            WebElement quantity = drv.findElement(By.cssSelector("div#tab-general input[name='quantity']"));
            quantity.clear();
            quantity.sendKeys(newQuantity);

        // SoldOut
            Select soldOut = new Select (drv.findElement(By.cssSelector("div#tab-general select[name='sold_out_status_id']")));
            soldOut.selectByValue(newSoldOut);

        // New Item Picture
            WebElement uploadImage = drv.findElement(By.cssSelector("div#tab-general input[name='new_images[]']"));
            uploadImage.sendKeys( System.getProperty("user.dir") + filePath );
            drv.manage().timeouts().implicitlyWait(300, TimeUnit.SECONDS);

        //if (!uploadImage.getAttribute("value").equals("")) {
        //    AssertionError assertError = new AssertionError();
        //    System.out.println("ERROR: " + assertError.getMessage());
         //   System.out.println("The Image doesn't loaded");
         //   Assert.fail();
        //}

        //Date Valid From
            WebElement validFrom = drv.findElement(By.cssSelector("div#tab-general input[name='date_valid_from'][type='date']"));
            validFrom.sendKeys(Keys.HOME + newValidFrom);
        //Date Valid To
            WebElement validTo = drv.findElement(By.cssSelector("div#tab-general input[name='date_valid_to'][type='date']"));
            validTo.sendKeys(Keys.HOME + newValidTo);


        //Information tab
            drv.findElement(By.cssSelector("div.tabs a[href='#tab-information']")).click();

        // manufacturer
            Select manufacturer = new Select(drv.findElement(By.cssSelector("div#tab-information select[name='manufacturer_id']")));
            manufacturer.selectByValue(newManufacturer);

        //keywords
            WebElement keywords = drv.findElement(By.cssSelector("div#tab-information input[name='keywords'][type='text']"));
            keywords.sendKeys(newItemName);

        //short description
            WebElement shortDescription = drv.findElement(By.cssSelector("div#tab-information input[name='short_description[en]'][type='text']"));
            shortDescription.sendKeys(newShortDescr);

        // description
            WebElement description = drv.findElement(By.cssSelector("div#tab-information div.trumbowyg-editor"));
            description.click();
            description.sendKeys(newDescription);
            description.sendKeys(Keys.ENTER);
            description.sendKeys(newDescription2);

        // Head title
            WebElement headTitle = drv.findElement(By.cssSelector("div#tab-information input[name='head_title[en]'][type='text']"));
            headTitle.sendKeys(newItemName);

        // Meta Description
            WebElement metaDescription = drv.findElement(By.cssSelector("div#tab-information input[name='meta_description[en]'][type='text']"));
            metaDescription.sendKeys(newItemName);



        //Prices tab
            drv.findElement(By.cssSelector("div.tabs a[href='#tab-prices']")).click();

            WebElement price = drv.findElement(By.cssSelector("div#tab-prices input[name='purchase_price'][type='number']"));
            price.clear();
            price.sendKeys(newPrice1);

            Select currency = new Select (drv.findElement(By.cssSelector("div#tab-prices select[name='purchase_price_currency_code']")));
            currency.selectByValue("USD");

            WebElement price2 = drv.findElement(By.cssSelector("div#tab-prices input[data-type='currency']"));
            price2.clear();
            price2.sendKeys(newPrice2);

            System.out.println("Pressed Save button");

        //save
            drv.findElement(By.cssSelector("span.button-set button[type='submit'][value='Save']")).click();
            drv.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            //wait.until(titleIs("Catalog | My Store"));
            if (!drv.getTitle().equals("Catalog | My Store")){
            AssertionError assertError = new AssertionError();
            System.out.println("ERROR: " +assertError.getMessage());
            System.out.println("The current page's URL is: " + drv.getCurrentUrl());
            Assert.fail();
        }
            System.out.println("Current Page Title is: " + drv.getTitle());

            WebElement itemsSecond = drv.findElement(By.cssSelector("table.dataTable tr.footer td"));
            String numSecond = itemsSecond.getAttribute("textContent");
            System.out.println("There are: " + numSecond);

            if (isElementPresent(drv))
            {
                System.out.println("Have fFound created item '"+newItemName+"' on the Catalog page");
            }
            else
            {
             AssertionError assertError = new AssertionError();
             System.out.println("error: " +assertError.getMessage());
             System.out.println("Can't find  the created and added item to the Catalog page");
                Assert.fail();
        }
    }
     // Check that new item is added
    protected boolean isElementPresent(WebDriver DRIVER){
        try{
            DRIVER.findElement(By.linkText(newItemName));
            return true;
        }
        catch(NoSuchElementException e){
            return false;
        }
    }
}



