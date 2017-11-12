package ru.stqa.msl.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.msl.addressbook.model.ContactData;
import ru.stqa.msl.addressbook.model.Contacts;
import ru.stqa.msl.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePrecondition(){
    app.goTo().homePage();
    if (app.db().contacts().size()==0){
      ContactData contact = new ContactData().withFirstName("Vu").withLastName("Vuru")
              .withHome("1111").withMobile("+7888").withWork("8999")
              .withEmail("test@mail.ru").withEmail2("te@dot.ru").withEmail3("aa@ddd.com")
              .withAddress("aaa");
      app.contact().creat(contact, true);
    }
  }

  @Test
  public void testContactDeletionDb() {
    Contacts before = app.db().contacts();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    assertThat(app.contact().count(),equalTo(before.size()-1));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(deletedContact)));
  }
}
