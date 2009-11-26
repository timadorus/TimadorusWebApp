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

  public Race() {
    super();
    this.name = name;
  }
  
  public static List<Race> getRaces(){
    List<Race> races = new ArrayList<Race>();
    Race race1 = new Race();
    race1.setName("Witzbold");
    race1.setdescription("Der Witzbold ist ein Witzbolt");
    races.add(race1);
    
    Race race2 = new Race();
    race2.setName("Laufbursche");
    race2.setdescription("Er läuft und läuft und läuft");
    races.add(race2);
    
    Race race3 = new Race();
    race3.setName("Mächtiger Winzer");
    race3.setdescription("Keiner macht so guten Wein wie er");
    races.add(race3);
    
    Race race4 = new Race();
    race4.setName("Terrorgnom");
    race4.setdescription("Für den kleinen Hunger zwischendurch");
    races.add(race4);
    
    Race race5 = new Race();
    race5.setName("Lausbub");
    race5.setdescription("Ja nu, irgendwas muss hier ja stehen");
    races.add(race5);
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
