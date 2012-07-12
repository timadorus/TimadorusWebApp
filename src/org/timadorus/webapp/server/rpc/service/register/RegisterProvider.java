package org.timadorus.webapp.server.rpc.service.register;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import org.timadorus.webapp.beans.User;
import org.timadorus.webapp.server.RegisteredUserList;

/**
 * 
 * @author sage
 *
 */
public final class RegisterProvider {

  // Mindestalter fÃ¼r User; "0" wenn keine EinschrÃ¤nkung
  private static final int MIN_AGE_USER = 18;

  private final RegisteredUserList userList;

  /**
   * 
   * @param list the user list to use
   */
  public RegisterProvider(RegisteredUserList list) {
    this.userList = list;
  }

  /**
   * Registers a user supplied as dataIn parameter.
   * 
   * @param dataIn
   *          The user which shall be registered
   * @return Return value of this.isValid(dataIn)
   */
  public String register(User dataIn) {
    System.out.println("Register aufgerufen");
    int value = isValid(dataIn);
    String activationCode = null;
    if (value == User.OK) {
      activationCode = userList.addUser(dataIn);
      if (activationCode == null) {
        value = User.USERNAME_FAULT;
      }
    }
    if (activationCode == null) {
      return String.valueOf(value);
    } else {
      return String.valueOf(value) + "_" + activationCode;
    }
  }

  /**
   * Checks if a user is valid in registration content.
   * 
   * @param user
   *          The supplied user data
   * @return An error code on failure, User.OK otherwise
   */
  private int isValid(User user) {
    int out = User.OK;
    out += checkBirthday(user.getGeburtstag());
    out += checkUsernameFree(user.getUsername());
    out += checkEmailAdresse(user);
    return out;
  }
  
  /**
   * Checks if the e-mail address inside the supplied User object is valid.
   * 
   * @param user The supplied User object
   * @return 0 if the E-Mail address has a valid format, User.EMAIL_FORMAT otherwise
   */
  public int checkEmailAdresse(User user) {
    if (user.getEmail().startsWith("www.") || !user.getEmail().matches(".+@.+\\.[a-z]+")) { 
      return User.EMAIL_FORMAT; 
    }
    return 0;
  }

  /**
   * Checks if the username is available or not.
   * 
   * @param username The username which shall be checked
   * @return 0 if the username is available, User.USERNAME_FAULT otherwise
   */
  public int checkUsernameFree(String username) {
    if (!userList.usernameAvailable(username)) { 
      return User.USERNAME_FAULT; 
    }
    return 0;
  }

  /**
   * Checks if the birthday is valid or not.
   * 
   * @param birthday The birthday which shall be checked as string
   * @return 0 if valid, User.GEBURTSTAG_FORMAT if format is invalid, User.GEBURTSTAG_AGE if the age was invalid or
   *          User.GEBURTSTAG_FAULT on parse exception.
   */
  public int checkBirthday(String birthday) {
    // Format "dd.mm.jjjj" erlaubt
    if (!birthday.matches("[0-9]{2}.[0-9]{2}.[0-9]{4}")) {
      return User.GEBURTSTAG_FORMAT;
    } else {
      try {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        // important because otherwise 32.01.1970 would be a valid format
        dateFormat.setLenient(false);
        Date date = dateFormat.parse(birthday);
        if (age(date) < MIN_AGE_USER) { 
          return User.GEBURTSTAG_AGE; 
        }
      } catch (ParseException e) {
        return User.GEBURTSTAG_FAULT;
      }
    }
    return 0;
  }

  /**
   * Calculates the age for a certain birth date.
   * 
   * @param gebdat The birth date for which the age shall be calculated 
   * @return The age as integer
   */
  public int age(Date gebdat) {
    GregorianCalendar theGregorianCalendar = new GregorianCalendar();
    int theActualYear, theActualDayOfYear, theDifference;

    theActualYear = theGregorianCalendar.get(GregorianCalendar.YEAR);
    theActualDayOfYear = theGregorianCalendar.get(GregorianCalendar.DAY_OF_YEAR);
    
    theGregorianCalendar.setTime(gebdat);
    
    theDifference = theActualYear - theGregorianCalendar.get(GregorianCalendar.YEAR);
    if (theActualDayOfYear < theGregorianCalendar.get(GregorianCalendar.DAY_OF_YEAR)) {
      --theDifference;
    }
    return theDifference;
  }

}
