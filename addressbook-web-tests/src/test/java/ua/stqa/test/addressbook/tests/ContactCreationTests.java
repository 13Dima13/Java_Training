package ua.stqa.test.addressbook.tests;

import org.testng.annotations.Test;
import ua.stqa.test.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

    @Test
    public void newContactCreation() {
        app.getContactHelper().addNewButton();
        app.getContactHelper().fillinAllInfo(new ContactData("Name", "aka", "LastName", "SDA", "MMM", "HOME", "test2"), true);
        app.getContactHelper().addNew();
        app.getNavigationHelper().openHomePage();
    }
}