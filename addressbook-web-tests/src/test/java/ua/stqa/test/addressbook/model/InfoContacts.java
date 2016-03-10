package ua.stqa.test.addressbook.model;

import ua.stqa.test.addressbook.tests.TestBase;

public class InfoContacts extends TestBase {
  private final String name;
  private final String middle;
  private final String lastname;
  private final String nickname;
  private final String title;
  private final String company;
  private String group;

  public InfoContacts(String name, String middle, String lastname, String nickname, String title, String company, String group) {
    this.name = name;
    this.middle = middle;
    this.lastname = lastname;
    this.nickname = nickname;
    this.title = title;
    this.company = company;
    this.group = group;
  }

  public String getName() {
    return name;
  }

  public String getMiddle() {
    return middle;
  }

  public String getLastname() {
    return lastname;
  }

  public String getNickname() {
    return nickname;
  }

  public String getTitle() {
    return title;
  }

  public String getCompany() {
    return company;
  }


  public String getGroup() {
    return group;
  }
}
