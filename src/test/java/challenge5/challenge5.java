package challenge5;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

public class challenge5 {

    public WebDriver driver;

    @BeforeSuite
    public void startSuite() throws Exception {
    }

    @AfterSuite
    public void stopSuite() throws Exception {
        System.out.println("All done!!!");
    }

    @BeforeClass
    public void startClass() throws Exception{
        System.setProperty("webdriver.chrome.driver", "./bin/chromedriver");
        driver =  new ChromeDriver();
    }

    @AfterClass
    public void stopClass(){
        driver.quit();
    }
    @BeforeMethod()
    public void beforeMethod() throws Exception {
    }

    @AfterMethod()
    public void afterMethod(){
    }

    /*
    For this challenge, go to https://www.copart.com (DONE)
    and do a search for “porsche” (DONE)
    and change the  drop down for “Show Entries” to 100 from 20. (DONE)
    Count how many different models of porsche is in the results on the
    first page and return in the terminal how many of each type exists.

    Possible values can be “CAYENNE S”, “BOXSTER S”, etc.
    *hint:  Look at adding every model into an array and then sort the array and count. (DONE with List)
    For the 2nd part of this challenge, create a switch statement to count the types of damages.
    Here’s the types:
    REAR END
    FRONT END
    MINOR DENT/SCRATCHES
    UNDERCARRIAGE
    And any other types can be grouped into one of MISC.
    */

    @Test()
    //Go to Copart.com
    public void goToCopart() throws Exception{
        WebDriverWait wait = new WebDriverWait(driver,30);
        driver.get("https://www.copart.com");
    }

    @Test(dependsOnMethods={"goToCopart"})
    //Check title
    public void verifyCopartTitle() throws Exception{
        Assert.assertTrue(driver.getTitle().contains("Copart"));
    }

    @Test(dependsOnMethods={"verifyCopartTitle"})
    //Do Search for Porsche.
    public void doPorscheSearch() throws Exception{
        WebElement searchBox = driver.findElement(By.id("input-search"));
        Thread.sleep(100);
        searchBox.sendKeys("porsche");
        WebElement searchButton = driver.findElement(By.xpath("//button[contains(.,'Search')]"));
        searchButton.click();
        Thread.sleep(100);
        Assert.assertTrue(driver.getTitle().contains("porsche"));
    }

    @Test(dependsOnMethods={"doPorscheSearch"})
    //Change displayed entries from 20 to 100
    public void changeNumberOfEntriesListed() throws Exception{
        WebElement numberOfEntries = driver.findElement(By.xpath("//select[@name='serverSideDataTable_length']"));
        numberOfEntries.click();
        Thread.sleep(100);
        numberOfEntries.sendKeys("100");
        Thread.sleep(100);
        numberOfEntries.click();
        Thread.sleep(100);
        String number = numberOfEntries.getText();
        Assert.assertTrue(number.contains("100"));
    }

    @Test(dependsOnMethods={"changeNumberOfEntriesListed"})
    //Look at adding every model into an array and then sort the array and count.
    public void getListOfPorscheModels() throws Exception {
        List<WebElement> modelsListOfElements = driver.findElements(By.xpath("//span[@data-uname=\"lotsearchLotmodel\"]"));

        TreeMap<String, Integer> models_count = new TreeMap<String, Integer>();
        for (WebElement model : modelsListOfElements) {
            String key = model.getText();
            if (models_count.containsKey(key)) {
                models_count.put(key,models_count.get(key)+1);
            } else {
                models_count.put(key, 1);
            }
//        }
        }
        System.out.println(models_count);
    }



}
