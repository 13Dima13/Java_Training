package ua.stqa.test.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ua.stqa.test.addressbook.model.InfoContacts;

public class ContactHelper extends BaseHelper {
  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void addNewButton() {
    click(By.linkText("add new"));
  }

  public void addNew() {
    click(By.name("submit"));
  }

  public void openHomePage() {
    click(By.linkText("home page"));
  }

  public void fillinAllInfo(InfoContacts infoContact) {
    type(By.name("firstname"), infoContact.getName());
    type(By.name("middlename"), infoContact.getMiddle());
    type(By.name("lastname"), infoContact.getLastname());
    type(By.name("nickname"), infoContact.getNickname());
    type(By.name("title"), infoContact.getTitle());
    type(By.name("company"), infoContact.getCompany());
  }

  public void selectContact() {
    click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[7]/a/img"));
  }

  public void modifyContact() {
    click(By.xpath("//div/div[4]/form[1]/input[2]"));
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
}
