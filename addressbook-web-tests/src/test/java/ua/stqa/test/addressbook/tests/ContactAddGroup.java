package ua.stqa.test.addressbook.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ua.stqa.test.addressbook.model.*;



import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactAddGroup extends TestBase{


  @BeforeClass
  public void ensurePreconditions(){
    if (app.db().groups().size() == 0)
    {   app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1").withHeader("test2").withFooter("test3"));
    }

    if (app.db().contacts().size() == 0){
      app.goTo().openHomePage();
      Groups groups = app.db().groups();
      ContactData contact = new ContactData().withFirstName("Test").withLastName("Test1").inGroup(groups.iterator().next());
      app.contact().create(contact);
    }

  }

  @Test
  public void testContactAddGroup()  {
    Contacts contacts =  app.db().contacts();
    Groups groups =  app.db().groups();
    ContactAddGroup before =  app.db().contactsGroups();
    ContactData contact = contacts.iterator().next();
    int  contactId = contact.getId();
    GroupData group = groups.iterator().next();
    int groupId = group.getId();
    String groupName =  group.getName();
    ContactsGroupsData contactsGroup = new ContactsGroupsData().withContactId(contactId).withGroupId(groupId);
    app.contact().selectContactById(contactId);
    app.contact().addToGroup(groupName);
    ContactAddGroup after =  app.db().contactsGroups();
    assertThat(after, equalTo(before.withAdded(contactsGroup)));
  }

}