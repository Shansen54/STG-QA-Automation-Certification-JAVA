package challenge7;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import java.util.List;
import java.util.TreeMap;

public class challenge7 {

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
    Challenge 7 - Array or ArrayList
    For this challenge, take a look at https://www.copart.com main page. Done.
    Go to the Makes/Models section of the page. Done.
    Create a 2 dimensional array or arraylist that stores all the values displayed on the page along w/ the URL for that link. Done.
    Once you have this array, you can verify all the elements in the array navigates to the correct page. Done.
    The only way to build a function/object for this section is to loop through each element. Done.
    Hint: xpath is easiest. Done.
    */

    @Test()
    //Go to Copart.com
    public void goToCopart() {
        driver.get("https://www.copart.com");
    }

    @Test(dependsOnMethods={"goToCopart"})
    //Get List of Popular Makes and Models from Popular list.
    public void getListOfPopularCars() {

        By popularSearchesOfMakesAndModels = By.xpath("//div[@ng-if=\"popularSearches\"]");
        By popularSearchesListOfURLs__By = By.xpath("//div[@ng-if=\"popularSearches\"]//a");

        wait.until(ExpectedConditions.visibilityOfElementLocated(popularSearchesOfMakesAndModels));

        List<WebElement> popularSearchesList = driver.findElements(popularSearchesListOfURLs__By);
        Assert.assertEquals(20, popularSearchesList.size(),"Could not find 20 popular Makes and Models");
        TreeMap<String,String> popularModels = new TreeMap<>();
        for (WebElement element:popularSearchesList) {
            String makeOrModel = element.getText() ;
            String href = element.getAttribute("href");
            popularModels.put(makeOrModel,href);
            System.out.println( makeOrModel+ " -- " + href);
        }

        for (String key: popularModels.keySet()) {
            driver.get(popularModels.get(key));
            try {
                wait.until(ExpectedConditions.titleContains(key.toLowerCase()));
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("link for " + key + " did not go to the right page");
            }
        }
    }
}
