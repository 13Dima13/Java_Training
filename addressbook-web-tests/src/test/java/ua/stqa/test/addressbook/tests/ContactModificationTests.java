package ua.stqa.test.addressbook.tests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ua.stqa.test.addressbook.model.ContactData;
import ua.stqa.test.addressbook.model.Contacts;

import java.util.Comparator;
import java.util.List;


public class ContactModificationTests extends TestBase {
  @BeforeMethod
  public  void ensurePreconditions() {
    if ( app.contact().all().size() == 0 ) {
      app.contact().createContact(new ContactData("Name", "aka", "LastName", "SDA", "MMM", "HOME", "test2"));
    }

  }

  @Test (enabled =  false)
  public void testContactModification (){
    ContactData contact = new ContactData("Name", "aka", "LastName", "SDA", "MMM", "HOME", "test2");
    Contacts before = app.contact().all();
    app.contact().modify(contact);
    Contacts after = app.contact().all();
    Assert.assertEquals(after, before);

    before.remove(before.size() -1 );
    before.add(contact);
    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    Assert.assertEquals(before, after);



  }


}


