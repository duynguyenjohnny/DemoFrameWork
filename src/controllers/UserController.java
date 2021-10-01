package controllers;

import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.Reporter;

import java.util.Random;

public class UserController extends BaseController {
	public UserController(WebDriver driver) {
		super(driver);
	}

	@Override
	public String getTextByXpath(String xpath) {
		String text = "";
		try {
			text = driver.findElement(By.xpath(xpath)).getText();
			addLog("Text : " + text);
		} catch (NoSuchElementException e) {
			addLog("NoSuchElementException at getTextByXpath :  " + xpath);
		}
		return text;
	}

	public boolean isElementPresentXpath(String xpath) {
		try {
			boolean isDisplayed = driver.findElement(By.xpath(xpath)).isDisplayed();
			if (isDisplayed) {
				addLog("Element displayed : " + xpath);
				return true;
			} else {
				addLog("Element doesn't existed : " + xpath);
				return false;
			}
		} catch (NoSuchElementException e) {
			addLog("Element doesn't existed : " + xpath);
			return false;
		}
	}

	public boolean isElementPresentId(String id) {
		try {
			boolean isDisplayed = driver.findElement(By.id(id)).isDisplayed();
			if (isDisplayed) {
				addLog("Element displayed : " + id);
				return true;
			} else {
				addLog("Element doesn't existed : " + id);
				return false;
			}
		} catch (NoSuchElementException e) {
			addLog("Element doesn't existed : " + id);
			return false;
		}
	}

	public String randomString() {
		String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while (salt.length() < 6) { // length of the random string.
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));
		}
		String saltStr = salt.toString();
		return saltStr;

	}

	public String randomNumber(int numberchars) {
		String SALTCHARS = "1234567890";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while (salt.length() < numberchars) { // length of the random string.
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));
		}
		String saltStr = salt.toString();
		return saltStr;

	}

	public String randomChars(int numberchars) {
		String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while (salt.length() < numberchars) { // length of the random string.
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));
		}
		String saltStr = salt.toString();
		return saltStr;

	}

	public void addLog(String text){
		Reporter.log(text+ "</br>", true);
	}

	public void type(String xpath, String data) {
		try {
			driver.findElement(By.xpath(xpath)).clear();
			addLog("change data : " + data);
			driver.findElement(By.xpath(xpath)).sendKeys(data);
		} catch (NoSuchElementException e) {
			addLog("NoSuchElementException at editData :  " + xpath);
		}

	}

	public String getElementText(WebElement element) {
		try {
			String text = element.getText();
			addLog("Text : " + text);
			return text;
		} catch (NoSuchElementException e) {
			addLog("NoSuchElementException at getTextByXpath : " + element);
		}
		return "";
	}

	public void typeId(String id, String data) {
		try {
			driver.findElement(By.id(id)).clear();
			addLog("change data : " + data);
			driver.findElement(By.id(id)).sendKeys(data);
		} catch (NoSuchElementException e) {
			addLog("NoSuchElementException at editData :  " + id);
		}

	}

	public void clickXpath(String xpath) {
		try {
			addLog("Click : " + xpath);
			driver.findElement(By.xpath(xpath)).click();

		} catch (NoSuchElementException e) {
			addLog("No element exception : " + xpath);
			Assert.assertTrue(false);
		}
	}

	public void clickId(String id) {
		try {
			addLog("Click : " + id);
			driver.findElement(By.id(id)).click();

		} catch (NoSuchElementException e) {
			addLog("No element exception : " + id);
			Assert.assertTrue(false);
		}
	}

}
