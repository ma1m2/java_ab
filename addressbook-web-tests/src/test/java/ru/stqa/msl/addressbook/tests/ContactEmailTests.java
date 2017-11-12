package ru.stqa.msl.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.msl.addressbook.model.ContactData;
import ru.stqa.msl.addressbook.model.GroupData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactEmailTests extends TestBase{

  @BeforeMethod
  public void ensurePrecondition(){
    app.goTo().homePage();
    if (app.contact().count()==0){
      ContactData contact = new ContactData().withFirstName("Vu").withLastName("Vuru")
              .withEmail("ttt@mail.ru").withEmail2("aaa@gmail.com").withEmail3("rrr@mail.ru");
/*      if (app.group().isThereGroupName(contact.getGroup())){
        app.contact().creat(contact, true);
      }else {
        app.group().create(new GroupData().withName(contact.getGroup()));
        app.contact().creat(contact, true);
      }*/
    }
  }

  @Test(enabled = false)
  public void testContactEmail(){
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getAllEmail(), equalTo(mergeEmails(contactInfoFromEditForm)));
  }

  private String mergeEmails(ContactData contact) {
    return Arrays.asList(contact.getEmail(),contact.getEmail2(), contact.getEmail3())
            .stream().filter((s)-> !s.equals(""))
            .map(ContactEmailTests::cleaned)
            .collect(Collectors.joining("\n"));
  }

  public static String cleaned(String email){
    return email.replaceAll("\\s","");
  }
}
