package my.tests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import my.tests.Drivers;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class RegistrationTask11 extends Drivers {

    //my data
    String nameData="Jelena";
    String surnameData="Uscelemova";
    String addressData="Lielirbes street";
    String postIndexData="1122";
    String cityData="Riga";
    String countryData="Latvia";
   // String stateUSA = "Alabama";
    String emailData="lena-usc@inbox.lv";
    String phoneData="+37128855306";
    String passwordData="22332233";



    @Test
    public void test() {

        drv.navigate().to("http://localhost/litecart/en/");


        // регистрация новой учётной записи.
        WebElement link = drv.findElement(By.cssSelector("div#box-account-login a[href*=create_account]"));
        link.click();
        drv.manage().timeouts().implicitlyWait(300, TimeUnit.SECONDS);

        if (!(drv.getCurrentUrl().equals("http://localhost/litecart/en/create_account"))){
            AssertionError assertError = new AssertionError();
            System.out.println("FAILED: " +assertError.getMessage());
            System.out.println("Page URL is" + drv.getCurrentUrl());
            Assert.fail();
        }

        WebElement firstName = drv.findElement(By.name("firstname")); //name
        firstName.click();
        firstName.clear();
        firstName.sendKeys(Keys.TAB);

        // firstName.sendKeys(nameData + Keys.ENTER);

        Actions actionsN = new Actions(drv);
        actionsN.pause(5);
        actionsN.moveToElement(firstName);
        actionsN.click();
        actionsN.sendKeys(nameData);
        actionsN.sendKeys(Keys.ENTER);
        actionsN.build().perform();



        WebElement surName = drv.findElement(By.name("lastname")); //surname
            surName.click();
            surName.clear();
            surName.sendKeys(surnameData);

        WebElement address1 = drv.findElement(By.name("address1")); // adress N1
            address1.click();
            address1.clear();
            address1.sendKeys(addressData);

        WebElement postcode = drv.findElement(By.name("postcode")); //postcode
            postcode.click();
            postcode.clear();
            postcode.sendKeys(postIndexData);

        WebElement city = drv.findElement(By.name("city"));
            city.click();
            city.clear();
            city.sendKeys(cityData);

        WebElement country = drv.findElement(By.cssSelector("span.selection"));//country

            Actions actions = new Actions(drv);
            actions.moveToElement(country);
            actions.click();
            actions.sendKeys(countryData);
            actions.sendKeys(Keys.ENTER);
            actions.build().perform();

      //  WebElement stateAlaska = drv.findElement(By.tagName("select")).findElement(By.name("zone_code")); // state
       // stateAlaska.click();
      //  Actions actions1 = new Actions(drv);
      //  actions1.moveToElement(stateAlaska);
      //  actions1.click();
      //  actions1.sendKeys(stateUSA);
      //  actions1.sendKeys(Keys.ENTER);
      //  actions1.build().perform();

       // email
        Random randNumber = new Random();
        int emailUniqueNumber = randNumber.nextInt(100) + 1;

        WebElement email = drv.findElement(By.name("email")); // email
            email.click();
            email.clear();
            email.sendKeys(emailUniqueNumber+emailData);
            System.out.println("The next email was used:  " + emailUniqueNumber+emailData);


        WebElement phone = drv.findElement(By.name("phone")); // phone
            phone.click();
            phone.clear();
            phone.sendKeys(phoneData);
            System.out.println("The phone was used:  " + phoneData);

        WebElement password = drv.findElement(By.name("password")); // password
             password.click();
             password.clear();
             password.sendKeys(passwordData);

        WebElement cpassword = drv.findElement(By.name("confirmed_password")); // password_confirm
            cpassword.click();
            cpassword.clear();
            cpassword.sendKeys(passwordData);

        WebElement submit = drv.findElement(By.cssSelector("button[type=submit]"));
             submit.click();



             drv.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);



        String createdAccountMsg = drv.findElement(By.cssSelector("div#main div#notices-wrapper")).getText();
         System.out.println(createdAccountMsg);


        System.out.println("Account created. I see message:  " + createdAccountMsg);

        // if (!createdAccountMsg.equals("Your customer account has been created.")){

        //   AssertionError assertError = new AssertionError();
        //   System.out.println("FAILED: " +assertError.getMessage());
        //   System.out.println("Do not see message: 'Your customer account has been created'");
        //   Assert.fail();
    // }

        //Log out выход (logout) после успешной регистрации нового пользователя. N1
        logout();

        //Log in Повторная авторизация.
        login(emailUniqueNumber);

        //log out N2
        logout();
     }

     private void logout(){

         //WebElement logout1 = drv.findElement(By.xpath(a[contains(@href,"logout")]));
      // WebElement logout1 = drv.findElement(By.cssSelector("div#box-account a[href*=logout]"));
         WebElement logout1 = drv.findElement(By.linkText("Logout"));
         drv.manage().timeouts().implicitlyWait(300, TimeUnit.SECONDS);
        logout1.click();


        String successLogoutText = drv.findElement(By.cssSelector("div#main div#notices-wrapper")).getText();


        System.out.println("You logged out. I see message:  " + successLogoutText);

       // if (!successLogoutText.equals(" You are now logged out.")){
        //  AssertionError assertError = new AssertionError();
       // System.out.println("FAILED: " +assertError.getMessage());
       //  System.out.println("Do not see message:  'You are now logged out'");
       //  Assert.fail();
       // }
    }

    private void login(int  uniqueNum){
        WebElement emailLogin = drv.findElement(By.cssSelector("div#box-account-login input[name=email]"));
             emailLogin.click();
             emailLogin.sendKeys(Keys.TAB);
             emailLogin.clear();

             emailLogin.sendKeys(uniqueNum+emailData);

        WebElement passwordLogin = drv.findElement(By.cssSelector("div#box-account-login input[name=password]"));
             passwordLogin.click();
             passwordLogin.clear();
             passwordLogin.sendKeys(passwordData);

        WebElement loginBtn = drv.findElement(By.cssSelector("div#box-account-login button[name=login]"));
              loginBtn.click();

        String successLText = drv.findElement(By.cssSelector("div#main div#notices-wrapper")).getText();
        String expectedMessage = "You are now logged in as "+nameData + " "+surnameData+".";

        drv.manage().timeouts().implicitlyWait(3000, TimeUnit.SECONDS);

        if (!successLText.equals(expectedMessage)){
           AssertionError assertError = new AssertionError();
            System.out.println("FAILED: " +assertError.getMessage());
           System.out.println("I don't see the message: 'You are now logged in as "+nameData + " "+surnameData+"'.");
           Assert.fail();
        }
        System.out.println("The message on login of created user is :  " + successLText);
    }
}
