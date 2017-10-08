package ru.stqa.msl.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.msl.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;

public class GroupModificationTests extends TestBase {

  @Test
  public void testGroupModification(){
    app.getNavigationHelper().goToGroupPage();
    if (!app.getGroupHelper().isThereAGroup()){
      app.getGroupHelper().creatGroup(new GroupData("test6", null, null));
    }
    List<GroupData> before = app.getGroupHelper().getGroupList();
    app.getGroupHelper().selectGroups(before.size()-1);
    app.getGroupHelper().initGroupModification();
    GroupData group = new GroupData(before.get(before.size()-1).getId(),"test2", "test2", "test2");
    app.getGroupHelper().fillGroupForm(group);
    app.getGroupHelper().submitGroupModification();
    app.getGroupHelper().returnToGroupPage();
    List<GroupData> after = app.getGroupHelper().getGroupList();

    before.remove(before.size()-1);
    before.add(group);
    Assert.assertEquals(new HashSet<>(after), new HashSet<>(before));
  }
}
