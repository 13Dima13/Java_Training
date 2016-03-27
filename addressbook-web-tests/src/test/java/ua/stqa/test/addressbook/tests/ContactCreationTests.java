package ua.stqa.test.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ua.stqa.test.addressbook.model.ContactData;
import ua.stqa.test.addressbook.model.Contacts;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

    @Test //(enabled =  false)
    public void newContactCreation() {
        Contacts before = app.contact().all();
        ContactData contact = new ContactData("Name", "aka", "LastName", "SDA", "MMM", "HOME", "test2");
        app.contact().createContact(contact);
        Contacts after = app.contact().all();
        Assert.assertEquals(after.size(), before.size() +1);

        before.add(contact);
        Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        Assert.assertEquals(before, after);
    }
}