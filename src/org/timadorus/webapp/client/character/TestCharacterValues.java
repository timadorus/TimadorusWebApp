package org.timadorus.webapp.client.character;

import java.util.ArrayList;
import java.util.List;

public class TestCharacterValues {
  
  List<Class> classes = new ArrayList<Class>();
  
  List<Race> races = new ArrayList<Race>();
  
  public TestCharacterValues(){
    createTestCharacterValues();
  }
  
  public void createTestCharacterValues(){
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
