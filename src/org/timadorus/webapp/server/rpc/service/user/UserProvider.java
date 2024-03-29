package org.timadorus.webapp.server.rpc.service.user;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import org.timadorus.webapp.beans.User;
import org.timadorus.webapp.server.RegisteredUserList;
import org.timadorus.webapp.server.Util;

public final class UserProvider {
  
  private UserProvider() {
    
  }
  
  private static final long serialVersionUID = -2215579735797066083L;
  
  public static final PersistenceManagerFactory PMF = RegisteredUserList.PMF;
  private static RegisteredUserList userList = RegisteredUserList.getInstance();
  
  /**
   * Returns a User object containing the current available informations of this user.
   * 
   * @param user which shall be returned with current state
   * @return The user in current state
   */
  @SuppressWarnings("unchecked")
  public static User getUser(User user) {
    try {
      PersistenceManager pm = PMF.getPersistenceManager();  
      
      Query query = pm.newQuery(User.class, "username == '" + user.getUsername() + "'");
      
      User ret = null;
      for (User tempUser : (List<User>) query.execute()) {
        pm.retrieve(tempUser);
        ret = pm.detachCopy(tempUser);
      }
      pm.close();
      
      return ret;
    
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    return null;
  }
  
  /**
   * Deletes the user provided as parameter for this method as RPC Service.
   * 
   * @param user The user which shall be deleted
   * @return The status of delete-process User.OK on success, User.USER_INVALID otherwise
   */
  public static String delete(User user) {
    boolean status = RegisteredUserList.getInstance().deleteUser(user);
    if (status) {
      return String.valueOf(User.OK);
    }
    return User.USER_INVALID;
  }
  
  /**
   * Updates the user data of a certain user profile.
   * 
   * @param id The id of the user
   * @param user The supplied user data
   * @return An integer value representing the state of the update attempt
   */
  public static int update(long id, User user) {
    PersistenceManager pm = PMF.getPersistenceManager();
    
    int value       = isValid(user);
    User origUser   = pm.getObjectById(User.class, id);
    
    if (!origUser.getUsername().equals(user.getUsername())) {
      value += Util.checkUsernameFree(user.getUsername());
    }
    
    if (value == User.OK) {
      
      origUser.setEmail(user.getEmail());
      origUser.setGeburtstag(user.getGeburtstag());
      origUser.setNachname(user.getNachname());
      origUser.setPassword(user.getPassword());
      origUser.setUsername(user.getUsername());
      origUser.setVorname(user.getVorname());
      
      pm.makePersistent(origUser);
      pm.close();
    }
    return value;
  }
  
  /**
   * Activates a user using the activation code, which was send as link to the users e-mail address.
   * 
   * @param activationCode The activation code which was send as link to the users e-mail address
   * @param user The supplied user data
   * @return User.USER_ALREADY_ACTIVATED    if the user is already activated, 
   *         User.USER_WRONG_CODE           if the supplied activation code has been incorrect, 
   *         User.USER_INVALID              if the username and password was wrong and
   *         User.USER_VERIFIED             if the user has been successfully verified.  
   */
  public static String verifyMail(String activationCode, User user) {
    if (user == null) { return null; }
    if (userList.isValid(user)) {
      if (userList.isActive(user)) {
        return User.USER_ALREADY_ACTIVATED;
      } else {
        user = getUser(user);
        if (activationCode.equals(user.getActivationCode())) {
          PersistenceManager pm = PMF.getPersistenceManager();          
          User origUser         = pm.getObjectById(User.class, user.getId());
          origUser.setActive(true);
          pm.makePersistent(origUser);
          pm.close();
          
          return User.USER_VERIFIED;
        }
        return User.USER_WRONG_CODE;
      }
    }
    return User.USER_INVALID;
  }
  
  /**
   * Checks if the supplied user data is valid for update.-
   * 
   * @param user The supplied user data as User object
   * @return User.OK or other integer values in case of error
   */
  private static int isValid(User user) {
    int out = User.OK;
    out += Util.checkBirthday(user.getGeburtstag());
    out += Util.checkEmailAdresse(user);
    return out;
  }

}
