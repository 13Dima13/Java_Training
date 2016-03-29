package ua.stqa.test.addressbook.tests;


import org.testng.annotations.Test;


import ua.stqa.test.addressbook.model.ContactData;
import ua.stqa.test.addressbook.model.Contacts;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @Test //(enabled =  false)
  public void testContactCreation() {
    app.goTo().openHomePage();
    Contacts before = app.contact().all();
    app.goTo().AddNewContactPage();
    ContactData contact = new ContactData().withFirstName("FirstName").withLastName("Lastname").withAddress("Street")
            .withGroup("test1").withHomePhone("111").withMobilePhone("222").withWorkPhone("333")
    .withEmail("test@test.tt").withAddress("Street");
    app.contact().create(contact, true);
    app.goTo().openHomePage();
    Contacts after = app.contact().all();
    assertThat(after.size(), equalTo(before.size() + 1));
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

  }
}