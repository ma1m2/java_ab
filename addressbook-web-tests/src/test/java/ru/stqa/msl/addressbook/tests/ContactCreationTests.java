package ru.stqa.msl.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.msl.addressbook.model.ContactData;
import ru.stqa.msl.addressbook.model.Contacts;
import ru.stqa.msl.addressbook.model.GroupData;
import ru.stqa.msl.addressbook.model.Groups;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase{

  @DataProvider
  public Iterator<Object[]> validContactsFromJson() throws IOException {
    File file = new File("src/test/resources/contacts.json");
    BufferedReader reader = new BufferedReader(new FileReader(file));
    String json = "";
    String line = reader.readLine();
    while (line != null){
      json += line;
      line = reader.readLine();
    }
    Gson gson = new Gson();
    List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>(){}.getType());
    return contacts.stream().map(g -> new Object[]{g}).collect(Collectors.toList()).iterator();
  }

  @Test(dataProvider = "validContactsFromJson")
  public void testContactCreationDb(ContactData contact) {
    Groups groups = app.db().groups();
    app.goTo().homePage();
    Contacts before = app.db().contacts();
    if (groups.size() == 0){
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test8").withHeader("test8").withFooter("test8"));
      groups = app.db().groups();
    }
    ContactData newContact = contact.inGroup(groups.iterator().next());
    app.contact().creat(newContact, true);
    assertThat(app.contact().count(),equalTo(before.size()+1));
    Contacts after = app.db().contacts();

    assertThat(after, equalTo(before
            .withAdded(contact.withId(after.stream().mapToInt((c)->c.getId()).max().getAsInt()))));
  }
}
