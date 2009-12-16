package org.timadorus.webapp.client.character;

import java.io.Serializable;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Faction implements Serializable {

  private static final long serialVersionUID = -5760923481329375866L;


  @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
  Long fractionID=new Long(-1);

  @Persistent
  String name="--";

  @Persistent
  String description="--";
  
  @Persistent ////braucht JDO für 1-to-n Relationship, beginnend von Race-Klasse
  Race race;

  Faction() {
    super();
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

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Race getRace() {
    return race;
  }

  public void setRace(Race race) {
    this.race = race;
  }
  
  @Override
  public String toString() {
  
    return "Faction-Name: "+name;
  }
  

}
