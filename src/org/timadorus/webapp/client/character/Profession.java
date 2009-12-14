/**
 * 
 */
package org.timadorus.webapp.client.character;

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
  private static final long serialVersionUID = 3930555680427575279L;

  /**
	 * 
	 */

  
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
