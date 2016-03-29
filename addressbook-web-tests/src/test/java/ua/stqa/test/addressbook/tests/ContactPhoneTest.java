package ua.stqa.test.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ua.stqa.test.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTest extends TestBase {
  @BeforeMethod
  public  void ensurePreconditions() {
    app.goTo().openHomePage();
    if (app.contact().list().size() == 0) {
      app.goTo().AddNewContactPage();
      app.contact().create(new ContactData().withFirstName("FirstName").withLastName("Lastname")
              .withMobilePhone("111").withWorkPhone("222"), true);
      app.goTo().openHomePage();
    }
  }

  @Test
  public void testContactPhone() {
    ContactData contact = app.contact().all().iterator().next(); // загружаем множество контактов
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact); // выбираем контакт случайным образом
    assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));  //contact.getAllPhones() загружаем телефоны с главной страницы
  }

  private String mergePhones(ContactData contact) {  // выбрасываем ненужные пустые строки и склеиваем строки
    return Arrays.asList(contact.getHomePhone(),contact.getMobilePhone(), contact.getWorkPhone())
            .stream().filter((s) -> !s.equals("")) //убрали пустые строки
            .map(ContactPhoneTest::cleaned) //удаляем все ненужные символы (чистим телефоны от символов)
            .collect(Collectors.joining("\n")); // между склеиваимыми частями будем вставлять энтер
  }

  public static String cleaned (String phone){  //вспомогательная функция которая приводит телефон на главной странице к очищенному виду: без тире, скобок, пробелов и тд
    return phone.replaceAll("\\s", "").replaceAll("[-()]", ""); // в скобках указаны регулярные выражения
  }

}
