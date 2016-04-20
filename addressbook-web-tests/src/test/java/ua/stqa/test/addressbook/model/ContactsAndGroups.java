package ua.stqa.test.addressbook.model;


import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class ContactsAndGroups extends ForwardingSet<ContactsGroupsData> {

  private Set<ContactsGroupsData> delegate;

  public ContactsAndGroups(ContactsAndGroups contactsGroups) {
    this.delegate = new HashSet<ContactsGroupsData>(contactsGroups.delegate());
  }

  public ContactsAndGroups(Collection<ContactsGroupsData> contactsGroups) {
    this.delegate = new HashSet<ContactsGroupsData>(contactsGroups);
  }

  public ContactsAndGroups() {
    this.delegate = new HashSet<ContactsGroupsData>();
  }

  @Override
  protected Set<ContactsGroupsData> delegate() {
    return delegate;
  }

  public ContactsAndGroups withAdded(ContactsGroupsData contactsGroup) {
    ContactsAndGroups contactsGroups = new ContactsAndGroups(this);
    contactsGroups.add(contactsGroup);
    return contactsGroups;
  }


  public ContactsAndGroups withOut(ContactsGroupsData contactsGroup) {
    ContactsAndGroups contactsGroups = new ContactsAndGroups(this);
    contactsGroups.remove(contactsGroup);
    return contactsGroups;
  }


}