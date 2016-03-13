package ua.stqa.test.addressbook.tests;
import org.testng.annotations.Test;
import ua.stqa.test.addressbook.model.InfoContacts;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification (){
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().selectContact();
    }
    app.getContactHelper().modifyContact();
    app.getContactHelper().fillinAllInfo(new InfoContacts("Name", "aka", "LastName", "SDA", "MMM", "HOME", null), false);
    app.getContactHelper().updateContact();
    app.getNavigationHelper().openHomePage();
  }

}


