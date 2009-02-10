/**
 * copyright 31.08.2007 Lutz Behnke <lutz.behnke@gmx.de> 
 */
package de.harper_hall.timadorus.webapp.bookkeeper.chargen.beans;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import de.harper_hall.keeper.RandomSource;
import de.harper_hall.keeper.SystemPseudoRandomSource;
import de.harper_hall.keeper.acid.weapons.GenericWeapon;
import de.harper_hall.keeper.classes.InvalidSkillNameException;
import de.harper_hall.keeper.classes.SkillDefinition;
import de.harper_hall.keeper.ejb.beans.CharCreationException;
import de.harper_hall.keeper.ejb.beans.CharCreatorFacade;
import de.harper_hall.keeper.ejb.beans.CharacterFactory;
import de.harper_hall.keeper.ejb.beans.SkillLine;
import de.harper_hall.keeper.tables.Stat;
import de.harper_hall.util.collections.EnumByStringMap;


/**
 * @author sage
 * 
 */
public class StatsBean {
  private static Logger log = Logger.getLogger(GenericWeapon.class);
  
  private EnumMap<Stat, Integer> tempStatMap = new EnumMap<Stat, Integer>(Stat.class);

  private EnumMap<Stat, Integer> potStatMap = new EnumMap<Stat, Integer>(Stat.class);

  private EnumMap<Stat, Integer> potRollMap = new EnumMap<Stat, Integer>(Stat.class);

  private EnumByStringMap<Stat, Integer> ebsTempStatMap = 
    new EnumByStringMap<Stat, Integer>(tempStatMap, Stat.class);

  private EnumByStringMap<Stat, Integer> ebsPotRollMap = 
    new EnumByStringMap<Stat, Integer>(potRollMap, Stat.class);

  // TODO: Get this config information from EJB-App
  private static final Stat[] TEMP_BASE_STAT_LIST = { Stat.CO, Stat.AG, Stat.SD, Stat.RE,
                                                      Stat.ME, Stat.LU, Stat.ST, Stat.QU,
                                                      Stat.EM, Stat.IN, Stat.PR, Stat.AP };

  private static final Stat[] POT_BASE_STAT_LIST = { Stat.CO, Stat.AG, Stat.SD, Stat.RE,
                                                     Stat.ME, Stat.LU, Stat.ST, Stat.QU, 
                                                     Stat.EM, Stat.IN, Stat.PR };

  private String addPickSkillName;

  private boolean addPickSkillValid;

  private String addPickSubSkillName;

  private boolean addPickSubSkillValid;

  private int addPickCost;

  private CharCreatorFacade creator = null;

  public StatsBean() {
    log.debug("created StatsBean");

    try {
      this.retrieveCreator();
    } catch (Exception e) {
      log.error("StatsBean(): Exception '"
          + e.getMessage()
          + "' when trying to get and init EJB. Reason:"
          + ((e.getCause() != null) ? "(none)" : e.getCause()
              .getLocalizedMessage()));
      creator = null;
    }
  }

  public long getEntityID() throws CharCreationException {
    return creator.getEntityID();
  }


  public Map<String, Integer> getTemp() {
    return this.ebsTempStatMap;
  }

  public void setTemp(Map<String, Integer> val) {
    ebsTempStatMap.putAll(val);
  }

  public Map<String, Integer> getPotRoll() {
    return this.ebsPotRollMap;
  }

  public void setPotRoll(Map<String, Integer> val) {
    ebsTempStatMap.putAll(val);
  }

  public String rollTempStep() {
    log.debug("the method rollTempStep is being called");

    // set to this is LU is no stat:
    // TODO: get config for this from EJB-App
    // Stat[] base_stats = { Stat.CO, Stat.AG, Stat.SD, Stat.RE, Stat.ME,
    // Stat.ST, Stat.QU, Stat.EM, Stat.IN, Stat.PR, Stat.AP };

    // TODO: Get this config information from EJB-App
    final int statMin = 30;

    RandomSource rand = new SystemPseudoRandomSource();

    for (int i = 0; i < TEMP_BASE_STAT_LIST.length; i++) {
      int val;

      do {
        val = (int) rand.rollDpercent("roll Temp");
      } while (val <= statMin);

      tempStatMap.put(TEMP_BASE_STAT_LIST[i], Integer.valueOf((int) val));
    }
    return "temp_stats_rolled";
  }

