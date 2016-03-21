package ua.stqa.test.addressbook.model;

import ua.stqa.test.addressbook.tests.TestBase;

public class ContactData extends TestBase {
  private int id;
  private final String name;
  private final String middle;
  private final String lastname;
  private final String nickname;
  private final String title;
  private final String company;
  private String group;


  public ContactData(String name, String middle, String lastname, String nickname, String title, String company, String group) {
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

  public void setId(int id) {
    this.id = id;
  }

  public int getId() {
    return id;
  }
  @Override
  public int hashCode() {
    return name != null ? name.hashCode() : 0;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactData that = (ContactData) o;

    if (name != null ? !name.equals(that.name) : that.name != null) return true;
    if (middle != null ? !middle.equals(that.middle) : that.middle != null) return true;
    return lastname != null ? lastname.equals(that.lastname) : that.lastname == null;

  }

  @Override
  public String toString() {
    return "ContactData{" +
            "name='" + name + '\'' +
            ", middle='" + middle + '\'' +
            ", lastname='" + lastname + '\'' +
            '}';
  }
}
