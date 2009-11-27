/**
 * 
 */
package org.timadorus.webapp.client.character;

import java.io.Serializable;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import java.util.ArrayList;

/**
 * @author maddin
 * 
 */
@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Character implements Serializable {

  /**
	 * 
	 */
  private static final long serialVersionUID = 1L;

  @PrimaryKey
  @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
  Long characterID;

  @Persistent
  Long userIF;

  @Persistent
  String name;

  @Persistent
  String gender;

  @Persistent
  ArrayList<Fraction> fraction_list;

  @Persistent
  Race race;
  
  Class charClass;

  /*
   * @Persistent String profession;
   */

  @Persistent
  boolean complete;

  public Character() {
    super();
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public ArrayList<Fraction> getFraction() {
    return fraction_list;
  }

  public void addFraction(Fraction fraction) {
    this.fraction_list.add(fraction);
  }

  public Race getRace() {
    return race;
  }

  public void setRace(Race race) {
    this.race = race;
  }

  /*
   * public String getProfession() { return profession; }
   * 
   * public void setProfession(String profession) { this.profession = profession; }
   */

  public Long getCharacterID() {
    return characterID;
  }

  public void setCharacterID(Long characterID) {
    this.characterID = characterID;
  }

  public Class getCharClass() {
    return charClass;
  }

  public void setCharClass(Class charClass) {
    this.charClass = charClass;
  }

}
