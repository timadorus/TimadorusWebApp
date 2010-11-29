package org.timadorus.webapp.client.character;

import java.util.ArrayList;
import java.util.List;

//This class serves dummy-Values for create-Character-progress
public class TestCharacterValues {

  List<CClass> classes = new ArrayList<CClass>();

  List<Race> races = new ArrayList<Race>();

  List<Faction> factions = new ArrayList<Faction>();

  List<Skill> skills = new ArrayList<Skill>();
  
  List<Skill> backupSkills = new ArrayList<Skill>();
  
  List<Skill> skillsLevel1 = new ArrayList<Skill>();
  
  List<Skill> backupSkillsLevel1 = new ArrayList<Skill>();

  public TestCharacterValues() {
    createTestCharacterValues();
  }

  public void  createTestCharacterValues() {
    // create testfactions
    Faction fac1 = new Faction();
    fac1.setName("Skater");
    fac1.setDescription("Die Kinder der Stadt sind deine Freunde");
    factions.add(fac1);

    Faction fac2 = new Faction();
    fac2.setName("Börsianer");
    fac2.setDescription("Geld, geld, geld, geld");
    factions.add(fac2);

    Faction fac3 = new Faction();
    fac3.setName("Grüne Alternative Liste");
    fac3.setDescription("Ihr wisst was das bedeutet");
    factions.add(fac3);

    Faction fac4 = new Faction();
    fac4.setName("Öffentlicher Dienst");
    fac4.setDescription("Alle Formulare erledigen sich wie von selbst mit den richtigen Freunden");
    factions.add(fac4);

    Faction fac5 = new Faction();
    fac5.setName("Pharmaindustrie");
    fac5.setDescription("Warum in die Apotheke, wenns an jeder Ecke Medis gibt");
    factions.add(fac5);

    Faction fac6 = new Faction();
    fac6.setName("Nooobs");
    fac6.setDescription("Oft unterschätzt, aber immer nützliche Partner");
    factions.add(fac6);

    Faction fac7 = new Faction();
    fac7.setName("VIP");
    fac7.setDescription("Ohhhh jeaaaah");
    factions.add(fac7);

    Faction fac8 = new Faction();
    fac8.setName("Juntas");
    fac8.setDescription("Hier wird gehängt, da wird verdient.");
    factions.add(fac8);

    // create testclasses
    CClass class1 = new CClass();
    class1.setName("Gärtner");
    class1
        .setDescription("Der Garten ist dein Leben. Keine Straßen, keine Autos, kein Lärm. Nur Bäume und Gräser und "
                        + "so.");
    class1.addFaction(fac8);
    class1.addFaction(fac7);
    class1.addFaction(fac6);
    class1.addFaction(fac4);
    classes.add(class1);

    CClass class2 = new CClass();
    class2.setName("Hühnerdieb");
    class2
        .setDescription("Hühnerdiebe sind enorm schnelle Viecher, die vorwiegen in Hühnerstellen rumhängen oder an " 
                        + "Grillimbissen");
    class2.addFaction(fac1);
    class2.addFaction(fac2);
    class2.addFaction(fac4);
    classes.add(class2);

    CClass class3 = new CClass();
    class3.setName("Söldner");
    class3.setDescription("Geld, Blut, das wars");
    class3.addFaction(fac7);
    class3.addFaction(fac6);
    class3.addFaction(fac4);
    classes.add(class3);

    CClass class4 = new CClass();
    class4.setName("Abmahnungsanwalt");
    class4.setDescription("ROFLROFLPWNED");
    class4.addFaction(fac7);
    class4.addFaction(fac6);
    class4.addFaction(fac4);
    classes.add(class4);

    CClass class5 = new CClass();
    class5.setName("Pilot");
    class5.setDescription("Über den wolken, blablubb.");
    class5.addFaction(fac7);
    class5.addFaction(fac6);
    class5.addFaction(fac4);
    classes.add(class5);

    CClass class6 = new CClass();
    class6.setName("Informatiker");
    class6.setDescription("Du schreibst Stundenlang testtexte für ne webapp.");
    class6.addFaction(fac7);
    class6.addFaction(fac6);
    class6.addFaction(fac4);
    classes.add(class6);

    CClass class7 = new CClass();
    class7.setName("Drücker");
    class7.setDescription("Ja, du hörst richtig. Und nun ab zur nächsten Tür - Geld ranschaffen!");
    class7.addFaction(fac7);
    class7.addFaction(fac6);
    class7.addFaction(fac4);
    classes.add(class7);

    // M**
    CClass c1 = new CClass(
                           "Capitalist class",
                           "Top-level executives, high-rung politicians, heirs. Ivy League education common. "
                           + "Source: http://en.wikipedia.org/wiki/Social_class");
    c1.addFaction(fac7);
    c1.addFaction(fac4);

    CClass c2 = new CClass(
                           "Upper middle class",
                           "Highly educated (often with graduate degrees), most commonly salaried, professionals and "
                           + "middle management with large work autonomy. "
                           + "Source: http://en.wikipedia.org/wiki/Social_class");
    c2.addFaction(fac4);
    c2.addFaction(fac7);
    c2.addFaction(fac5);
    CClass c3 = new CClass(
                           "Working class",
                           "Clerical and most blue collar workers whose work is highly routinized. Standard of living "
                           + "varies depending on number of income earners, but is commonly just adequate. High school "
                           + "education. Source: http://en.wikipedia.org/wiki/Social_class");
    c3.addFaction(fac4);
    c3.addFaction(fac2);
    c3.addFaction(fac1);
    CClass c4 = new CClass(
                           "Underclass",
                           "Those with limited or no participation in the labor force. Reliant on government transfers."
                           + " Some high school education. Source: http://en.wikipedia.org/wiki/Social_class");
    c4.addFaction(fac4);
    c4.addFaction(fac2);
    c4.addFaction(fac1);
    c4.addFaction(fac5);
    c4.addFaction(fac6);
    c4.addFaction(fac3);
    classes.add(c1);
    classes.add(c2);
    classes.add(c3);
    classes.add(c4);

    // **

    // create testraces

    Race race1 = new Race();
    race1.setName("Witzbold");
    race1.setdescription("Der Witzbold ist ein Witzbolt");
    race1.addClass(class1);
    race1.addClass(class3);
    race1.addFaction(fac3);
    race1.addFaction(fac5);
    race1.addFaction(fac7);
    race1.addFaction(fac4);
    races.add(race1);

    Race race2 = new Race();
    race2.setName("Laufbursche");
    race2.setdescription("Er läuft und läuft und läuft");
    race2.addClass(class2);
    race2.addClass(class4);
    race2.addFaction(fac4);
    race2.addFaction(fac1);
    race2.addFaction(fac2);
    race2.addFaction(fac3);
    races.add(race2);

    Race race3 = new Race();
    race3.setName("Mächtiger Winzer");
    race3.setdescription("Keiner macht so guten Wein wie er");
    race3.addClass(class1);
    race3.addClass(class4);
    race3.addClass(class5);
    race3.addFaction(fac6);
    race3.addFaction(fac7);
    race3.addFaction(fac8);
    race3.addFaction(fac4);
    races.add(race3);

    Race race4 = new Race();
    race4.setName("Terrorgnom");
    race4.setdescription("Für den kleinen Hunger zwischendurch");
    race4.addClass(class1);
    race4.addClass(class2);
    race4.addClass(class4);
    race4.addClass(class6);
    race4.addFaction(fac6);
    race4.addFaction(fac7);
    race4.addFaction(fac8);
    race4.addFaction(fac4);
    races.add(race4);

    Race race5 = new Race();
    race5.setName("Lausbub");
    race5.setdescription("Ja nu, irgendwas muss hier ja stehen");
    race5.addClass(class1);
    race5.addClass(class3);
    race5.addClass(class5);
    race5.addClass(class6);
    race5.addFaction(fac6);
    race5.addFaction(fac7);
    race5.addFaction(fac8);
    race5.addFaction(fac5);
    race5.addFaction(fac1);
    race5.addFaction(fac2);
    race5.addFaction(fac3);
    race5.addFaction(fac4);
    races.add(race5);

    // M**
    Race r1 = new Race(
                       new Long(001),
                       "Race-Almas",
                       "The Almas, Mongolian for \"wild man,\" is a purported hominid cryptozoological species reputed"
                       + " to inhabit the Caucasus and Pamir Mountains of central Asia, and the Altai Mountains of "
                       + "southern Mongolia. Source: http://en.wikipedia.org/wiki/Almas_%28cryptozoology%29");
    r1.addClass(c1);
    r1.addFaction(fac7);
    r1.addFaction(fac6);

    Race r2 = new Race(
                       new Long(002),
                       "Race-Amomongo",
                       "The Amomongo is a creature of Philippine mythology described as hairy, man-sized and ape-like "
                       + "with long nails. Source: http://en.wikipedia.org/wiki/Amomongo");
    r2.addClass(c2);
    r2.addFaction(fac5);
    r2.addFaction(fac3);

    Race r3 = new Race(
                       new Long(003),
                       "Race-Chuchunya",
                       "Chuchunya is a hominid cryptid rumoured to exist in Siberia, Russia. It has been described as "
                       + "six to seven feet tall and covered with dark hair. Source: http://en.wikipedia.org/wiki/Tjutjuna");

    r3.addClass(c3);
    r3.addFaction(fac1);
    r3.addFaction(fac8);

    Race r4 = new Race(
                       new Long(004),
                       "Race-Yeti",
                       "The Yeti or Abominable Snowman is a creature and an ape-like cryptid said to inhabit the "
                       + "Himalayan region of Nepal and Tibet. The names Yeti and Meh-Teh are commonly used by the "
                       + "people indigenous to the region,[1] and are part of their history and mythology. Stories of "
                       + "the Yeti first emerged as a facet of Western popular culture in the 19th century. "
                       + "Source: http://en.wikipedia.org/wiki/Yeti");

    r4.addClass(c4);
    r4.addFaction(fac8);
    r4.addFaction(fac3);

    races.add(r1);
    races.add(r2);
    races.add(r3);
    races.add(r4);

    // ***

    // <skill-def label="Acro" lvl-bonus-cat="ATH" stat1="AG" stat2="QU" action-type="MM" calc-type="Std"> <locale-desc
    // label="Acrobatics" language="en-US" default="true"> </locale-desc> <locale-desc label="Akrobatik"
    // language="de-DE"
    // default="false"> </locale-desc> </skill-def>
    

    Skill sk1 = new Skill(
                          "Acro",
                          "ATH",
                          "AG",
                          "QU",
                          "MM",
                          "Std",
                          "Acrobatics",
                          "en-US",
                          true,
                          "Acrobatic traditions are found in many Western cultures as well. Minoan art from circa 2000 "
                          + "BC contains depictions of acrobatic feats on the backs of bulls, which may have been a "
                          + "religious ritual.[3] The noble court displays of the European Middle Ages would often "
                          + "include acrobatic performances along with song, juggling and other activities. "
                          + "\nSource: http://en.wikipedia.org/wiki/Acrobatics",
                          5, 3, 6, 9, 10, 40);
    // int Cost, int Rank, int Rk_Bn, int Stat_Bn, int Level_Bn, int Item, int Total) {
    /*
     * <skill-def label="Act" lvl-bonus-cat="SUB" stat1="PR" stat2="EM" action-type="SA" calc-type="Std"> <locale-desc
     * label="Acting" language="en-US" default="true"> </locale-desc> <locale-desc label="Schauspielern"
     * language="de-DE" default="false"> </locale-desc> </skill-def>
     */

   
      
    
    Skill sk2 = new Skill(
                          "Act",
                          "SUB",
                          "PR",
                          "EM",
                          "SA",
                          "Std",
                          "Acting",
                          "en-US",
                          true,
                          "Acting is the work of an actor or actress, which is a person in theatre or any other "
                          + "storytelling medium who tells the story by portraying a character and, usually, speaking "
                          +	"or singing the written text or play. \nSource: http://en.wikipedia.org/wiki/Acting",
                          9, 6, 4, 10, 2, 40);

    Skill sk3 = new Skill(
                          "HRi",
                          "SUB",
                          "PR",
                          "EM",
                          "SA",
                          "Std",
                          "Riding Animals",
                          "en-US",
                          true,
                          "They include equines such as horses, ponies, donkeys,\n and mules; elephants; "
                          + "ostriches[citation needed];\nyaks; and camels. Dromedaries (with one hump) live in arid "
                          + "areas of Australia, \nNorth Africa and the Middle East; the far rarer Bactrian camel "
                          + "\ninhabits central and East Asia; both are used for \ntransportation and haulage."
                          +	"\n\nSome mythical creatures \nare believed to act as divine mounts, such as \ngaruda "
                          + "in Hinduism and the winged horse Pegasus in \nGreek mythology. "
                          +	"Source: http://en.wikipedia.org/wiki/Riding_animal#Riding_animals_or_mounts ",
                          11, 2, 6, 10, 3, 30);

      
    
    skills.add(new Skill(sk1));
    skills.add(new Skill(sk2));
    skills.add(new Skill(sk3));
    
    //clone() had not worked, this is why i choose this way of manual copy
    backupSkills.add(new Skill(sk1));
    backupSkills.add(new Skill(sk2));
    backupSkills.add(new Skill(sk3));

//  backupSkills.add(sk1.clone());
//  backupSkills.add(sk2.clone());
//  backupSkills.add(sk3.clone());
    
    
    //Skills für Level=1
    Skill skl11 = new Skill(
                          "SMan",
                          "SUB",
                          "PR",
                          "EM",
                          "SA",
                          "Std",
                          "Swordsmanship",
                          "en-US",
                          true,
                          "Swordsmanship refers to the skills of a swordsman, a person versed in the art of the sword. "
                          + "The term is modern, and as such was mainly used to refer to smallsword fencing, but by "
                          + "extension it can also be applied to any martial art involving the use of a sword. "
                          +	"The formation of the English word \"swordsman\" is parallel to the Latin word "
                          +	"gladiator[1], a term for the professional fighters who fought against each other and a "
                          + "variety of other foes for the entertainment of spectators in the Roman Empire. "
                          +	"The word gladiator itself comes from the Latin word gladius, meaning \"sword\"[1]. "
                          +	"Source: http://en.wikipedia.org/wiki/Swordsmanship",
                          12, 8, 4, 17, 22, 40);
    
    
    Skill skl12 = new Skill(
                            "RSC",
                            "SUB",
                            "PR",
                            "EM",
                            "SA",
                            "Std",
                            "Rüstungsschmiede",
                            "de",
                            true,
                            "Rüstungsschmiede sind NSC, die für die Spieler Rüstungen herstellen. Sie können in den "
                            + "meisten Städten gefunden werden, wobei es aber auch einge gibt, die sich in Instanzen "
                            + "selbst aufhalten. Sie alle haben gemein, dass sie nur im Austausch gegen Gold und "
                            + "Handwerksmaterialien arbeiten. Dabei steigen die Kosten je nach Art der Rüstung stark "
                            + "an, was zum Teil auch von den Verwendeten Materialien abhängig ist. "
                            + "Source: http://www.guildwiki.de/wiki/R%C3%BCstungsschmied",
                            20, 18, 14, 4, 12, 20);
    

    Skill skl13 = new Skill(
                            "RSC",
                            "SUB",
                            "PR",
                            "EM",
                            "SA",
                            "Std",
                            "Zauberstabskämpfer",
                            "de",
                            true,
                            "Ein Zauberstabskämpfer ist ein Kämpfer, der als Waffe Zauberstäbe einsetzt u. mit "
                            + "deren Magie umgehen kann.\nDer Zauberstab dient dem Magier im Ritual, die speziellen "
                            + "magischen Energien in die gewünschte Richtung zu lenken, z.b wird damit auf ein Foto "
                            + "der Person gezeigt die verzaubert werden soll. Außerdem symbolisiert der Zauberstab "
                            + "den Willen des Zaubernden. Source: http://de.wikipedia.org/wiki/Zauberstab",
                            18, 22, 17, 10, 13, 30);
    
    skillsLevel1.add(new Skill(skl11));
    skillsLevel1.add(new Skill(skl12));
    skillsLevel1.add(new Skill(skl13));
    
    //clone() had not worked, this is why i choose this way of manual copy
    backupSkillsLevel1.add(new Skill(skl11));
    backupSkillsLevel1.add(new Skill(skl12));
    backupSkillsLevel1.add(new Skill(skl13));
   
      
    }


