package ua.stqa.test.addressbook.tests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ua.stqa.test.addressbook.model.ContactData;
import ua.stqa.test.addressbook.model.Contacts;

import java.util.Comparator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.AssertJUnit.assertEquals;


public class ContactModificationTests extends TestBase {
  @BeforeMethod
  public  void ensurePreconditions() {
    if ( app.contact().all().size() == 0 ) {
      app.contact().createContact(new ContactData().withFirstName("Test").withLastName("Test").withGroup("test1"));
    }

  }

  @Test //(enabled =  false)
  public void testContactModification (){
    ContactData contact = new ContactData().withFirstName("Test").withLastName("Test").withGroup("test1");
    Contacts before = app.contact().all();
    ContactData modifyContact = before.iterator().next();
    app.contact().modify(contact);
    Contacts after = app.contact().all();
    assertEquals(after.size(), before.size());
    assertThat(after, equalTo(before.without(modifyContact).withAdded(contact)));
  }

  }