  public String rollPotStep() {
    log.debug("the method rollPotStep is being called");

    // set to this is LU is no stat:
    // TODO: get config for this from EJB-App
    // Stat[] base_stats = { Stat.CO, Stat.AG, Stat.SD, Stat.RE, Stat.ME,
    // Stat.ST, Stat.QU, Stat.EM, Stat.IN, Stat.PR };

    RandomSource rand = new SystemPseudoRandomSource();

    for (int i = 0; i < POT_BASE_STAT_LIST.length; i++) {
      int val;

      val = rand.rollDpercent("roll Pot");

      potRollMap.put(POT_BASE_STAT_LIST[i], Integer.valueOf(val));
    }
    return "pot_stats_rolled";
  }

  private void retrieveCreator() {
    log.debug("the method retrieveCreator() is being called...");
    InitialContext ctx = null;
    CharacterFactory factory = null;
    try {
      ctx = new InitialContext();
    } catch (NamingException e) {
      log.error("could not get initial context", e);
      return;
    }

    try {
      factory = (CharacterFactory) ctx
          .lookup("BookKeeper/CharacterFactory/local");
    } catch (NamingException e) {
      log
          .error(
                 "could not retrieve 'BookKeeper/CharacterFactory/local' from context.",
                 e);
      return;
    }

    log.debug("calling factory to create charCreator");

    creator = factory.createCharacter();
  }

  /**
   * 
   * @return
   */
  public String resolveTempStep() {
    log.debug("the method resolveTempStep is being called");

    if (creator == null) {
      return "failure";
    }

    try {
      creator.setTemp_stats(tempStatMap);
    } catch (CharCreationException e) {
      log.debug("failure in resolveTempStep(): " + e.getMessage());
      return "failure";
    }

    // do table lookup for each stat
    return "temp_stats_resolved";
  }

  public String resolvePotStep() {
    log.debug("the method resolvePotStep is being called");

    if (creator == null) {
      return "failure";
    }

    try {
      potStatMap = creator.resolvePot_stats(this.potRollMap);
    } catch (CharCreationException e) {
      log.error("resolvePotStep(): Exception '" + e.getMessage() + "' thrown");
      return "failure";
    }

    return "pot_stats_resolved";
  }

  public class StatRecord {

    private String name;

    private String acro;

    private int temp;

    private int pot;

    private double devPts;

    private int statBonus;

    private int raceBonus;

    private int specBonus;

    private int totBonus;

    /**
     * @return the acro
     */
    public String getAcro() {
      return acro;
    }

    /**
     * @param acro
     *            the acro to set
     */
    public void setAcro(String acro) {
      this.acro = acro;
    }

    /**
     * @return the name
     */
    public String getName() {
      return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
      this.name = name;
    }

    /**
     * @return the devPts
     */
    public double getDevPts() {
      return devPts;
    }

    /**
     * @param devPts
     *            the devPts to set
     */
    public void setDevPts(double devPts) {
      this.devPts = devPts;
    }

    /**
     * @return the pot
     */
    public int getPot() {
      return pot;
    }

    /**
     * @param pot
     *            the pot to set
     */
    public void setPot(int pot) {
      this.pot = pot;
    }

    /**
     * @return the raceBonus
     */
    public int getRaceBonus() {
      return raceBonus;
    }

    /**
     * @param raceBonus
     *            the raceBonus to set
     */
    public void setRaceBonus(int raceBonus) {
      this.raceBonus = raceBonus;
    }

    /**
     * @return the specBonus
     */
    public int getSpecBonus() {
      return specBonus;
    }

    /**
     * @param specBonus
     *            the specBonus to set
     */
    public void setSpecBonus(int specBonus) {
      this.specBonus = specBonus;
    }

    /**
     * @return the statBonus
     */
    public int getStatBonus() {
      return statBonus;
    }

    /**
     * @param statBonus
     *            the statBonus to set
     */
    public void setStatBonus(int statBonus) {
      this.statBonus = statBonus;
    }

    /**
     * @return the temp
     */
    public int getTemp() {
      return temp;
    }

    /**
     * @param temp
     *            the temp to set
     */
    public void setTemp(int temp) {
      this.temp = temp;
    }

    /**
     * @return the totBonus
     */
    public int getTotBonus() {
      return totBonus;
    }

    /**
     * @param totBonus
     *            the totBonus to set
     */
    public void setTotBonus(int totBonus) {
      this.totBonus = totBonus;
    }

    /**
     * @param name
     * @param acro
     * @param temp
     * @param pot
     * @param devPts
     * @param statBonus
     * @param raceBonus
     * @param specBonus
     * @param totBonus
     */
    public StatRecord(String n, String a, int t, int p, double dp, int sb,
                      int rb, int spb, int tb) {
      name = n;
      acro = a;
      temp = t;
      pot = p;
      devPts = dp;
      statBonus = sb;
      raceBonus = rb;
      specBonus = spb;
      totBonus = tb;
    }

