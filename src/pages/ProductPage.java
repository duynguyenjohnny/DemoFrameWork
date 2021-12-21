package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Random;

public class ProductPage {

    /**

     * All WebElements are identified by @FindBy annotation

     */

    WebDriver driver;

    @FindBy(xpath="//a[contains(text(),'Products')]")

    WebElement products;

    @FindBy(xpath="//header/nav[1]/a[1]")

    WebElement application;

    @FindBy(xpath="//span[contains(text(),'Update Quantity')]")

    WebElement updateQuantity;

    @FindBy(id="o_field_input_734")

    WebElement productName;

    @FindBy(xpath="//tbody/tr[5]/td[2]/div[1]/div[1]")

    WebElement product;

    @FindBy(name="product_uom_qty")

    WebElement productQty;

    @FindBy(xpath="//button[contains(text(),'Create')]")

    WebElement btnCreate;

    @FindBy(xpath="//em[contains(text(),'Product')]")

    WebElement searchProduct;

    @FindBy(xpath="//span[contains(text(),'Confirm')]")

    WebElement btnConfirm;

    @FindBy(xpath="//span[contains(text(),'Ok')]")

    WebElement btnOk;

    @FindBy(xpath="//body/div[5]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/input[1]")

    WebElement search;

    @FindBy(xpath="//span[contains(text(),'Filters')]")

    WebElement btnFilters;

    @FindBy(xpath="//a[contains(text(),'To Do')]")

    WebElement btnToDo;

    @FindBy(xpath="//span[contains(text(),'Mark as Done')]")

    WebElement btnMarkAsDone;

    @FindBy(xpath="//span[contains(text(),'Apply')]")

    WebElement btnApply;

    @FindBy(linkText="Add a line")

    WebElement btnAddALine;

    @FindBy(xpath="//tbody/tr[1]/td[1]/div[1]/div[1]/input[1]")

    WebElement comProduct;

    @FindBy(xpath="//div[contains(text(),'Manufacturing')]")

    WebElement manufacturing;

    public ProductPage(WebDriver driver){

        this.driver = driver;

        //This initElements method will create all WebElements

        PageFactory.initElements(driver, this);

    }

    //Click on Products

    public void clickProducts(){

        products.click();

    }

    public void clickApplication(){

        application.click();

    }

    public void clickManufacturing(){

        manufacturing.click();

    }

    public void clickUpdateQuantity(){

        updateQuantity.click();

    }

    public void setProductName(String strProductName){

        productName.sendKeys(strProductName);
    }

    public void updateQuantityandCreateManufacturing(){

        Random random = new Random();
        // Generates random integers 0 to 999
        int number = random.nextInt(1000);
        String productName = "Test" + number;
        this.setProductName(productName);
        this.clickUpdateQuantity();
        this.clickApplication();
        this.clickManufacturing();
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.elementToBeClickable(btnCreate));
        btnCreate.click();
        wait.until(ExpectedConditions.elementToBeClickable(product));
        product.sendKeys(productName);
        btnAddALine.click();
        comProduct.sendKeys(productName);
        productQty.sendKeys("100");
        //btnSave.click();
        btnConfirm.click();
        btnOk.click();
        btnMarkAsDone.click();
        btnApply.click();
        this.clickApplication();
        this.clickManufacturing();
        btnFilters.click();
        btnToDo.click();
        search.sendKeys(productName);
        searchProduct.click();
    }

}
