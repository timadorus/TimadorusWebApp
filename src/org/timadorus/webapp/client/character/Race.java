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
  
  List<Class> availableClasses = new ArrayList<Class>();

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
  
  public List<Class> getAvailableClasses() {
    return availableClasses;
  }
  
  public void setAvailableClasses(List<Class> availableClasses) {
    this.availableClasses = availableClasses;
  }
  
  public void addClass(Class newClass){
    availableClasses.add(newClass);    
  }
  
  public static List<Race> getSampleRaces() {

    Race r1 = new Race(
                       new Long(001),
                       "Race-Almas",
                       "The Almas, Mongolian for \"wild man,\" is a purported hominid cryptozoological species reputed to inhabit the Caucasus and Pamir Mountains of central Asia, and the Altai Mountains of southern Mongolia. Source: http://en.wikipedia.org/wiki/Almas_%28cryptozoology%29");
    Race r2 = new Race(
                       new Long(002),
                       "Race-Amomongo",
                       "The Amomongo is a creature of Philippine mythology described as hairy, man-sized and ape-like with long nails. Source: http://en.wikipedia.org/wiki/Amomongo");

    Race r3 = new Race(
                       new Long(003),
                       "Race-Chuchunya",
                       "Chuchunya is a hominid cryptid rumoured to exist in Siberia, Russia. It has been described as six to seven feet tall and covered with dark hair. Source: http://en.wikipedia.org/wiki/Tjutjuna");

    Race r4 = new Race(
                       new Long(004),
                       "Race-Yeti",
                       "The Yeti or Abominable Snowman is a creature and an ape-like cryptid said to inhabit the Himalayan region of Nepal and Tibet. The names Yeti and Meh-Teh are commonly used by the people indigenous to the region,[1] and are part of their history and mythology. Stories of the Yeti first emerged as a facet of Western popular culture in the 19th century. Source: http://en.wikipedia.org/wiki/Yeti");

    ArrayList<Race> rlist=new ArrayList<Race>();
    rlist.add(r1);
    rlist.add(r2);
    rlist.add(r3);
    rlist.add(r4);
    
    return rlist;
  }

}
