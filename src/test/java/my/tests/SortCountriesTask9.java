package my.tests;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SortCountriesTask9 extends Drivers {

    @Test
    public void sort() {
        ScenarioLoginTask3 test = new ScenarioLoginTask3();
        test.login1();

        drv.navigate().to("http://localhost/litecart/admin/?app=countries&doc=countries");

        if (!drv.getTitle().equals("Countries | My Store")) {
            AssertionError assertError = new AssertionError("Can not open required page");
            System.out.println("FAILED: Wrong page name " + drv.getTitle() + "" + assertError.getMessage());
            Assert.fail();
        }

        System.out.println("Page Title is " + drv.getTitle());
        // String coName = null;

        // Country list for sorting
        List<String> myCountries = new ArrayList<String>();

        // Current Country list
        List<String> myCountriesOrig = new ArrayList<String>();
        List<WebElement> rows = drv.findElements(By.cssSelector("table.dataTable tr.row"));

            for (int i = 0; i < rows.size(); i++) {
                String coName = rows.get(i).findElements(By.cssSelector("td")).get(4).findElement((By.cssSelector("a"))).getText();
                myCountries.add(coName);
                myCountriesOrig.add(coName);

// Zone
                String coZone = rows.get(i).findElements(By.cssSelector("td")).get(5).getText();
                List<String> mySubCountries = new ArrayList<String>();
                List<String> mySubCountriesOrig = new ArrayList<String>();
                if (!coZone.equals("0")) {
                    System.out.println("Found country " + coName + " with " + coZone + " zones");
                    WebElement countryLink = rows.get(i).findElements(By.cssSelector("td")).get(4).findElement((By.cssSelector("a")));
                    //  WebElement codeLink = rows.get(i).findElements(By.cssSelector("td")).get(3).findElement((By.cssSelector("a")));
                    countryLink.click();

                    if (!drv.getTitle().equals("Edit Country | My Store")) {
                        AssertionError assertError = new AssertionError("Can not open required page");
                        System.out.println("FAILED: Wrong page name " + drv.getTitle() + "" + assertError.getMessage());
                        Assert.fail();
                    }

                    List<WebElement> Subrows = drv.findElements(By.cssSelector("table#table-zones tr"));
                    for (int j = 1; j < Subrows.size()-1; j++) {
                        String coSubName = Subrows.get(j).findElements(By.cssSelector("td")).get(2).findElement((By.cssSelector("a"))).getText();
                        mySubCountries.add(coSubName);
                        mySubCountriesOrig.add(coSubName);
                    }


                    // sort the SubCountry list for sorting
                    Collections.sort(mySubCountries);

                    //Compare sorted SubCountry list with original
                    System.out.println("The results of Zone Companies sorting for  " + coName + " is = " + mySubCountriesOrig.equals(mySubCountries));
                    drv.navigate().to("http://localhost/litecart/admin/?app=countries&doc=countries");
                    rows = drv.findElements(By.cssSelector("table.dataTable tr.row"));
                }
            }
        // sort the Country list for sorting
        Collections.sort(myCountries);

        // print the current Countries list
        for (int k = 0; k < rows.size(); k++) {
            System.out.println(myCountriesOrig.get(k));
        }
        System.out.println("Countries are displayed in an alphabetical order");

        //Compare sorted Country list with original
        System.out.println("The results of Country List compare with sorted list is = " + myCountriesOrig.equals(myCountries));





    }

}