package org.timadorus.webapp.server;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.jdo.Extent;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import org.timadorus.webapp.beans.Character;
import org.timadorus.webapp.beans.User;

/**
 * Includes some user data for login.
 */
public final class RegisteredUserList {

  // MD5
  private static final String MD5_ALGORITHM = "MD5";
  

  private final PersistenceManagerFactory pmf; 

  private HashMap<String, User> users = new HashMap<String, User>();

  /**
   * @param pmf the factory to use
   */
  public RegisteredUserList(PersistenceManagerFactory pmf) {
    this.pmf = pmf;
  }

  /**
   * This method checks whether a user is active or not.
   * 
   * @param tmpUser
   *          The user which shall be checked
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
      PersistenceManager pm = pmf.getPersistenceManager();
      users.put(tmpUser.getUsername(), pm.getObjectById(User.class, users.get(tmpUser.getUsername()).getId()));
      pm.close();
    }
    if (users.get(tmpUser.getUsername()).getActive()) { return true; }
    return false;
  }

  /**
   * This method checks if the supplied username and password is correct.
   * 
   * @param tmpUser
   *          The user containing the username and password
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
   * @param username
   *          The username which shall be checked
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
   * @param username
   *          The username
   * @return If the user was found, it will be returned, otherwise an empty user will be returned
   */
  private User getUser(String username) {
    username = username.toLowerCase();
    PersistenceManager pm = pmf.getPersistenceManager();

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

  /**
   * sent a registration mail.
   * 
   * TODO: all this stuff has to be made configurable in the Admin Pages.
   * 
   * @param user the user to send to
   */
  private void sendActivationMail(User user) {

    new EmailSender(user);

  }

  /**
   * This method adds a new user to the user list and to the database.
   * 
   * @param user
   *          The new user, which shall be added
   * @return ActivationCode as String on success, null otherwise
   */
  public String addUser(User user) {
    String activationCode = generateActivationCode(user);

    if (usernameAvailable(user.getUsername()) && !activationCode.equals("")) {
      PersistenceManager pm = pmf.getPersistenceManager();
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

  /**
   * Generates an activation code for a supplied user, including the current system time stamp.
   * 
   * @param user The user object
   * @return A string containing an 32byte MD5-Hash as activation code for the supplied user object.
   */
  public static String generateActivationCode(User user) {
    String pass = user.getVorname() + user.getNachname() + user.getEmail() + user.getPassword() 
                  + user.getGeburtstag() + System.currentTimeMillis();
    
    try {
      MessageDigest m = MessageDigest.getInstance(MD5_ALGORITHM);
      byte[] data = pass.getBytes(); 
      m.update(data, 0, data.length);
      BigInteger i = new BigInteger(1, m.digest());
      return String.format("%1$032X", i);
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
      return "";
    }
  }

  /** delete a user.
   * 
   * @param user the user to delete
   * @return true if the user was deleted successfully, false otherwise
   */
  @SuppressWarnings("unchecked")
  public Boolean deleteUser(User user) {
    PersistenceManager pm = pmf.getPersistenceManager();
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

  /**
   * 
   * @param u user
   */
  public void addC(User u) {

    PersistenceManager pm = pmf.getPersistenceManager();

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

  /** print the curren users for debug purposes.
   * 
   */
  public void print() {
    System.out.println("Derzeit geladene User:");
    for (String user : users.keySet()) {
      System.out.println(user);
    }
  }

  /**
   * 
   * @param cname name of the character
   * @return the character bean or null
   */
  @SuppressWarnings("unchecked")
  public Character getCharObjectFromAppEngine(String cname) {
    // cname = cname.toLowerCase();

    try {
      PersistenceManager pm = pmf.getPersistenceManager();

      List<Character> entries = new ArrayList<Character>();

      Query query = pm.newQuery("SELECT FROM " + Character.class.getName());

      entries = (List<Character>) query.execute();

      // pm.close();

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

  /**
   * 
   * @param userName name of the user 
   * @return user object or null
   */
  @SuppressWarnings("unchecked")
  public User getC(String userName) {
    userName = userName.toLowerCase();

    try {
      PersistenceManager pm = pmf.getPersistenceManager();

      // **
      List<User> entries = new ArrayList<User>();

      Query query = pm.newQuery("SELECT FROM " + User.class.getName());

      entries = (List<User>) query.execute();

      // pm.close();

      for (User user : entries) {
        if (user.getNachname().equals(userName)) {
          System.out.println("YEAH! " + userName);
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
