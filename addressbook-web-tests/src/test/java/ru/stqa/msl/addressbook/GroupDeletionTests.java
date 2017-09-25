package ru.stqa.msl.addressbook;

import org.testng.annotations.Test;

public class GroupDeletionTests extends TestBase{

    @Test
    public void testGroupDeletion() {
        goToGroupPage();
        selectGroups();
        deleteSelectedGroups();
        returnToGroupPage();
    }

}
