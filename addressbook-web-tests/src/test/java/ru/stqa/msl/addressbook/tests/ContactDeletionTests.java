package ru.stqa.msl.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import ru.stqa.msl.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase {

  @Test(enabled = false)
  public void testContactDeletion() {
    app.goTo().homePage();
    if (!app.contact().isThareAContact()){
      app.contact().creat(new ContactData(1,"Ru", "Ruru",
              "+73339993118", "test113@mail.ru", "test1"), true);
    }
    List<ContactData> before = app.contact().list();
    app.contact().selectContact(before.size() - 1);
    app.contact().deleteSelectedContact();
    app.contact().closeAlert();
    app.goTo().homePage();
    List<ContactData> after = app.contact().list();
    before.remove(before.size() - 1);
    Assert.assertEquals(before,after);
  }

}
