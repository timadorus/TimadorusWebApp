package org.timadorus.webapp.client.campaign;

import java.io.Serializable;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.jdo.annotations.Unique;

//This class represents a Fraction, which can be created by the Game Master or Admin of a Campaign
@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Fraction implements Serializable {
  
  private static final long serialVersionUID = 1401541196634155322L;

  @PrimaryKey
  @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
  private Long fractionID = new Long(-1);
  
  @Unique
  @Persistent
  private String name = "--";
  
  @Persistent
  private String anzeigename = "--";
  
  @Persistent
  private String beschreibung = "--";
  
  @Persistent
  private String informationen = "--";
 
  @Persistent
  private String campaignName = "--";
  
  
  public Fraction() {
    
  }
  
  public Long getFractionID() {
    return fractionID;
  }

  public void setFractionID(Long fractionID) {
    this.fractionID = fractionID;
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

  public void setInformationen(String informationen) {
    this.informationen = informationen;
  }

  public String getInformationen() {
    return informationen;
  }

  public void setAnzeigename(String anzeigename) {
    this.anzeigename = anzeigename;
  }

  public String getAnzeigename() {
    return anzeigename;
  }
  
  public void setCampaignName(String campaignName) {
    this.campaignName = campaignName;
  }

  public String getCampaignName() {
    return campaignName;
  }
  
  @Override
  public String toString() {
    return "Campaign-Name: " + name + " Beschreibung: " + beschreibung;
  }

}
