package ua.stqa.test.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ua.stqa.test.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

    @Test
    public void newContactCreation() {
        int before = app.getContactHelper().getContactCount();
        app.getContactHelper().addNewButton();
        app.getContactHelper().fillinAllInfo(new ContactData("Name", "aka", "LastName", "SDA", "MMM", "HOME", "test2"));
        app.getContactHelper().addNew();
        app.getNavigationHelper().openHomePage();
        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after, before +1);
    }
}