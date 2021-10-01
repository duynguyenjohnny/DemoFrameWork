package wrappers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ButtonElement extends BaseElement {
	
	public ButtonElement(WebDriver driver, By searchBy){
		super(driver, searchBy);
	}	
	
	@Override
	public void click(){
		if(isReady){
			element.click();
		}		
	}
	
}
