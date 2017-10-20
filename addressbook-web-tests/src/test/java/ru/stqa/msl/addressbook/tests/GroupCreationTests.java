package ru.stqa.msl.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.msl.addressbook.model.GroupData;
import ru.stqa.msl.addressbook.model.Groups;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase{

  @Test
  public void testGroupCreation() {
    app.goTo().groupPage();
    Groups before = app.group().all();
    GroupData group = new GroupData().withName("test5");
    app.group().create(group);
    Groups after = app.group().all();
    assertThat(after.size(), equalTo(before.size() + 1));

    //Assert.assertEquals(after, before);
    assertThat(after, equalTo(before.withAdded(group
            .withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt()))));
  }
}
