package ua.stqa.test.addressbook.tests;


import org.testng.annotations.Test;


import ua.stqa.test.addressbook.model.ContactData;
import ua.stqa.test.addressbook.model.Contacts;


import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @Test //(enabled =  false)
  public void testContactCreation() {
    app.goTo().openHomePage();
    Contacts before = app.contact().all();
    app.goTo().AddNewContactPage();
    File photo = new File("src/test/resources/body.png");
    ContactData contact = new ContactData().withFirstName("FirstName").withLastName("Lastname").withAddress("Street")
            .withGroup("test1").withHomePhone("111").withMobilePhone("222").withWorkPhone("333").withPhoto(photo)
            .withEmail("test@test.tt").withAddress("Street");
    app.contact().create(contact, true);
    app.goTo().openHomePage();
    Contacts after = app.contact().all();
    assertThat(after.size(), equalTo(before.size() + 1));
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

  }

  @Test (enabled =  false)
  public void testBadContactCreation() {
    app.goTo().openHomePage();
    Contacts before = app.contact().all();
    app.goTo().AddNewContactPage();
    ContactData contact = new ContactData().withFirstName("FirstName").withLastName("Lastname").withAddress("Street")
            .withGroup("test44").withHomePhone("111").withMobilePhone("222").withWorkPhone("333")
            .withEmail("test@test.tt").withAddress("Street");
    app.contact().create(contact, true);
    app.goTo().openHomePage();
    Contacts after = app.contact().all();
    assertThat(after.size(), equalTo(before.size() + 1));
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

@Test (enabled =  false) // Небольшой тест для проверки относительного пути файла.
  public void testCurrentDir() {
  File currentDir = new File(".");
  System.out.println(currentDir.getAbsolutePath());
  File photo = new File("src/test/resources/body.png");
  System.out.println(photo.getAbsolutePath());
  System.out.println(photo.exists());
}

}