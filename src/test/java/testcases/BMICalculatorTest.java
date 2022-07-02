package testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.BMICalculatorPage;
import supports.BMICalculatorUtils;
import supports.BrowserUtils;

public class BMICalculatorTest extends BrowserUtils implements BMICalculatorUtils {
    public BMICalculatorPage page;
    private WebDriver driver;

    @BeforeMethod
    void testSetup(){
        driver = getDriver();
    }

    @DataProvider
    Object[][] validDataTest(){
        return new Object[][]{
                {25, "male", 180, 65}
//                {29.34, "male", 180.9032, 70.23423},
//                {20, "female", 150, 55},
//                {2, "female", 0.5, 0.5},
//                {120, "male", 2000, 160}
        };
    }
    @Test(dataProvider = "validDataTest")
    void verifyCalculateWithValidTestData(double age, String gender, double height, double weight) throws InterruptedException {
        page = new BMICalculatorPage(driver);
//        Thread.sleep(2000);
        page.selectMetricUnitTab();
//        Thread.sleep(500);
        page.clickClearBtn();
//        Thread.sleep(500);
        page.fillForm(age, gender, height, weight);
//        Thread.sleep(500);
        page.clickCalculateBtn();
//        Thread.sleep(3000);
        String actualResult = page.getResult();
        String expectedResult = bmiResult(height, weight);
        Assert.assertEquals(actualResult, expectedResult);

    }
}
