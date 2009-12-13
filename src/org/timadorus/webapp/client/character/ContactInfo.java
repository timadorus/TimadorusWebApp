package org.timadorus.webapp.client.character;

import javax.jdo.annotations.*;

//... imports ...

public class ContactInfo {
 @PrimaryKey
 @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
 private Long key;

 @Persistent
 private String streetAddress;

 // ...
 
 public String getStreetAddress() {
  return streetAddress;
}

public void setStreetAddress(String streetAddress) {
  this.streetAddress = streetAddress;
}

public ContactInfo() {
  // TODO Auto-generated constructor stub
}
}
