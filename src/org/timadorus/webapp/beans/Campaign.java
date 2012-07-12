package org.timadorus.webapp.beans;

import java.io.Serializable;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.jdo.annotations.Unique;

/** a Campaign, which can be created by the Game Master or Admin.
 * 
 */
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
  
  /**
   * @return the campaignID
   */
  public Long getCampaignID() {
    return campaignID;
  }



  /**
   * @param campaignID the campaignID to set
   */
  public void setCampaignID(Long campaignID) {
    this.campaignID = campaignID;
  }



  /**
   * @return the name
   */
  public String getName() {
    return name;
  }



  /**
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }



  /**
   * @return the beschreibung
   */
  public String getBeschreibung() {
    return beschreibung;
  }



  /**
   * @param beschreibung the beschreibung to set
   */
  public void setBeschreibung(String beschreibung) {
    this.beschreibung = beschreibung;
  }



  /**
   * @return the username
   */
  public String getUsername() {
    return username;
  }



  /**
   * @param username the username to set
   */
  public void setUsername(String username) {
    this.username = username;
  }



  @Override
  public String toString() {
    return "Campaign-Name: " + name;
  }

}
