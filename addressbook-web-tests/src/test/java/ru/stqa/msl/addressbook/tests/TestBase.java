package ru.stqa.msl.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.stqa.msl.addressbook.appmanager.ApplicationManager;


public class TestBase {

  protected static final ApplicationManager app = new ApplicationManager(BrowserType.CHROME);
  //protected final ApplicationManager app = new ApplicationManager(BrowserType.IE);
  //protected final ApplicationManager app = new ApplicationManager(BrowserType.FIREFOX);

  @BeforeSuite
  public void setUp() throws Exception {
    app.init();
  }

  @AfterSuite
  public void tearDown() {
    app.stop();
  }

}
