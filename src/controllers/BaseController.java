package controllers;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import java.io.File;
import java.util.List;

public class BaseController {

    protected WebDriver driver;
    protected Wait<WebDriver> wait;

    public class Constant {
        public static final int TIME_WAIT = 30;

    }

    public enum enumOsType{
        Windows,
        Linux,
        MacOS,
        Unknown
    }
    public BaseController(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Constant.TIME_WAIT);
    }

    protected enumOsType getOsType() {
        enumOsType osType = enumOsType.Unknown;
        String osname = System.getProperty("os.name").toLowerCase();
        if (osname.contains("unix") || osname.contains("linux")) {
            osType = enumOsType.Linux;
        } else if (osname.contains("windows")) {
            osType = enumOsType.Windows;
        } else if (osname.contains("mac os")) {
            osType = enumOsType.MacOS;
        }
        return osType;
    }

    public WebDriver getDriver(){
        return driver;
    }

    public void addLog(String logmsg) {
        Reporter.log(logmsg + "</br>", true);
    }

    public void addErrorLog(String logmsg) {
        Reporter.log("<font color='red'> " + logmsg + " </font></br>", true);
    }

    public void addSuccessLog(String logmsg) {
        Reporter.log("<font color='green'> " + logmsg + " </font></br>", true);
    }

    public void waitForAjax() {
        System.err.println("Checking active ajax calls by calling jquery.active ...");
        try {
            if (driver instanceof JavascriptExecutor) {
                JavascriptExecutor jsDriver = (JavascriptExecutor) driver;
                for (int i = 0; i < Constant.TIME_WAIT; i++) {
                    Object numberOfAjaxConnections = jsDriver.executeScript("return jQuery.active");
                    // return should be a number
                    if (numberOfAjaxConnections instanceof Long) {
                        Long n = (Long) numberOfAjaxConnections;
                        System.err.println("Number of active jquery ajax calls: " + n);
                        if (n.longValue() == 0L)
                            break;
                    }
                    Thread.sleep(3000);
                }
            } else {
                System.err.println("Web driver: " + driver
                        + " cannot execute javascript");
            }
        } catch (InterruptedException e) {
            System.err.println(e);
        }
    }

    public String getTextByXpath(String xpath) {
        String text = "";
        try {
            text = driver.findElement(By.xpath(xpath)).getText();
            addLog("Text : " + text);
        } catch (NoSuchElementException e) {
            addLog("NoSuchElementException at getTextByXpath :  "+ xpath);
        }
        return text;
    }

    public String readField(String xpath) {
        try {
            waitForAjax();
            WebElement footer = driver.findElement(By.xpath(xpath));
            List<WebElement> columns = footer
                    .findElements(By.tagName("option"));
            for (WebElement column : columns) {
                if (column.isSelected()) {
                    String selected = column.getText();
                    addLog("Item selected : " + selected);
                    return selected;
                }
            }
        } catch (NoSuchElementException e) {
            System.err.println("NoSuchElementException at readField : " + xpath);
        }
        return "";
    }
}
