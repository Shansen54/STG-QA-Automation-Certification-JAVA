package challenge2;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import static org.openqa.selenium.Keys.ENTER;

public class challenge2 {
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

/*    @AfterClass
    public void stopClass(){
        driver.quit();
    }
*/
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
    //Do a search for exotics
    public void searchForExotics() throws Exception{
        WebDriverWait wait = new WebDriverWait(driver,30);
        WebElement element;
        element = driver.findElement(By.id("input-search"));
        element.sendKeys("exotics" + ENTER);
        waitForLoad();
        wait.until(ExpectedConditions.titleContains("exotics"));
        Assert.assertEquals(driver.getTitle(), "exotics For Auction at Copart - Salvage Cars For Sale");
    }

    @Test(dependsOnMethods={"searchForExotics"})
    //Check title for exotics
    public void searchForPorsche() throws Exception{
        WebDriverWait wait = new WebDriverWait(driver,30);
        waitForLoad();
        Assert.assertEquals(driver.getTitle(), "exotics For Auction at Copart - Salvage Cars For Sale");
    }

}
