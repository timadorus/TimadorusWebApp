package org.timadorus.webapp.client.character;

import java.io.Serializable;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

@PersistenceCapable
public class Stat implements Serializable {
 
  private static final long serialVersionUID = -7796504950459320478L;

  
  @Persistent
  String name="--";

  @Persistent
  String description="--";
  
  @Persistent
  Integer tempStat=new Integer(-1);
  
  @Persistent
  Integer potStat=new Integer(-1);
  

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
  
  @Override
  public String toString() {
  
    return "Stat-Name: "+name;
  }
  
}
