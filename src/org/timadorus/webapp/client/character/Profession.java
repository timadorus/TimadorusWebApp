package org.timadorus.webapp.client.character;

import java.io.Serializable;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

/**
 * @author maddin
 */
@PersistenceCapable
public class Profession implements Serializable {

  private static final long serialVersionUID = 3930555680427575279L;

  
  @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
  Long raceID = new Long(-1);

  @Persistent
  String name = "--";

  @Persistent
  String description = "--";

  public Profession() {
    super();
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

  @Override
  public String toString() {
    return "Faction-Name: " + name;
  }
}
