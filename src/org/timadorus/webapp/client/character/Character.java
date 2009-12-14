/**
 * 
 */
package org.timadorus.webapp.client.character;

import java.io.Serializable;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author maddin
 * 
 */
@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Character implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = -5074030667922748006L;

  /**
	 * 
	 */

  @PrimaryKey
  @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
  Long characterID;

  @Persistent
  Long userIF;

  @Persistent
  String name;

  @Persistent
  String gender;

  @Persistent
  Race race;

  @Persistent
  CClass charClass;
  
  @Persistent
  Faction faction;

  @Persistent(mappedBy="character")
  List<Stat> statList;

  @Persistent
  boolean complete;

  private Character() {
    super();

//    Stat s1 = new Stat("Konstitution", "Konsti");
//    addStat(s1);
//    Stat s2 = new Stat("Agilität", "Agi");
//    addStat(s2);
//    Stat s3 = new Stat("Selbstdisziplin", "Diszi");
//    addStat(s3);
//    Stat s4 = new Stat("Schlußfolgern","Schlußfolgern");
//    addStat(s4);
//    Stat s5 = new Stat("Erinnerungsvermögen","Memory");
//    addStat(s5);
//    Stat s6 = new Stat("Glück","Lucky luck");
//    addStat(s6);
//    Stat s7 = new Stat("Stärke","Strength");
//    addStat(s7);
//    Stat s8 = new Stat("Schnelligkeit","SChnelligkeit");
//    addStat(s8);
//    Stat s9 = new Stat("Intuition","Intuition");
//    addStat(s9);
//    Stat s10 = new Stat("Präsenz","Präsenz");
//    addStat(s10);
//    Stat s11 = new Stat("Empathie","Emp");
//    addStat(s11);
//    Stat s12 = new Stat("Aussehen", "aussehen");    
//    int randomInt = (int) Math.floor((Math.random()*100)+1);
//    s12.setTempStat(randomInt);
//    addStat(s12);
  }
  
  void fillStats(){

    Stat s1 = new Stat("Konstitution", "Konsti");
    statList=new ArrayList<Stat>();
    statList.add(s1);
    Stat s2 = new Stat("Agilität", "Agi");
    statList.add(s2);
    Stat s3 = new Stat("Selbstdisziplin", "Diszi");
    statList.add(s3);
    Stat s4 = new Stat("Schlußfolgern","Schlußfolgern");
    statList.add(s4);
    Stat s5 = new Stat("Erinnerungsvermögen","Memory");
    statList.add(s5);
    Stat s6 = new Stat("Glück","Lucky luck");
    statList.add(s6);
    Stat s7 = new Stat("Stärke","Strength");
    statList.add(s7);
    Stat s8 = new Stat("Schnelligkeit","Schnelligkeit");
    statList.add(s8);
    Stat s9 = new Stat("Intuition","Intuition");
    statList.add(s9);
    Stat s10 = new Stat("Präsenz","Präsenz");
    statList.add(s10);
    Stat s11 = new Stat("Empathie","Emp");
    statList.add(s11);
    Stat s12 = new Stat("Aussehen", "aussehen");    
    int randomInt = (int) Math.floor((Math.random()*100)+1);
    s12.setTempStat(randomInt);
    statList.add(s12);
      
    
  }
  
  public static Character getInstance(){
    
    Character character=new Character();
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

  List<Integer> tempStat;


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
}
