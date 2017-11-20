package ru.stqa.msl.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.msl.addressbook.model.ContactData;
import ru.stqa.msl.addressbook.model.Contacts;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ContactDeleteFromGroupTests extends TestBase{

  @Test
  public void testDeleteContactFromGroup(){
    Contacts contacts = app.db().contacts();
    ContactData contact = contacts.iterator().next();
    ContactData correctCont = prepareContDbToCompare(contact);
    System.out.println(correctCont);
  }

  private ContactData prepareContDbToCompare(ContactData contact){
    String allEmail = mergeEmail(contact);
    String allPhone = mergePhones(contact);
    ContactData correctedCont = contact.withHome("").withMobile("").withWork("")
            .withAllPhone(allPhone)
            .withEmail("").withEmail2("").withEmail3("")
            .withAllEmail(allEmail);
    return correctedCont;
  }

  private String mergeEmail(ContactData contact) {
    String allEmail = Arrays.asList(contact.getEmail(),contact.getEmail2(), contact.getEmail3())
            .stream().filter((s)-> !s.equals(""))
            .collect(Collectors.joining("\n"));
    System.out.println(allEmail);
    return allEmail;
  }

  private String mergePhones(ContactData contact) {
    String allPhone = Arrays.asList(contact.getHome(),contact.getMobile(), contact.getWork())
            .stream().filter((s)-> !s.equals(""))
            .map(ContactAddToGroupTests::cleaned)
            .collect(Collectors.joining("\n"));
    System.out.println(allPhone);
    return allPhone;
  }
}
