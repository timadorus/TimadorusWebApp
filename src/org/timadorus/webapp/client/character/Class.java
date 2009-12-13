package org.timadorus.webapp.client.character;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

@PersistenceCapable
public class Class implements Serializable {

  @Persistent
  String name;

  @Persistent
  String description;

  @Persistent
  List<Faction> availableFactions = new ArrayList<Faction>();

  private static final long serialVersionUID = 1L;

  public Class() {

  }

  public Class(String name, String description) {

    this.name = name;
    this.description = description;

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

  public List<Faction> getAvailableFactions() {
    return availableFactions;
  }

  public void addFaction(Faction faction) {
    availableFactions.add(faction);
  }

}
