package org.timadorus.webapp.beans;

import java.io.Serializable;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.jdo.annotations.Unique;

//This class represents a Campaign, which can be created by the Game Master or Admin
@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Campaign implements Serializable {
  
  private static final long serialVersionUID = 1401541196634155322L;

  @PrimaryKey
  @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
  private Long campaignID = new Long(-1);
  
  @Unique
  @Persistent
  private String name = "--";
  
  @Persistent
  private String beschreibung = "--";
  
  @Persistent
  private String username = "--";
  
  public Campaign() {
    
  }
  
  public Long getCampaignID() {
    return campaignID;
  }

  public void setCampaignID(Long campaignID) {
    this.campaignID = campaignID;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getBeschreibung() {
    return beschreibung;
  }

  public void setBeschreibung(String beschreibung) {
    this.beschreibung = beschreibung;
  }
  
  @Override
  public String toString() {
    return "Campaign-Name: " + name;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getUsername() {
    return username;
  }
}
