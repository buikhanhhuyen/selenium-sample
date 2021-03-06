package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.DecimalFormat;
import java.time.Duration;

public class BMICalculatorPage {
    private WebDriver driver;
//    By metricUnitTab = By.xpath("//*[@id='menuon']//a");
    By metricUnitTab = By.linkText("Metric Units");
    By ageInput = By.id("cage");
    By maleRad = By.xpath("//label[@class='cbcontainer'][1]");
    By femaleRad = By.xpath("//label[@class='cbcontainer'][2]");
//    By heightInput = By.id("cheightmeter");
    By heightInput = By.xpath("//input[@id='cheightmeter']");
    By weightInput =By.id("ckg");
    By calculateBtn = By.xpath("//input[@value='Calculate']");
    By clearBtn = By.xpath("//*[@class='clearbtn']");
    By resultLabel = By.xpath("//*[@class='bigtext']//b[1]");
    By ageErrorMsg = By.id("cageifcErr");
    By heightErrorMsg = By.id("cheightmeterifcErr");
    By weightErrorMsg = By.id("ckgifcErr");
    By errorMsg = By.className("rightresult");
    private DecimalFormat format = new DecimalFormat("0.#");

    public BMICalculatorPage(WebDriver driver){
        this.driver = driver;
    }

    public void fillForm(double age, String gender, double height, double weight){
        inputAge(age);
        chooseGender(gender);
        inputHeight(height);
        inputWeight(weight);
    }

    public void selectMetricUnitTab(){
        driver.findElement(metricUnitTab).click();
//        driver.findElement(By.linkText("Metric Units")).click();
    }

    public void clickClearBtn(){
        driver.findElement(clearBtn).click();
    }

    public void clickCalculateBtn(){
        driver.findElement(calculateBtn).click();
    }

    public void inputAge(double age){

        driver.findElement(ageInput).sendKeys(String.valueOf(format.format(age)));
    }

    public void chooseGender(String gender){
        if(gender.equalsIgnoreCase("male"))  driver.findElement(maleRad).click();
        else    driver.findElement(femaleRad).click();
    }

    public void inputHeight(double height){
//        waitUntilElementVisible(driver.findElement(heightInput));
        driver.findElement(heightInput).sendKeys(String.valueOf(format.format(height)));

//        je.executeScript("document.getElementById('" + heightInput + "').value = '" + String.valueOf(format.format(height)) + "'");
//        je.executeScript("document.getElementById('" + heightInput + "').setAttribute('value', '" + String.valueOf(format.format(height)) + "')");
//        JavascriptExecutor je = (JavascriptExecutor) driver;
//        String xpath = "document.evaluate(\"//input[@id='cheightmeter']\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null)";
//        je.executeScript("document.evaluate(" + xpath + ").singleNodeValue.value=" + String.valueOf(format.format(height)) + ";");
//        je.executeScript(xpath + "singleNodeValue.value=" + String.valueOf(format.format(height)) + ";");
    }

    public void inputWeight(double weight){
        driver.findElement(weightInput).sendKeys(String.valueOf(format.format(weight)));
    }

    public String getRequiredErrorMsg(String scope){
        if(scope.equalsIgnoreCase("age")){
            driver.findElement(ageInput).sendKeys(Keys.DELETE);
//            driver.findElement(By.xpath("//*[@class='rightresult']//h2")).click();
            // use step to lose focus from input
            chooseGender("male");
            return getAgeErrorMsg();
        } else if(scope.equalsIgnoreCase("height")){
            driver.findElement(heightInput).sendKeys(Keys.DELETE);
//            driver.findElement(By.xpath("//*[@class='rightresult']//h2")).click();
            chooseGender("male");
            return getHeightErrorMsg();
        } else{
            driver.findElement(weightInput).sendKeys(Keys.DELETE);
//            driver.findElement(By.xpath("//*[@class='rightresult']//h2")).click();
            chooseGender("male");
            return getWeightErrorMsg();
        }
    }

    public String getResult(){
        return driver.findElement(resultLabel).getText();
    }

    public String getAgeErrorMsg(){
        return driver.findElement(ageErrorMsg).getText();
    }

    public String getHeightErrorMsg(){
        return driver.findElement(heightErrorMsg).getText();
    }

    public String getWeightErrorMsg(){
        return driver.findElement(weightErrorMsg).getText();
    }

    public String getErrorMsg(){
        return driver.findElement(errorMsg).getText();
    }

    private void waitToFinishLoading(){
        JavascriptExecutor j = (JavascriptExecutor) driver;
        do{
            driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        } while(!j.executeScript("return document.readyState").toString().equalsIgnoreCase("complete"));
    }

    private void waitUntilElementVisible(WebElement element){
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOf(element));
    }
}
