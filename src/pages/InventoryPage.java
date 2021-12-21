package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class InventoryPage {

    /**

     * All WebElements are identified by @FindBy annotation

     */

    WebDriver driver;

    @FindBy(xpath="//a[contains(text(),'Products')]")

    WebElement products;

    @FindBy(xpath="//span[contains(text(),'Products')]")

    WebElement lvlProducts;

    @FindBy(xpath="//button[contains(text(),'Create')]")

    WebElement btnCreate;

    @FindBy(id="o_field_input_734")

    WebElement productName;

    public InventoryPage(WebDriver driver){

        this.driver = driver;

        //This initElements method will create all WebElements

        PageFactory.initElements(driver, this);

    }

    //Click on Products

    public void clickProducts(){

        products.click();

    }

    // Create new product
    public void createNewProduct()  {

        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.elementToBeClickable(products));
        products.click();
        wait.until(ExpectedConditions.elementToBeClickable(lvlProducts));
        lvlProducts.click();
        wait.until(ExpectedConditions.elementToBeClickable(btnCreate));
        btnCreate.click();
        wait.until(ExpectedConditions.elementToBeClickable(productName));
        Assert.assertTrue(productName.isDisplayed(),"Passed Case");
    }

}
