package org.timadorus.webapp.client.campaign;

import java.io.Serializable;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.Unique;

public class Campaign implements Serializable {
  
  private static final long serialVersionUID = 1401541196634155322L;

  @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
  private Long campaignID = new Long(-1);
  
  @Unique
  @Persistent
  private String name;
  
  @Persistent
  private String beschreibung;
  
  //TODO Attribute uebergeben oder mit Setter setzen?
  public Campaign(String name, String beschreibung) {
    this.name = name;
    this.beschreibung = beschreibung;
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
}
