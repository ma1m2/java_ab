package ru.stqa.msl.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import ru.stqa.msl.addressbook.appmanager.ApplicationManager;


public class TestBase {

  //protected final ApplicationManager app = new ApplicationManager(BrowserType.CHROME);
  //protected final ApplicationManager app = new ApplicationManager(BrowserType.IE);
  protected final ApplicationManager app = new ApplicationManager(BrowserType.FIREFOX);

  @BeforeMethod
  public void setUp() throws Exception {
    app.init();
  }

  @AfterMethod
  public void tearDown() {
    app.stop();
  }

}
