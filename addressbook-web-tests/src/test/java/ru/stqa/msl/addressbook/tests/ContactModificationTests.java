package ru.stqa.msl.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.msl.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase{

  @Test
  public void testContactModification(){
    app.getNavigationHelper().goToHomePage();
    if (!app.getContactHelper().isThareAContact()){
      app.getContactHelper().creatContact(new ContactData(1,"Vu", "Vuru",
              "+72229993118", "test222@mail.ru", "test1"), true);
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(before.size()-1);
    app.getContactHelper().initContactModification(before.size()-1);
    ContactData contact = new ContactData(before.get(before.size()-1).getId()
            ,"Yy", "Yyyy",  null);
    app.getContactHelper().fillContactForm(contact, false);
    app.getContactHelper().submitContactModification();
    app.getContactHelper().returnToHomePage();

    List<ContactData> after = app.getContactHelper().getContactList();
    before.remove(before.size()-1);
    before.add(contact);
    Comparator<ContactData> byId = (o1, o2) -> Integer.compare(o1.getId(),o2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before,after);
  }
}
