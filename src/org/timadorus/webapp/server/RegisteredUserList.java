package org.timadorus.webapp.server;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.jdo.*;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import org.timadorus.webapp.client.User;
import org.timadorus.webapp.client.character.Character;

import com.google.appengine.api.datastore.KeyFactory;

public class RegisteredUserList {

  private static final PersistenceManagerFactory PMF = JDOHelper.getPersistenceManagerFactory("transactions-optional");

  private HashMap<String, User> users = new HashMap<String, User>();

  private static RegisteredUserList userList = null;

  /**
   * Konstruktor muss PRIVATE bleiben -> Singelton-Pattern
   */
  private RegisteredUserList() {
    addUser(new User("test", "test", "test", "test", "test", "test"));
    User test2 = new User("test2", "test2", "test2", "test2", "test2", "test2");
    test2.setActive(true);
    addUser(test2);
  }

  /**
   * Singelton-Pattern
   * 
   * @return Single-Instance
   */
  public static RegisteredUserList getInstance() {
    if (userList == null) {
      userList = new RegisteredUserList();
    }
    return userList;
  }

  /**
   * Diese Methode überprüft, ob der User aktiviert ist oder nicht.
   * 
   * @param tmpUser
   * @return true, wenn der User aktiviert ist, false sonst
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
    }
    if (users.get(tmpUser.getUsername()).getActive()) { return true; }
    return false;
  }

  /**
   * Diese Methode überprüft Username und Passwort des übergebenen Users.
   * 
   * @param tmpUser
   * @return true, wenn Username und Passwort gültig, false sonst
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
   * Diese Methode prüft, ob ein Username bereits vergeben ist.
   * 
   * @param username
   * @return false, wenn Username bereits vergeben, true sonst
   */
  public Boolean usernameAvailable(String username) {
    if (!users.containsKey(username.toLowerCase())) {
      // User im Datastore suchen
      if (!getUser(username).isValid()) { return true; }
    }
    return false;
  }

  /**
   * Die Methode versucht einen User anhand eines Usernamens aus dem Datastore zu laden. Ist kein User mit dem
   * angegebenen Namen vorhanden, wird ein "leerer" User zurückgegeben. Somit kann mit Methode "isValid" des
   * User-Objekts geprüft werden, ob der User vorhanden ist. Somit entfällt die Prüfung auf "null".
   * 
   * @param username
   * @return User aus Datastore, wenn vorhanden; "leeren" User sonst
   */
  @SuppressWarnings("unchecked")
  private User getUser(String username) {
    username = username.toLowerCase();
    PersistenceManager pm = PMF.getPersistenceManager();
    Extent<User> extent = pm.getExtent(User.class, true);

    Query query = pm.newQuery(extent, "username == name");
    query.declareParameters("String name");

    Iterator<User> iterator = ((Collection<User>) query.execute(username)).iterator();
    if (iterator.hasNext()) {
      User found = iterator.next();
      System.out.println("Datastore: '" + found.getDisplayname() + "' wurde geladen...");
      return found;
    }
    return new User();// "leerer" User; isValid liefert "false";
  }

  /**
   * Die Methode fügt einen User zu der Liste der registrierten User hinzu. Dabei wird der Username zwecks verbesserter
   * Vergleichsmöglichkeit als "lowerCase" gespeichert.
   * 
   * @param user
   * @return false, wenn Username bereits vergeben, true sonst
   */
  public Boolean addUser(User user) {
    addC(user);
    if (usernameAvailable(user.getUsername())) {
      User u=new User();
      
      PersistenceManager pm = PMF.getPersistenceManager();
      pm = PMF.getPersistenceManager();
      user.setActive(true);
      pm.makePersistent(user);
      pm.makePersistent(u);
      System.out.println("Datastore: '" + user.getDisplayname() + "' hinzugefügt...");
      users.put(user.getUsername(), user);
      return true;
    }
    return false;
  }

  public void addC(User u) {

    // ContactInfo co1=new ContactInfo();
    // co1.setStreetAddress("MyStreet!!!");
    // WriteEmpl e1=new WriteEmpl();
    // Key key = KeyFactory.createKey(WriteEmpl.class.getSimpleName(), 43234);
    //
    // e1.setMail("A1@1.de");
    // e1.setContactInfo(co1);
    //    
    // WriteEmpl e2= new WriteEmpl();
    // e2.setMail("A2@2.de");

    PersistenceManager pm = PMF.getPersistenceManager();
    // pm.makePersistent(e1);
    // pm.makePersistent(e2);
    //    
    // WriteEmpl e = pm.getObjectById(WriteEmpl.class, key);
    //    
    // System.out.println(e.getMail()); Character()

    try {
       Character c = Character.getInstance();
       c.setName("MyCharacter1");
       User us=new User("testx", "testx", "testx", "testx", "testx", "testx");
      pm.makePersistent(us);
      

       pm.makePersistent(c);
      pm.close();
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    getC("testx");

  }

  public void print() {
    System.out.println("Derzeit geladene User:");
    for (String user : users.keySet()) {
      System.out.println(user);
    }
  }

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
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return null;
  }
}
