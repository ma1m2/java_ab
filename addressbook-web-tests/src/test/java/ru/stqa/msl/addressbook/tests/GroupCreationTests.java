package ru.stqa.msl.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.msl.addressbook.model.GroupData;

import java.util.List;

public class GroupCreationTests extends TestBase{

  @Test
  public void testGroupCreation() {
    app.getNavigationHelper().goToGroupPage();
    List<GroupData> before = app.getGroupHelper().getGroupList();
    GroupData group = new GroupData("test3", "test1", "test1");
    app.getGroupHelper().creatGroup(group);
    List<GroupData> after = app.getGroupHelper().getGroupList();

    int max = 0;
    for (GroupData gD : after){
      if (gD.getId() > max) {
        max = gD.getId();
      }
    }
    group.setId(max);
    before.add(group);

    Assert.assertEquals(after, before);
  }
}
