package ru.stqa.msl.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.msl.addressbook.model.ContactData;
import ru.stqa.msl.addressbook.model.Contacts;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstName());
    type(By.name("lastname"), contactData.getLastName());
    type(By.name("home"), contactData.getHome());
    type(By.name("mobile"), contactData.getMobile());
    type(By.name("work"), contactData.getWork());
    type(By.name("email"), contactData.getEmail());
    type(By.name("email2"), contactData.getEmail2());
    type(By.name("email3"), contactData.getEmail3());
    type(By.name("address"), contactData.getAddress());
    attach(By.name("photo"), contactData.getPhoto());

    if (creation) {
      if (contactData.getGroups().size() > 0) {
        Assert.assertTrue(contactData.getGroups().size() == 1);
        new Select(wd.findElement(By.name("new_group")))
                .selectByVisibleText(contactData.getGroups().iterator().next().getName());
      } else {
        Assert.assertFalse(isElementPresent(By.name("new_group")));
      }
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

  public void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
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

  private void initContactModificationById(int id) {
    //wd.findElement(By.xpath(String.format("//tr[.//input[@value='%s']]/td[8]/a",id))).click();
    /*WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
    WebElement row = checkbox.findElement(By.xpath("./../.."));
    List<WebElement> cells = row.findElements(By.tagName("td"));
    cells.get(7).findElement(By.tagName("a")).click();*/
    //wd.findElement(By.xpath(String.format("//input[@value='%s']/../../td[8]/a",id))).click();
    wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
  }

  public void submitContactModification() {
    click(By.name("update"));
  }

  public boolean isThareAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int count(){
    return wd.findElements(By.name("selected[]")).size();
  }

  public void returnToHomePage() {
    click(By.linkText("home page"));
  }

  public void homePage() {
    if (isElementPresent(By.id("maintable"))){
      return;
    }
    click(By.linkText("home"));
  }

  public void creat(ContactData contactData, boolean creation) {
    initContactCreation();
    fillContactForm(contactData, creation);
    submitContactCreation();
    contactCache = null;
    returnToHomePage();
  }

  public void modify(int index, ContactData contact) {
    selectContact(index);
    initContactModification(index);
    fillContactForm(contact, false);
    submitContactModification();
    contactCache = null;
    returnToHomePage();
  }

  public void modify(ContactData contact) {
    selectContactById(contact.getId());
    initContactModificationById(contact.getId());
    fillContactForm(contact, false);
    submitContactModification();
    contactCache = null;
    returnToHomePage();
  }


  public void delete(int index) {
    selectContact(index);
    deleteSelectedContact();
    contactCache = null;
    closeAlert();
    homePage();
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteSelectedContact();
    contactCache = null;
    closeAlert();
    homePage();
  }

  private Contacts contactCache = null;

  public Contacts all() {
    if (contactCache != null){
      return new Contacts(contactCache);//берем копию кэша, если он уже есть
    }
    contactCache = new Contacts();
    List<WebElement> rows = wd.findElements(By.cssSelector("tr[name=entry]"));
    for (WebElement row : rows){
      List<WebElement> cells = row.findElements(By.tagName("td"));
      int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
      String firstName = cells.get(2).getText();
      String lastName = cells.get(1).getText();
      String allPhones = cells.get(5).getText();
      String allEmail = cells.get(4).getText();
      String address = cells.get(3).getText();
      //String[] phones = allPhones.split("\n");
      ContactData contact = new ContactData()
              .withId(id).withFirstName(firstName).withLastName(lastName)
              .withAllPhone(allPhones).withAllEmail(allEmail).withAddress(address);
              //.withHome(phones[0]).withMobile(phones[1]).withWork(phones[2]);
      contactCache.add(contact);
    }
    return new Contacts(contactCache);
  }

  public List<ContactData> list() {
    List<ContactData> contacts = new ArrayList<>();
    //List<WebElement> elements = wd.findElements(By.name("entry")); It works well!
    List<WebElement> elements = wd.findElements(By.cssSelector("tr[name=entry]"));//It's nice selector too.
    for (WebElement el : elements){
      int id = Integer.parseInt(el.findElement(By.tagName("input")).getAttribute("value"));
      String firstName = el.findElement(By.xpath("./td[3]")).getText();//How is it correct "//" or "/"?
      String lastName = el.findElement(By.xpath("./td[2]")).getText();
      ContactData contact = new ContactData()
              .withId(id).withFirstName(firstName).withLastName(lastName);
      contacts.add(contact);
    }
    return contacts;
  }

  public ContactData infoFromEditForm(ContactData contact) {
    initContactModificationById(contact.getId());
    String firstName = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastName = wd.findElement(By.name("lastname")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getText();
    wd.navigate().back();
    return new ContactData().withFirstName(firstName).withLastName(lastName)
            .withHome(home).withMobile(mobile).withWork(work)
            .withEmail(email).withEmail2(email2).withEmail3(email3).withAddress(address);
  }
}
