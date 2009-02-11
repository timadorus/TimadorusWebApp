package de.harper_hall.keeper.ejb.beans;

import javax.ejb.Stateful;


/**
 * Session Bean implementation class SkillLineBean
 */
@Stateful
public class SkillLineBean implements SkillLineRemote, SkillLine {

    private static final long serialVersionUID = 5208404319016975416L;

    private String name;

    private String stats;

    private String picks;

    private int rankBonus;

    private int statBonus;

    private int levelBonus;

    private int itemBonus;

    private int miscBonus;

    private int totalBonus;

    private String type;

    /**
     * Default constructor. 
     */
    public SkillLineBean() {
        // TODO Auto-generated constructor stub
    }


    /*
     * (non-Javadoc)
     * 
     * @see de.harper_hall.keeper.ejb.beans.SkillLine#getItem_bonus()
     */
    public int getItem_bonus() {
      return itemBonus;
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.harper_hall.keeper.ejb.beans.SkillLine#setItem_bonus(int)
     */
    public void setItem_bonus(int itemBonus) {
      this.itemBonus = itemBonus;
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.harper_hall.keeper.ejb.beans.SkillLine#getLevel_bonus()
     */
    public int getLevel_bonus() {
      return levelBonus;
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.harper_hall.keeper.ejb.beans.SkillLine#setLevel_bonus(int)
     */
    public void setLevel_bonus(int levelBonus) {
      this.levelBonus = levelBonus;
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.harper_hall.keeper.ejb.beans.SkillLine#getMisc_bonus()
     */
    public int getMisc_bonus() {
      return miscBonus;
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.harper_hall.keeper.ejb.beans.SkillLine#setMisc_bonus(int)
     */
    public void setMisc_bonus(int miscBonus) {
      this.miscBonus = miscBonus;
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.harper_hall.keeper.ejb.beans.SkillLine#getName()
     */
    public String getName() {
      return name;
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.harper_hall.keeper.ejb.beans.SkillLine#setName(java.lang.String)
     */
    public void setName(String name) {
      this.name = name;
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.harper_hall.keeper.ejb.beans.SkillLine#getPicks()
     */
    public String getPicks() {
      return picks;
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.harper_hall.keeper.ejb.beans.SkillLine#setPicks(int)
     */
    public void setPicks(String picks) {
      this.picks = picks;
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.harper_hall.keeper.ejb.beans.SkillLine#getRank_bonus()
     */
    public int getRank_bonus() {
      return rankBonus;
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.harper_hall.keeper.ejb.beans.SkillLine#setRank_bonus(int)
     */
    public void setRank_bonus(int rankBonus) {
      this.rankBonus = rankBonus;
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.harper_hall.keeper.ejb.beans.SkillLine#getStat_bonus()
     */
    public int getStat_bonus() {
      return statBonus;
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.harper_hall.keeper.ejb.beans.SkillLine#setStat_bonus(int)
     */
    public void setStat_bonus(int statBonus) {
      this.statBonus = statBonus;
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.harper_hall.keeper.ejb.beans.SkillLine#getStats()
     */
    public String getStats() {
      return stats;
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.harper_hall.keeper.ejb.beans.SkillLine#setStats(java.lang.String)
     */
    public void setStats(String stats) {
      this.stats = stats;
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.harper_hall.keeper.ejb.beans.SkillLine#getTotal_bonus()
     */
    public int getTotal_bonus() {
      return totalBonus;
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.harper_hall.keeper.ejb.beans.SkillLine#setTotal_bonus(int)
     */
    public void setTotal_bonus(int totalBonus) {
      this.totalBonus = totalBonus;
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.harper_hall.keeper.ejb.beans.SkillLine#getType()
     */
    public String getType() {
      return type;
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.harper_hall.keeper.ejb.beans.SkillLine#setType(java.lang.String)
     */
    public void setType(String type) {
      this.type = type;
    }

}
