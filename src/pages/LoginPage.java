package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ultilites.SecuredProperties;
import ultilites.SecuredPropertiesConfig;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.io.File;

public class LoginPage {

    /**

     * All WebElements are identified by @FindBy annotation

     */

    WebDriver driver;

    @FindBy(id="login")

    WebElement Username;

    @FindBy(id="username")

    WebElement Username3;

    @FindBy(id="password")

    WebElement Password3;

    @FindBy(xpath="//i[contains(text(),'Login')]")

    WebElement login3;

    @FindBy(xpath="//body/div[2]/div[1]/div[1]/a[1]")

    WebElement logout3;

    @FindBy(id="password")

    WebElement Password;

    @FindBy(xpath="//header")

    WebElement titleText;

    @FindBy(xpath="//button[contains(text(),'Log in')]")

    WebElement login;

    @FindBy(xpath="//div[contains(text(),'Inventory')]")

    WebElement inventory;

    public LoginPage(WebDriver driver){

        this.driver = driver;

        //This initElements method will create all WebElements

        PageFactory.initElements(driver, this);

    }

    //Set user name in textbox

    public void setUserName(String strUserName){

        Username.sendKeys(strUserName);
    }

    //Set password in password textbox

    public void setPassword(String strPassword){

        Password.sendKeys(strPassword);

    }

    //Click on login button

    public void clickLogin(){

        login.click();

    }

    //Set user name in textbox

    public void setUserName3(String strUserName){

        Username3.sendKeys(strUserName);
    }

    //Set password in password textbox

    public void setPassword3(String strPassword){

        Password3.sendKeys(strPassword);

    }

    //Click on login button

    public void clickLogin3(){

        login3.click();

    }

    public void checkUI() throws InterruptedException {
        String sMethodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        Thread.sleep(1500);
        Assert.assertTrue((Username.isDisplayed()), sMethodName + " was failed");
        Assert.assertTrue((Password.isDisplayed()), sMethodName + " was failed");
    }

    //Get the title of Login Page

    public String getLoginTitle(){

        return    titleText.getText();

    }

    public void failedcased() throws InterruptedException {

        //WebDriverWait wait = new WebDriverWait(driver,5);
        //wait.until(ExpectedConditions.elementToBeClickable(login));
        final SecuredPropertiesConfig config = new SecuredPropertiesConfig()
                .withSecretFile(new File("./src/tests/loginpage/mysecret.key"))
                .initDefault();
        SecuredProperties.encryptNonEncryptedValues(config,
                new File("./src/tests/loginpage/Login.properties"),
                "Username1");
        SecuredProperties.encryptNonEncryptedValues(config,
                new File("./src/tests/loginpage/Login.properties"),
                "Password1");
        String Username = SecuredProperties.getSecretValue(config,
                new File("./src/tests/loginpage/Login.properties"),
                "Username1");
        String Password = SecuredProperties.getSecretValue(config,
                new File("./src/tests/loginpage/Login.properties"),
                "Password1");

        //Fill user name
        this.setUserName(Username);
        Thread.sleep(200);
        //Fill password
        this.setPassword(Password);
        Thread.sleep(200);
        //Click Login button
        this.clickLogin();
        Assert.assertFalse(true,"Failed Case");
    }

    public void loginWithValidInfo() throws InterruptedException {

        //WebDriverWait wait = new WebDriverWait(driver,5);
        //wait.until(ExpectedConditions.elementToBeClickable(login));
        final SecuredPropertiesConfig config = new SecuredPropertiesConfig()
                .withSecretFile(new File("./src/tests/loginpage/mysecret.key"))
                .initDefault();
        SecuredProperties.encryptNonEncryptedValues(config,
                new File("./src/tests/loginpage/Login.properties"),
                "Username2");
        SecuredProperties.encryptNonEncryptedValues(config,
                new File("./src/tests/loginpage/Login.properties"),
                "Password2");
        String Username = SecuredProperties.getSecretValue(config,
                new File("./src/tests/loginpage/Login.properties"),
                "Username2");
        String Password = SecuredProperties.getSecretValue(config,
                new File("./src/tests/loginpage/Login.properties"),
                "Password2");

        //Fill user name
        this.setUserName(Username);
        Thread.sleep(200);
        //Fill password
        this.setPassword(Password);
        Thread.sleep(200);
        //Click Login button
        this.clickLogin();
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.elementToBeClickable(inventory));
        Assert.assertTrue(inventory.isDisplayed(),"Passed Case");
    }

    public void loginWithValidInfoYojee() throws InterruptedException {

        //WebDriverWait wait = new WebDriverWait(driver,5);
        //wait.until(ExpectedConditions.elementToBeClickable(login));
        final SecuredPropertiesConfig config = new SecuredPropertiesConfig()
                .withSecretFile(new File("./src/tests/loginpage/mysecret.key"))
                .initDefault();
        SecuredProperties.encryptNonEncryptedValues(config,
                new File("./src/tests/loginpage/Login.properties"),
                "Username3");
        SecuredProperties.encryptNonEncryptedValues(config,
                new File("./src/tests/loginpage/Login.properties"),
                "Password3");
        String Username = SecuredProperties.getSecretValue(config,
                new File("./src/tests/loginpage/Login.properties"),
                "Username3");
        String Password = SecuredProperties.getSecretValue(config,
                new File("./src/tests/loginpage/Login.properties"),
                "Password3");

        //Fill user name
        this.setUserName3(Username);
        Thread.sleep(200);
        //Fill password
        this.setPassword3(Password);
        Thread.sleep(200);
        //Click Login button
        this.clickLogin3();
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.elementToBeClickable(logout3));
        Assert.assertTrue(logout3.isDisplayed(),"Passed Case");
    }

    public void loginToWeb(String strUsername, String strPassword) throws InterruptedException {
        //Fill user name
        Thread.sleep(500);
        this.setUserName(strUsername);
        Thread.sleep(500);

        //Fill password
        this.setPassword(strPassword);
        Thread.sleep(500);

        //Click Login button
        this.clickLogin();
    }
}