    /**
     * 
     * 
     */
    public StatRecord() {
      this.name = null;
      this.acro = null;
      this.temp = 0;
      this.pot = 0;
      this.devPts = 0.0;
      this.statBonus = 0;
      this.raceBonus = 0;
      this.specBonus = 0;
      this.totBonus = 0;
    }

  }

  public List<StatRecord> getStatsLines() {
    List<StatRecord> retval = new ArrayList<StatRecord>();
    EnumMap<Stat, Double> devPointMap;
    EnumMap<Stat, Integer> statBonusMap;
    EnumMap<Stat, Integer> raceBonusMap;
    EnumMap<Stat, Integer> specialBonusMap;

    try {
      devPointMap = creator.getDevPts();
      statBonusMap = creator.getStatsBonuses();
      raceBonusMap = creator.getRaceBonuses();
      specialBonusMap = creator.getSpecialBonuses();

    } catch (CharCreationException e) {
      log.error("getStatsLines(): Exception '" + e.getMessage() + "' thrown");
      return null;
    }

    Stat[] values = Stat.values();
    for (int i = 0; i < values.length; i++) {
      Stat stat = values[i];
      int tot = 0;
      Integer val, nullVal = Integer.valueOf(0);
      Double dval, nulldVal = Double.valueOf(0.0);

      if (stat.isBaseStat()) {
        StatRecord sr = new StatRecord();
        int currval;

        sr.setName(stat.getLongName());
        sr.setAcro(stat.name());

        val = tempStatMap.get(stat);
        val = (val != null) ? val : nullVal;
        sr.setTemp(val.intValue());

        val = potStatMap.get(stat);
        val = (val != null) ? val : nullVal;
        sr.setPot(val.intValue());

        dval = devPointMap.get(stat);
        dval = (dval != null) ? dval : nulldVal;
        sr.setDevPts(dval.doubleValue());

        val = statBonusMap.get(stat);
        val = (val != null) ? val : nullVal;
        currval = val.intValue();
        tot += currval;
        sr.setStatBonus(currval);

        val = raceBonusMap.get(stat);
        val = (val != null) ? val : nullVal;
        currval = val.intValue();
        tot += currval;
        sr.setRaceBonus(currval);

        val = specialBonusMap.get(stat);
        val = (val != null) ? val : nullVal;
        currval = val.intValue();
        tot += currval;
        sr.setSpecBonus(currval);

        sr.setTotBonus(tot);

        retval.add(sr);

      }
    }

    return retval;
  }

  /**
   * 
   * @return
   */
  public Map<String, String> getRaceNames() {
    String[] arr = creator.getRaceNames();
    Arrays.sort(arr);
    Map<String, String> retval = new TreeMap<String, String>();
    for (String s : arr) { retval.put(s, s); }

    return retval;
  }

  /**
   * @return the raceName
   */
  public String getRaceName() {
    return creator.getRaceName();
  }

  /**
   * @param raceName
   *            the raceName to set
   */
  public void setRaceName(String raceName) {
    creator.setRaceByName(raceName);
  }

  /**
   * 
   * @return
   */
  public Map<String, String> getClassNames() {
    String[] arr = creator.getClassNames();
    Map<String, String> retval = new HashMap<String, String>();
    for (String s : arr) { retval.put(s, s); }

    return retval;
  }

  /**
   * 
   * @return
   */
  public String getClassName() {
    return creator.getClassName();
  }

  /**
   * 
   * @param name
   */
  public void setClassName(String name) {
    creator.setClassName(name);
  }

  /**
   * retrieve the characters name
   * 
   */
  public String getName() {
    return creator.getName();
  }

  /**
   * 
   * @param name
   */
  public void setName(String name) {
    creator.setName(name);
  }

  /**
   * 
   * @return
   */
  public String getGender() {
    return creator.getGender();
  }

  /**
   * 
   * @return
   */
  public int getAge() {
    return creator.getAge();
  }

  /**
   * set the race and class
   * 
   * @return
   */
  public String setClassRace() {
    /*
     * the actual setting is done in the accessor methods for class and race
     */
    return "race_stat_selected";
  }

  /**
   * 
   * @return
   */
  public int getLevel() {
    return creator.getLevel();
  }

  /**
   * 
   * @return
   */
  public int getEp() {
    return creator.getEp();
  }

  /**
   * 
   * @return
   */
  public int getHitDice() {
    return creator.getHitDice();
  }

  /**
   * 
   * @return
   */
  public double getFreeDevPts() {
    return creator.getFreeDevPts();
  }

