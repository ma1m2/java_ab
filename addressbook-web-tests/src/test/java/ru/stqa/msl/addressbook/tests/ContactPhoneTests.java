package ru.stqa.msl.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.msl.addressbook.model.ContactData;
import ru.stqa.msl.addressbook.model.GroupData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;

public class ContactPhoneTests extends TestBase{

  @BeforeMethod
  public void ensurePrecondition(){
    app.goTo().homePage();
    if (app.contact().count()==0){
      ContactData contact = new ContactData().withFirstName("Vu").withLastName("Vuru")
              .withHome("111").withMobile("222").withWork("333");
/*      if (app.group().isThereGroupName(contact.getGroup())){
        app.contact().creat(contact, true);
      }else {
        app.group().create(new GroupData().withName(contact.getGroup()));
        app.contact().creat(contact, true);
      }*/
    }
  }

  @Test(enabled = false)
  public void testContactPhones(){
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getAllPhone(), equalTo(mergePhones(contactInfoFromEditForm)));
  }

  private String mergePhones(ContactData contact) {
     return Arrays.asList(contact.getHome(),contact.getMobile(), contact.getWork())
            .stream().filter((s)-> !s.equals(""))
             .map(ContactPhoneTests::cleaned)
             .collect(Collectors.joining("\n"));
  }

  public static String cleaned(String phone){
    return phone.replaceAll("\\s","").replaceAll("[-()]","");
  }
}
