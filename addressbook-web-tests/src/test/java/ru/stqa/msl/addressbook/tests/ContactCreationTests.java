package ru.stqa.msl.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.msl.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase{

  @Test
  public void testContactCreation() {
    app.getContactHelper().initContactCreation();
    app.getContactHelper().fillContactForm(new ContactData("Cu", "Cucu", "+75557773883", "test555@mail.ru"));
    app.getContactHelper().submitContactCreation();
  }

}