  public List<Faction> getFactions() {
    return factions;
  }

  public void setFactions(List<Faction> factionsIn) {
    this.factions = factionsIn;
  }

  public List<CClass> getClasses() {
    return classes;
  }

  public void setClasses(List<CClass> classesIn) {
    this.classes = classesIn;
  }

  public List<Race> getRaces() {
    return races;
  }

  public void setRaces(List<Race> racesIn) {
    this.races = racesIn;
  }

  public TestCharacterValues getTestCharacterValues() {
    return this;
  }

  public List<Skill> getSkills() {
    return skills;
  }

  public void setSkills(List<Skill> skillsIn) {
    this.skills = skillsIn;
  }

  public List<Skill> getBackupSkills() {
    return backupSkills;
  }

  public void setBackupSkills(List<Skill> backupSkillsIn) {
    this.backupSkills = backupSkillsIn;
  }

  public List<Skill> getSkillsLevel1() {
    return skillsLevel1;
  }

  public void setSkillsLevel1(List<Skill> skillsLevel_1) {
    skillsLevel1 = skillsLevel_1;
  }

  public List<Skill> getBackupSkills_Level1() {
    return backupSkillsLevel1;
  }

  public void setBackupSkills_Level1(List<Skill> backupSkillsLevel1) {
    this.backupSkillsLevel1 = backupSkillsLevel1;
  }
  
  
  
  
  
 

}
