package org.timadorus.webapp.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.Unique;

/** a Character-Class object, which is related to a Character object.
 * 
 */
@PersistenceCapable
public class CClass implements Serializable {

  /**
   * Serial ID.
   */
  private static final long serialVersionUID = -9162491221927969566L;

  @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
  private Long cclassID = new Long(-1);

  @Unique
  @Persistent
  private String name = "--";

  @Persistent
  private String description = "--";

  @NotPersistent
  private List<Faction> availableFactions1 = new ArrayList<Faction>();

  /**
   * 
   * @param nameIn the name of the character class
   * @param descriptionIn a short description of the character class
   */
  public CClass(final String nameIn, final String descriptionIn) {
    this.name = nameIn;
    this.description = descriptionIn;
  }

  

  /**
   * @return the cclassID
   */
  public Long getCclassID() {
    return cclassID;
  }



  /**
   * @param cclassID the cclassID to set
   */
  public void setCclassID(Long cclassID) {
    this.cclassID = cclassID;
  }



  /**
   * @return the name
   */
  public String getName() {
    return name;
  }



  /**
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }



  /**
   * @return the description
   */
  public String getDescription() {
    return description;
  }



  /**
   * @param description the description to set
   */
  public void setDescription(String description) {
    this.description = description;
  }


  /**
   * 
   * @return the availiable factions
   */
  public final List<Faction> getAvailableFactions() {
    return availableFactions1;
  }

  /**
   * 
   * @param faction the faction to add
   */
  public final void addFaction(final Faction faction) {
    this.availableFactions1.add(faction);
  }

  /**
   * 
   * @param availableFactions the list of factions to set
   */
  public final void setAvailableFactions(final List<Faction> availableFactions) {
    this.availableFactions1 = availableFactions;
  }


  /**
   * Checks if the given {@link Faction} is in the factions available for this class.
   * 
   * @param faction the faction to check
   * @return <code>true</code> if the faction is allowed for this class, <code>false</code> otherwise.
   */
  public boolean containsFaction(Faction faction) {
    return availableFactions1.contains(faction);
  }

  @Override
  public final String toString() {
    return "Character-Class-Name: " + name;
  }
}