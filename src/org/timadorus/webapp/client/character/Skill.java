package org.timadorus.webapp.client.character;

import java.io.Serializable;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

// This class represents a Skill of a Character-Object
@PersistenceCapable
public class Skill implements Serializable, Cloneable {

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
  
  @Persistent
  private int Cost = 0;
  
  @Persistent
  private int Rank= 0;
  
  @Persistent
  private int Rk_Bn = 0;
  
  @Persistent
  private int Stat_Bn = 0;
  
  
  @Persistent
  private int Level_Bn = 0;
  
  @Persistent
  private int Item = 0;
  
  @Persistent
  private int Total = 0;
  
  @Persistent
  private String[] gesamtInfo;
  
  
  

  public Skill() {
    // TODO Auto-generated constructor stub
  }

  public Skill(String def_label, String lvl_bonus_cat, String stat1, String stat2, String action_type,
               String calc_type, String locale_desc_label, String locale_desc_language, boolean locale_desc_default,
               String description,int Cost, int Rank, int Rk_Bn, int Stat_Bn, int Level_Bn, int Item) {

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
    this.Cost=Cost; 
    this.Rank=Rank;
    this.Rk_Bn=Rk_Bn;
    this.Stat_Bn=Stat_Bn;
    this.Level_Bn=Level_Bn;
    this.Item=Item;
    setTotal("start");
    
  }
  
  public Skill(Skill skill) {

this.action_type =skill. action_type;
this.calc_type =skill. calc_type;
this.def_label =skill. def_label;
this.locale_desc_default =skill. locale_desc_default;
this.locale_desc_label =skill. locale_desc_label;
this.locale_desc_language =skill. locale_desc_language;
this.lvl_bonus_cat =skill. lvl_bonus_cat;
this.stat1 =skill. stat1;
this.stat2 =skill. stat2;
this.name =skill.locale_desc_label;
this.description =skill. description;
this.Cost=skill.Cost; 
this.Rank=skill.Rank;
this.Rk_Bn=skill.Rk_Bn;
this.Stat_Bn=skill.Stat_Bn;
this.Level_Bn=skill.Level_Bn;
this.Item=skill.Item;
setTotal("start");

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
  
  

  public int getCost() {
    return Cost;
  }

  public void setCost(int cost) {
    Cost = cost;
  }

  public int getRank() {
    return Rank;
  }

  public void setRank(int rank) {
    Rank = rank;
  }

  public int getRk_Bn() {
    return Rk_Bn;
  }

  public void setRk_Bn(int rkBn) {
    Rk_Bn = rkBn;
  }

  public int getStat_Bn() {
    return Stat_Bn;
  }

  public void setStat_Bn(int statBn) {
    Stat_Bn = statBn;
  }
  
  

  public int getLevel_Bn() {
    return Level_Bn;
  }

  public void setLevel_Bn(int levelBn) {
    Level_Bn = levelBn;
  }

  public int getItem() {
    return Item;
  }

  public void setItem(int item) {
    Item = item;
  }

  public int getTotal() {
    return Total;
  }

  public void setTotal(String update) {
//    Total = total;
    Total=getRk_Bn()+getStat_Bn();
  }
  
  

  public String[] getGesamtInfo() {
    
    //Skill                  Cost          Rank          Rk Bn         Stat B        Level Bn.     Item          Total  
    gesamtInfo=new String[8];
    gesamtInfo[0]=this.name;
    gesamtInfo[1]=""+this.Cost;
    gesamtInfo[2]=""+this.Rank;
    gesamtInfo[3]=""+this.Rk_Bn;
    gesamtInfo[4]=""+this.Stat_Bn;
    gesamtInfo[5]=""+this.Level_Bn;
    gesamtInfo[6]=""+this.Item;
    gesamtInfo[7]=""+getTotal();
    return gesamtInfo;
  }

  public void setGesamtInfo(String[] gesamtInfo) {
    this.gesamtInfo = gesamtInfo;
  }
  
//  @Override
//  public Skill clone(){
//      
//      try {
//        return (Skill)clone();
//    } catch (Exception e) {
//        // TODO Auto-generated catch block
//        e.printStackTrace();
//        throw new InternalError();
//    }
////  return null;
//  }

  
  
  @Override
  public String toString() {

    String s = "";

    s += "<br>Skill-Name: " + name + "<br>Level-Bonus-Cat: " + lvl_bonus_cat + "<br>Stat1: " + stat1 + "<br>Stat2: " + stat2
        + "<br>Action-Type: " + action_type + "<br>Calc-Type: " + calc_type + "<br>Local-Desc-Language: "
        + locale_desc_language + "<br>Local-Desc-Default: " + locale_desc_default;

    return s;

  }

}
