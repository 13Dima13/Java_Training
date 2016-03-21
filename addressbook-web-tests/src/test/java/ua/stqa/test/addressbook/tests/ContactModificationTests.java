package ua.stqa.test.addressbook.tests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ua.stqa.test.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;


public class ContactModificationTests extends TestBase {
  @BeforeMethod
  public  void ensurePreconditions() {
    if ( app.contact().isThereAContact()) {
      app.contact().createContact(new ContactData("Name", "aka", "LastName", "SDA", "MMM", "HOME", "test2"));
    }

  }

  @Test (enabled =  false)
  public void testContactModification (){
    ContactData contact = new ContactData("Name", "aka", "LastName", "SDA", "MMM", "HOME", "test2");
    List<ContactData> before = app.contact().list();
    app.contact().modifyContact(contact);
    List <ContactData> after = app.contact().list();
    Assert.assertEquals(after, before);

    before.remove(before.size() -1 );
    before.add(contact);
    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);



  }


}


