package ua.stqa.test.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ua.stqa.test.addressbook.model.ContactData;
import ua.stqa.test.addressbook.model.Contacts;

import java.util.Comparator;
import java.util.List;

public class ContactDeletionTests extends TestBase{

  @BeforeMethod
  public  void ensurePreconditions() {

    ContactData contact = new ContactData("Name", "aka", "LastName", "SDA", "MMM", "HOME", "test2");
    if ( app.contact().all().size() == 0) {
      app.contact().createContact(new ContactData("Name", "aka", "LastName", "SDA", "MMM", "HOME", "test2"));
    }

  }

  @Test (enabled =  false)
  public void testContactDeletion(){
    Contacts before = app.contact().all();

    app.contact().delete();
    Contacts after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() -1);

    before.remove(before.size() - 1);
    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    Assert.assertEquals(before, after);
  }

}
