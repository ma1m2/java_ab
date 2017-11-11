package ru.stqa.msl.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.msl.addressbook.model.GroupData;
import ru.stqa.msl.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class GroupModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePrecondition(){//обеспечить предварительное условие
    app.goTo().groupPage();
    if (app.db().groups().size()==0){
      app.group().create(new GroupData().withName("test1"));
    }
  }

  @Test
  public void testGroupModificationDb(){
    Groups before = app.db().groups();
    GroupData modifiedGroup = before.iterator().next();
    GroupData group = new GroupData().withId(modifiedGroup.getId())
            .withName("test10").withHeader("test7").withFooter("test2");
    app.group().modify(group);
    assertEquals(app.group().count(),before.size());
    Groups after = app.db().groups();
    assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));
  }

  @Test(enabled = false)
  public void testGroupModification(){
    Groups before = app.group().all();
    GroupData modifiedGroup = before.iterator().next();
    GroupData group = new GroupData().withId(modifiedGroup.getId())
            .withName("test9").withHeader("test2").withFooter("test2");
    app.group().modify(group);
    assertEquals(app.group().count(),before.size());
    Groups after = app.group().all();
    assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));
  }
}
