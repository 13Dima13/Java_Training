package ua.stqa.test.addressbook.tests;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import ua.stqa.test.addressbook.model.ContactData;
import ua.stqa.test.addressbook.model.Contacts;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {
  @DataProvider
  public Iterator<Object[]> validContactsFromJson() throws IOException {
    List<Object[]> list = new ArrayList<Object[]>();
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")));
    String json = "";
    String line = reader.readLine();
    while (line !=null){ //цикл на проверку всех строк в файле
      json += line;
      line = reader.readLine();
    }
    Gson gson = new Gson();
    List<ContactData>  contacts = gson.fromJson(json, new TypeToken<List<ContactData>>(){}.getType()); // List<GroupData>.class
    XStream xstream = new XStream();
    return contacts.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
  }
  @DataProvider
  public Iterator<Object[]> validContactsFromXml() throws IOException {
    List<Object[]> list = new ArrayList<Object[]>();
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")));
    String xml = "";
    String line = reader.readLine();
    while (line !=null){
      xml += line;
      line = reader.readLine();
    }
    XStream xstream = new XStream();
    xstream.processAnnotations(ContactData.class);
    List<ContactData> contacts = (List<ContactData>) xstream.fromXML(xml);
    return contacts.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
  }

  @Test (dataProvider = "validContactsFromJson")
  public void testContactCreation(ContactData contact) {
    app.goTo().openHomePage();
    Contacts before = app.contact().all();
    app.goTo().AddNewContactPage();
    app.contact().create(contact, true);
    app.goTo().openHomePage();
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

  }

  @Test (enabled =  false)  //старый тест с добавлением фото
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