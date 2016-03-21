package ua.stqa.test.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ua.stqa.test.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

    @Test (enabled =  false)
    public void newContactCreation() {
        List <ContactData> before = app.getContactHelper().getContactList();
        ContactData contact = new ContactData("Name", "aka", "LastName", "SDA", "MMM", "HOME", "test2");
        app.getContactHelper().addNewButton();
        app.getContactHelper().fillinAllInfo(contact);
        app.getContactHelper().addNew();
        app.getNavigationHelper().openHomePage();
        List <ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() +1);

        before.add(contact);
        Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}