package ua.stqa.test.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import ua.stqa.test.addressbook.model.ContactData;
import ua.stqa.test.addressbook.model.Contacts;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {
  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void addNewButton() {
    click(By.linkText("add new"));
  }

  public void addNew() {
    click(By.name("submit"));
  }

  public void fillinAllInfo(ContactData contactData) {
    type(By.name("firstname"), contactData.getName());
    type(By.name("middlename"), contactData.getMiddle());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("nickname"), contactData.getNickname());
    type(By.name("title"), contactData.getTitle());
    type(By.name("company"), contactData.getCompany());

    if (isElementPresent(By.name("new_group"))) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    }
  }

  public void modify() {
    click(By.cssSelector("img[alt='Edit']"));
  }

  public void updateContact() {
    click(By.xpath("//div/div[4]/form[1]/input[1]"));
  }

  public void selectContactForDelete() {
    click(By.xpath(".//tr[@name='entry'][1]//input"));
  }

  public void confirmRemove() {
    wd.switchTo().alert().accept();
  }

  public void deleteContact() {
    click(By.xpath(".//*[@value='Delete']"));
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("Details"));
  }
  public void returnToHomePage() {
    click(By.linkText("home page"));
  }
  public void createContact(ContactData contact) {
    addNewButton();
    fillinAllInfo(contact);
    updateContact();
    returnToHomePage();
  }

  public void modify(ContactData contact) {
    modify();
    fillinAllInfo(contact);
    updateContact();
    returnToHomePage();
  }
  public void delete() {
    selectContactForDelete();
    deleteContact();
    confirmRemove();
    openHomePage();
  }

  private void openHomePage() {
    if (isElementPresent(By.id("maintable"))) {
      return;
    }
    click(By.linkText("home page"));
  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public Contacts all() {
    Contacts contacts = new Contacts();
    List<WebElement> elements = wd.findElements(By.name("selected[]"));
    for (WebElement element : elements){
      String name = element.getText();
      String id = element.findElement(By.xpath("//tr[@class='odd']/td[1]")).getAttribute("id");
      ContactData contact = new ContactData(id, name, null, null, null, null, null);
      contacts.add(contact);
    }
    return contacts;
  }
}
