package ru.stqa.msl.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import ru.stqa.msl.addressbook.model.ContactData;
import ru.stqa.msl.addressbook.model.GroupData;

public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePrecondition(){
    app.goTo().homePage();
    if (app.contact().list().size()==0){
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
  public void testContactDeletion() {
    List<ContactData> before = app.contact().list();
    int index = before.size() - 1;
    app.contact().delete(index);
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(before.size(),after.size()+1);
    before.remove(index);
    Assert.assertEquals(before,after);
  }

}
