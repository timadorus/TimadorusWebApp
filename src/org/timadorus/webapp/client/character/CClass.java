package org.timadorus.webapp.client.character;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

//This Class represents a Character-Class-Object, which is related to a
//Character-Object
@PersistenceCapable
public class CClass implements Serializable {

  /**
   * Serial ID.
   */
  private static final long serialVersionUID = -9162491221927969566L;

  @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
  private Long cclassID = new Long(-1);

  @Persistent
  private String name = "--";

  @Persistent
  private String description = "--";

  @NotPersistent
  private List<Faction> availableFactions1 = new ArrayList<Faction>();
  

  public CClass() { }

  public CClass(final String nameIn, final String descriptionIn) {
    this.name = nameIn;
    this.description = descriptionIn;
  }

  public final String getName() {
    return name;
  }

  public final void setName(final String nameIn) {
    this.name = nameIn;
  }

  public final String getDescription() {
    return description;
  }

  public final void setDescription(final String descriptionIn) {
    this.description = descriptionIn;
  }

  public final Long getClassID() {
    return cclassID;
  }

  public final void setClassID(final Long classIDIn) {
    this.cclassID = classIDIn;
  }

  public final List<Faction> getAvailableFactions() {
    return availableFactions1;
  }

  public final void addFaction(final Faction faction) {
    this.availableFactions1.add(faction);
  }

  public final void setAvailableFactions(final List<Faction> availableFactions) {
    this.availableFactions1 = availableFactions;
  }

  public final List<Faction> getAvailableFactions1() {
    return availableFactions1;
  }

  public final void setAvailableFactions1(final List<Faction> availableFactions1In) {
    this.availableFactions1 = availableFactions1In;
  }

  @Override
  public final String toString() {
    return "Character-Class-Name: " + name;
  }
}