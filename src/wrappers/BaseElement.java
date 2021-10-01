package wrappers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

public class BaseElement {
	protected WebElement element;
	protected boolean isReady = true;
	
	public BaseElement(WebDriver driver, By searchBy){
		element = driver.findElement(searchBy);
		if(element == null){
			addErrorLog("Not found element: " + searchBy.toString());
			isReady = false;
		}
	}	
	
	public String getText(){
		if(isReady){
			return element.getText();
		}
		return "";
	}
	
	public void click(){
		if(isReady){
			addLog("Click on " + element);
			element.click();
		}
	}
	
	protected void addLog(String msg) {
		Reporter.log(msg + "</br>", true);
	}
	
	protected void addErrorLog(String msg) {
		Reporter.log("<font color='red'> " + msg + " </font></br>", true);
	}

}
