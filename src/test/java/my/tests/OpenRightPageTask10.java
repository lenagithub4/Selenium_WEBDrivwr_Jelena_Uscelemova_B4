package my.tests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import my.tests.Drivers;
import java.util.concurrent.TimeUnit;


public class OpenRightPageTask10  extends Drivers {

     // method for Grey Color RGB parsing
    private boolean isEqualRGBgrey (String colorG) {

        String[] hexValue = colorG.replace("rgba(", "").replace(")", "").split(",");

        int hexValue1=Integer.parseInt(hexValue[0]);
        hexValue[1] = hexValue[1].trim();
        int hexValue2=Integer.parseInt(hexValue[1]);
        hexValue[2] = hexValue[2].trim();
        int hexValue3=Integer.parseInt(hexValue[2]);
        return (( hexValue1== hexValue2) && (hexValue2==hexValue3));
    }
       // method for RED Color RGB parsing
    private boolean isEqualRGBred (String colorR) {

        String[] hexValueR = colorR.replace("rgba(", "").replace(")", "").split(",");

        int hexValueR1=Integer.parseInt(hexValueR[0]);
        hexValueR[1] = hexValueR[1].trim();
        int hexValueR2=Integer.parseInt(hexValueR[1]);
        hexValueR[2] = hexValueR[2].trim();
        int hexValueR3=Integer.parseInt(hexValueR[2]);


                return (hexValueR2==0 && hexValueR3==0);
    }
    // method for Font-size convert to float type
    private boolean isFontSizeCompare (String fontsize1, String fontsize2) {

               float fontValue1=Float.parseFloat(fontsize1.replaceAll("[a-z]",""));
               float fontValue2=Float.parseFloat(fontsize2.replaceAll("[a-z]",""));
               float rezult = fontValue2 - fontValue1;

               return rezult > 0;
    }


    // main big test

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
            System.out.println("The Color of Usual price on main page is : "+ priceUsualColor);
                        boolean equals = isEqualRGBgrey(priceUsualColor);
                         if (equals) {
                           System.out.println("   Color is Grey");
                         } else {
                           System.out.println("   Color isn't Grey");
                            }
            System.out.println("The Color of Usual price on the Duck's page is : "+ priceUsualWColor );
                           boolean equalsW = isEqualRGBgrey(priceUsualWColor);
                             if (equalsW) {
                               System.out.println("   Color is Grey");
                                } else {
                                 System.out.println("   Color isn't Grey");
                                }
            System.out.println("The Font-size of Usual price on main page is : "+ priceUsualFontSize);
            System.out.println("The Font-size of Usual price on the Duck's page is : "+ priceUsualFontSizeW);
                             boolean isFontSizeU = isFontSizeCompare(priceUsualFontSize, priceUsualFontSizeW );
                               if (isFontSizeU) {
                              System.out.println("   Main page: The font-size of Discount price > that usual price.");
                               } else {
                              System.out.println("   Main page: The font-size of Discount price <= that usual price.");
                                 }
                           System.out.println("----------------------- ");


            // The Discount (New) price attributes comparison
            if (!(priceNew.equals(priceNewWDuck)) ) {
                AssertionError assertError = new AssertionError();
                System.out.println("FAILED: " + assertError.getMessage());
                System.out.println("The Discount price on the main page ("+ priceNew +") is different with the opened window ("+priceNewWDuck+")" );
                Assert.fail();
            }
            System.out.println("The Discount price on the main page  ("+ priceNew +") is the same with the opened window ("+priceNewWDuck+")" );
            System.out.println("The Color of Discount price on the main page is : "+ priceNewColor );
                        boolean equalsR = isEqualRGBred(priceNewColor);
                            if (equalsR) {
                              System.out.println("   Color is Red");
                          } else {
                            System.out.println("   Color isn't Red");
                          }
            System.out.println("The Color of Discount price on the Duck's page is : "+ priceNewColorW );
                          boolean equalsRW = isEqualRGBred(priceNewColor);
                            if (equalsRW) {
                            System.out.println("   Color is Red");
                          } else {
                          System.out.println("   Color isn't Red");
                          }
            System.out.println("The Font-size of Discount price on the main page is : "+ priceNewFontSize);
            System.out.println("The Font-size of Discount price  on the Duck's page is : "+ priceNewFontSizeW);
                 boolean isFontSizeW = isFontSizeCompare(priceNewFontSize, priceNewFontSizeW );
                   if (isFontSizeW) {
                   System.out.println("   Duck's page: The font-size of Discount price > that usual price.");
                    } else {
                   System.out.println("   Duck's page: The font-size of Discount price <= that usual price.");
                   }

            System.out.println("----------------------- ");

        }
    }


