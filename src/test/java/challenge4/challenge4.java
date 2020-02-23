package challenge4;
/*
For this challenge, we are going to write a class that display the fibonacci sequence up to a certain number.
If I want the fibonacci for the 9 order of the sequence, I should see 21.
Keep your function to calculate the fibonacci sequence separate from the test file.

However, to add additional challenge to this challenge, instead of displaying the number 21,
I want the string representation of twenty one.
This will require you to use string concatenation to print out the string.

Your console output will look something like this.

13 - thirteen
144 - one hundred forty four
7408 - seven thousand four hundred eight

Now that you know how to write a class, copy your previous challenge folder and create a new folder.
Now let’s refactor your “before” method and make it a one line call out to another function.
The reason why we do this is so that we only have one function that does our initialization of the driver.
Otherwise, the poor implementation would be to copy multiple lines from script to script.
When it comes time to change the “before” method, you will have to change it in multiple places versus one.

*/


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.testng.annotations.*;

public class challenge4 {

    public WebDriver beforeChallenge4() throws Exception {
        System.setProperty("webdriver.chrome.driver", "./bin/chromedriver");
        WebDriver driver =  new ChromeDriver();
        return driver;
    }

    @BeforeSuite
    public void startSuite() throws Exception {
    }

    @AfterSuite
    public void stopSuite() throws Exception {
        System.out.println("All done!!!");
    }

    @BeforeClass
    public void startClass() throws Exception{
        beforeChallenge4();
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



}
