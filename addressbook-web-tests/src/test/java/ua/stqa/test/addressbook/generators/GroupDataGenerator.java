package ua.stqa.test.addressbook.generators;


import com.beust.jcommander.JCommander;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import ua.stqa.test.addressbook.model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class GroupDataGenerator {
  @Parameter(names = "-c", description = "Group count")
  public int count;

  @Parameter(names = "-f", description = "Target file")
  public String file;

  @Parameter(names = "-d", description = "Data format")
  public String format;

  public static void main(String[] args) throws IOException {
    GroupDataGenerator generator = new GroupDataGenerator();
    JCommander jCommander = new JCommander(generator);
    try {
      jCommander.parse(args);
    } catch (ParameterException ex) {
      jCommander.usage();
      return;
    }

    generator.run();

  }

  private void run() throws IOException {

    List<GroupData> groups = generatorGroups(count);
    if (format.equals("csv")) {
      saveAsCsv(groups, new File(file));
    } else if (format.equals("xml")) {
      saveAsXml(groups, new File(file));
    } else if (format.equals("json")) {
      saveAsJson(groups, new File(file));
    } else {
      System.out.println("Wrong format file" + format);
    }
  }

  private void saveAsJson(List<GroupData> groups, File file) throws IOException {
    Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
    String json = gson.toJson(groups);
    try (Writer writer = new FileWriter(file)) { //try для закрытия, закроется автоматом как завершится запись
      writer.write(json);
    }
  }


  private void saveAsXml(List<GroupData> groups, File file) throws IOException {
    XStream xstream = new XStream();
    xstream.processAnnotations(GroupData.class); // внести изменения в груп дата
    String xml = xstream.toXML(groups);
    try (Writer writer = new FileWriter(file)) { //try для закрытия, закроется автоматом как завершится запись
      writer.write(xml);
    }
  }

  private void saveAsCsv(List<GroupData> groups, File file) throws IOException {
    System.out.println(new File(".").getAbsolutePath()); //для проверки пути (вывод инфы)
    try (Writer writer = new FileWriter(file)){
            for (GroupData group : groups) {
      writer.write(String.format("%s;%s;%s\n", group.getName(), group.getHeader(), group.getFooter()));
      }
    }
  }

  private List<GroupData> generatorGroups(int count) {
    List<GroupData> groups = new ArrayList<GroupData>();
    for (int i = 0; i < count; i++) {
      groups.add(new GroupData()
              .withName(String.format("test %s", i))
              .withHeader(String.format("header %s", i))
              .withFooter(String.format("footer %s", i)));
    }
    return groups;
  }
}
