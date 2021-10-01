package wrappers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckBoxElement extends BaseElement {

	public CheckBoxElement(WebDriver driver, By searchBy) {
		super(driver, searchBy);
		// TODO Auto-generated constructor stub
	}

	public void check(){
		if(isReady && !element.isSelected()){
			element.click();
		}
	}
	
	public void unCheck(){
		if(isReady && element.isSelected()){
			element.click();
		}
	}
	
	public boolean isChecked(){
		if(isReady){
			return element.isSelected();
		} else {
			return false;
		}
	}
	
}
