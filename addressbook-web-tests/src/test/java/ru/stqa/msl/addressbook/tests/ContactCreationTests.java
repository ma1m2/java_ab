package ru.stqa.msl.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.msl.addressbook.model.ContactData;
import ru.stqa.msl.addressbook.model.Contacts;
import ru.stqa.msl.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase{

  @Test(enabled = true)
  public void testContactCreation() {
    app.goTo().homePage();
    Contacts before = app.contact().all();
    ContactData contact = new ContactData()
            .withFirstName("Pu").withLastName("Puru").withGroup("test1");
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

/*HW#9  @Test(enabled = true)
  public void testContactCreation() {
    app.goTo().homePage();
    List<ContactData> before = app.contact().list();
    ContactData contact = new ContactData()
            .withFirstName("Tu").withLastName("Turu").withGroup("test1");
    app.contact().creat(contact, true);
    List<ContactData> after = app.contact().list();
    before.add(contact);

    Comparator<? super ContactData> byId = (o1, o2) -> Integer.compare(o1.getId(), o2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before,after);
  }*/
}
