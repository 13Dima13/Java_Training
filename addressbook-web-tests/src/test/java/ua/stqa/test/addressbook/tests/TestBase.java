package ua.stqa.test.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ua.stqa.test.addressbook.appmanager.ApplicationManager;

public class TestBase {

   public static ApplicationManager app;


    @BeforeSuite
    public void setUp() throws Exception {
        app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));
        app.init();
    }

    @AfterSuite
    public void tearDown() {
        app.stop();
    }

}
