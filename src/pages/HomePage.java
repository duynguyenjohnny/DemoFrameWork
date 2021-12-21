package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import ultilites.SecuredProperties;
import ultilites.SecuredPropertiesConfig;

import java.io.File;

public class HomePage {

    /**

     * All WebElements are identified by @FindBy annotation

     */

    WebDriver driver;

    @FindBy(xpath="//div[contains(text(),'Inventory')]")

    WebElement inventory;

    @FindBy(xpath="//div[contains(text(),'Manufacturing')]")

    WebElement manufacturing;

    @FindBy(xpath="//a[contains(text(),'Products')]")

    WebElement products;

    public HomePage(WebDriver driver){

        this.driver = driver;

        //This initElements method will create all WebElements

        PageFactory.initElements(driver, this);

    }

    public void clickManufacturing(){

        manufacturing.click();

    }

    public void navigateToInventory() throws InterruptedException {
     //Navigate to inventory
        this.driver.navigate().to("https://aspireapp.odoo.com/web#action=207&model=stock.picking.type&view_type=kanban&cids=1&menu_id=101");
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.elementToBeClickable(products));
        Assert.assertTrue(products.isDisplayed(),"Passed Case");
    }
}
