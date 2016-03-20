package ua.stqa.test.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ua.stqa.test.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase{
  @Test
  public void testContactDeletion(){
    List<ContactData> before = app.getContactHelper().getContactList();
    if ( app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Name", "aka", "LastName", "SDA", "MMM", "HOME", "test2"));
    }
    app.getContactHelper().selectContactForDelete();
    app.getContactHelper().deleteContact();
    app.getContactHelper().confirmRemove();
    List <ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() -1);
  }
}
