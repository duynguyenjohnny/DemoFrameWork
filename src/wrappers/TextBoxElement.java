package wrappers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TextBoxElement extends BaseElement {
	
	public TextBoxElement(WebDriver driver, By searchBy){
		super(driver, searchBy);
	}	
	
	public void enterText(String text){
		if(isReady){
			element.sendKeys(text);
		}
	}

}