package org.timadorus.webapp.client.character;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Stat implements Serializable {

  String name;

  String description;
  
  Integer tempStat;
  
  Integer potStat;
  
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
}
