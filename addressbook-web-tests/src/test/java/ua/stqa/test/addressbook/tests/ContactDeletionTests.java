package ua.stqa.test.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ua.stqa.test.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactDeletionTests extends TestBase{
  @Test (enabled =  false)
  public void testContactDeletion(){
    List<ContactData> before = app.getContactHelper().getContactList();
    ContactData contact = new ContactData("Name", "aka", "LastName", "SDA", "MMM", "HOME", "test2");
    if ( app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Name", "aka", "LastName", "SDA", "MMM", "HOME", "test2"));
    }
    app.getContactHelper().selectContactForDelete();
    app.getContactHelper().deleteContact();
    app.getContactHelper().confirmRemove();
    List <ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() -1);

    before.remove(before.size() - 1);
    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);






  }

}
