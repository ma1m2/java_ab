package ru.stqa.msl.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.HashSet;
import java.util.Set;

public class Contacts extends ForwardingSet<ContactData> {

  private  Set<ContactData> delegate;

  @Override
  protected Set delegate() {
    return delegate;
  }

  public Contacts() {
    this.delegate = new HashSet<>();
  }

  public Contacts(Contacts contacts) {
    this.delegate = new HashSet<>(contacts.delegate);
  }

  public Contacts withAdded(ContactData contact){
    Contacts contacts = new Contacts(this);
    contacts.add(contact);
    return contacts;
  }

  public Contacts without(ContactData contact){
    Contacts contacts = new Contacts(this);
    contacts.remove(contact);
    return contacts;
  }
}