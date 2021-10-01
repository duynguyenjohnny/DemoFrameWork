package pages;

import controllers.BaseController;
import controllers.UserController;
import ultilites.*;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.*;

import java.awt.*;
import java.awt.Point;
import java.io.File;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

public abstract class BasePage {

    private static String reportFolder = "";
    protected ExtentTest test;
    private static ExtentReports extent;
    final String reportPath = "./Extent.html";
    protected String url;
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected UserController userControl;
    protected BaseController baseAction;

    public void setUp(String propertyFile) throws Exception {
        try{
            System.out.println("Before Method: Setup");
        }catch (Exception ex){
            System.out.println("Error Before Method: Setup:" + ex.getMessage());
        }

    }

    public enum OS {
        OS_LINUX, OS_WINDOWS, OS_OTHERS, OS_SOLARIS, OS_MAC_OS_X
    }

    protected void initControllers(WebDriver driver) {
        userControl = new UserController(driver);
        baseAction = new BaseController(driver);
    }

    protected abstract void initData();

    @BeforeSuite
    protected void beforeSuite() {
        reportFolder = DateTime.getCurrentTime("MM-dd-yyyy_HHmmss");
        String reportPath = "test-reports/" + reportFolder + "/images";
        FolderFile.createMutilFolder(reportPath);
        extent = ExtentManager.getReporter("test-reports/" + reportFolder
                + "/ExtentReport.html");
    }

