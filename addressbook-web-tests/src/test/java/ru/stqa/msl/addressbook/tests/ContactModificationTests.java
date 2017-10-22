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
    if (app.contact().all().size()==0){
      ContactData contact = new ContactData().withFirstName("Vu").withLastName("Vuru").withGroup("test1");
      if (app.group().isThereGroupName(contact.getGroup())){
        app.contact().creat(contact, true);
      }else {
        app.group().create(new GroupData().withName(contact.getGroup()));
        app.contact().creat(contact, true);
      }
    }
  }

  @Test(enabled = true)
  public void testContactModification(){
    Contacts before = app.contact().all();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData().withId(modifiedContact.getId())
            .withFirstName("Wy").withLastName("Wyyy");
    app.contact().modify(contact);
    Contacts after = app.contact().all();

    Assert.assertEquals(before.size(),after.size());
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
  }

  /*HW#9 @Test(enabled = true)
  public void testContactModification(){
    List<ContactData> before = app.contact().list();
    int index = before.size()-1;
    ContactData contact = new ContactData().withId(before.get(index).getId())
            .withFirstName("Wy").withLastName("Wyyy");
    app.contact().modify(index, contact);

    List<ContactData> after = app.contact().list();
    before.remove(index);
    before.add(contact);
    Comparator<ContactData> byId = (o1, o2) -> Integer.compare(o1.getId(),o2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before,after);
  }*/
}