  /**
   * 
   * @return
   */
  public double getTotalDevPts() {
    return creator.getTotalDevPts();
  }

  /**
   * 
   * @return
   */
  public int getBaseHits() {
    try {
      return creator.getBaseHits();
    } catch (CharCreationException e) {
      // TODO: proper exception handling
      return 0;
    }
  }

  /**
   * 
   * @return
   */
  public int getTotalHits() {
    try {
      return creator.getTotalHits();
    } catch (CharCreationException e) {
      // TODO Auto-generated catch block
      return 0;
    }
  }

  /**
   * 
   * @return
   */
  public Map<String, Integer> getRaceResistAll() {
    return creator.getRaceResistAll();
  }

  /**
   * 
   * @return
   */
  public Map<String, Integer> getStatResistAll() {
    return creator.getStatResistAll();
  }

  /**
   * 
   * @return
   */
  public Map<String, Integer> getTotalResistAll() {
    return creator.getTotalResistAll();
  }

  public SkillLine[] getSkillLines() {
    return creator.skillLines();
  }

  /**
   * @return the addPickCost
   */
  public int getAddPickCost() {
    return addPickCost;
  }

  /**
   * @param addPickCost
   *            the addPickCost to set
   */
  public void setAddPickCost(int addPickCost) {
    // this.addPickCost = addPickCost;
  }

  /**
   * @return the addPickSkillName
   */
  public String getAddPickSkillName() {
    return addPickSkillName;
  }

  /**
   * @param addPickSkillName
   *            the addPickSkillName to set
   */
  public void setAddPickSkillName(String addPickSkillName) {
    this.addPickSkillName = addPickSkillName;
    try {
      // the sub skill does not change the cost
      addPickCost = creator.getStdCost(addPickSkillName, null);
      log.debug("skill cost set to " + addPickCost);
      addPickSkillValid = true;
    } catch (CharCreationException e) {
      addPickSkillValid = false;
    }
  }

  public boolean addPickSkillHasSubs() {
    try {
      SkillDefinition sk = SkillDefinition.getSkillDefinition(addPickSkillName);
      return sk.hasSubs();
    } catch (InvalidSkillNameException e) {
      return false;
    }
  }

  public boolean getSkillComboIsValid() {
    if (!this.addPickSkillValid) { return false; }
    
    if (addPickSkillHasSubs()) { return addPickSubSkillValid; }
    
    return true;
  }

  /**
   * @return the addPickSubkillName
   */
  public String getAddPickSubSkillName() {
    return addPickSubSkillName;
  }

  /**
   * @param addPickSubkillName
   *            the addPickSubkillName to set
   */
  public void setAddPickSubSkillName(String addPickSubSkillName) {
    this.addPickSubSkillName = addPickSubSkillName;

    // check that this is a valid combination
    if (SkillDefinition.checkValid(addPickSkillName, addPickSubSkillName)) {
      addPickSubSkillValid = true;
    } else {
      addPickSubSkillValid = false;
      log.debug("Invalid Skill/subSkill combo '" + addPickSkillName + "'/'"
          + addPickSubSkillName + "'. ");
    }
  }

  /**
   * TODO: move this to EJB Interface
   * @param event
   * @return
   */
  public List<String> subSkillNames(Object event) {
    List<String> retval = new ArrayList<String>();

    String pref = event.toString();
    try {
      SkillDefinition sk = SkillDefinition.getSkillDefinition(addPickSkillName);

      for (String name : sk.getSubSkillLabels()) {
        if (name.toLowerCase().startsWith(pref.toLowerCase())) { retval.add(name); }
      }
    } catch (InvalidSkillNameException e) {
      log.error("Invalid skill name '" + addPickSkillName
          + "'. The validation upon setting it should have handled it");
    }

    return retval;
  }

  /**
   * return some helping information
   * 
   * @return
   */
  public String getAddSkillMessage() {
    String retval;

    if (addPickSkillName == null) { return "no Skill name set"; }
    
    if (!getSkillComboIsValid()) {
      retval = addPickSkillName;
      if (addPickSubSkillName != null) { retval += ", " + addPickSubSkillName; }
      
      retval += "is not a valid skill name";
    } else {
      retval = "skill name " + addPickSkillName + " is valid";
    }
    return retval;
  }

  /**
   * 
   * @return
   */
  public String addSkill() {
    // TODO: do some message texting here
    try {
      creator.addSkill(addPickSkillName, addPickSubSkillName, addPickCost);
    } catch (CharCreationException e) {
      return "failure";
    }

    return "skill_pick_added";
  }

}
