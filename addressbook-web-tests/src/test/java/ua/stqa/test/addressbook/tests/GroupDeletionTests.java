package ua.stqa.test.addressbook.tests;

import org.testng.annotations.Test;
import ua.stqa.test.addressbook.model.GroupData;

public class GroupDeletionTests extends TestBase {

    
    @Test
    public void testGroupDeletion() {
        app.getNavigationHelper().gotoGroupPage();
        if (app.getGroupHelper().isThereAGroup()) {
            app.getGroupHelper().selectGroup();
        } else {
            app.getGroupHelper().initGroupCreation();
            app.getGroupHelper().fillGroupForm(new GroupData("test2", "test3", "test4"));
            app.getGroupHelper().submitGroupCreation(new GroupData("test1", null, null));
            app.getGroupHelper().returnToGroupPage();
        }
        app.getGroupHelper().deleteSelectedGroups();
        app.getGroupHelper().returnToGroupPage();
    }

}
