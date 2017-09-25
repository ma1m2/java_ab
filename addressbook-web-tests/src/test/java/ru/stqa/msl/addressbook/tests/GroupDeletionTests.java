package ru.stqa.msl.addressbook.tests;

import org.testng.annotations.Test;

public class GroupDeletionTests extends TestBase{

    @Test
    public void testGroupDeletion() {
        app.goToGroupPage();
        app.selectGroups();
        app.deleteSelectedGroups();
        app.returnToGroupPage();
    }

}
