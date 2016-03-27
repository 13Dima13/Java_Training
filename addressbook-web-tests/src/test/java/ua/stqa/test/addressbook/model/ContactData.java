package ua.stqa.test.addressbook.model;

import ua.stqa.test.addressbook.tests.TestBase;

public class ContactData extends TestBase {
  private int id;
  private  String firstName;
  private  String middleName;
  private  String mobile;
  private  String work;
  private  String home;
  private  String nickname;
  private  String title;
  private  String company;
  private String group;
  private String lastName;
  private String email;
                                        ////////////////////////////////With/////
  public ContactData withFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public ContactData withMiddleName(String middleName) {
    this.middleName = middleName;
    return this;
  }

  public ContactData withLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public ContactData withMobilePhone(String mobile) {
    this.mobile = mobile;
    return this;
  }
  public ContactData withWorkPhone(String work) {
    this.work = work;
    return this;
  }
  public ContactData withHomePhone(String home) {
    this.home = home;
    return this;
  }

  public ContactData withEmail(String email) {
    this.email = email;
    return this;
  }

  public ContactData withGroup(String group) {
    this.group = group;
    return this;
  }
                                        //////////////////////////Get



  public String getFirstName() {
    return firstName;
  }
  public int getId() {
    return id;
  }

  public String getMiddleName() {
    return middleName;
  }
  public String getHomePhone() {
    return home;
  }
  public String getWorkPhone() {
    return work;
  }

  public String getMobile() {
    return mobile;
  }

  public String getLastName() {
    return lastName;
  }

  public String getEmail() {
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


  @Override
  public int hashCode() {
    return firstName != null ? firstName.hashCode() : 0;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactData that = (ContactData) o;

    if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return true;
    if (middleName != null ? !middleName.equals(that.middleName) : that.middleName != null) return true;
    return lastName != null ? lastName.equals(that.lastName) : that.lastName == null;

  }

  @Override
  public String toString() {
    return "ContactData{" +
            "name='" + firstName + '\'' +
            ", middle='" + middleName + '\'' +
            ", lastname='" + lastName + '\'' +
            '}';
  }

  public ContactData withId(int id) {
    this.id = id;
    return this;
  }
}
