package ua.stqa.test.addressbook.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import ua.stqa.test.addressbook.tests.TestBase;

import java.io.File;
@XStreamAlias("contact")

public class ContactData extends TestBase {
  @XStreamOmitField
  private int id;
  private  String firstName;
  private  String middleName;
  private  String lastName;
  private  String group;
  private  String mobilePhone;
  private  String homePhone;
  private  String workPhone;
  private  String allPhones;
  private  String address;
  private  String email;
  private  String nickname;
  private  String email2;
  private  String email3;
  private File photo;

  public File getPhoto() {
    return photo;

  }

  public ContactData withPhoto(File photo) {
    this.photo = photo;
    return this;
  }

  public ContactData withNickname (String nickname) {
    this.nickname = nickname;
    return this;
  }
  public ContactData withEmail2 (String email2) {
    this.email2 = email2;
    return this;
  }
  public ContactData withEmail3 (String email3) {
    this.email3 = email3;
    return this;
  }

  public String getNickname() {
    return nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  public String getEmail2() {
    return email2;
  }

  public void setEmail2(String email2) {
    this.email2 = email2;
  }

  public String getEmail3() {
    return email3;
  }

  public void setEmail3(String email3) {
    this.email3 = email3;
  }



  public ContactData withId(int id) {
    this.id = id;
    return this;
  }
  public int getId() {
    return id;
  }

  public ContactData withFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }
  public String getFirstName() {
    return firstName;
  }

  public ContactData withMiddleName(String middleName) {
    this.middleName = middleName;
    return this;
  }
  public String getMiddleName() {
    return middleName;
  }

  public ContactData withLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }
  public String getLastName() {
    return lastName;
  }

  public ContactData withGroup(String group) {
    this.group = group;
    return this;
  }
  public String getGroup() {
    return group;
  }

  public ContactData withMobilePhone(String mobilePhone) {
    this.mobilePhone = mobilePhone;
    return this;
  }
  public String getMobilePhone() {
    return mobilePhone;
  }

  public ContactData withWorkPhone(String workPhone) {
    this.workPhone = workPhone;
    return this;
  }
  public String getWorkPhone() {
    return workPhone;
  }

  public ContactData withHomePhone(String homePhone) {
    this.homePhone = homePhone;
    return this;
  }
  public String getHomePhone() {
    return homePhone;
  }

  public ContactData withAllPhones (String allPhones) {
    this.allPhones = allPhones;
    return this;
  }
  public String getAllPhones() {
    return allPhones;
  }

  public ContactData withAddress (String address) {
    this.address = address;
    return this;
  }
  public String getAddress() {
    return address;
  }

  public ContactData withEmail(String email) {
    this.email = email;
    return this;
  }
  public String getEmail() {
    return email;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactData that = (ContactData) o;

    if (id != that.id) return false;
    if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
    return lastName != null ? lastName.equals(that.lastName) : that.lastName == null;

  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
    result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            '}';

  }


}


