package ru.stqa.msl.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.msl.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase{

  @Test
  public void testGroupCreation() {
    app.getNavigationHelper().goToGroupPage();
    app.getGroupHelper().creatGroup(new GroupData("test1", "test1", "test1"));
  }

}
