/**
 * 
 */
package org.timadorus.webapp.client.character;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Dictionary;
import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;



/**
 * @author maddin
 * 
 */
@PersistenceCapable
public class Race implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1841788742607089967L;

  /**
	 * 
	 */

  @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
  Long raceID;

  @Persistent
  String name;

  @Persistent
  String description;
  
  @Persistent(mappedBy="race")
  List<CClass> availableClasses = new ArrayList<CClass>();
  
  @Persistent(mappedBy="race")
  List<Faction> availableFactions2 = new ArrayList<Faction>(); 

  public Race() {
    super();
  }

  public Race(Long raceID, String name, String description) {

    this.raceID = raceID;
    this.name = name;
    this.description = description;

  }
  
  public Long getRaceID() {
    return raceID;
  }

  public void setRaceID(Long raceID) {
    this.raceID = raceID;
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

  public void setdescription(String description) {
    this.description = description;
  }
  
  public List<CClass> getAvailableClasses() {
    return availableClasses;
  }
  
  public void setAvailableClasses(List<CClass> availableClasses) {
    this.availableClasses = availableClasses;
  }
  
  public void addClass(CClass newClass){
    availableClasses.add(newClass);    
  }
  
  public List<Faction> getAvailableFactions(){
    return availableFactions2;
  }
  
  public void addFaction(Faction faction){
    availableFactions2.add(faction);
  }
  
  
}
