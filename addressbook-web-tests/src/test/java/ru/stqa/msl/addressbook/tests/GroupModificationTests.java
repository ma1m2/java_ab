package ru.stqa.msl.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.msl.addressbook.model.GroupData;
import java.util.Set;

public class GroupModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePrecondition(){//обеспечить предварительное условие
    app.goTo().groupPage();
    if (app.group().all().size()==0){
      app.group().create(new GroupData().withName("test1"));
    }
  }

  @Test
  public void testGroupModification(){
    Set<GroupData> before = app.group().all();
    GroupData modifiedGroup = before.iterator().next();
    GroupData group = new GroupData().withId(modifiedGroup.getId())
            .withName("test8").withHeader("test2").withFooter("test2");

    app.group().modify(group);

    Set<GroupData> after = app.group().all();
    before.remove(modifiedGroup);
    before.add(group);
    Assert.assertEquals(before,after);
  }
}
