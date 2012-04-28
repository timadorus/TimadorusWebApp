package org.timadorus.webapp.beans;

import java.io.Serializable;

import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

@PersistenceCapable
public class Stat implements Serializable {
 
  private static final long serialVersionUID = -7796504950459320478L;
  
  @NotPersistent
  private final int initialTempStat = 30;
  
  @Persistent
  String name = "--";

  @Persistent
  String description = "--";
  
  @Persistent
  Integer tempStat = new Integer(-1);
  
  @Persistent
  Integer potStat = new Integer(-1);
  

  public Stat() { }

  public Stat(String nameIn, String descriptionIn) {

    this.name = nameIn;
    this.description = descriptionIn;
    this.tempStat = initialTempStat;
    this.potStat = 0;
  }

  public String getName() {
    return name;
  }

  public void setName(String nameIn) {
    this.name = nameIn;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String descriptionIn) {
    this.description = descriptionIn;
  }

  public Integer getTempStat() {
    return tempStat;
  }

  public void setTempStat(Integer tempStatIn) {
    this.tempStat = tempStatIn;
  }

  public Integer getPotStat() {
    return potStat;
  }

  public void setPotStat(Integer potStatIn) {
    this.potStat = potStatIn;
  } 
  
  @Override
  public String toString() {
  
    return "Name: " + name;
  }
  
}
