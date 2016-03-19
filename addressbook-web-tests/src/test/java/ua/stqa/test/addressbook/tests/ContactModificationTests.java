package ua.stqa.test.addressbook.tests;
import org.testng.Assert;
import org.testng.annotations.Test;
import ua.stqa.test.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification (){
    int before = app.getContactHelper().getContactCount();
    if ( app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Name", "aka", "LastName", "SDA", "MMM", "HOME", "test2"));
    }
    app.getContactHelper().modifyContact();
    app.getContactHelper().fillinAllInfo(new ContactData("Name", "aka", "LastName", "SDA", "MMM", "HOME", null));
    app.getContactHelper().updateContact();
    app.getNavigationHelper().openHomePage();
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after, before);
  }

}


