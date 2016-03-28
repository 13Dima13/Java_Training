package ua.stqa.test.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ua.stqa.test.addressbook.model.ContactData;
import ua.stqa.test.addressbook.model.Contacts;

import java.util.ArrayList;
import java.util.List;

import static org.apache.bcel.verifier.VerifierFactory.attach;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void submitContactCreation() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstName());
    type(By.name("middlename"), contactData.getMiddleName());
    type(By.name("lastname"), contactData.getLastName());
    type(By.name("mobile"), contactData.getMobileNumber());
    type(By.name("email"), contactData.getEmail());

    if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void deleteContact() {
    click(By.xpath("//div[@id='content']/form[2]/input[2]"));

  }


  public void editContact(int index) {
    wd.findElements(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img")).get(index).click();
  }

  public void editContactById(int id) {
    wd.findElement(By.cssSelector("a[href='edit.php?id="+id+"']")).click();
  }

  public void submitContactModificatio() {
    click(By.xpath("//div[@id='content']/form[1]/input[22]"));
  }


  public void create(ContactData contactData, boolean b) {

    fillContactForm(new ContactData().withFirstName("Test").withLastName("Test").withGroup("test1"), true);
    submitContactCreation();
  }

  public void modify(ContactData contact) {
    editContactById(contact.getId());
    fillContactForm(contact, false);
    submitContactModificatio();
  }

  public boolean isThisAContact() {
    return isElementPresent(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
  }

  public int getContactCount() {
    return wd.findElements(By.name("entry")).size();
  }

  public List<ContactData> list() {
    List<ContactData> contacts = new ArrayList<ContactData>();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element : elements) {
      String name = element.findElement(By.xpath("./td[3]")).getText();
      String lastname = element.findElement(By.xpath("./td[2]")).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
      ContactData contact = new ContactData().withId(id).withFirstName(name).withLastName(lastname);
      contacts.add(contact);
    }
    return contacts;
  }

  public void delete(int index) {
    editContact(index);
    deleteContact();
  }

  public void delete(ContactData contact) {
    editContactById(contact.getId());
    deleteContact();
  }

  public Contacts all() {
    Contacts contacts = new Contacts();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element : elements) {
      String name = element.findElement(By.xpath("./td[3]")).getText();
      String lastname = element.findElement(By.xpath("./td[2]")).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
      ContactData contact = new ContactData().withId(id).withFirstName(name).withLastName(lastname);
      contacts.add(contact);
    }
    return contacts;
  }
}

