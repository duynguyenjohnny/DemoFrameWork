package tests.loginpage;

import pages.*;
import org.testng.annotations.Test;

public class LoginTest extends BasePage {

    @Override
    protected void initData() {
    }

//    @Test(priority=2)
//    public void TC_001() throws InterruptedException {
//        userControl.addLog("TC-001 Login with invalid Username/Password - Failed Case");
//        LoginPage loginPage = new LoginPage(driver);
//        loginPage.failedcased();
//
//    }

    @Test(priority = 1)
    public void Scenario_001() throws InterruptedException {
        userControl.addLog("TC-001 Aspire QA Development Challenge");
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        InventoryPage inventoryPage = new InventoryPage(driver);
        ProductPage productPage = new ProductPage(driver);
        //1. Login to web application
        userControl.addLog("Step 1 Login to web application");
        loginPage.loginWithValidInfo();
        //2. Navigate to `Inventory` feature
        userControl.addLog("Step 2 Navigate to `Inventory` feature");
        homePage.navigateToInventory();
        //3. From the top-menu bar, select `Products -> Products` item, then create a new product
        userControl.addLog("Step 3 select `Products -> Products` item, then create a new product");
        inventoryPage.createNewProduct();
        //4. Update the quantity of new product is more than 10 //random Test Name, save Test Name
        userControl.addLog("Step 4 Update the quantity of new product is more than 10");
        productPage.updateQuantityandCreateManufacturing();
        //5. From top-left page, click on `Application` icon // back to home by click application
        userControl.addLog("Step 5 click on `Application` icon");
        //6. Navigate to `Manufacturing` feature, then create a new Manufacturing Order item for the created Product on step #3 // input Test Name Product
        userControl.addLog("Step 6 create a new Manufacturing Order item for the created Product on step #3");
        //7. Update the status of new Orders to “Done” successfully // input test name product at components, mask as done
        userControl.addLog("Step 7 Update the status of new Orders to “Done” successfully");
        //8. Validate the new Manufacturing Order is created with corrected information. // back to manufacturing, click filter and remove to do, search product name    }
        userControl.addLog("Step 8 Validate the new Manufacturing Order is created with corrected information");

    }

    @Test(priority = 1)
    public void Scenario_002() throws InterruptedException {
        userControl.addLog("TS-002 Yojee WebUI");
        //https://the-internet.herokuapp.com/login  .This is where you can log into the secure area. Enter tomsmith for the username and SuperSecretPassword! for the password. If the information is wrong you should see error messages.
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginWithValidInfoYojee();

    }
}
