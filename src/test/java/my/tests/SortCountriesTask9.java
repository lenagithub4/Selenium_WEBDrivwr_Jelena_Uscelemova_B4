package my.tests;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
        test.login2();

        Test1();
        Test2();

    }

    private void Test1() {
        //countries
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
        for (int i = 0; i < rows.size(); i++) {
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
            for (WebElement srow : subrows) {
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
    }

    private void Test2() {

        drv.navigate().to("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");

        if (!drv.getTitle().equals("Geo Zones | My Store")) {
            AssertionError assertError = new AssertionError("Can not open required page");
            System.out.println("FAILED: Wrong page name " + drv.getTitle() + "" + assertError.getMessage());
            Assert.fail();
        }

        System.out.println("Page Title is " + drv.getTitle());

        List<WebElement> rowsGeoZone = drv.findElements(By.cssSelector("table.dataTable tr.row"));
        for (int k = 0; k < rowsGeoZone.size(); k++) {
            WebElement rowGZ = rowsGeoZone.get(k);
            drv.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            WebElement countryLinkGeoZone = rowGZ.findElements(By.cssSelector("td")).get(2).findElement((By.cssSelector("a")));
            String zoneName = countryLinkGeoZone.getText();
            countryLinkGeoZone.click();

            List<WebElement> rowsZoneGeoZones = drv.findElements(By.cssSelector("table.dataTable tr:not(.header)"));

            List<String> selectedZonesList = new ArrayList<String>();
            for (int j = 0; j < rowsZoneGeoZones.size() - 1; j++) {
                WebElement rowZGZ = rowsZoneGeoZones.get(j);
                drv.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
                Select select = new Select(rowZGZ.findElements(By.cssSelector("td")).get(2).findElement(By.cssSelector("select")));

            selectedZonesList.add(select.getFirstSelectedOption().getText());

            }
            boolean equals = alphabeticCompare(selectedZonesList);

            if (equals) {
                System.out.println(zoneName + " zones sorted correctly");
            } else {
                System.out.println(zoneName + " zones sorted not correctly ");
            }
              //} else {
               // System.out.println(zoneName + " no geozones ");
              // }
            drv.navigate().back();
            rowsGeoZone = drv.findElements(By.cssSelector("table.dataTable tr.row"));
            System.out.println("Load content...");

        }
    }

}