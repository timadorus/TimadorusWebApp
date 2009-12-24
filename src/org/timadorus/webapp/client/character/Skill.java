package org.timadorus.webapp.client.character;

import java.io.Serializable;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

@PersistenceCapable
public class Skill implements Serializable {

  private static final long serialVersionUID = -5634375002626095838L;

  /*
   * 
   * <skill-def label="Acro" lvl-bonus-cat="ATH" stat1="AG" stat2="QU" action-type="MM" calc-type="Std"> <locale-desc
   * label="Acrobatics" language="en-US" default="true"> </locale-desc> <locale-desc label="Akrobatik" language="de-DE"
   * default="false"> </locale-desc> </skill-def>
   */
  @Persistent
  private String def_label = "";

  @Persistent
  private String lvl_bonus_cat = "";

  @Persistent
  private String stat1 = "";

  @Persistent
  private String stat2 = "";

  @Persistent
  private String action_type = "";

  @Persistent
  private String calc_type = "";

  @Persistent
  private String locale_desc_label = "";

  @Persistent
  private String locale_desc_language = "";

  @Persistent
  private boolean locale_desc_default = false;

  @Persistent
  private String name = "";

  @Persistent
  private String description = "";

  public Skill() {
    // TODO Auto-generated constructor stub
  }

  public Skill(String def_label, String lvl_bonus_cat, String stat1, String stat2, String action_type,
               String calc_type, String locale_desc_label, String locale_desc_language, boolean locale_desc_default,
               String description) {

    this.action_type = action_type;
    this.calc_type = calc_type;
    this.def_label = def_label;
    this.locale_desc_default = locale_desc_default;
    this.locale_desc_label = locale_desc_label;
    this.locale_desc_language = locale_desc_language;
    this.lvl_bonus_cat = lvl_bonus_cat;
    this.stat1 = stat1;
    this.stat2 = stat2;
    this.name = this.locale_desc_label;
    this.description = description;
  }

  public String getDef_label() {
    return def_label;
  }

  public void setDef_label(String defLabel) {
    def_label = defLabel;
  }

  public String getLvl_bonus_cat() {
    return lvl_bonus_cat;
  }

  public void setLvl_bonus_cat(String lvlBonusCat) {
    lvl_bonus_cat = lvlBonusCat;
  }

  public String getStat1() {
    return stat1;
  }

  public void setStat1(String stat1) {
    this.stat1 = stat1;
  }

  public String getStat2() {
    return stat2;
  }

  public void setStat2(String stat2) {
    this.stat2 = stat2;
  }

  public String getAction_type() {
    return action_type;
  }

  public void setAction_type(String actionType) {
    action_type = actionType;
  }

  public String getCalc_type() {
    return calc_type;
  }

  public void setCalc_type(String calcType) {
    calc_type = calcType;
  }

  public String getLocale_desc_label() {
    return locale_desc_label;
  }

  public void setLocale_desc_label(String localeDescLabel) {
    locale_desc_label = localeDescLabel;
  }

  public String getLocale_desc_language() {
    return locale_desc_language;
  }

  public void setLocale_desc_language(String localeDescLanguage) {
    locale_desc_language = localeDescLanguage;
  }

  public boolean isLocale_desc_default() {
    return locale_desc_default;
  }

  public void setLocale_desc_default(boolean localeDescDefault) {
    locale_desc_default = localeDescDefault;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public String toString() {

    String s = "";

    s += "<br>Skill-Name: " + name + "<br>Level-Bonus-Cat: " + lvl_bonus_cat + "<br>Stat1: " + stat1 + "<br>Stat2: " + stat2
        + "<br>Action-Type: " + action_type + "<br>Calc-Type: " + calc_type + "<br>Local-Desc-Language: "
        + locale_desc_language + "<br>Local-Desc-Default: " + locale_desc_default;

    return s;

  }

}
