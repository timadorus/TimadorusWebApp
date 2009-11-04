/**
 * 
 */
package org.timadorus.webapp.client;

import java.io.Serializable;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/**
 * @author maddin
 * 
 */
public class Profession implements Serializable {

  /**
	 * 
	 */
  private static final long serialVersionUID = 1L;

  @PrimaryKey
  @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
  Long rasseID;

  @Persistent
  String namen;

  @Persistent
  String Beschreibung;

  public Profession() {
    super();
  }

}
