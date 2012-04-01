package org.timadorus.webapp.server;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import org.timadorus.webapp.beans.User;

/**
 * A Util class containing different static util functions related to the server code.
 * 
 * @author Malte Kantak
 */
public final class Util {
  
  // Mindestalter für User; "0" wenn keine Einschränkung
  private static final int MIN_AGE_USER = 18;
  
  // MD5
  private static final String MD5_ALGORITHM = "MD5";
  
  /**
   * Private constructor, because there will be no need for an instance of this class. 
   */
  private Util() { }

  /**
   * Checks if the e-mail address inside the supplied User object is valid.
   * 
   * @param user The supplied User object
   * @return 0 if the E-Mail address has a valid format, User.EMAIL_FORMAT otherwise
   */
  public static int checkEmailAdresse(User user) {
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
  public static int checkUsernameFree(String username) {
    RegisteredUserList userList = RegisteredUserList.getInstance();
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
  public static int checkBirthday(String birthday) {
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
  public static int age(Date gebdat) {
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
  
  /**
   * Generates an activation code for a supplied user, including the current system timestamp.
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
}
