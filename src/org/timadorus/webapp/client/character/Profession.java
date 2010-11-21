/**
 * 
 */
package org.timadorus.webapp.client.character;

import java.io.Serializable;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

/**
 * @author maddin
 * 
 */
@PersistenceCapable
public class Profession implements Serializable {

  private static final long serialVersionUID = 3930555680427575279L;

  
  @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
  Long rasseID=new Long(-1);

  @Persistent
  String namen="--";

  @Persistent
  String Beschreibung="--";

  public Profession() {
    super();
  }

  public Long getRasseID() {
    return rasseID;
  }

  public void setRasseID(Long rasseID) {
    this.rasseID = rasseID;
  }

  public String getNamen() {
    return namen;
  }

  public void setNamen(String namen) {
    this.namen = namen;
  }

  public String getBeschreibung() {
    return Beschreibung;
  }

  public void setBeschreibung(String beschreibung) {
    Beschreibung = beschreibung;
  }

  @Override
  public String toString() {
  
    return "Faction-Name: "+namen;
  }
  
}
