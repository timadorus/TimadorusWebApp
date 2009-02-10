package de.harper_hall.keeper.ejb.beans;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateful;

import org.apache.log4j.Logger;
import org.jboss.ejb3.annotation.LocalBinding;
import org.jboss.ejb3.annotation.RemoteBinding;

import de.harper_hall.keeper.ExternalDataException;
import de.harper_hall.keeper.applications.GUIHelper;
import de.harper_hall.keeper.character.CharClassNotSetException;
import de.harper_hall.keeper.character.CharCreator;
import de.harper_hall.keeper.character.InsufficientDevPointException;
import de.harper_hall.keeper.character.InvalidRaceNameException;
import de.harper_hall.keeper.character.Race;
import de.harper_hall.keeper.character.RaceBase;
import de.harper_hall.keeper.character.RaceNotSetException;
import de.harper_hall.keeper.character.Resistance;
import de.harper_hall.keeper.classes.GenericCharacterClass;
import de.harper_hall.keeper.classes.InvalidClassNameException;
import de.harper_hall.keeper.classes.InvalidSkillNameException;
import de.harper_hall.keeper.classes.RequiredSubSkillNotSetException;
import de.harper_hall.keeper.classes.Skill;
import de.harper_hall.keeper.classes.SkillDefinition;
import de.harper_hall.keeper.classes.SkillInvokationException;
import de.harper_hall.keeper.classes.SkillUserNotValidException;
import de.harper_hall.keeper.tables.Stat;

/**
 * Session Bean implementation class CharCreatorFacadeBean
 */
@Stateful
@LocalBinding(jndiBinding = "BookKeeper/CharCreatorFacade/local")
@RemoteBinding(jndiBinding = "BookKeeper/CharCreatorFacade/remote")
public class CharCreatorFacadeBean implements CharCreatorFacadeRemote, CharCreatorFacade {
  
  private static Logger log = Logger.getLogger(CharCreatorFacadeBean.class);
  
  CharCreator creator = null;

  public CharCreatorFacadeBean(CharCreator creator) {
    this.creator = creator;
  }

  /**
   * 
   * @see de.harper_hall.keeper.ejb.interfaces.CharCreatorFacade#getEntityID()
   */
  public long getEntityID() throws CharCreationException {
    // TODO Auto-generated method stub
    return 0;
  }

  /**
   *  
   * @see de.harper_hall.keeper.ejb.interfaces.CharCreatorFacade#isComplete()
   */
  public boolean isComplete() throws CharCreationException {
    return creator.isComplete();
  }

  /**
   * @see de.harper_hall.keeper.ejb.interfaces.CharCreatorFacade#resolvePot_stats(java.util.EnumMap)
   */
  public EnumMap<Stat, Integer> resolvePot_stats(EnumMap<Stat, Integer> potRolls)
      throws CharCreationException {
    try {
      return creator.resolvePot_stats(potRolls);
    } catch (Exception e) {
      throw new CharCreationException(e);
    }
  }

  /**
   * @see de.harper_hall.keeper.ejb.interfaces.CharCreatorFacade#setTemp_stats(java.util.EnumMap)
   */
  public void setTemp_stats(EnumMap<Stat, Integer> tempStats)
      throws CharCreationException {
    try {
      creator.setTemp_stats(tempStats);
    } catch (Exception e) {
      throw new CharCreationException(e);
    }
  }

  /**
   * @see de.harper_hall.keeper.ejb.interfaces.RemoteCharCreatorFacade#ServerInfo()
   */
  public String serverInfo() {
    return "if you can read this, the Bean was called on a remote maschine";
  }

  /**
   * @see de.harper_hall.keeper.ejb.interfaces.CharCreatorFacade#getDevPts()
   */
  public EnumMap<Stat, Double> getDevPts() throws CharCreationException {
    EnumMap<Stat, Double> retval = new EnumMap<Stat, Double>(Stat.class);

    for (Stat stat : Stat.values()) {
      if (stat.isDPProvider()) {  retval.put(stat, creator.getStatDevPts(stat)); }
    }
    return retval;
  }

