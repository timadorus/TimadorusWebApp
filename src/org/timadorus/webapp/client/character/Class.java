package org.timadorus.webapp.client.character;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Class implements Serializable {

  String name;

  String description;
  
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
  
  public void addAvailableFaction(Faction faction){
    availableFactions.add(faction);
  }

}
