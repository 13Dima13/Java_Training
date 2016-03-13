package ua.stqa.test.addressbook.tests;

import org.testng.annotations.Test;
import ua.stqa.test.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase{
  @Test
  public void testContactDeletion(){
    if (app.getContactHelper().isThereAContact()) {
      app.getContactHelper().selectContactForDelete();
    } else {
      app.getContactHelper().addNewButton();
      app.getContactHelper().fillinAllInfo(new ContactData("Name", "aka", "LastName", "SDA", "MMM", "HOME", "test2"), true);
      app.getContactHelper().addNew();
      app.getNavigationHelper().openHomePage();
    }
    app.getContactHelper().selectContactForDelete();
    app.getContactHelper().deleteContact();
    app.getContactHelper().confirmRemove();
  }
}
