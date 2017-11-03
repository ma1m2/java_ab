package ru.stqa.msl.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.msl.addressbook.model.ContactData;
import ru.stqa.msl.addressbook.model.Contacts;
import ru.stqa.msl.addressbook.model.GroupData;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase{

  @Test(enabled = true)
  public void testContactCreation() {
    app.goTo().homePage();
    Contacts before = app.contact().all();
    File photo = new File("src/test/resources/yin_yang.jpg");
    ContactData contact = new ContactData()
            .withFirstName("Pu").withLastName("Puru").withGroup("test1").withPhoto(photo);
    if (app.group().isThereGroupName(contact.getGroup())){
      app.contact().creat(contact, true);
    }else {
      app.group().create(new GroupData().withName(contact.getGroup()));
      app.contact().creat(contact, true);
    }
    assertThat(app.contact().count(),equalTo(before.size()+1));
    Contacts after = app.contact().all();

    assertThat(after, equalTo(before
            .withAdded(contact.withId(after.stream().mapToInt((c)->c.getId()).max().getAsInt()))));
  }
}
