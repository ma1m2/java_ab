package ru.stqa.msl.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.msl.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupCreationTests extends TestBase{

  @Test
  public void testGroupCreation() {
    app.getNavigationHelper().goToGroupPage();
    List<GroupData> before = app.getGroupHelper().getGroupList();
    GroupData group = new GroupData("test5", "test1", "test1");
    app.getGroupHelper().creatGroup(group);
    List<GroupData> after = app.getGroupHelper().getGroupList();

    /*int max = 0;
    for (GroupData gD : after){
      if (gD.getId() > max) {
        max = gD.getId();
      }
    }*/

    /*Comparator<? super GroupData> byId = new Comparator<GroupData>() {
      @Override
      public int compare(GroupData o1, GroupData o2) {
        return Integer.compare(o1.getId(), o2.getId());
      }
    };
    int max1 = after.stream().max(byId).get().getId();*/

    /*Comparator<? super GroupData> byId = (Comparator<GroupData>)
            (o1, o2) -> Integer.compare(o1.getId(), o2.getId());
    int max1 = after.stream().max(byId).get().getId();*/

    /*group.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
    before.add(group);
    Assert.assertEquals(new HashSet<>(after), new HashSet<>(before));*/
    before.add(group);
    Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(),g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(after, before);
  }
}
