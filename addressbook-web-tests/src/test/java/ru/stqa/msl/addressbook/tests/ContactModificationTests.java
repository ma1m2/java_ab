package ru.stqa.msl.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.msl.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase{

  @Test
  public void testContactModification(){
    app.getNavigationHelper().goToHomePage();
    if (!app.getContactHelper().isThareAContact()){
      app.getContactHelper().creatContact(new ContactData("Ru", "Ruru",
              "+73339993118", "test113@mail.ru", "test1"), true);
    }
    app.getContactHelper().selectContact();
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData
            ("Ig", "Cher", "+9998887744", "test@gmail.com", null), false);
    app.getContactHelper().submitContactModification();
    app.getContactHelper().returnToHomePage();
  }
}
