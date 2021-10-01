package wrappers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LinkTextElement extends BaseElement {

	public LinkTextElement(WebDriver driver, By searchBy) {
		super(driver, searchBy);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void click(){
		if(isReady){
			element.click();
		}		
	}
}
