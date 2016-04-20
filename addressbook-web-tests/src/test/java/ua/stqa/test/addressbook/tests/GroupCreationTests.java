package ua.stqa.test.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.slf4j.LoggerFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ua.stqa.test.addressbook.model.GroupData;
import ua.stqa.test.addressbook.model.Groups;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {


  @DataProvider //провайдер тестовых данных
  public Iterator<Object[]> validGroupsFromJson() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.json")))){
      String json = "";
      String line = reader.readLine();
      while (line != null) { //цикл на проверку всех строк в файле
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<GroupData>  groups = gson.fromJson(json, new TypeToken<List<GroupData>>(){}.getType()); // List<GroupData>.class
      XStream xstream = new XStream();
      return groups.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
    }

  }
  @DataProvider
  public Iterator<Object[]> validGroupsFromXml() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.xml")))){
      String xml = "";
      String line = reader.readLine();
      while (line != null) { //цикл на проверку всех строк в файле
        xml += line;
        line = reader.readLine();
      }
      XStream xstream = new XStream();
      xstream.processAnnotations(GroupData.class);
      List<GroupData>  groups = (List<GroupData>) xstream.fromXML(xml);
      return groups.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
    }
  }

  @Test(dataProvider = "validGroupsFromJson") //указываем дата провайдер у теста
  public void testGroupCreation(GroupData group) {
      app.goTo().groupPage();
      Groups before = app.db().groups();
      app.group().create(group);
      Groups after = app.db().groups();
      assertThat(app.group().count(), equalTo(before.size() +1));
      assertThat(after, equalTo(
              before.withAdded(group.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt()))));
  }

  @Test
  public void testBadGroupCreation() {
    app.goTo().groupPage();
    Groups before = app.db().groups();
    GroupData group = new GroupData().withName("test'");
    app.group().create(group);
    assertThat(app.group().count(), equalTo(before.size()));
    Groups after = app.db().groups();
    assertThat(after, equalTo(before));
  }
}
