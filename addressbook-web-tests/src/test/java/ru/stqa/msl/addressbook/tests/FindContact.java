package ru.stqa.msl.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.msl.addressbook.model.ContactData;
import ru.stqa.msl.addressbook.model.Contacts;
import ru.stqa.msl.addressbook.model.GroupData;
import ru.stqa.msl.addressbook.model.Groups;

public class FindContact extends TestBase{

  @Test
  public void testFindContact(){
    ContactData chosenContact = null;
    GroupData chosenGroup = null;
    Contacts contacts = app.db().contacts();
    Groups groups = app.db().groups();
    for (ContactData contact : contacts){
      System.out.println("1st loop: " + contact);
      if (contact.getGroups().size() < groups.size() && contact.getGroups().size() > 0){
        for (GroupData groupAtCont : contact.getGroups()){
          System.out.println("2nd loop: " + groupAtCont);
          for (GroupData group : groups){
            System.out.println("3rd loop: " + group);
            if (group.getId() != groupAtCont.getId()){
              chosenContact = contact;
              chosenGroup = group;
              System.out.println("chosenContact: " + chosenContact);
              System.out.println("chosenGroup: " + chosenGroup);
              return;
            }
          }
        }
      }
    }
  }
}
