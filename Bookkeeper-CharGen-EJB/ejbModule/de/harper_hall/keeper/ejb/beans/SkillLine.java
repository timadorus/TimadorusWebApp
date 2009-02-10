package de.harper_hall.keeper.ejb.beans;
import javax.ejb.Local;

@Local
public interface SkillLine {
  /**
   * @return the item_bonus
   */
  public abstract int getItem_bonus();

  /**
   * @param itemBonus
   *            the item_bonus to set
   */
  public abstract void setItem_bonus(int itemBonus);

  /**
   * @return the level_bonus
   */
  public abstract int getLevel_bonus();

  /**
   * @param levelBonus
   *            the level_bonus to set
   */
  public abstract void setLevel_bonus(int levelBonus);

  /**
   * @return the misc_bonus
   */
  public abstract int getMisc_bonus();

  /**
   * @param miscBonus
   *            the misc_bonus to set
   */
  public abstract void setMisc_bonus(int miscBonus);

  /**
   * @return the name
   */
  public abstract String getName();

  /**
   * @param name
   *            the name to set
   */
  public abstract void setName(String name);

  /**
   * @return the picks
   */
  public abstract String getPicks();

  /**
   * @param string
   *            the picks to set
   */
  public abstract void setPicks(String string);

  /**
   * @return the rank_bonus
   */
  public abstract int getRank_bonus();

  /**
   * @param rankBonus
   *            the rank_bonus to set
   */
  public abstract void setRank_bonus(int rankBonus);

  /**
   * @return the stat_bonus
   */
  public abstract int getStat_bonus();

  /**
   * @param statBonus
   *            the stat_bonus to set
   */
  public abstract void setStat_bonus(int statBonus);

  /**
   * @return the stats
   */
  public abstract String getStats();

  /**
   * @param stats
   *            the stats to set
   */
  public abstract void setStats(String stats);

  /**
   * @return the total_bonus
   */
  public abstract int getTotal_bonus();

  /**
   * @param totalBonus
   *            the total_bonus to set
   */
  public abstract void setTotal_bonus(int totalBonus);

  /**
   * @return the type
   */
  public abstract String getType();

  /**
   * @param type
   *            the type to set
   */
  public abstract void setType(String type);

}
