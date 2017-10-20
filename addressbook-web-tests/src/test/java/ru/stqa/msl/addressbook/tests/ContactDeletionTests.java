package ru.stqa.msl.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import ru.stqa.msl.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase {

  @Test(enabled = false)
  public void testContactDeletion() {
    app.goTo().goToHomePage();
    if (!app.getContactHelper().isThareAContact()){
      app.getContactHelper().creatContact(new ContactData(1,"Ru", "Ruru",
              "+73339993118", "test113@mail.ru", "test1"), true);
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(before.size() - 1);
    app.getContactHelper().deleteSelectedContact();
    app.getContactHelper().closeAlert();
    app.goTo().goToHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    before.remove(before.size() - 1);
    Assert.assertEquals(before,after);
  }

}
