package ru.stqa.msl.addressbook.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.stqa.msl.addressbook.appmanager.ApplicationManager;
import ru.stqa.msl.addressbook.model.ContactData;
import ru.stqa.msl.addressbook.model.Contacts;
import ru.stqa.msl.addressbook.model.GroupData;
import ru.stqa.msl.addressbook.model.Groups;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class TestBase {

  Logger logger= LoggerFactory.getLogger(TestBase.class);

  protected static final ApplicationManager app
          = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));
  //protected final ApplicationManager app = new ApplicationManager(BrowserType.IE);
  //protected final ApplicationManager app = new ApplicationManager(BrowserType.FIREFOX);

  @BeforeSuite
  public void setUp() throws Exception {
    app.init();
  }

  @AfterSuite(alwaysRun = true)
  public void tearDown() {
    app.stop();
  }

  @BeforeMethod
  public void logTestStart(Method m, Object[] p){
    logger.info("Start test " + m.getName() + "with parametrs " + Arrays.asList(p));
  }

  @AfterMethod(alwaysRun = true)
  public void logTestStop(Method m){
    logger.info("Stop test " + m.getName());
  }


  public void verifyGroupListInUi() {
    if (Boolean.getBoolean("verifyUi")){
      Groups dbGroups = app.db().groups();
      Groups uiGroups = app.group().all();
      assertThat(uiGroups, equalTo(dbGroups.stream().map(g -> new GroupData()
              .withId(g.getId()).withName(g.getName()))
              .collect(Collectors.toSet())));
    }
  }

  public void verifyContactListInUi() {
    if (Boolean.getBoolean("verifyUi")){
      Contacts dbContacts = app.db().contacts();
      Contacts uiContacts = app.contact().all();
      assertThat(uiContacts, equalTo(dbContacts.stream().map(c -> new ContactData().withId(c.getId())
              .withFirstName(c.getFirstName()).withLastName(c.getLastName())
              .withHome(c.getHome()).withMobile(c.getMobile()).withWork(c.getWork())
              .withEmail(c.getEmail()).withEmail2(c.getEmail2()).withEmail3(c.getEmail3())
              .withAddress(c.getAddress())).collect(Collectors.toSet())));
    }
  }
}
