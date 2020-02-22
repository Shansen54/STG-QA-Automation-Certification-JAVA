package challenge3;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import javax.swing.*;
import java.util.List;

public class challenge3 {

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

    public void waitForLoad() {
        ExpectedCondition<Boolean> pageLoadCondition = new
                ExpectedCondition<Boolean>() {
                    @org.jetbrains.annotations.NotNull
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
                    }
                };
    }

    /*
    For this challenge, go to copart (Done) and print a list of all the “Popular Items” of vehicle Make/Models on the
    home page and the URL/href for each type. (Done)  This list can dynamically change depending on what is authored by
    the content creator but using a loop will make sure that everything will be displayed regardless of the list size.
    Your output in the console would look like:
    IMPREZA - https://www.copart.com/popular/model/impreza
    CAMRY - https://www.copart.com/popular/model/camry
    ...
    (Done)
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
        waitForLoad();
        Assert.assertTrue(driver.getTitle().contains("Copart"));
    }

    @Test(dependsOnMethods={"verifyCopartTitle"})
    //Check title
    public void getPopularModels() throws Exception{
        List <WebElement> vehicles = driver.findElements(By.cssSelector("#tabTrending > div > div > div > ul > li > a"));
        String car = new String();
        String url = new String();
        for (WebElement model : vehicles) {
        car = model.getText();
        url = model.getAttribute("href");
        System.out.println(car + " - " + url);

        }
    }


}
