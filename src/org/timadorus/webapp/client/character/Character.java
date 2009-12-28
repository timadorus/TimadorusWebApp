/**
 * 
 */
package org.timadorus.webapp.client.character;

import java.io.Serializable;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

//import com.google.gwt.junit.client.WithProperties.Property;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author kilic_a, willat_j
 * 
 */
@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Character implements Serializable {

  private static final long serialVersionUID = -5074030667922748006L;

  @PrimaryKey
  @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
  Long characterID = new Long(-1);

  @Persistent
  Long userIF = new Long(-1);

  @Persistent
  String name = "--";

  @Persistent
  String gender = "--";

  @Persistent
  Race race;

  @Persistent
  CClass charClass;

  @Persistent
  Faction faction;

  @NotPersistent
  List<Stat> statList = new ArrayList<Stat>();

  @Persistent
  boolean complete = false;

  @Persistent
  String allAttrInfo = "--";
  
  @NotPersistent
  List<Skill> skillList = new ArrayList<Skill>();
  
  @NotPersistent
  List<Skill> skillList_Level_1 = new ArrayList<Skill>();

  List<Integer> tempStats = new ArrayList<Integer>();
  
  List<Integer> potStats = new ArrayList<Integer>();
  
  
  private Character() {
    super();
  }

  void fillStats() {

    Stat s1 = new Stat("Konstitution", "Konsti");
    statList = new ArrayList<Stat>();
    statList.add(s1);
    Stat s2 = new Stat("Agilität", "Agi");
    statList.add(s2);
    Stat s3 = new Stat("Selbstdisziplin", "Diszi");
    statList.add(s3);
    Stat s4 = new Stat("Schlußfolgern", "Schlußfolgern");
    statList.add(s4);
    Stat s5 = new Stat("Erinnerungsvermögen", "Memory");
    statList.add(s5);
    Stat s6 = new Stat("Glück", "Lucky luck");
    statList.add(s6);
    Stat s7 = new Stat("Stärke", "Strength");
    statList.add(s7);
    Stat s8 = new Stat("Schnelligkeit", "Schnelligkeit");
    statList.add(s8);
    Stat s9 = new Stat("Intuition", "Intuition");
    statList.add(s9);
    Stat s10 = new Stat("Präsenz", "Präsenz");
    statList.add(s10);
    Stat s11 = new Stat("Empathie", "Emp");
    statList.add(s11);
    Stat s12 = new Stat("Aussehen", "aussehen");
    int randomInt = (int) Math.floor((Math.random() * 100) + 1);
    s12.setTempStat(randomInt);
    statList.add(s12);

  }

  public static Character getInstance() {

    Character character = new Character();
    character.fillStats();
    return character;

  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public Race getRace() {
    return race;
  }

  public void setRace(Race race) {
    this.race = race;
  }

  /*
   * public String getProfession() { return profession; }
   * 
   * public void setProfession(String profession) { this.profession = profession; }
   */

  public Long getCharacterID() {
    return characterID;
  }

  public void setCharacterID(Long characterID) {
    this.characterID = characterID;
  }

  public Long getUserIF() {
    return userIF;
  }

  public void setUserIF(Long userIF) {
    this.userIF = userIF;
  }

  public boolean isComplete() {
    return complete;
  }

  public void setComplete(boolean complete) {
    this.complete = complete;
  }

  public String getAllAttrInfo() {
    return allAttrInfo;
  }

  public void setAllAttrInfo(String allAttrInfo) {
    this.allAttrInfo = allAttrInfo;
  }

  /* Save Character-Relevance Info's as one-String-Object, for later saving as one-String-Object in AppEngine-JDO */
  public void setAllAttrInfo() {
    this.allAttrInfo = this.toString();
  }

  public List<Integer> getTempStat() {
    return tempStats;
  }

  public void setTempStat(List<Integer> tempStat) {
    this.tempStats = tempStat;
  }

  public void setStatList(List<Stat> statList) {
    this.statList = statList;
  }

  public CClass getCharClass() {
    return charClass;
  }

  public void setCharClass(CClass charClass) {
    this.charClass = charClass;
  }

  public List<Stat> getStatList() {
    return statList;
  }

  public void addStat(Stat stat) {
    statList.add(stat);
  }

  public Faction getFaction() {
    return faction;
  }

  public void setFaction(Faction faction) {
    this.faction = faction;
  }
  
  

  public List<Skill> getSkillList() {
    return skillList;
  }

  public void setSkillList(List<Skill> skillList) {
    this.skillList = skillList;
  }
  
  public String getSkillListNames() {
    String s="";
    for (Skill skill : skillList) {
      s+=skill.getName()+", ";
    }
    if (! s.isEmpty()) {
      s=s.substring(0, s.length()-2); //delete last ", " char  
    }
    
    
    return s;
  }
  
  

  public List<Skill> getSkillList_Level_1() {
    return skillList_Level_1;
  }

  public void setSkillList_Level_1(List<Skill> skillListLevel_1) {
    skillList_Level_1 = skillListLevel_1;
  }

  public String getStatListString() {
    String s = "";
    for (Stat stat : statList) {
      s += stat.toString() + ", ";
    }

    return s;
  }

  @Override
  public String toString() {
    String s = "";

    s += "Character-ID: " + characterID + "\nCharacter-Name: " + name + "\nCharacter-Gender: " + gender;
    if (race != null) {
      s += "\n" + race.toString();
    }
    if (charClass != null) {
      s += "\n" + charClass.toString();
    }
    if (faction != null) {
      s += "\n" + faction.toString();
    }
    if (getStatList() != null) {
      s += "\nCharacter-Stat-Liste: " + getStatListString().toString();
    }
    return s;

  }

}
