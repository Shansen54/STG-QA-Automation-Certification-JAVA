package challenge6;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import javax.lang.model.util.Types;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class challenge6 {

    public WebDriver driver;
    WebDriverWait wait;

    @BeforeSuite
    public void startSuite() {
    }

    @AfterSuite
    public void stopSuite() {
        System.out.println("All done!!!");
    }

    @BeforeClass
    public void startClass() {
        System.setProperty("webdriver.chrome.driver", "./bin/chromedriver");
        driver =  new ChromeDriver();
        wait = new WebDriverWait(driver, 20);
    }

    @AfterClass
    public void stopClass(){
        driver.quit();
    }
    @BeforeMethod()
    public void beforeMethod() {
    }

    @AfterMethod()
    public void afterMethod(){
    }

    /*
    Challenge 6 - Error Handling
    For this challenge, go to copart site, (Done) search for nissan, (Done)
    and then for the model on the left side filter option, search for “skyline”.
    Now look for a check box for a Skyline.  This is a rare car that might or might not be in the list for models.
    When the link does not exist to click on, your script will throw an exception.
    Catch the exception and take a screenshot of the page of what it looks like. 
    */

    @Test()
    //Go to Copart.com
    public void goToCopart() {
        driver.get("https://www.copart.com");
    }

    @Test(dependsOnMethods={"goToCopart"})
    //Check title
    public void verifyCopartTitle() {
        Assert.assertTrue(driver.getTitle().contains("Copart"));
    }

    @Test(dependsOnMethods={"verifyCopartTitle"})
    //Do Search for Nissan.
    public void doNissanSearch() throws Exception{
        WebElement searchBox = driver.findElement(By.id("input-search"));
        Thread.sleep(1000);
        searchBox.sendKeys("nissan");
        WebElement searchButton = driver.findElement(By.xpath("//button[contains(.,'Search')]"));
        searchButton.click();
        Thread.sleep(1000);
        Assert.assertTrue(driver.getTitle().contains("nissan"));
    }

    @Test(dependsOnMethods={"doNissanSearch"})
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
    //Find Nissan in the serverSideDataTable.
    public void findNissanInTheServerSideDataTable() throws Exception {
        String htmltext = driver.findElement(By.id("serverSideDataTable")).getAttribute("innerHTML");
        Assert.assertTrue(htmltext.contains("NISSAN"));
    }

    @Test(dependsOnMethods={"findNissanInTheServerSideDataTable"})
    //Finds all Nissan models in the list group.
    public void findNissanModelsInListGroup() throws Exception {
        List<WebElement> filterList = driver.findElements(By.xpath("//*[@id='filters-collapse-1']//li//a[1]"));
        for (var i = 0; i < filterList.size(); i++){
            if (filterList.get(i).getText().equals("Model")){
                filterList.get(i);
                break;
            }
        }
     }

    @Test(dependsOnMethods={"findNissanModelsInListGroup"})
    //Search the Model Filter and enter Skyline to search for it.
    public <e> void useTheModelFilterToSearchForSkyline() throws IOException {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-search")));
        WebElement filterBox = driver.findElement(By.id("input-search"));
        System.out.println(filterBox.getText());
        driver.findElement(By.xpath("(//button[@ng-click=\"search()\"])[2]")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("serverSideDataTable")));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@data-uname=\"ModelFilter\"]")));
        driver.findElement(By.xpath("//a[@data-uname=\"ModelFilter\"]")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//input[@ng-model=\"filter.searchText\"])[4]")));
        WebElement modelInputField = driver.findElement(By.xpath("(//input[@ng-model=\"filter.searchText\"])[4]"));

        modelInputField.sendKeys("skyline" + Keys.ENTER);

        try {
            WebElement filterCheckbox = driver.findElement(By.xpath("//*[@id='lot_model_descSKYLINE']"));
            filterCheckbox.click();
            String htmltext = driver.findElement(By.id("lot_model_descSKYLINE")).getText();
            Assert.assertFalse(htmltext.contains("skyline"));
            System.out.println(htmltext);
            String htmltext2 = driver.findElement(By.xpath("//tr[1]//td[6]//span[1]")).getText();
            Assert.assertFalse(htmltext2.contains("skyline"));
            System.out.println(htmltext2);
        } catch (Exception e) {
            WebElement filterCheckbox = driver.findElement(By.xpath("//*[@id='lot_model_descSKYLINE']"));
            filterCheckbox.click();
            File src;
            src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            System.out.printf("File Saved", src.createNewFile());
        }
    }
}
