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


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.testng.annotations.*;

public class challenge4 {

    public WebDriver beforeChallenge4() throws Exception {
        System.setProperty("webdriver.chrome.driver", "./bin/chromedriver");
        WebDriver driver = new ChromeDriver();
        return driver;
    }

    public WebDriver afterChallenge4() throws Exception {
        WebDriver driver = new ChromeDriver();
        driver.quit();
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
    public void startClass() throws Exception {
        beforeChallenge4();
    }

    @AfterClass
    public void stopClass() throws Exception {
        afterChallenge4();
    }

    @BeforeMethod()
    public void beforeMethod() throws Exception {
        beforeChallenge4();
    }

    @AfterMethod()
    public void afterMethod() {
    }
/*
    //Fibonacci Series using Recursion - This code is contributed by Rajat Mishra
    class fibonacci {
        static int fib(int n) {
            if (n <= 1)
                return n;
            return fib(n - 1) + fib(n - 2);
        }
    }

    class toWords {
        String[] singleNum_array = {"", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten",
                "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};
        String[] tens_array = {"", "", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};
        String[] th = {"", "thousand", "million", "billion", "trillion"};

        String s = s_number = number.toString();
        Integer x = s.length;
        String n = s.split(",");
        String str = "";
        Integer scount = 0;
        Integer i = 0;

        public void doTheWork() {
            for (i = 0; i < x; i++) {
                if ((x - i) % 3 == 2) {
                    if (n[i] == '1') {
                        str += singleNum_array[Number(n[i + 1])] + ' ';
                        i++;
                        scount = 1;
                    } else if (n[i] != 0) {
                        str += tens_array[n[i]] + ' ';
                        scount = 1;
                    }
                } else if (n[i] != 0) {
                    str += singleNum_array[n[i]] + ' ';
                    if ((x - i) % 3 == 0) str += "hundred ";
                    scount = 1;
                }
                if ((x - i) % 3 == 1) {
                    if (scount) str += th[(x - i - 1) / 3] + ' ';
                    scount = 0;
                }
            }
            return str;
        }
    }

    @Test
            //("Challenge4 suite", function(){

        public static void shouldReturnTheFibonacciNumberAndTheWordsOfTheNumber(){
        //order is the nth order in the fibonacci sequence
        int[] order = {1,4,9,13,17,20,26,31};
        for(var i=0;i< 8;i++)
        {
        System.out.println("The (" + order[i] + ") number in the fibonacci sequence is "+fibonacci(order[i])+
        " and its words are "+toWords(fibonacci(order[i])));
        }
        }
     */
}
