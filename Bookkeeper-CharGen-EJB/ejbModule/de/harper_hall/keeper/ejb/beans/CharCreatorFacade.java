package de.harper_hall.keeper.ejb.beans;
import java.util.EnumMap;
import java.util.Map;

import javax.ejb.Local;

import de.harper_hall.keeper.tables.Stat;

/**
 * Create new character and perform steps for initialising character
 * 
 * This class implements the nescessary controler methods to create a new keeper
 * character. most of this methods are never used later on.
 * 
 * In order to perform a character creation you have to
 * <ul>
 * <li> create new storage entity and set some initial values (EP, user name)
 * <li> Set the tempory stats. The EnumMap will have been initialized and all
 * stats will have been set if the step is complete
 * <li> Set the potential value. The Enum will have been initialized and all
 * stats have been set. The stats can either be set by using dice rolls and the
 * <tt>resolvePot()</tt> method or by setting the Stats in the pot_stats
 * EnumMap directly (via <tt>setPot_stats</tt>).
 * <li> The race and the character class have been selected. This step is
 * complete if applicable fields have been filled with the names of race and
 * class (<tt>setClassName</tt> and <tt>setRaceName</tt> resp.).
 * <li> the special ability, resources, wealth, status or background rolls have
 * been made. This step is complete if all Background Option Picks of the Race
 * have been used and properly documented in the character
 * <li> The adolescence development (level 0) points have been spend. This step
 * is complete if no remaining dev points are availiable from the initial
 * allotment, and a first level record has been created along with the proper
 * entries in the skill dev log.
 * <li> The adult development (level 1) points have been spend. Complete if
 * entries have been recording similarly to adolescence development.
 * <li> BTS
 * <li> Languages
 * <li> Spells
 * <li> Hits
 * <li> PPDev
 * <li>
 * </ul>
 * 
 * @author sage
 * 
 */
@Local
public interface CharCreatorFacade {
  /**
   * check whether all steps of character creation have been performed
   * 
   * @return true if all steps have properly been performed
   */
  public boolean isComplete() throws CharCreationException;

  /**
   * 
   * @param potRolls
   * @throws TempStatsNotSetException
   * @throws EntityNotSetException
   */
  public EnumMap<Stat, Integer> resolvePot_stats(EnumMap<Stat, Integer> potRolls)
      throws CharCreationException;

  /**
   * @param temp_stat_map
   * @throws EntityNotSetException
   */
  public void setTemp_stats(EnumMap<Stat, Integer> tempStats)
      throws CharCreationException;

  /**
   * @return
   * @throws de.harper_hall.keeper.ejb.beans.CharCreationException 
   * @throws EntityNotSetException
   */
  public long getEntityID() throws CharCreationException, CharCreationException;

  /**
   * get the stat bonuses for each of the stats
   * 
   * @return
   */
  public EnumMap<Stat, Integer> getStatsBonuses() throws CharCreationException;

  /**
   * get the Race bonuses for each of the stats, if any
   * 
   * @return
   */
  public EnumMap<Stat, Integer> getRaceBonuses() throws CharCreationException;

  /**
   * get the special bonuses for each of the stats, if any
   * 
   * @return
   */
  public EnumMap<Stat, Integer> getSpecialBonuses()
      throws CharCreationException;

  /**
   * @return
   */
  public EnumMap<Stat, Double> getDevPts() throws CharCreationException;

  /**
   * @return
   */
  public String[] getRaceNames();

  /**
   * @return
   */
  public String getRaceName();

  /**
   * @param raceName
   */
  public void setRaceByName(String raceName);

  /**
   * 
   */
  public String getName();

  /**
   * @param name
   */
  public void setName(String name);

  /**
   * @return
   */
  public String getGender();

  /**
   * @return
   */
  public int getAge();

  /**
   * @return
   */
  public String getClassName();

  /**
   * @param name
   */
  public void setClassName(String name);

  /**
   * @return
   */
  public String[] getClassNames();

  /**
   * 
   */
  public int getLevel();

  /**
   * 
   */
  public int getEp();

  /**
   * @return
   */
  public int getHitDice();

  /**
   * @return
   */
  public double getFreeDevPts();

  /**
   * @return the number of dev pts for this level
   */
  public double getTotalDevPts();

  /**
   * @return
   * @throws ExternalDataException
   */
  public int getBaseHits() throws CharCreationException;

  /**
   * @return
   * @throws ExternalDataException
   */
  public int getTotalHits() throws CharCreationException;

  /**
   * @return
   */
  public Map<String, Integer> getRaceResistAll();

  /**
   * @return
   */
  public Map<String, Integer> getStatResistAll();

  /**
   * @return
   */
  public Map<String, Integer> getTotalResistAll();

  /**
   * @return
   */
  public SkillLine[] skillLines();

  /**
   * @param addPickSkillName
   * @param addPickSubSkillName
   * @param cost
   *            cost for this pick. this may deviate from the normal cost if
   *            special situations apply.
   * @throws InsufficientDevPointException
   * @throws ExternalDataException
   *             some internal error with the definition of the skills occured
   */
  public void addSkill(String addPickSkillName, String addPickSubSkillName,
                       int cost) throws CharCreationException;

  /**
   * retrieve the standard cost of taking a skill pick
   * 
   * thrown Exceptions are wrapped in a CharCreationException:
   * <dl>
   * <dt>InvalidSkillNameException<dd>
   *             if the skill name or the combination of skill name and
   *             sub-skill name do not constitude a valid name/combination.
   * <dt>CharClassNotSetException<dd>
   *             the character class has not been set in the character.
   * <dt>ExternalDataException<dd>
   *             some internal error with the definition of the skills occured.
   *</dl>
   *
   * @param addPickSkillName
   * @param addPickSubSkillName
   * @return standard skill costs for the set class and the skill named by
   *         skillName and subSkillName.
   * @throws CharCreationException
   */
  public int getStdCost(String skillName, String subSkillName)
      throws CharCreationException;

}
