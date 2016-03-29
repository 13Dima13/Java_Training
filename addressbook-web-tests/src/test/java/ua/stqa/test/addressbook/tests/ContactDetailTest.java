package ua.stqa.test.addressbook.tests;


import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ua.stqa.test.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDetailTest extends TestBase {
  @BeforeMethod
  public  void ensurePreconditions() {
    app.goTo().openHomePage();
    if (app.contact().list().size() == 0) {
      app.goTo().AddNewContactPage();
      app.contact().create(new ContactData().withFirstName("FirstName").withLastName("Lastname").withGroup("test1").withHomePhone("111").withMobilePhone("222").withWorkPhone("333").withAddress("Street").withEmail("test@test.tt"), true);
      app.goTo().openHomePage();
    }
  }

  @Test
  public void testContactDetails(){
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    WebElement contactInfo = app.contact().infoFromDetailsContact(contact);
    String contactInf = cleaned(contactInfo);
    String contactFromEdit = mergeContactInfo(contactInfoFromEditForm);
    assertThat(contactInf, equalTo(contactFromEdit));
  }

  private String mergeContactInfo(ContactData contact) {
    return Arrays.asList(contact.getFirstName(), contact.getMiddleName(), contact.getLastName(), contact.getNickname()
            , contact.getAddress(), contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone()
            , contact.getEmail(), contact.getEmail2(), contact.getEmail3())
            .stream().filter(s -> ! s.equals(""))
            .map(ContactDetailTest::cleaned)
            .collect(Collectors.joining(""));
  }

  private String cleaned(WebElement contactInfo){
    String contact = contactInfo.getText().replaceAll("[-()]", "").replace("test@test.tt", "").replace("H:", "")
            .replace("M:", "").replace("W:", "").replace(" ", "").replace("\n", "").replace(" ", "");
    return contact;
  }

  public static String cleaned(String phone){
    return  phone.replaceAll("[-()]", "").replace("\n", "");
  }


}

