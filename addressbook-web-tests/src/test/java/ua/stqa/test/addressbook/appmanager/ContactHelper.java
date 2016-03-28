package ua.stqa.test.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.Select;
import ua.stqa.test.addressbook.model.ContactData;
import ua.stqa.test.addressbook.model.Contacts;

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

  public void fillinAllInfo(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstName());
    type(By.name("lastname"), contactData.getLastName());
    type(By.name("mobile"), contactData.getMobile());
    type(By.name("home"), contactData.getHomePhone());
    type(By.name("work"), contactData.getWorkPhone());
    type(By.name("email"), contactData.getEmail());

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
    fillinAllInfo(contact, true);
    updateContact();
    returnToHomePage();
  }

  public void modify(ContactData contact) {
    modify();
    fillinAllInfo(contact, false);
    updateContact();
    returnToHomePage();
  }
  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteContact();
    confirmRemove();
    openHomePage();
  }
  public void selectContactById(int id) { wd.findElement(By.cssSelector("input[id='" + id +"']")).click();  }
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
    List<WebElement> rows = wd.findElements(By.name("entry"));
    for (WebElement row : rows){
      List<WebElement> cells = row.findElements(By.tagName("td"));
      int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
      String lastname = cells.get(1).getText();
      String firstname = cells.get(2).getText();
      String[] phones = cells.get(5).getText().split("\n");
      contacts.add(new ContactData().withId(id).withFirstName(firstname).withLastName(lastname)
              .withHomePhone(phones[0]).withMobilePhone(phones[1]).withWorkPhone(phones[2]));
    }
    return contacts;
  }

  public ContactData infoFromEditForm(ContactData contact) {
    initContactModificationById(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    wd.navigate().back();
    return new ContactData()
            .withId(contact.getId()).withFirstName(firstname).withLastName(lastname)
            .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work);


  }

  private void initContactModificationById(int id) {
    WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
    WebElement row = checkbox.findElement(By.xpath("./../.."));
    List<WebElement> cells = row.findElements(By.tagName("td"));
    modify();
    //cells.get(7).findElements(By.tagName("a")).click();
  }
}
