package org.timadorus.webapp.server;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.jdo.Extent;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.timadorus.webapp.client.User;
import org.timadorus.webapp.client.character.Character;

/**
 * Includes some user data for login.
 */
public final class RegisteredUserList {

  public static final PersistenceManagerFactory PMF = JDOHelper.getPersistenceManagerFactory("transactions-optional");

  private HashMap<String, User> users = new HashMap<String, User>();
  
  private static RegisteredUserList registeredUserList = null;

  /**
   * Constructor has to stay private -> Singelton-Pattern.
   */
  private RegisteredUserList() { }

  /**
   * Returns an instance of the RegisteredUserList. If it was already requested, a cached object will be returned.
   * (Singelton-Pattern).
   * 
   * @return An instance of the RegisteredUserList 
   */
  public static RegisteredUserList getInstance() {
    if (registeredUserList == null) {
      registeredUserList = new RegisteredUserList();
    }
    return registeredUserList;
  }

  /**
   * This method checks whether a user is active or not.
   * 
   * @param tmpUser The user which shall be checked
   * @return True if the user is active, false otherwise
   */
  public boolean isActive(User tmpUser) {
    if (!users.containsKey(tmpUser.getUsername())) {
      // User im Datastore suchen
      User found = getUser(tmpUser.getUsername());
      if (found.isValid()) {
        users.put(found.getUsername(), found);
      } else {
        System.out.println("Datastore: keine Treffer für '" + tmpUser.getDisplayname() + "'");
        return false;
      }
    } else {
      PersistenceManager pm = PMF.getPersistenceManager();          
      users.put(tmpUser.getUsername(), pm.getObjectById(User.class, users.get(tmpUser.getUsername()).getId()));
      pm.close();
    }
    if (users.get(tmpUser.getUsername()).getActive()) { return true; }
    return false;
  }

  /**
   * This method checks if the supplied username and password is correct.
   * 
   * @param tmpUser The user containing the username and password
   * @return True if username and password was valid, false otherwise
   */
  public Boolean isValid(User tmpUser) {
    if (!users.containsKey(tmpUser.getUsername())) {
      // User im Datastore suchen
      User found = getUser(tmpUser.getUsername());
      if (found.isValid()) {
        users.put(found.getUsername(), found);
      } else {
        System.out.println("Datastore: keine Treffer für '" + tmpUser.getDisplayname() + "'");
        return false;
      }
    }
    if (users.get(tmpUser.getUsername()).getPassword().equals(tmpUser.getPassword())) { return true; }
    return false;
  }

  /**
   * Checks if a username is available or not.
   * 
   * @param username The username which shall be checked
   * @return True if the username is available, false otherwise
   */
  public Boolean usernameAvailable(String username) {
    if (!users.containsKey(username.toLowerCase())) {
      // User im Datastore suchen
      if (!getUser(username).isValid()) { return true; }
    }
    return false;
  }

  /**
   * This method finds a user object by name and returns it.
   * 
   * @param username The username
   * @return If the user was found, it will be returned, otherwise an empty user will be returned
   */
  private User getUser(String username) {
    username = username.toLowerCase();
    PersistenceManager pm = PMF.getPersistenceManager();

    Query query = pm.newQuery(User.class, "username == name");
    query.declareParameters("String name");

    @SuppressWarnings("unchecked")
    Iterator<User> iterator = ((Collection<User>) query.execute(username)).iterator();
    
    if (iterator.hasNext()) {
      User found = iterator.next();
      System.out.println("Datastore: '" + found.getDisplayname() + "' wurde geladen...");
      return found;
    }
    
    pm.close();
    
    return new User(); // "leerer" User; isValid liefert "false";
  }

  /** sent a registration mail.
   * 
   * TODO: all this stuff has to be made configurable in the Admin Pages.
   * 
   * @param user
   */
  private void sendActivationMail(User user) {
    
    Email email = new SimpleEmail();
    email.setHostName("mailgate.informatik.haw-hamburg.de");
    email.setTLS(true);
    // email.setAuthenticator(new DefaultAuthenticator("username", "password"));

    try {
      email.setFrom("root@informatik.haw-hamburg.de");
    } catch (EmailException e) {
      // TODO do some proper handling if the sender mail is invalid.
      e.printStackTrace();
    }
    email.setSubject("Timadorus Registration Mail");
    
    // TODO: get this from some template. Also to be set in the admin panel. And i18n'ized
    try {
      email.setMsg("Hello " + user.getDisplayname() 
                   + ",\n\nPlease register your user " + user.getUsername()
                   + "by visiting the following address: \n\n" 
                   + "http://www.timadorus.org:8080/TimadorusWebApp.html?activationCode=" 
                   + user.getActivationCode()
                   + "\n\nthe Timadorus-Team.");
      
    } catch (EmailException e) {
      // only likely to happen upon a mime error
      e.printStackTrace();
    }
    
    try {
      email.addTo(user.getEmail());
    } catch (EmailException e) {
      // TODO: handle an invalid to address.
      e.printStackTrace();
    }
    
    try {
      email.send();
    } catch (EmailException e) {
      // TODO handle a failed mail. 
      e.printStackTrace();
    }

  }
  

