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
    app.goTo().openHomePage();
    if (app.db().contacts().size() == 0) {
      app.goTo().AddNewContactPage();
      app.contact().create(new ContactData().withFirstName("FirstName").withLastName("Lastname").withAddress("Street")
              .withGroup("test1").withHomePhone("111").withMobilePhone("222").withWorkPhone("333")
              .withEmail("test@test.tt").withAddress("Street"));
      app.goTo().openHomePage();
    }
  }

  @Test
  public void testContactModification() {
    Contacts before = app.db().contacts();
    ContactData modifyContact = before.iterator().next();
    ContactData contact = new ContactData().withId(modifyContact.getId()).withFirstName("FirstName").withLastName("Lastname");

    app.contact().modify(contact);
    app.goTo().openHomePage();
    Contacts after = app.db().contacts();
    assertEquals(after.size(), before.size());
    assertThat(after, equalTo(before.without(modifyContact).withAdded(contact)));
  }

  }