    @Parameters({ "browser", "url" })
    @BeforeMethod
    public void initializeDriver(@Optional("chrome") String browser,
                                  @Optional("") String url, Method method) {
        System.out.println("*********************** Start :" + method.getName()
                + " ***********************");
        try {
            this.url = url;
            if (browser.equalsIgnoreCase("firefox")) {
                System.out.println(" Executing on FireFox");
                setDriverSystemPath();
                driver = new FirefoxDriver();
                wait = new WebDriverWait(driver, 600);
            } else if (browser.equalsIgnoreCase("chrome")) {
                System.out.println(" Executing on CHROME");
                if (getOs() == OS.OS_WINDOWS) {
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("chrome.switches", "--disable-extensions");
                    options.addArguments("disable-popup-blocking");
                    options.addArguments("disable-impl-side-painting");
                    System.setProperty("webdriver.chrome.driver",
                            "driver//chromedriver.exe");
                    driver = new ChromeDriver(options);
                    wait = new WebDriverWait(driver, 600);
                    // driver = new ChromeDriver();
                } else if (getOs() == OS.OS_LINUX) {
                    System.setProperty("webdriver.chrome.driver", "driver/chromedriver");
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--headless");
                    chromeOptions.addArguments("--no-sandbox");
                    chromeOptions.addArguments("chrome.switches", "--disable-extensions");
                    chromeOptions.addArguments("disable-popup-blocking");
                    chromeOptions.addArguments("disable-impl-side-painting");
                    driver = new ChromeDriver(chromeOptions);
                    wait = new WebDriverWait(driver, 600);
                }

            } else if (browser.equalsIgnoreCase("ie")) {
                System.out.println("Executing on IE");
                System.setProperty("webdriver.ie.driver",
                        "driver/IEDriverServer64.exe");
                driver = new InternetExplorerDriver();
                wait = new WebDriverWait(driver, 600);

            } else {
                throw new IllegalArgumentException(
                        "The Browser Type is Undefined");
            }
            // prepare custom config
            final SecuredPropertiesConfig config = new SecuredPropertiesConfig()
                    .withSecretFile(new File("./src/tests/loginpage/mysecret.key"))
                    .initDefault();

            // auto-encrypt values in the property-file:
            SecuredProperties.encryptNonEncryptedValues(config,
                    new File("./src/tests/loginpage/Login.properties"), // The Property File
                    "Entribe_Enviroment_QA"); // the property-key from "myConfiguration.properties"

            // read encrypted values from the property-file
            String Entribe_Enviroment_QA = SecuredProperties.getSecretValue(config,
                    new File("./src/tests/loginpage/Login.properties"), // The Property File
                    "Entribe_Enviroment_QA"); // the property-key from "myConfiguration.properties"

            String URL = Entribe_Enviroment_QA;
            try {
                driver.navigate().to(URL);
            } catch (WebDriverException e) {
                System.out.println("URL was not loaded");
            }
            test = extent.startTest(method.getName()).assignCategory(
                    getClass().getSimpleName());
            // driver.get(url);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.manage().deleteAllCookies();
            driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.MINUTES);
            driver.manage().window().maximize();
            initControllers(driver);
            initData();

        } catch (TimeoutException e) {
            System.out.println("Page load time out exception");
            driver.navigate().refresh();
            driver.quit();
        } catch (UnreachableBrowserException e) {
            System.out.println("Unreacheable browser exception");
            driver.navigate().refresh();
            driver.quit();

        } catch (NullPointerException e){
            System.out.println("Unreacheable browser exception");
            driver.navigate().refresh();
            driver.quit();
        }

    }

    private void setDriverSystemPath() {
        if (getOs() == OS.OS_WINDOWS) {
            System.setProperty("webdriver.gecko.driver",
                    "driver//geckodriver.exe");
        } else if (getOs() == OS.OS_LINUX) {
            System.setProperty("webdriver.gecko.driver", "driver/geckodriver");
        }
        // Add another check when have a new OS
    }

    public OS getOs() {
        String osName = System.getProperty("os.name");
        String osNameMatch = osName.toLowerCase();
        OS osType = null;

        if (osNameMatch.contains("linux")) {
            osType = OS.OS_LINUX;
        } else if (osNameMatch.contains("windows")) {
            osType = OS.OS_WINDOWS;
        } else if (osNameMatch.contains("solaris")
                || osNameMatch.contains("sunos")) {
            osType = OS.OS_SOLARIS;
        } else if (osNameMatch.contains("mac os")
                || osNameMatch.contains("macos")
                || osNameMatch.contains("darwin")) {
            osType = OS.OS_MAC_OS_X;
        } else {
            osType = OS.OS_OTHERS;
        }
        return osType;
    }

        @AfterMethod(alwaysRun = true)
        public void afterTest(ITestResult iTestResult, Method method) {
            String exception = null;
            String result = "";
            if (driver == null)
                return;
            Reporter.setCurrentTestResult(iTestResult);
            MouseMover();
            if (!iTestResult.isSuccess()) {
                System.setProperty("org.uncommons.reportng.escape-output", "false");
                //test.log(LogStatus.FAIL, iTestResult.getThrowable() + screenShoot());
                result = "false";
                exception = iTestResult.toString();
                // exception =iTestResult.getMessage();
                System.out.println(exception);
                String TCNAME = iTestResult.getName().toUpperCase();
                String testcaseid = TCNAME.replace("_", "-");
                System.out.println(testcaseid);
                userControl.addErrorLog(exception);
                //String imgPath = "";
                String imgName = DateTime.getCurrentTime("MM-dd-yyyy_HHmmss") + "_"+ method.getName() + ".png";
                try {
                    File scrnsht = ((TakesScreenshot) driver)
                            .getScreenshotAs(OutputType.FILE);
                    File path = new File("").getAbsoluteFile();
                    String pathfile = path + "\\test-reports\\"
                            + reportFolder + "\\images\\" + imgName;
                    FileHandler.copy(scrnsht, new File(pathfile));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //imgPath = test.addScreenCapture("./images/" + imgName);
                //return imgPath;
                Reporter.log("FAILED", true);
                test.log(LogStatus.FAIL, test.addScreenCapture("./images/" + imgName) +
                        Reporter.getOutput().get(0) + "<font color='red'>" + testcaseid + "</font>" +
                        "<font color='red'> was failed </font>") ;
                test.log(LogStatus.FAIL, iTestResult.getThrowable());
            } else if(iTestResult.isSuccess()){
                System.setProperty("org.uncommons.reportng.escape-output", "false");
                //test.log(LogStatus.PASS, iTestResult.getThrowable());
                result = "pass";
                exception = iTestResult.toString();
                // exception =iTestResult.getMessage();
                System.out.println(exception);
                String TCNAME = iTestResult.getName().toUpperCase();
                String testcaseid = TCNAME.replace("_", "-");
                System.out.println(testcaseid);
                userControl.addSuccessLog(exception);
                //String imgPath = "";
                String imgName = DateTime.getCurrentTime("MM-dd-yyyy_HHmmss") + "_"+ method.getName() + ".png";
                try {
                    File scrnsht = ((TakesScreenshot) driver)
                            .getScreenshotAs(OutputType.FILE);
                    File path = new File("").getAbsoluteFile();
                    String pathfile = path + "\\test-reports\\"
                            + reportFolder + "\\images\\" + imgName;
                    FileHandler.copy(scrnsht, new File(pathfile));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //imgPath = test.addScreenCapture("./images/" + imgName);
                //return imgPath;
                Reporter.log("PASSED", true);
                test.log(LogStatus.PASS, test.addScreenCapture("./images/" + imgName) +
                        Reporter.getOutput().get(0) + "<font color='green'>" + testcaseid + "</font>" +
                        "<font color='green'> was passed </font>") ;
            } else {
                System.setProperty("org.uncommons.reportng.escape-output", "false");
                String TCNAME = iTestResult.getName().toUpperCase();
                String testcaseid = TCNAME.replace("_", "-");
                Reporter.log("SKIPPED", true);
                test.log(LogStatus.SKIP, Reporter.getOutput().get(0) + "<font color='yellow'>" +
                        testcaseid + "</font>" + "<font color='yellow'> was skipped </font>") ;
            }
            // close browser after finish test case
            if (driver == null) {
                return;
            }
            System.out.println("Close browser");
            driver.close();
            Reporter.log(
                    "===========================================================================",
                    true);
            extent.endTest(test);
            extent.flush();
            driver.quit();
        }

	@AfterSuite
	protected void afterSuite() {
		extent.close();
	}

    public void MouseMover() {
        try {
            Robot robot = new Robot();
            Point point = MouseInfo.getPointerInfo().getLocation();
            robot.mouseMove(point.x + 120, point.y + 120);
            robot.mouseMove(point.x, point.y);
        } catch (AWTException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
