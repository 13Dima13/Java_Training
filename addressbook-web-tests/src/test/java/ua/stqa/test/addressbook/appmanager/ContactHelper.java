package ua.stqa.test.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ua.stqa.test.addressbook.model.InfoContacts;

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

  public void fillinAllInfo(InfoContacts infoContacts, boolean creation ) {
    type(By.name("firstname"), infoContacts.getName());
    type(By.name("middlename"), infoContacts.getMiddle());
    type(By.name("lastname"), infoContacts.getLastname());
    type(By.name("nickname"), infoContacts.getNickname());
    type(By.name("title"), infoContacts.getTitle());
    type(By.name("company"), infoContacts.getCompany());

    if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(infoContacts.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
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