  /**
   * This method adds a new user to the user list and to the database.
   * 
   * @param user The new user, which shall be added
   * @return ActivationCode as String on success, null otherwise
   */
  public String addUser(User user) {
    String activationCode = Util.generateActivationCode(user);
    
    if (usernameAvailable(user.getUsername()) && !activationCode.equals("")) {
      PersistenceManager pm = PMF.getPersistenceManager();
      user.setActive(false);
      user.setActivationCode(activationCode);
      pm.makePersistent(user);
      pm.close();
      System.out.println("Datastore: '" + user.getDisplayname() + "' hinzugefügt mit ActivationCode: "
                         + user.getActivationCode());
      users.put(user.getUsername(), user);
      sendActivationMail(user);
      
      return user.getActivationCode();
    }
    return null;
  }
  
  @SuppressWarnings("unchecked")
  public Boolean deleteUser(User user) {
    PersistenceManager pm = PMF.getPersistenceManager();    
    Extent<User> extent = pm.getExtent(User.class, true);
    Query query = pm.newQuery(extent, "username == name");
    query.declareParameters("String name");    
    User found = null;
    Iterator<User> iterator = ((Collection<User>) query.execute(user.getUsername())).iterator();
    if (iterator.hasNext()) {
      found = iterator.next();      
    }
    if (found == null) {
      System.out.println("User " + found + " not found in database!");
      return false;
    } else {
      Extent<Character> extent2 = pm.getExtent(Character.class, true);
      Query query2 = pm.newQuery(extent2, "username == '" + user.getUsername() + "'");
      System.out.println("Username: " + "'" + user.getUsername() + "'");
      for (Character character : (List<Character>) query2.execute()) {
        System.out.println("Character " + character.getName() + " found!");
        pm.retrieve(character);
        pm.deletePersistent(character);
      }        
      pm.deletePersistent(found);
      users.remove(user.getUsername());
      // TODO: Delete user's characters    
      System.out.println(found.getDisplayname() + " has been deleted from database!");
      pm.close();
      return true;
    }    
  }

  public void addC(User u) {

    PersistenceManager pm = PMF.getPersistenceManager();

    try {
       Character c = Character.getInstance(); //
       c.setName("MyCharacter1");
       c.setAllAttrInfo();      

       pm.makePersistent(c);
      pm.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void print() {
    System.out.println("Derzeit geladene User:");
    for (String user : users.keySet()) {
      System.out.println(user);
    }
  }

  @SuppressWarnings("unchecked")
  public Character getCharObjectFromAppEngine(String cname) {
//    cname = cname.toLowerCase();

    try {
      PersistenceManager pm = PMF.getPersistenceManager();

      List<Character> entries = new ArrayList<Character>();

      Query query = pm.newQuery("SELECT FROM " + Character.class.getName());

      entries = (List<Character>) query.execute();

//      pm.close();

      for (Character character : entries) {
        if (character.getName().equals(cname)) {
          System.out.println("YES ! Character-Object: \"" + cname + "\" saved to Datastore and read from there !");
          pm.close();
          return character;
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    return null;
  }

  @SuppressWarnings("unchecked")
  public User getC(String cname) {
      cname = cname.toLowerCase();
  
      try {
        PersistenceManager pm = PMF.getPersistenceManager();
  
        // **
        List<User> entries = new ArrayList<User>();
  
        Query query = pm.newQuery("SELECT FROM " + User.class.getName());
  
        entries = (List<User>) query.execute();
  
  //      pm.close();
  
        for (User user : entries) {
          if (user.getNachname().equals(cname)) {
            System.out.println("YEAH! " + cname);
            pm.close();
            return user;
          }
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
  
      return null;
    }
}
