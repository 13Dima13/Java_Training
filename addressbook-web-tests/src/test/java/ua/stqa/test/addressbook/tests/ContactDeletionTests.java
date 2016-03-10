package ua.stqa.test.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase{
  @Test
  public void testContactDeletion(){
    app.getContactHelper().selectContactForDelete();
    app.getContactHelper().deleteContact();
    app.getContactHelper().confirmRemove();
  }
}
