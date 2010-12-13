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

import java.util.LinkedList;
import java.util.List;

/**
 * @author kilic_a, willat_j
 * This class represents a Character-Object, which will build at the client-Side and afterward it will be send to
 * Server for Storing in JDO-DB
 */
@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Character implements Serializable {

  private static final long serialVersionUID = -5074030667922748006L;

  @PrimaryKey
  @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
  Long key;
    
  @Persistent
  String characterID = "999";

  @Persistent
  String username;

  @Persistent
  String name = "Dummy-Character";

  @Persistent
  String gender = "Dummy-Gender";

  @Persistent
  Race race;

  @Persistent
  CClass charClass;

  @Persistent
  Faction faction;

  @Persistent
  List<Stat> statList = new LinkedList<Stat>();

  @Persistent
  boolean complete = false;

  @NotPersistent
  String allAttrInfoPart1 = "--";

  @NotPersistent
  String allAttrInfoPart2 = "--";
  
  @Persistent
  List<Skill> skillList = new LinkedList<Skill>();
  
  @Persistent
  List<Skill> skillListLevel1 = new LinkedList<Skill>();
  
  @Persistent
  List<Integer> tempStats = new LinkedList<Integer>();
  
  @Persistent
  List<Integer> potStats = new LinkedList<Integer>();
  
  
  Character() {
    super();
  }

  void fillStats() {
    statList = new LinkedList<Stat>();
    
    Stat s1 = new Stat("Konstitution", "Konsti");
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
    
    final int times = 100;
    int randomInt = (int) Math.floor((Math.random() * times) + 1);
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

  public void setName(String nameIn) {
    this.name = nameIn;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String genderIn) {
    this.gender = genderIn;
  }

  public Race getRace() {
    return race;
  }

  public void setRace(Race raceIn) {
    this.race = raceIn;
  } 

  public Long getKey() {
    return key;
  }

  public void setKey(Long keyIn) {
    this.key = keyIn;
  }

 

  public String getCharacterID() {
    return characterID;
  }

  public void setCharacterID(String characterIDIn) {
    this.characterID = characterIDIn;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String usernameIn) {
    this.username = usernameIn;
  }

  public boolean isComplete() {
    return complete;
  }

  public void setComplete(boolean completeIn) {
    this.complete = completeIn;
  }


  public String getAllAttrInfo_Part1() {
    return allAttrInfoPart1;
  }

  public void setAllAttrInfo_Part1() {
    allAttrInfoPart1 = this.toString();
  }
 
  //split Information-String in two Parts, because com.google.appengine.api.datastore.Text don't works on client side
  public void setAllAttrInfo() {
    setAllAttrInfo_Part1();
    setAllAttrInfo_Part2();
  }
  

  public String getAllAttrInfo_Part2() {
    return allAttrInfoPart2;
  }

  public void setAllAttrInfo_Part2() {
    allAttrInfoPart2 = this.toStringPart2();
  }

  /* Save Character-Relevance Info's as one-String-Object, for later saving as one-String-Object in AppEngine-JDO */
  
  public List<Integer> getTempStat() {
    return tempStats;
  }

  public void setTempStat(List<Integer> tempStat) {
    this.tempStats = tempStat;
  }

  public void setStatList(List<Stat> statList2) {
    this.statList = statList2;
  }

  public CClass getCharClass() {
    return charClass;
  }

  public void setCharClass(CClass charClassIn) {
    this.charClass = charClassIn;
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

  public void setFaction(Faction factionIn) {
    this.faction = factionIn;
  }
  
  

  public List<Skill> getSkillList() {
    return skillList;
  }

  public void setSkillList(List<Skill> skillListIn) {
    this.skillList = skillListIn;
  }
  
  public String getSkillListNames() {
    String s = "";
    for (Skill skill : skillList) {
      s += skill.getName() + ", ";
    }
    if (!s.isEmpty()) {
      //delete last ", " char
      s = s.substring(0, s.length() - 2);   
    }
    
    return s;
  }
  
  public String getSkillLevel1ListNames() {
    String s = "";
    for (Skill skill : skillListLevel1) {
      s += skill.getName() + ", ";
    }
    if (!s.isEmpty()) {
      //delete last ", " char
      s = s.substring(0, s.length() - 2);  
    }
    
    return s;
  }
  
  public String getPotStatListWerte() {
    String s = "";
    for (Integer i : potStats) {
      s += i + ", ";
    }
    if (!s.isEmpty()) {
      //delete last ", " char
      s = s.substring(0, s.length() - 2);   
    }
    
    return s;
  }
  
  public String getTempStatListWerte() {
    String s = "";
    for (Integer i : tempStats) {
      s += i + ", ";
    }
    if (!s.isEmpty()) {
      //delete last ", " char
      s = s.substring(0, s.length() - 2);  
    }
    
    return s;
  }

  public List<Skill> getSkillListLevel1() {
    return skillListLevel1;
  }

  public void setSkillListLevel1(List<Skill> skillListLevel1In) {
    this.skillListLevel1 = skillListLevel1In;
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

  public void setTempStats(List<Integer> tempStatsIn) {
    this.tempStats = tempStatsIn;
  }

  public List<Integer> getPotStats() {
    return potStats;
  }

  public void setPotStats(List<Integer> potStats2) {
    this.potStats = potStats2;
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
  
  public String toStringPart2() {
    String s = "";

    
    if (getSkillList() != null) {
      s += "\nSkill-Liste: " + getSkillListNames();
    }
    
    if (getSkillListLevel1() != null) {
      s += "\nSkill_Level1-Liste: " + getSkillLevel1ListNames();
    }
    
    if (getPotStats() != null) {
      s += "\nPot-Stat-Liste: " + getPotStatListWerte();
    }
    
    if (getTempStat() != null) {
      s += "\nTemp.-Stat-Liste: " + getTempStatListWerte();
    }
    
    return s;
  }
}
