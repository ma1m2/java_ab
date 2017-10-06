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
    app.getGroupHelper().creatGroup(new GroupData("test2", "test1", "test1"));
    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size() + 1);
  }
}
