package org.timadorus.webapp.server;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import org.timadorus.webapp.client.User;

public final class Util {
  
  // Mindestalter für User; "0" wenn keine Einschränkung
  private static final int MIN_AGE_USER = 18;
  
  private Util() { }

  public static int checkEmailAdresse(User user) {
    if (user.getEmail().startsWith("www.") || !user.getEmail().matches(".+@.+\\.[a-z]+")) { 
      return User.EMAIL_FORMAT; 
    }
    return 0;
  }

  public static int checkUsernameFree(String username) {
    RegisteredUserList userList = RegisteredUserList.getInstance();
    if (!userList.usernameAvailable(username)) { 
      return User.USERNAME_FAULT; 
    }
    return 0;
  }

  public static int checkBirthday(String birthday) {
    // Format "dd.mm.jjjj" erlaubt
    if (!birthday.matches("[0-9]{2}.[0-9]{2}.[0-9]{4}")) {
      return User.GEBURTSTAG_FORMAT;
    } else {
      try {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
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

  public static int age(Date gebdat) {
    GregorianCalendar cal = new GregorianCalendar();
    int y, d, a;

    y = cal.get(GregorianCalendar.YEAR);
    d = cal.get(GregorianCalendar.DAY_OF_YEAR);
    cal.setTime(gebdat);
    a = y - cal.get(GregorianCalendar.YEAR);
    if (d < cal.get(GregorianCalendar.DAY_OF_YEAR)) {
      --a;
    }
    return (a);
  }
  
  public static String generateActivationCode(User user) {
    String pass = user.getVorname() + user.getNachname() + user.getEmail() + user.getPassword() 
                  + user.getGeburtstag() + System.currentTimeMillis();
    
    try {
      MessageDigest m = MessageDigest.getInstance("MD5");
      byte[] data = pass.getBytes(); 
      m.update(data, 0, data.length);
      BigInteger i = new BigInteger(1, m.digest());
      return String.format("%1$032X", i);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "";
  }
}
