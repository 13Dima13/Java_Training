package ua.stqa.test.addressbook.model;

import ua.stqa.test.addressbook.tests.TestBase;

public class InfoContacts extends TestBase {
  private final String name;
  private final String middle;
  private final String lastname;
  private final String nickname;
  private final String title;
  private final String company;

  public InfoContacts(String name, String middle, String lastname, String nickname, String title, String company) {
    this.name = name;
    this.middle = middle;
    this.lastname = lastname;
    this.nickname = nickname;
    this.title = title;
    this.company = company;
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
}
