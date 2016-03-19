package ua.stqa.test.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ua.stqa.test.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase{
  @Test
  public void testContactDeletion(){
    int before = app.getContactHelper().getContactCount();
    if ( app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Name", "aka", "LastName", "SDA", "MMM", "HOME", "test2"));
    }
    app.getContactHelper().selectContactForDelete();
    app.getContactHelper().deleteContact();
    app.getContactHelper().confirmRemove();
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after, before -1);
  }
}
