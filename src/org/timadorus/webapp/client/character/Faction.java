package org.timadorus.webapp.client.character;

import java.io.Serializable;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

public class Faction implements Serializable {

  /**
	 * 
	 */
  private static final long serialVersionUID = 1L;

  @PrimaryKey
  @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
  Long fractionID;

  @Persistent
  String name;

  @Persistent
  String description;

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

}