  /**
   * @see de.harper_hall.keeper.ejb.interfaces.CharCreatorFacade#getRaceBonuses()
   */
  public EnumMap<Stat, Integer> getRaceBonuses() throws CharCreationException {
    EnumMap<Stat, Integer> retval = new EnumMap<Stat, Integer>(Stat.class);
    for (Stat s : Stat.values()) {
      if (s.isBaseStat()) { retval.put(s, creator.getStatRaceBonus(s)); }
    }
    return retval;
  }

  /**
   * @see de.harper_hall.keeper.ejb.interfaces.CharCreatorFacade#getSpecialBonuses()
   */
  public EnumMap<Stat, Integer> getSpecialBonuses()
      throws CharCreationException {
    EnumMap<Stat, Integer> retval = new EnumMap<Stat, Integer>(Stat.class);
    for (Stat s : Stat.values()) {
      if (s.isBaseStat()) {  retval.put(s, creator.getStatSpecBonus(s)); }
    }
    return retval;
  }

  /**
   * @see de.harper_hall.keeper.ejb.interfaces.CharCreatorFacade#getStatsBonuses()
   */
  public EnumMap<Stat, Integer> getStatsBonuses() throws CharCreationException {
    EnumMap<Stat, Integer> retval = new EnumMap<Stat, Integer>(Stat.class);
    for (Stat s : Stat.values()) {
      if (s.isBaseStat()) { retval.put(s, creator.getStatBaseBonus(s)); }
    }
    return retval;
  }

  /*
   * (non-Javadoc)
   * 
   * @see de.harper_hall.keeper.ejb.interfaces.CharCreatorFacade#getRaceNames()
   */
  public String[] getRaceNames() {
    return RaceBase.getNames();
  }

  /*
   * (non-Javadoc)
   * 
   * @see de.harper_hall.keeper.ejb.interfaces.CharCreatorFacade#getRaceName()
   */
  public String getRaceName() {
    Race r;
    try {
      r = creator.getRace();
    } catch (RaceNotSetException e) {
      return "";
    }
    return r.getName();
  }

  /*
   * (non-Javadoc)
   * 
   * @see de.harper_hall.keeper.ejb.interfaces.CharCreatorFacade#setRaceByName(java.lang.String)
   */
  public void setRaceByName(String raceName) {
    try {
      Race r = RaceBase.getRaceByName(raceName);
      creator.setRace(r);
    } catch (InvalidRaceNameException e) {
      log.error("failed to look up race: " + e.getMessage());
    }
  }

  /**
   * 
   * @see de.harper_hall.keeper.ejb.interfaces.CharCreatorFacade#getAge()
   */
  public int getAge() {
    // TODO we need a system of keeping track of game time first
    return 0;
  }

  /**
   * 
   * @see de.harper_hall.keeper.ejb.interfaces.CharCreatorFacade#getGender()
   */
  public String getGender() {
    return creator.getGender().toString();
  }

  /**
   * 
   * @see de.harper_hall.keeper.ejb.interfaces.CharCreatorFacade#getName()
   */
  public String getName() {
    return creator.getName();
  }

  /*
   * (non-Javadoc)
   * 
   * @see de.harper_hall.keeper.ejb.interfaces.CharCreatorFacade#getClassNames()
   */
  public String[] getClassNames() {
    return GenericCharacterClass.getAllClassNames();
  }

  /*
   * (non-Javadoc)
   * 
   * @see de.harper_hall.keeper.ejb.interfaces.CharCreatorFacade#setName(java.lang.String)
   */
  public void setName(String name) {
    creator.setName(name);
  }

