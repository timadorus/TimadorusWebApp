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


//import com.google.appengine.api.datastore.Text;

//import com.google.gwt.junit.client.WithProperties.Property;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author kilic_a, willat_j
 * This class represents a Character-Object, which will build at the client-Side and afterward it will be send to Server for Storing in JDO-DB
 */
@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Character implements Serializable {

  private static final long serialVersionUID = -5074030667922748006L;

  @PrimaryKey
  @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
  Long key; //art of Table-Key
    
  @Persistent
  String characterID = "999";

  @NotPersistent
  Long userIF = new Long(-1);

  @Persistent
  String name = "Dummy-Character";

  @Persistent
  String gender = "Dummy-Gender";

  @NotPersistent
  Race race;

  @NotPersistent
  CClass charClass;

  @NotPersistent
  Faction faction;

  @NotPersistent
  LinkedList<Stat> statList = new LinkedList<Stat>();

  @Persistent
  boolean complete = false;

  @Persistent
  String allAttrInfo_Part1="--" ;//

  @Persistent
  String allAttrInfo_Part2="--";
  
  @NotPersistent
  List<Skill> skillList = new ArrayList<Skill>();
  
  @NotPersistent
  List<Skill> skillList_Level_1 = new ArrayList<Skill>();
  @NotPersistent
  List<Integer> tempStats = new ArrayList<Integer>();
  @NotPersistent
  LinkedList<Integer> potStats = new LinkedList();
  
  
  private Character() {
    super();
  }

  void fillStats() {

    Stat s1 = new Stat("Konstitution", "Konsti");
    statList = new LinkedList();
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
    character.setAllAttrInfo_Part1();
    character.setAllAttrInfo_Part2();
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
  
  

 

  public Long getKey() {
    return key;
  }

  public void setKey(Long key) {
    this.key = key;
  }

 

  public String getCharacterID() {
    return characterID;
  }

  public void setCharacterID(String characterID) {
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


  public String getAllAttrInfo_Part1() {
    return allAttrInfo_Part1;
  }

  public void setAllAttrInfo_Part1() {
    allAttrInfo_Part1 = this.toString();
  }
 
  //split Information-String in two Parts, because com.google.appengine.api.datastore.Text don't works on client side
  public void setAllAttrInfo(){
setAllAttrInfo_Part1();
setAllAttrInfo_Part2();
  }
  

  public String getAllAttrInfo_Part2() {
    return allAttrInfo_Part2;
  }

  public void setAllAttrInfo_Part2() {
    allAttrInfo_Part2 = this.toString_Part2();
  }

  /* Save Character-Relevance Info's as one-String-Object, for later saving as one-String-Object in AppEngine-JDO */
  
  public List<Integer> getTempStat() {
    return tempStats;
  }

  public void setTempStat(LinkedList<Integer> tempStat) {
    this.tempStats = tempStat;
  }

  public void setStatList(LinkedList<Stat> statList) {
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
  
  public String getSkillLevel1ListNames() {
    String s="";
    for (Skill skill : skillList_Level_1) {
      s+=skill.getName()+", ";
    }
    if (! s.isEmpty()) {
      s=s.substring(0, s.length()-2); //delete last ", " char  
    }
    
    
    return s;
  }
  
  public String getPotStatListWerte() {
    String s="";
    for (Integer i : potStats) {
      s+=i+", ";
    }
    if (! s.isEmpty()) {
      s=s.substring(0, s.length()-2); //delete last ", " char  
    }
    
    
    return s;
  }
  
  public String getTempStatListWerte() {
    String s="";
    for (Integer i : tempStats) {
      s+=i+", ";
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
  
  

  public List<Integer> getTempStats() {
    return tempStats;
  }

  public void setTempStats(List<Integer> tempStats) {
    this.tempStats = tempStats;
  }

  public List<Integer> getPotStats() {
    return potStats;
  }

  public void setPotStats(LinkedList<Integer> potStats) {
    this.potStats = potStats;
  }
  
  
  
  

  @Override
  public String toString() {
    String s = "";

    s += "Character-ID: " + getCharacterID() + "\nCharacter-Name: " + getName() + "\nCharacter-Gender: " + getGender();
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
      s += "\nCharacter-Stat-Liste: " + getStatListString();
    }
    
    
    return s;

  }
  
  public String toString_Part2() {
    String s = "";

    
    if (getSkillList() != null) {
      s += "\nSkill-Liste: " + getSkillListNames();
    }
    
    if (getSkillList_Level_1() != null) {
      s += "\nSkill_Level1-Liste: " + getSkillLevel1ListNames();
    }
    
    if (getPotStats() != null) {
      s += "\nPot-Stat-Liste: " + getPotStatListWerte();
    }
    
    if (getTempStat()!= null) {
      s += "\nTemp.-Stat-Liste: " + getTempStatListWerte();
    }
    
    return s;

  }
  

}
