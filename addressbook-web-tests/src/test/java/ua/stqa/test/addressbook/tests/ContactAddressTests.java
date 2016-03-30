package ua.stqa.test.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ua.stqa.test.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddressTests extends TestBase {

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
  public void testContactAddress() {
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    assertThat(contact.getAddress(), equalTo(mergeAddress(contactInfoFromEditForm)));
  }

  private String mergeAddress(ContactData contact) {  // выбрасил ненужные пустые строки и склеил строки
    return Arrays.asList(contact.getAddress())
            .stream().filter((s) -> !s.equals("")) //removed empty field
            .collect(Collectors.joining("\n")); // между склеиваимыми частями будем вставлять энтер
  }
}
