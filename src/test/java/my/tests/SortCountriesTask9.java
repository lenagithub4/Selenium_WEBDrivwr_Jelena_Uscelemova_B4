package my.tests;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortCountriesTask9 extends Drivers {

    private boolean alphabeticCompare(List<String> list) {
        list.remove("");
        List<String> sortedList = new ArrayList<String>(list);
        Collections.sort(
                sortedList,
                Comparator.comparing(String::toString)
        );
        return sortedList.equals(list);
    }



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

        // Country list for sorting
        List<String> myCountries = new ArrayList<String>();

        // Current Country list
        List<String> myCountriesOrig = new ArrayList<String>();

        List<WebElement> rows = drv.findElements(By.cssSelector("table.dataTable tr.row"));
        String coName = "";
        String coZone = "";
        for (int i=0; i < rows.size(); i++) {
            WebElement row = rows.get(i);
            try {
                coName = row.findElements(By.cssSelector("td")).get(4).findElement((By.cssSelector("a"))).getText();
            } catch (org.openqa.selenium.StaleElementReferenceException e) {
                System.out.println(e.getMessage());
                System.out.println(row.toString());
                System.out.println("Page Title is " + drv.getTitle());
                break;
            }
            myCountries.add(coName);

            coZone = row.findElements(By.cssSelector("td")).get(5).getText();
            if (coZone.equals("0")) {
                continue;
            }

            List<String> mySubCountries = new ArrayList<String>();

            System.out.println("Found country " + coName + " with " + coZone + " zones");
            WebElement countryLink = row.findElements(By.cssSelector("td")).get(4).findElement((By.cssSelector("a")));
            countryLink.click();

            if (!drv.getTitle().equals("Edit Country | My Store")) {
                AssertionError assertError = new AssertionError("Can not open required page");
                System.out.println("FAILED: Wrong page name " + drv.getTitle() + "" + assertError.getMessage());
                Assert.fail();
            }

            List<WebElement> subrows = drv.findElements(By.cssSelector("table#table-zones tr"));
            String coSubName = "";
            subrows.remove(0);
            for (WebElement srow: subrows) {
                coSubName = srow.findElements(By.cssSelector("td")).get(2).getText();
                mySubCountries.add(coSubName);
            }

            boolean equals = alphabeticCompare(mySubCountries);

            if (equals) {
                System.out.println(coName + " zones sorted correctly");
            } else {
                System.out.println(coName + " zones sorted not correctly");
            }

            drv.navigate().back();
            rows = drv.findElements(By.cssSelector("table.dataTable tr.row"));
            System.out.println("Load content...");
        }

        boolean equals = alphabeticCompare(myCountries);

        if (equals) {
            System.out.println("Countries sorted correctly");
        } else {
            System.out.println("Countries sorted not correctly");
        }

/*
        for (int i = 0; i < rows.size(); i++) {
            coName = rows.get(i).findElements(By.cssSelector("td")).get(4).findElement((By.cssSelector("a"))).getText();
            myCountries.add(coName);
            myCountriesOrig.add(coName);

        }
        // sort the Country list for sorting
        Collections.sort(
                myCountries,
                Comparator.comparing(String::toString)
        );

        // print the current Countries list
        for (int k = 0; k < rows.size(); k++) {
            System.out.println(myCountriesOrig.get(k));
        }

        //Compare sorted Country list with original
        System.out.println("The main list Countries are displayed in an alphabetical order! The result of the test is = " + myCountriesOrig.equals(myCountries));


        // Zone


        List<WebElement> rowszone = drv.findElements(By.cssSelector("table.dataTable tr.row"));

        for (int j = 0; j < rows.size(); j++) {
            // Country list for sorting
            List<String> mySubCountries = new ArrayList<String>();

            // Current Country list
            List<String> mySubCountriesOrig = new ArrayList<String>();

            coZone = rowszone.get(j).findElements(By.cssSelector("td")).get(5).getText();
            String coNameZ = rowszone.get(j).findElements(By.cssSelector("td")).get(4).getText();
            if (!coZone.equals("0")) {
                System.out.println("Found country " + coNameZ + " with " + coZone + " zones");
                WebElement countryLink = rowszone.get(j).findElements(By.cssSelector("td")).get(4).findElement((By.cssSelector("a")));
                //WebElement codeLink = rowszone.get(j).findElements(By.cssSelector("td")).get(3).findElement((By.cssSelector("a")));

                countryLink.click();

                if (!drv.getTitle().equals("Edit Country | My Store")) {
                    AssertionError assertError = new AssertionError("Can not open required page");
                    System.out.println("FAILED: Wrong page name " + drv.getTitle() + "" + assertError.getMessage());
                    Assert.fail();
                }

                List<WebElement> Subrows = drv.findElements(By.cssSelector("table#table-zones tr"));

                for (int m = 1; m < Subrows.size() - 1; m++) {
                    String coSubName = Subrows.get(m).findElements(By.cssSelector("td")).get(2).getText();
                    mySubCountries.add(coSubName);
                    mySubCountriesOrig.add(coSubName);
                }


                // sort the SubCountry list for sorting
                     Collections.sort(mySubCountries);

                //Compare sorted SubCountry list with original
                    System.out.println("The results of Zone Companies sorting for  " + coNameZ + " is = " + mySubCountriesOrig.equals(mySubCountries));
                    drv.navigate().to("http://localhost/litecart/admin/?app=countries&doc=countries");
                    rowszone = drv.findElements(By.cssSelector("table.dataTable tr.row"));
            }

        }*/

    }
}
