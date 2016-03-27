package ua.stqa.test.addressbook.tests;

import org.testng.annotations.Test;
import ua.stqa.test.addressbook.model.ContactData;

public class ContactPhoneTest extends TestBase {
  @Test
  public void testContactPhones (){
    app.goTo().openHomePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromForm = app.contact().infoFromEditForm(contact);
  }

}
