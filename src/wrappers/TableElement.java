package wrappers;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class TableElement  extends BaseElement {

	public TableElement(WebDriver driver, By searchBy) {
		super(driver, searchBy);
		// TODO Auto-generated constructor stub
	}

	public ArrayList<String> getTableHeader(){
		// TODO Need to implement function here
		return new ArrayList<String>();
	}
	
	public int getColumnHeaderPosition(String columnHeader) {
		// TODO Need to implement function here
		int index = -1;
		ArrayList<String> tblHeader = getTableHeader();
		for (int i = 0; i < tblHeader.size(); i++){
			if(columnHeader.equals(tblHeader.get(i))){
				index = i;
				break;
			}
		}
		return index;
	}
	
	public boolean clickOnLinkTextByName(String name, String columnName, int tableSize, WebElement nextBtn){
		// TODO Need to implement function here
		try {
			int columnIndex = getColumnHeaderPosition(columnName);
			if (tableSize <= 0 || columnIndex == -1){
				return false;
			}
			
			for (int i = 0; i < tableSize; i++) {
				WebElement tbody = element.findElement(By.tagName("tbody"));
				List<WebElement> rows = tbody.findElements(By.tagName("tr"));
				for (WebElement row : rows) {
					List<WebElement> columns = row.findElements(By.tagName("td"));
					if (name.equals(columns.get(columnIndex).getText())) {
						addLog("Select : " + name);
						row.findElement(By.tagName("a")).click();
						return true;
					}
				}
				nextBtn.click();
			}
			addErrorLog("Item: " + name + " not found");
		} catch (NoSuchElementException e) {
			return false;
		}
		return false;
	}
	
	public boolean isCellExisted (String name, String columnName, int tableSize, WebElement nextBtn){
		// TODO Need to implement function here
		try {
			int columnIndex = getColumnHeaderPosition(columnName);
			if (tableSize <= 0 || columnIndex == -1){
				return false;
			}
			
			for (int i = 0; i < tableSize; i++) {
				WebElement tbody = element.findElement(By.tagName("tbody"));
				List<WebElement> rows = tbody.findElements(By.tagName("tr"));
				for (WebElement row : rows) {
					List<WebElement> columns = row.findElements(By.tagName("td"));
					if (name.equals(columns.get(columnIndex).getText())) {
						return true;
					}
				}
				nextBtn.click();
			}
			addErrorLog("Item: " + name + " not found");
		} catch (NoSuchElementException e) {
			return false;
		}
		return false;
	}
	
}
