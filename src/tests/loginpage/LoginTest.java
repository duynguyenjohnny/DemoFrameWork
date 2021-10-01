package tests.loginpage;

import pages.BasePage;
import pages.LoginPage;
import org.testng.annotations.Test;

public class LoginTest extends BasePage {

    @Override
    protected void initData() {
    }

    @Test(priority=1)
    public void TC_001() throws InterruptedException {
        userControl.addLog("TC-001 Login with invalid Username/Password - Failed Case");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.demoTest001();

    }

    @Test(priority=1)
    public void TC_002() throws InterruptedException {
        userControl.addLog("TC-002 Passed Case");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.demoTest002();

    }

}
