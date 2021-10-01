package wrappers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ComboBoxElement extends BaseElement {

	public ComboBoxElement(WebDriver driver, By searchBy) {
		super(driver, searchBy);
		// TODO Auto-generated constructor stub
	}

//	public ArrayList<WebElement> getListComponent() {
//		// TODO Need to implement function here
//		return new ArrayList<WebElement>();
//	}

	public boolean clickByIndex(int index) {
		if (isReady) {
			WebElement[] list = (WebElement[])element.findElements(By.tagName("option")).toArray();
			if(index < list.length && index >=0){
				for (int i = 0; i < list.length; i++) {
					if(i == index) {
						list[i].click();
						return true;
					}
				}
			}
		}
		return false;
	}

	public boolean clickByText(String text) {
		if (isReady) {
			List<WebElement> list = element.findElements(By.tagName("option"));
			for (WebElement webElement : list) {
				if (text.equals(webElement.getText())) {
					webElement.click();
					return true;
				}
			}
		}
		return false;
	}

	public int getSize() {
		if (isReady) {
			List<WebElement> list = element.findElements(By.tagName("option"));
			return list.size();
		}
		return 0;
	}
}
