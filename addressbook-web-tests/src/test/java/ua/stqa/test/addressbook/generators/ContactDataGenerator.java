package ua.stqa.test.addressbook.generators;


import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ua.stqa.test.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {


  @Parameter (names = "-c", description = "Contact count")
  public  int count;

  @Parameter (names = "-f", description = "Target file")
  public String file;

  @Parameter(names = "-d", description = "Contact format")
  public String format;

  public static void main(String[] args) throws IOException {
    ContactDataGenerator generator = new ContactDataGenerator();
    JCommander jCommander = new JCommander(generator);
    try {
      jCommander.parse(args); // args - те опции которые переданны в командной строке
    } catch (ParameterException ex){
      jCommander.usage();
      return;
    }
    generator.run();
  }

  private void run() throws IOException {
    List<ContactData> contacts = generateContacts(count);
    if (format.equals("csv")) {
      saveAsCsv(contacts, new File(file));
    } else if (format.equals("xml")){
      saveAsXml(contacts, new File(file));
    } else if (format.equals("json")){
      saveAsJson(contacts, new File(file));
    } else {
      System.out.println("Wrong format" + format);
    }
  }

  private void saveAsJson(List<ContactData> contacts, File file) throws IOException {
    Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
    String json = gson.toJson(contacts);
    try (Writer writer = new FileWriter(file)) { //автозакрытие
      writer.write(json);
    }
  }

  private void saveAsXml(List<ContactData> contacts, File file) throws IOException {
    XStream xstream = new XStream();
    xstream.processAnnotations(ContactData.class); // внести изменения в контакт дата
    String xml = xstream.toXML(contacts);
    try (Writer writer = new FileWriter(file)){
      writer.write(xml);
    }
  }


  private void saveAsCsv(List<ContactData> contacts, File file) throws IOException { //список который сгенерили для контактов сохраняем в файл
    System.out.println(new File(".").getAbsolutePath());                            //для проверки пути (вывод инфы)
    try (Writer writer = new FileWriter(file)){
      for (ContactData contact : contacts) {                                        //проходимся в цикле по всем группам и каждую из них записываем
        writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s\n",
                contact.getFirstName(), contact.getLastName(),
                contact.getAddress(), contact.getGroup(),
                contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone(),
                contact.getEmail()));

      }
    }
  }

  private List<ContactData> generateContacts(int count) { //генерим тестовые данные
    List<ContactData> contacts = new ArrayList<ContactData>();
    for (int i = 0; i < count; i++) {
      contacts.add(new ContactData()
              .withFirstName(String.format("FirstName %s", i))
              .withLastName(String.format("Lastname %s", i))
              .withGroup(String.format("test1 %s", i))
              .withHomePhone(String.format("111 %s", i))
              .withMobilePhone(String.format("222 %s", i))
              .withWorkPhone(String.format("333 %s", i))
              .withEmail(String.format("test@test.tt %s", i))
              .withAddress(String.format("Street %s", i)));
    }
    return contacts;
  }
}