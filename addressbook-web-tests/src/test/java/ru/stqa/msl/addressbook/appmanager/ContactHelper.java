package ru.stqa.msl.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.msl.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstName());
    type(By.name("lastname"), contactData.getLastName());
    type(By.name("mobile"), contactData.getMobile());
    type(By.name("email"), contactData.getEmail());

    if (creation){
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    }else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public  void submitContactCreation() {
    click(By.name("submit"));
  }

  public void initContactCreation() {
    click(By.linkText("add new"));
  }

  public void selectContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
/*    if (!wd.findElement(By.name("selected[]")).isSelected()) {
      wd.findElement(By.name("selected[]")).click();
    }*/
  }

  public void deleteSelectedContact() {
    wd.findElement(By.xpath("//div[@id='content']/form[2]/div[2]/input")).click();
  }

  public void closeAlert() {
    wd.switchTo().alert().accept();
  }

  public void initContactModification(int index) {
    wd.findElements(By.name("entry")).get(index).findElement(By.xpath("./td[8]/a/img")).click();
    //click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
  }

  public void submitContactModification() {
    click(By.name("update"));
  }

  public boolean isThareAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public void returnToHomePage() {
    click(By.linkText("home page"));
  }

  public void creat(ContactData contactData, boolean creation) {
    initContactCreation();
    fillContactForm(contactData, creation);
    submitContactCreation();
    returnToHomePage();
  }

  public List<ContactData> list() {
    List<ContactData> contacts = new ArrayList<>();
    //List<WebElement> elements = wd.findElements(By.name("entry")); It works well!
    List<WebElement> elements = wd.findElements(By.cssSelector("tr[name=entry]"));//It's nice selector too.
    for (WebElement el : elements){
      int id = Integer.parseInt(el.findElement(By.tagName("input")).getAttribute("value"));
      String firstName = el.findElement(By.xpath("./td[3]")).getText();//How is it correct "//" or "/"?
      String lastName = el.findElement(By.xpath("./td[2]")).getText();
      ContactData contact = new ContactData(id,firstName,lastName, "test1");
      contacts.add(contact);
    }
    return contacts;
  }
}
