package supports;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.IOException;

public class BrowserUtils {
    private WebDriver driver;

    public WebDriver getDriver(){
        return driver;
    }

    @Parameters({"browserName", "url"})
    @BeforeClass
    public void initializeBaseSetup(String browserName, String url){
//    public void initializeBaseSetup(){
        driver = openBrowser(browserName);
//        driver = openBrowser("chrome");
        driver.get(url);
        driver.manage().window().maximize();
//        driver.get("https://www.calculator.net/bmi-calculator.html");
    }

    @AfterMethod
    public void testTeardownBaseSetup(ITestResult result) throws IOException{
        if(!result.isSuccess()){
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            File destinationFile = new File("./target/screenshots/" + result.getName() + ".png");
            FileUtils.copyFile(screenshot.getScreenshotAs(OutputType.FILE), destinationFile);
        }
    }

    @AfterClass
    public void teardownBaseSetup(){
        driver.quit();
    }

    public static WebDriver openBrowser(String browserName){
        if(browserName.equalsIgnoreCase("chrome")){
            WebDriverManager.chromedriver().setup();
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.setHeadless(true);
            return new ChromeDriver(chromeOptions);
//            return new ChromeDriver();
        }
        else if(browserName.equalsIgnoreCase("safari")){
            return new SafariDriver();
        }
        else {
            throw new IllegalArgumentException("This browser " + browserName + " doesn't supported.");
        }
    }


}
