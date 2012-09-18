/**
 * 
 */
package org.timadorus.webapp.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.Element;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.jdo.annotations.Unique;


/**
 * @author kilic_a, willat_j This class represents a Character-Object, which will build at the client-Side and afterward
 *         it will be send to Server for Storing in JDO-DB
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

  @Unique
  @Persistent
  String name = "Dummy-Character";

  @Persistent
  String gender = "Dummy-Gender";

  @Persistent
  String skinColor;

  @Persistent
  String hairColor;

  // @Persistent(dependent = "true")
  // wenn einkommentiert funktioniert löschen nicht,
  // wenn anderer Charakter mit gleicher Rasse existiert,
  // java.sql.SQLIntegrityConstraintViolationException:
  // DELETE on table 'FACTION' caused a violation of foreign key constraint
  // 'CHARACTER_FK1' for key (1). The statement has been rolled back.
  Race race;

  // @Persistent(dependent = "true")
  CClass charClass;

  // @Persistent(dependent = "true")
  Faction faction;

  @Persistent
  @Element(dependent = "true")
  ArrayList<Stat> statList = new ArrayList<Stat>();

  @Persistent
  boolean complete = false;

  @NotPersistent
  String allAttrInfoPart1 = "--";

  @NotPersistent
  String allAttrInfoPart2 = "--";

  @Persistent
  @Element(dependent = "true")
  ArrayList<Skill> skillList = new ArrayList<Skill>();

  @Persistent
  @Element(dependent = "true")
  ArrayList<Skill> skillListLevel1 = new ArrayList<Skill>();

  @Persistent
  ArrayList<Integer> tempStats = new ArrayList<Integer>();

  @Persistent
  ArrayList<Integer> potStats = new ArrayList<Integer>();

  /**
   * 
   */
  private void fillStats() {
    statList = new ArrayList<Stat>();

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

  /**
   * 
   * @return a pre-configured instance of the class.
   */
  public static Character getInstance() {
    Character character = new Character();
    character.fillStats();
    character.setAllAttrInfo_Part1();
    character.setAllAttrInfo_Part2();
    return character;
  }

  /**
   * Returns the {@link CharacterColors} of the character, or <code>null</code>
   * if the saved value is invalid.
   * 
   * @return the skin color of the character
   */
  public CharacterColors getSkinColor() {
    return CharacterColors.getByValue(skinColor);
  }

  /**
   * 
   * @param skinColorIn the skin color to set
   */
  public void setSkinColor(CharacterColors skinColorIn) {
    this.skinColor = skinColorIn.getValue();
  }

  /**
   * @param skinColor the skinColor to set
   */
  public void setSkinColor(String skinColor) {
    this.skinColor = skinColor;
  }

  /**
   * 
   * @return the hair color of the character
   */
  public CharacterColors getHairColor() {
    return CharacterColors.getByValue(hairColor);
  }

  /**
   * 
   * @param hairColorIn the hair color to set
   */
  public void setHairColor(CharacterColors hairColorIn) {
    this.hairColor = hairColorIn.getValue();
  }


  /**
   * @param hairColor the hairColor to set
   */
  public void setHairColor(String hairColor) {
    this.hairColor = hairColor;
  }

  /**
   * 
   * @return attribute information part 1
   */
  public String getAllAttrInfo_Part1() {
    return allAttrInfoPart1;
  }

  /**
   * 
   * @return attribute information part 2
   */
  public String getAllAttrInfo_Part2() {
    return allAttrInfoPart2;
  }

  /**
   * 
   */
  public void setAllAttrInfo_Part1() {
    allAttrInfoPart1 = this.toString();
  }

 

  /**
   * split Information-String in two Parts, because com.google.appengine.api.datastore.Text 
   * don't works on client side.
   */
  public void setAllAttrInfo() {
    setAllAttrInfo_Part1();
    setAllAttrInfo_Part2();
  }

  /**
   * 
   */
  public void setAllAttrInfo_Part2() {
    allAttrInfoPart2 = this.toStringPart2();
  }

  /* Save Character-Relevance Info's as one-String-Object, for later saving as one-String-Object in AppEngine-JDO */


  /**
   * 
   */
  public ArrayList<Integer> getTempStat() {
    return tempStats;
  }

  /**
   * 
   * @param tempStat list of temp stats to set
   */
  public void setTempStat(ArrayList<Integer> tempStat) {
    this.tempStats = tempStat;
  }

  /**
   * 
   * @param statList the stat list to set
   */
  public void setStatList(ArrayList<Stat> statList) {
    this.statList = statList;
  }

  /**
   * 
   * @return the character class
   */
  public CClass getCharClass() {
    return charClass;
  }

  /**
   * 
   * @param charClassIn character class to set
   */
  public void setCharClass(CClass charClassIn) {
    this.charClass = charClassIn;
  }

  /**
   * 
   * @return the list of stats
   */
  public List<Stat> getStatList() {
    return statList;
  }

  /**
   * 
   * @param stat the stat to add
   */
  public void addStat(Stat stat) {
    statList.add(stat);
  }

  /**
   * 
   * @return the list of skills 
   */
  public ArrayList<Skill> getSkillList() {
    return skillList;
  }

  /**
   * 
   * @param skillListIn the list of skills to set
   */
  public void setSkillList(ArrayList<Skill> skillListIn) {
    this.skillList = skillListIn;
  }

  /**
   * 
   * @return the list of skill names as comma separated list
   */
  public String getSkillListNames() {
    String s = "";
    for (Skill skill : skillList) {
      s += skill.getName() + ", ";
    }
    if (!s.isEmpty()) {
      // delete last ", " char
      s = s.substring(0, s.length() - 2);
    }

    return s;
  }

  /**
   * 
   * @return get comma separated list of skill levels
   */
  public String getSkillLevel1ListNames() {
    String s = "";
    for (Skill skill : skillListLevel1) {
      s += skill.getName() + ", ";
    }
    if (!s.isEmpty()) {
      // delete last ", " char
      s = s.substring(0, s.length() - 2);
    }

    return s;
  }

  /**
   * 
   * @return pot stat list werte
   */
  public String getPotStatListWerte() {
    String s = "";
    for (Integer i : potStats) {
      s += i + ", ";
    }
    if (!s.isEmpty()) {
      // delete last ", " char
      s = s.substring(0, s.length() - 2);
    }

    return s;
  }

  /**
   * 
   * @return get temp stat werte
   */
  public String getTempStatListWerte() {
    String s = "";
    for (Integer i : tempStats) {
      s += i + ", ";
    }
    if (!s.isEmpty()) {
      // delete last ", " char
      s = s.substring(0, s.length() - 2);
    }

    return s;
  }

  /**
   * 
   * @return the skill level list
   */
  public List<Skill> getSkillListLevel1() {
    return skillListLevel1;
  }

  /**
   * 
   * @param skillListLevel1In
   */
  public void setSkillListLevel1(ArrayList<Skill> skillListLevel1In) {
    this.skillListLevel1 = skillListLevel1In;
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

    s += "Character-ID: " + getCharacterID() + "\nCharacter-Name: " + getName()
        + "\nCharacter-Gender: " + getGender();
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

  /**
   * @return the key
   */
  public Long getKey() {
    return key;
  }

  /**
   * @param key the key to set
   */
  public void setKey(Long key) {
    this.key = key;
  }

  /**
   * @return the characterID
   */
  public String getCharacterID() {
    return characterID;
  }

  /**
   * @param characterID the characterID to set
   */
  public void setCharacterID(String characterID) {
    this.characterID = characterID;
  }

  /**
   * @return the username
   */
  public String getUsername() {
    return username;
  }

  /**
   * @param username the username to set
   */
  public void setUsername(String username) {
    this.username = username;
  }

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * @return the gender
   */
  public String getGender() {
    return gender;
  }

  /**
   * @param gender the gender to set
   */
  public void setGender(String gender) {
    this.gender = gender;
  }

  /**
   * @return the race
   */
  public Race getRace() {
    return race;
  }

  /**
   * @param race the race to set
   */
  public void setRace(Race race) {
    this.race = race;
  }

  /**
   * @return the faction
   */
  public Faction getFaction() {
    return faction;
  }

  /**
   * @param faction the faction to set
   */
  public void setFaction(Faction faction) {
    this.faction = faction;
  }

  /**
   * @return the complete
   */
  public boolean isComplete() {
    return complete;
  }

  /**
   * @param complete the complete to set
   */
  public void setComplete(boolean complete) {
    this.complete = complete;
  }

  /**
   * @return the allAttrInfoPart1
   */
  public String getAllAttrInfoPart1() {
    return allAttrInfoPart1;
  }

  /**
   * @param allAttrInfoPart1 the allAttrInfoPart1 to set
   */
  public void setAllAttrInfoPart1(String allAttrInfoPart1) {
    this.allAttrInfoPart1 = allAttrInfoPart1;
  }

  /**
   * @return the allAttrInfoPart2
   */
  public String getAllAttrInfoPart2() {
    return allAttrInfoPart2;
  }

  /**
   * @param allAttrInfoPart2 the allAttrInfoPart2 to set
   */
  public void setAllAttrInfoPart2(String allAttrInfoPart2) {
    this.allAttrInfoPart2 = allAttrInfoPart2;
  }

  /**
   * @return the tempStats
   */
  public List<Integer> getTempStats() {
    return tempStats;
  }

  /**
   * @param tempStats the tempStats to set
   */
  public void setTempStats(ArrayList<Integer> tempStats) {
    this.tempStats = tempStats;
  }

  /**
   * @return the potStats
   */
  public List<Integer> getPotStats() {
    return potStats;
  }

  /**
   * @param potStats the potStats to set
   */
  public void setPotStats(ArrayList<Integer> potStats) {
    this.potStats = potStats;
  }
  
  
}
