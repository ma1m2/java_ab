package ru.stqa.msl.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.msl.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase{

  @Test
  public void testContactCreation() {
    app.getContactHelper().initContactCreation();
    app.getContactHelper().fillContactForm(new ContactData("Ivan3", "Kuzin", "+79057773883", "test5@mail.ru"));
    app.getContactHelper().submitContactCreation();
  }

}
