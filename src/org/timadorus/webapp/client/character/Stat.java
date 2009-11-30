package org.timadorus.webapp.client.character;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.ui.Button;

public class Stat implements Serializable {

  String name;

  String description;
  
  Integer tempStat;
  
  Integer potStat;
  
  Button incButton = new Button("+");
  
  Button decButton = new Button("-");
  
  private static final long serialVersionUID = 1L;

  public Stat() {

  }

  public Stat(String name, String description) {

    this.name = name;
    this.description = description;
    this.tempStat = 30;
    this.potStat = 0;
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

  public Integer getTempStat() {
    return tempStat;
  }

  public void setTempStat(Integer tempStat) {
    this.tempStat = tempStat;
  }

  public Integer getPotStat() {
    return potStat;
  }

  public void setPotStat(Integer potStat) {
    this.potStat = potStat;
  }

  public Button getIncButton() {
    return incButton;
  }

  public void setIncButton(Button incButton) {
    this.incButton = incButton;
  }

  public Button getDecButton() {
    return decButton;
  }

  public void setDecButton(Button decButton) {
    this.decButton = decButton;
  }    
}
