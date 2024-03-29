package org.timadorus.webapp.server.rpc.service.character;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.Extent;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import org.timadorus.webapp.beans.CClass;
import org.timadorus.webapp.beans.Character;
import org.timadorus.webapp.beans.Faction;
import org.timadorus.webapp.beans.Race;
import org.timadorus.webapp.beans.User;
import org.timadorus.webapp.server.RegisteredUserList;

import de.harper_hall.keeper.tables.PotStatResolve;

public final class CharacterProvider {
  
  private CharacterProvider() {
    
  }

  private static final PersistenceManagerFactory PMF = RegisteredUserList.PMF;

  public static List<Character> getCharacterList(User user) {
    // Get character objects from db
    List<Character> chars = getCharacters(user);
    
    return chars;
  }
  
  @SuppressWarnings("unchecked")
  public static List<Character> getCharacters(User user) {

    try {
      PersistenceManager pm = PMF.getPersistenceManager();

      Extent<Character> extent = pm.getExtent(Character.class, true);
      Query query = pm.newQuery(extent, "username == '" + user.getUsername() + "'");

      List<Character> ret = new ArrayList<Character>();
      for (Character character : (List<Character>) query.execute()) {
        pm.retrieve(character);
        ret.add(pm.detachCopy(character));
      }
      return ret;
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    return null;
  }
  
  @SuppressWarnings("unchecked")
  public static String deleteCharacter(Character character) {
    PersistenceManager pm = PMF.getPersistenceManager();
    Extent<Character> extent = pm.getExtent(Character.class, true);
    Query query = pm.newQuery(extent, "name == '" + character.getName() + "'");
    List<Character> characters = (List<Character>) query.execute();
    Character found = null;
    if (!characters.isEmpty()) {
      found = characters.get(0);
      pm.deletePersistent(found);
      return "OK";
    }    
    return "FAIL";
  }
  
  public static String createCharacter(Character character) {
    if (character != null) {
      System.out.println("\n\n**********" + character.toString() + "\n" + character.toStringPart2()
                         + "\n****************\n\n");
      return saveCharacterToDB(character);
    } else {
      return "FAILURE";
    }
    
  }

  @SuppressWarnings("unchecked")
  public static String saveCharacterToDB(Character character) {
    PersistenceManager pm = PMF.getPersistenceManager();
    
    // check if character name already exists
    Extent<Character> characterExtent = pm.getExtent(Character.class, true);
    Query characterQuery = pm.newQuery(characterExtent, "name == '" + character.getName() + "'");
    List<Character> chars = (List<Character>) characterQuery.execute();
    if (!chars.isEmpty()) {
      return "FAILURE";
    }
    
    if (character.getFaction() == null) {
      System.out.println("no faction set in character " + character.getName());
      return "FAILURE";
    }
    // check if faction already exists, if yes: use it so it doesn't get saved into the db again
    Extent<Faction> factionExtent = pm.getExtent(Faction.class, true);
    Query factionQuery = pm.newQuery(factionExtent, "name == '" + character.getFaction().getName() + "'");
    List<Faction> factions = (List<Faction>) factionQuery.execute();
    if (!factions.isEmpty()) {
      character.setFaction(factions.get(0));
    }
    
    if (character.getRace() == null) {
      System.out.println("no race set in character " + character.getName());
      return "FAILURE";
    }
    // check if race already exists, if yes: use it so it doesn't get saved into the db again
    Extent<Race> raceExtent = pm.getExtent(Race.class, true);
    Query raceQuery = pm.newQuery(raceExtent, "name == '" + character.getRace().getName() + "'");
    List<Race> races = (List<Race>) raceQuery.execute();
    if (!races.isEmpty()) {
      character.setRace(races.get(0));
    }
    
    if (character.getCharClass() == null) {
      System.out.println("no class set in character " + character.getName());
      return "FAILURE";
    }
    // check if character class already exists, if yes: use it so it doesn't get saved into the db again
    Extent<CClass> cclassExtent = pm.getExtent(CClass.class, true);
    Query cclassQuery = pm.newQuery(cclassExtent, "name == '" + character.getCharClass().getName() + "'");
    List<CClass> cclasses = (List<CClass>) cclassQuery.execute();
    if (!cclasses.isEmpty()) {
      character.setCharClass(cclasses.get(0));
    }
    
    
    try {        
       character.setAllAttrInfo();

       pm.makePersistent(character);
       System.out.println("\n\n******WRITE TO DB FOLLOW CHARACTER-OBJECT **********");
       System.out.println("\n" + character.getAllAttrInfo_Part1() + "\n" + character.getAllAttrInfo_Part2());
       System.out.println("\n*********************************************************\n");
       
      pm.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "SUCCESS";
  }

  @SuppressWarnings("unchecked")
  public Character getCharacterFromDB(String cname) {

    try {
      PersistenceManager pm = PMF.getPersistenceManager();
  
      List<Character> entries = new ArrayList<Character>();
  
      Query query = pm.newQuery("SELECT FROM " + Character.class.getName());
  
      entries = (List<Character>) query.execute();
  
  
      for (Character character : entries) {
        if (character.getName().equals(cname)) {
          System.out.println("\n\n**********Read from DB FOLLOW CHARACTER-OBJECT*********\n" 
                             + character.getAllAttrInfo_Part1() + "\n" + character.getAllAttrInfo_Part2() 
                             + "\n****************\n\n");
          System.out.println("\n*********************************************************\n");
          
          pm.close();
          return character;
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  
    return null;
  }

  public static int makePotStat(int temp) {
    final int maxPotRoll = 100;
    int potRoll = (int) Math.floor((Math.random() * maxPotRoll) + 1);
    System.out.println("makePotStat called");
    return PotStatResolve.getPotStat(temp, potRoll);
  }
}
