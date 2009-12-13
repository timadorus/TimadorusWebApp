package org.timadorus.webapp.client.character;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;


@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class WriteEmpl {

public WriteEmpl() {
  // TODO Auto-generated constructor stub
}
  
@PrimaryKey
@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
private Key key;


  @Persistent
  private String mail;
  
 
  
  
  
  public Key getKey() {
    return key;
  }
  public void setKey(Key key) {
    this.key = key;
  }
  public String getMail() {
    return mail;
  }
  public void setMail(String mail) {
    this.mail = mail;
  }

  private ContactInfo contactInfo;

  public ContactInfo getContactInfo() {
      return contactInfo;
  }
 public void setContactInfo(ContactInfo contactInfo) {
      this.contactInfo = contactInfo;
  }
  
  public void setInfo(String s){
    System.out.println(s);
  }

  // ...


}
