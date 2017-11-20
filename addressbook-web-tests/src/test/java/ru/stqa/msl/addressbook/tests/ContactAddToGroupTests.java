package ru.stqa.msl.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.msl.addressbook.model.ContactData;
import ru.stqa.msl.addressbook.model.Contacts;
import ru.stqa.msl.addressbook.model.GroupData;
import ru.stqa.msl.addressbook.model.Groups;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddToGroupTests extends TestBase{

  private GroupData group = new GroupData().withName("test8").withHeader("test8").withFooter("test8");
  private ContactData contact = new ContactData().withFirstName("Vu").withLastName("Vuru")
          .withHome("1111").withMobile("+7888").withWork("8999")
          .withEmail("test@mail.ru").withEmail2("te@dot.ru").withEmail3("aa@ddd.com")
          .withAddress("aaa");

  @BeforeMethod
  public void ensurePrecondition(){
    if (app.db().groups().size()==0){
      app.goTo().groupPage();
      app.group().create(group);
    }
    app.goTo().homePage();
    app.contact().creat(contact, true);
  }

  @Test
  public void testContactToGroup(){
    Contacts uiContacts = app.contact().all();
    int id = uiContacts.stream().mapToInt((c)-> c.getId()).max().getAsInt();
    ContactData chosenContact = uiContacts.stream().filter((c)-> (c.getId()==id)).findFirst().get();
    GroupData chosenGroup = app.group().all().iterator().next();

    app.contact().addInGroup(chosenGroup,chosenContact);

    Contacts dbContacts = app.db().contacts();
    ContactData comparedDbContact = prepareContDbToCompare(dbContacts
            .stream().filter((c)-> (c.getId()==id)).findFirst().get());
    ContactData comparedUiContact = chosenContact.inGroup(chosenGroup);

    assertThat(comparedDbContact, equalTo(comparedUiContact));
  }

  private ContactData prepareContDbToCompare(ContactData contact){
    String allEmail = mergeEmail(contact);
    String allPhone = mergePhones(contact);
    return contact.withHome("").withMobile("").withWork("")
            .withAllPhone(allPhone)
            .withEmail("").withEmail2("").withEmail3("")
            .withAllEmail(allEmail)
            .inGroup(contact.getGroups().iterator().next().withHeader("").withFooter(""));
  }

  private String mergeEmail(ContactData contact) {
    return Arrays.asList(contact.getEmail(),contact.getEmail2(), contact.getEmail3())
            .stream().filter((s)-> !s.equals(""))
            .collect(Collectors.joining("\n"));
  }

  private String mergePhones(ContactData contact) {
    return Arrays.asList(contact.getHome(),contact.getMobile(), contact.getWork())
            .stream().filter((s)-> !s.equals(""))
            .map(ContactAddToGroupTests::cleaned)
            .collect(Collectors.joining("\n"));
  }

  public static String cleaned(String phone){
    return phone.replaceAll("\\s","").replaceAll("[-()]","");
  }
}
