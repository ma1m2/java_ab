package ru.stqa.msl.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.msl.addressbook.model.ContactData;
import ru.stqa.msl.addressbook.model.Contacts;
import ru.stqa.msl.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase{

  @BeforeMethod
  public void ensurePrecondition(){
    app.goTo().homePage();
    if (app.db().contacts().size() == 0){
      ContactData contact = new ContactData().withFirstName("Vu").withLastName("Vuru")
              .withHome("1111").withMobile("+7888").withHome("8999")
              .withEmail("test@mail.ru").withEmail("te@dot.ru").withEmail3("aa@ddd.com")
              .withAddress("aaa").withGroup("test 1");
      app.contact().creat(contact, true);
    }
/*    if (app.contact().count()==0){
      ContactData contact = new ContactData().withFirstName("Vu").withLastName("Vuru").withGroup("test1");
      if (app.group().isThereGroupName(contact.getGroup())){
        app.contact().creat(contact, true);
      }else {
        app.group().create(new GroupData().withName(contact.getGroup()));
        app.contact().creat(contact, true);
      }
    }*/
  }

  @Test
  public void testContactModificationDb(){
    Contacts before = app.db().contacts();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData().withId(modifiedContact.getId())
            .withFirstName("Tu").withLastName("Tuqu").withHome("1111").withMobile("+7888").withWork("8999")
            .withEmail("test@mail.ru").withEmail2("te@dot.ru").withEmail3("aa@ddd.com")
            .withAddress("aaa").withGroup("test 1");
    app.contact().modify(contact);
    assertThat(app.contact().count(),equalTo(before.size()));
    Contacts after = app.db().contacts();
    System.out.println(before);
    System.out.println(after);
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
  }
}
