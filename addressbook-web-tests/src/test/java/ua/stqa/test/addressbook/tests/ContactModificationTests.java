package ua.stqa.test.addressbook.tests;
import org.testng.annotations.Test;
import ua.stqa.test.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification (){
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().selectContact();
    } else {
      app.getContactHelper().addNewButton();
      app.getContactHelper().fillinAllInfo(new ContactData("Name", "aka", "LastName", "SDA", "MMM", "HOME", "test2"), true);
      app.getContactHelper().addNew();
      app.getNavigationHelper().openHomePage();
    }
    app.getContactHelper().modifyContact();
    app.getContactHelper().fillinAllInfo(new ContactData("Name", "aka", "LastName", "SDA", "MMM", "HOME", null), false);
    app.getContactHelper().updateContact();
    app.getNavigationHelper().openHomePage();
  }

}


