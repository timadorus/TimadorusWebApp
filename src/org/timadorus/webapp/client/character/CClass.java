package org.timadorus.webapp.client.character;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

//This Class represents a Character-Class-Object, which is related to a Character-Object 
@PersistenceCapable
public class CClass implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = -9162491221927969566L;
  
  @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
  Long classID=new Long(-1);

  @Persistent
  String name="--";

  @Persistent
  String description="--";

  @NotPersistent
  List<Faction> availableFactions1 = new ArrayList<Faction>();
  
  @Persistent //braucht JDO für 1-to-n Relationship, beginnend von Race-Klasse
  Race race;



  public CClass() {

  }

  public CClass(String nameIn, String descriptionIn) {

    this.name = nameIn;
    this.description = descriptionIn;

  }

  public String getName() {
    return name;
  }

  public void setName(String nameIn) {
    this.name = nameIn;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String descriptionIn) {
    this.description = descriptionIn;
  }

  public List<Faction> getAvailableFactions() {
    return availableFactions1;
  }

  public void addFaction(Faction faction) {
    availableFactions1.add(faction);
  }

  public Race getRace() {
    return race;
  }

  public void setRace(Race raceIn) {
    this.race = raceIn;
  }

  public Long getClassID() {
    return classID;
  }

  public void setClassID(Long classIDIn) {
    this.classID = classIDIn;
  }

  public void setAvailableFactions(List<Faction> availableFactions) {
    this.availableFactions1 = availableFactions;
  }
  
  public List<Faction> getAvailableFactions1() {
    return availableFactions1;
  }

  public void setAvailableFactions1(List<Faction> availableFactions1In) {
    this.availableFactions1 = availableFactions1In;
  }

  @Override
  public String toString() {
  
    return "Character-Class-Name: "+name;
  }
  

}
