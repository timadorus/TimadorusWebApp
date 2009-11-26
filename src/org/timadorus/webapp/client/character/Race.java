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
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/**
 * @author maddin
 * 
 */
public class Race implements Serializable {

  /**
	 * 
	 */
  private static final long serialVersionUID = 1L;

  @PrimaryKey
  @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
  Long raceID;

  @Persistent
  String name;

  @Persistent
  String description;

  public Race(String name) {
    super();
    this.name = name;
  }
  
  public static List<Race> getRaces(){
    List<Race> races = new ArrayList<Race>();
    races.add(new Race("Witzbold"));
    races.add(new Race("Laufbursche"));
    races.add(new Race("Mächtiger Winzer"));
    races.add(new Race("Terrorgnom"));
    races.add(new Race("Lausbub"));    
    return races;
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

}
