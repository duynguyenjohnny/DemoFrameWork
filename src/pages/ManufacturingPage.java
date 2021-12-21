package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class ManufacturingPage {

    /**

     * All WebElements are identified by @FindBy annotation

     */

    WebDriver driver;


    @FindBy(xpath="//span[contains(text(),'Filters')]")

    WebElement btnFilters;


    public ManufacturingPage(WebDriver driver){

        this.driver = driver;

        //This initElements method will create all WebElements

        PageFactory.initElements(driver, this);

    }

    // Create new product
    public void createNewManufacturing()  {


    }

}
