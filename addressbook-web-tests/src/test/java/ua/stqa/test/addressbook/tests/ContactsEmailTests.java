package ua.stqa.test.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ua.stqa.test.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactsEmailTests extends TestBase {

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
  public void testContactEmail() {
    ContactData contact = app.db().contacts().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    assertThat(contact.getEmail(), equalTo(mergeEmail(contactInfoFromEditForm)));
    verifyContactListInUI();
  }

  private String mergeEmail(ContactData contact) {  // выбрасываем ненужные пустые строки и склеиваем строки
    return Arrays.asList(contact.getEmail())
            .stream().filter((s) -> !s.equals("")) //removed empty fields
            .map(ContactsEmailTests::cleaned) //удалил все ненужные символы
            .collect(Collectors.joining("\n")); // между склеиваимыми частями будем вставлять энтер
  }

  public static String cleaned (String email){  //вспомогательная функция которая выводит телефон на главной странице к очищенному виду: без тире, скобок, пробелов и тд
    return email.replaceAll("\\s", ""); // в скобках указаны регулярные выражения
  }
}
