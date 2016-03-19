package ua.stqa.test.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import ua.stqa.test.addressbook.model.ContactData;

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

  public void modifyContact() {
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

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
  }
}
