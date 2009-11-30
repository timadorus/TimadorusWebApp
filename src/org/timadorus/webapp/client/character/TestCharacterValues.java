package org.timadorus.webapp.client.character;

import java.util.ArrayList;
import java.util.List;

public class TestCharacterValues {
  
  List<Class> classes = new ArrayList<Class>();
  
  List<Race> races = new ArrayList<Race>();
  
  List<Faction> factions = new ArrayList<Faction>();
  
  public TestCharacterValues(){
    createTestCharacterValues();
  }
  
  public void createTestCharacterValues(){
    //create testfactions
    Faction fac1 = new Faction();
    fac1.setName("Skater");
    fac1.setDescription("Die Kinder der Stadt sind deine Freunde");
    factions.add(fac1);
    
    Faction fac2 = new Faction();
    fac2.setName("Börsianer");
    fac2.setDescription("Geld, geld, geld, geld");
    factions.add(fac2);
    
    Faction fac3 = new Faction();
    fac3.setName("Grüne Alternatithis.testValues = new TestCharacterValues();ve Liste");
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
    
    //create testclasses   
    Class class1 = new Class();
    class1.setName("Gärtner");
    class1.setDescription("Der Garten ist dein Leben. Keine Straßen, keine Autos, kein Lärm. Nur Bäume und Gräser und so.");
    classes.add(class1);
    
    Class class2 = new Class();
    class2.setName("Hühnerdieb");
    class2.setDescription("Hühnerdiebe sind enorm schnelle Viecher, die vorwiegen in Hühnerstellen rumhängen oder an Grillimbissen");
    classes.add(class2);
    
    Class class3 = new Class();
    class3.setName("Söldner");
    class3.setDescription("Geld, Blut, das wars");
    classes.add(class3);
    
    Class class4 = new Class();
    class4.setName("Abmahnungsanwalt");
    class4.setDescription("ROFLROFLPWNED");
    classes.add(class4);
        
    Class class5 = new Class();
    class5.setName("Pilot");
    class5.setDescription("Über den wolken, blablubb.");
    classes.add(class5);
    
    Class class6 = new Class();
    class6.setName("Informatiker");
    class6.setDescription("Du schreibst Stundenlang testtexte für ne webapp.");
    classes.add(class6);
    
    Class class7 = new Class();
    class7.setName("Drücker");
    class7.setDescription("Ja, du hörst richtig. Und nun ab zur nächsten Tür - Geld ranschaffen!");
    classes.add(class7);
    
    
  //M**
    Class c1 = new Class(
                         "Capitalist class",
                         "Top-level executives, high-rung politicians, heirs. Ivy League education common. Source: http://en.wikipedia.org/wiki/Social_class");

    Class c2 = new Class(
                         "Upper middle class",
                         "Highly educated (often with graduate degrees), most commonly salaried, professionals and middle management with large work autonomy. Source: http://en.wikipedia.org/wiki/Social_class");

    Class c3 = new Class(
                         "Working class",
                         "Clerical and most blue collar workers whose work is highly routinized. Standard of living varies depending on number of income earners, but is commonly just adequate. High school education. Source: http://en.wikipedia.org/wiki/Social_class");

    Class c4 = new Class(
                         "Underclass",
                         "Those with limited or no participation in the labor force. Reliant on government transfers. Some high school education. Source: http://en.wikipedia.org/wiki/Social_class");

    classes.add(c1);
    classes.add(c2);
    classes.add(c3);
    classes.add(c4);
    
    //**
    
    
    //create testraces
    
    Race race1 = new Race();
    race1.setName("Witzbold");
    race1.setdescription("Der Witzbold ist ein Witzbolt");
    race1.addClass(class1);
    race1.addClass(class3);
    races.add(race1);
    
    Race race2 = new Race();
    race2.setName("Laufbursche");
    race2.setdescription("Er läuft und läuft und läuft");
    race2.addClass(class2);
    race2.addClass(class4);
    races.add(race2);
    
    Race race3 = new Race();
    race3.setName("Mächtiger Winzer");
    race3.setdescription("Keiner macht so guten Wein wie er");
    race3.addClass(class1);
    race3.addClass(class4);
    race3.addClass(class5);
    races.add(race3);
    
    Race race4 = new Race();
    race4.setName("Terrorgnom");
    race4.setdescription("Für den kleinen Hunger zwischendurch");
    race4.addClass(class1);
    race4.addClass(class2);
    race4.addClass(class4);
    race4.addClass(class6);
    races.add(race4);
    
    Race race5 = new Race();
    race5.setName("Lausbub");
    race5.setdescription("Ja nu, irgendwas muss hier ja stehen");
    race5.addClass(class1);
    race5.addClass(class3);
    race5.addClass(class5);
    race5.addClass(class6);
    races.add(race5);
    
  //M**
    Race r1 = new Race(
                       new Long(001),
                       "Race-Almas",
                       "The Almas, Mongolian for \"wild man,\" is a purported hominid cryptozoological species reputed to inhabit the Caucasus and Pamir Mountains of central Asia, and the Altai Mountains of southern Mongolia. Source: http://en.wikipedia.org/wiki/Almas_%28cryptozoology%29");
    r1.addClass(c1);
    
    Race r2 = new Race(
                       new Long(002),
                       "Race-Amomongo",
                       "The Amomongo is a creature of Philippine mythology described as hairy, man-sized and ape-like with long nails. Source: http://en.wikipedia.org/wiki/Amomongo");
    r2.addClass(c2);
    

    Race r3 = new Race(
                       new Long(003),
                       "Race-Chuchunya",
                       "Chuchunya is a hominid cryptid rumoured to exist in Siberia, Russia. It has been described as six to seven feet tall and covered with dark hair. Source: http://en.wikipedia.org/wiki/Tjutjuna");

    r3.addClass(c3);
    
    
    Race r4 = new Race(
                       new Long(004),
                       "Race-Yeti",
                       "The Yeti or Abominable Snowman is a creature and an ape-like cryptid said to inhabit the Himalayan region of Nepal and Tibet. The names Yeti and Meh-Teh are commonly used by the people indigenous to the region,[1] and are part of their history and mythology. Stories of the Yeti first emerged as a facet of Western popular culture in the 19th century. Source: http://en.wikipedia.org/wiki/Yeti");

    r4.addClass(c4);
    
    races.add(r1);
    races.add(r2);
    races.add(r3);
    races.add(r4);
    
    //***
    
    
    
  }

  public List<Faction> getFactions() {
    return factions;
  }

  public void setFactions(List<Faction> factions) {
    this.factions = factions;
  }

  public List<Class> getClasses() {
    return classes;
  }

  public void setClasses(List<Class> classes) {
    this.classes = classes;
  }

  public List<Race> getRaces() {
    return races;
  }

  public void setRaces(List<Race> races) {
    this.races = races;
  }
  
  public TestCharacterValues getTestCharacterValues(){
    return this;
  }

}
