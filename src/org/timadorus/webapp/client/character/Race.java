package org.timadorus.webapp.client.character;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

/**
 * @author maddin, kilic, willat
 *  This class represents a Race, which will related to the Character-Object
 */
@PersistenceCapable
public class Race implements Serializable {

  private static final long serialVersionUID = 1841788742607089967L;

  @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
  Long raceID = new Long(-1);

  @Persistent
  String name = "--";

  @Persistent
  String description = "--";
  
  @Persistent
  List<CClass> availableClasses = new ArrayList<CClass>();
  
  @Persistent
  List<Faction> availableFactions2 = new ArrayList<Faction>(); 

  public Race() {

  }

  public Race(Long raceIDIn, String nameIn, String descriptionIn) {

    this.raceID = raceIDIn;
    this.name = nameIn;
    this.description = descriptionIn;

  }
  
  public Long getRaceID() {
    return raceID;
  }

  public void setRaceID(Long raceIDIn) {
    this.raceID = raceIDIn;
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
  
  public List<CClass> getAvailableClasses() {
    return availableClasses;
  }
  
  public void setAvailableClasses(List<CClass> availableClassesIn) {
    this.availableClasses = availableClassesIn;
  }
  
  public void addClass(CClass newClass) {
    availableClasses.add(newClass);    
  }
  
  public List<Faction> getAvailableFactions() {
    return availableFactions2;
  }
  
  public void addFaction(Faction faction) {
    availableFactions2.add(faction);
  }

  public List<Faction> getAvailableFactions2() {
    return availableFactions2;
  }

  public void setAvailableFactions2(List<Faction> availableFactions2In) {
    this.availableFactions2 = availableFactions2In;
  }
  
  @Override
  public String toString() {
    return "Race-Name: " + name;
  }
}