  /*
   * (non-Javadoc)
   * 
   * @see de.harper_hall.keeper.ejb.interfaces.CharCreatorFacade#getClassName()
   */
  public String getClassName() {
    try {
      return creator.getCharacterClass().getName();
    } catch (CharClassNotSetException e) {
      return "(not set)";
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see de.harper_hall.keeper.ejb.interfaces.CharCreatorFacade#setClassName(java.lang.String)
   */
  public void setClassName(String name) {
    try {
      creator.setClass(GenericCharacterClass.getClassByName(name));
    } catch (InvalidClassNameException e) {
      log.error("failed to look up race: " + e.getMessage());
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see de.harper_hall.keeper.ejb.interfaces.CharCreatorFacade#getEp()
   */
  public int getEp() {
    return creator.getEP();
  }

  /*
   * (non-Javadoc)
   * 
   * @see de.harper_hall.keeper.ejb.interfaces.CharCreatorFacade#getLevel()
   */
  public int getLevel() {
    return creator.getLevel();
  }

  /*
   * (non-Javadoc)
   * 
   * @see de.harper_hall.keeper.ejb.interfaces.CharCreatorFacade#getFreeDevPts()
   */
  public double getFreeDevPts() {
    return creator.getFreeDevPts();
  }

  /*
   * (non-Javadoc)
   * 
   * @see de.harper_hall.keeper.ejb.interfaces.CharCreatorFacade#getHitDice()
   */
  public int getHitDice() {
    Race r;
    try {
      r = creator.getRace();
    } catch (RaceNotSetException e) {
      return 0;
    }

    return r.getHitDiceMax();
  }

  /*
   * (non-Javadoc)
   * 
   * @see de.harper_hall.keeper.ejb.interfaces.CharCreatorFacade#getTotalDevPts()
   */
  public double getTotalDevPts() {
    return creator.getDevPtsForLvl();
  }

  /*
   * (non-Javadoc)
   * 
   * @see de.harper_hall.keeper.ejb.interfaces.CharCreatorFacade#getBaseHits()
   */
  public int getBaseHits() throws CharCreationException {
    try {
      return creator.getBaseHits();
    } catch (ExternalDataException e) {
      throw new CharCreationException("external data error:" + e.getMessage());
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see de.harper_hall.keeper.ejb.interfaces.CharCreatorFacade#getTotalHits()
   */
  public int getTotalHits() throws CharCreationException {
    try {
      return creator.getTotalHits();
    } catch (ExternalDataException e) {
      throw new CharCreationException("external data error:" + e.getMessage());
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see de.harper_hall.keeper.ejb.interfaces.CharCreatorFacade#getRaceResistAll()
   */
  public Map<String, Integer> getRaceResistAll() {
    Resistance[] rests = Resistance.values();
    Map<String, Integer> retval = new HashMap<String, Integer>();
    Race race = null;
    try {
      race = creator.getRace();
    } catch (RaceNotSetException e) {
      return retval;
    }

    for (int i = 0; i < rests.length; i++) {
      Resistance rest = rests[i];
      retval.put(rest.name().toLowerCase(), Integer.valueOf(race
          .getResistanceBonus(rest)));
    }

    return retval;
  }

  /*
   * (non-Javadoc)
   * 
   * @see de.harper_hall.keeper.ejb.interfaces.CharCreatorFacade#getStatResistAll()
   */
  public Map<String, Integer> getStatResistAll() {
    Resistance[] rests = Resistance.values();
    Map<String, Integer> retval = new HashMap<String, Integer>();

    for (int i = 0; i < rests.length; i++) {
      Resistance rest = rests[i];
      retval.put(rest.name().toLowerCase(), Integer.valueOf(rest
          .calcStatBonus(creator)));
    }

    return retval;
  }

  /*
   * (non-Javadoc)
   * 
   * @see de.harper_hall.keeper.ejb.interfaces.CharCreatorFacade#getTotalResistAll()
   */
  public Map<String, Integer> getTotalResistAll() {
    Map<String, Integer> retval = new HashMap<String, Integer>();
    Map<String, Integer> races = getRaceResistAll();
    Map<String, Integer> stats = getStatResistAll();

    for (String key : races.keySet()) {
      int rv = races.get(key);
      int sv = stats.get(key);
      retval.put(key, rv + sv);
    }

    return retval;
  }

  /*
   * (non-Javadoc)
   * 
   * @see de.harper_hall.keeper.ejb.interfaces.CharCreatorFacade#skillLines()
   */
  public SkillLine[] skillLines() {
    Iterable<Skill> skills = creator.allSkills();
    int sum = 0, bonus = 0;

    ArrayList<SkillLine> retval = new ArrayList<SkillLine>();

    for (Skill skill : skills) {
      creator.bindSkill(skill);
      SkillLine elem = new SkillLineBean();
      retval.add(elem);

      // private String name;
      elem.setName(skill.getFullLabel());

      // private String stats;
      StringBuilder buffer = new StringBuilder();
      String sep = "";
      for (Stat s : skill.getDefinition().getStats()) {
        buffer.append(sep);
        buffer.append(s.toString());
        sep = ", ";
      }
      elem.setStats(buffer.toString());

      // draw the picks line
      elem.setPicks(GUIHelper.genPickLine(creator.getSkillRating(skill)));
      
      // rank bonus
      bonus = skill.calcRankBonus();
      sum += bonus;
      elem.setRank_bonus(bonus);

      // stat bonus
      bonus = skill.calcStatBonus();
      sum += bonus;
      elem.setStat_bonus(bonus);

      // private int level_bonus;
      try {
        bonus = skill.calcLevelBonus();
      } catch (SkillUserNotValidException e) {
        log.error("skill user of skill " + skill.getFullLabel()
                  + " not valid :" + e.getMessage());
        // no valid user, no bonus
        bonus = 0;
      }
      sum += bonus;
      elem.setLevel_bonus(bonus);

      // private int item_bonus;

      // private int misc_bonus;

      // private int total_bonus;
      elem.setTotal_bonus(sum);

      // private String type;
      elem.setType(skill.getDefinition().getAction_type().toString());
    }

    return retval.toArray(new SkillLine[retval.size()]);
  }

  /**
   * @throws InsufficientDevPointException
   * @throws InvalidSkillNameException
   * @throws ExternalDataException
   *             some internal error with the definition of the skills occured
   * @see de.harper_hall.keeper.ejb.interfaces.CharCreatorFacade#addSkill(java.lang.String,
   *      java.lang.String)
   */
  public void addSkill(String addPickSkillName, String addPickSubSkillName,
                       int cost) throws CharCreationException {

    try {
      Skill sk = SkillDefinition.getSkill(addPickSkillName, addPickSubSkillName);
      creator.bindSkill(sk);
      sk.addSkillPick(cost);
    } catch (SkillUserNotValidException e) {
      throw new CharCreationException("the creator failed to set the skill user");
    } catch (SkillInvokationException e) {
      throw new CharCreationException("the creator failed to create the skill object", e);
    } catch (InvalidSkillNameException e) {
      throw new CharCreationException("the (sub-)skill name is not valid", e);
    } catch (InsufficientDevPointException e) {
      throw new CharCreationException("not enough skill points left", e);
    }
  }

  /**
   * @throws CharCreationException if any error occours
   * @see de.harper_hall.keeper.ejb.interfaces.CharCreatorFacade#getStdCost(java.lang.String)
   */
  public int getStdCost(String skillName, String subSkillName) throws CharCreationException {

    try {
      Skill skill = SkillDefinition.getSkill(skillName, subSkillName);
      creator.bindSkill(skill);

      return skill.getCost();

    } catch (SkillUserNotValidException e) {
      throw new CharCreationException("the creator failed to set the skill user");
    } catch (SkillInvokationException e) {
      throw new CharCreationException("the creator failed to create the skill object");
    } catch (RequiredSubSkillNotSetException e) {
      throw new CharCreationException("a required sub-skill was not set");
    } catch (InvalidSkillNameException e) {
      throw new CharCreationException("the skill name " + skillName + " is not known");
    } catch (ExternalDataException e) {
      throw new CharCreationException("a general Error loading application data has occoured. Please contact the administrator");
    }
  }

    /**
     * Default constructor. 
     */
    public CharCreatorFacadeBean() {
        // TODO Auto-generated constructor stub
    }

}
