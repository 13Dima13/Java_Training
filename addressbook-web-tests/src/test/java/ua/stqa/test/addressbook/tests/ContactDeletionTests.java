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

public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public  void ensurePreconditions() {
    app.goTo().openHomePage();
    if (app.contact().list().size() == 0) {
      app.goTo().AddNewContactPage();
      app.contact().create(new ContactData().withFirstName("FirstName").withLastName("Lastname").withAddress("Street")
              .withGroup("test1").withHomePhone("111").withMobilePhone("222").withWorkPhone("333")
              .withEmail("test@test.tt").withAddress("Street"), true);
      app.goTo().openHomePage();
    }
  }

  @Test
  public void testContactDeletion() {
    Contacts before = app.contact().all();
    ContactData deleteContact = before.iterator().next();

    app.contact().delete(deleteContact);
    app.goTo().openHomePage();
    Contacts after = app.contact().all();
    assertEquals(after.size(), before.size() -1);
    assertThat(after, equalTo(before.without(deleteContact)));



  }
}
