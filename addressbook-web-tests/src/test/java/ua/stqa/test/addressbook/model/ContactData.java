package ua.stqa.test.addressbook.model;

import ua.stqa.test.addressbook.tests.TestBase;

public class ContactData extends TestBase {
  private int id;
  private  String firstName;
  private  String middleName;
  private  String mobileNumber;
  private  String nickname;
  private  String title;
  private  String company;
  private String group;
  private String lastName;
  private String email;

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

  public ContactData withMobileNumber(String mobileNumber) {
    this.mobileNumber = mobileNumber;
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

  public String getFirstName() {
    return firstName;
  }

  public String getMiddleName() {
    return middleName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getMobileNumber() {
    return nickname;
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

  public int getId() {
    return id;
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
