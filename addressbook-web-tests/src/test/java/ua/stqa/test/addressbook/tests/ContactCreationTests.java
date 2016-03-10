package ua.stqa.test.addressbook.tests;

import org.testng.annotations.Test;
import ua.stqa.test.addressbook.model.InfoContacts;

public class ContactCreationTests extends TestBase {

    @Test
    public void newContactCreation() {
        app.getContactHelper().addNewButton();
        app.getContactHelper().fillinAllInfo(new InfoContacts("Name", "aka", "LastName", "SDA", "MMM", "HOME"));
        app.getContactHelper().addNew();
        app.getContactHelper().openHomePage();
    }
}