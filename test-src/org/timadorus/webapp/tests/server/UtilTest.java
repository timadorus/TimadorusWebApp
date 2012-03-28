package org.timadorus.webapp.tests.server;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import junit.framework.Assert;

import org.junit.Test;
import org.timadorus.webapp.beans.User;
import org.timadorus.webapp.server.Util;

/**
 * Test-Class for Util.
 * 
 * @author rt
 * 
 */
public class UtilTest {

  private static final int AGE = 18;
  private static final int ONE_OVER_AGE = 17;

  /**
   * Test for the method checkEmailAdresse in Util. 
   */
  @Test
  public void testCheckEMailAdresse() {
    User theUser = new User();
    
    // Test with an empty eMail
    testEMail(theUser, "", User.EMAIL_FORMAT);
    
    // Test with prefix www.
    testEMail(theUser, "www.timadorus.org", User.EMAIL_FORMAT);
    
    // Test with a starting @
    testEMail(theUser, "@timadorus.org", User.EMAIL_FORMAT);

    // Test without tld
    testEMail(theUser, "admin@timadorus", User.EMAIL_FORMAT);
    
    // Test with a valid email
    testEMail(theUser, "admin@timadorus.org", 0);
  }
  
  /**
   * just a little helper method
   * @param anUser
   * @param anEmail
   * @param aReturn
   */
  private void testEMail(User anUser, String anEmail, int aReturn) {
    anUser.setEmail(anEmail);
    int checkEmailAdresse = Util.checkEmailAdresse(anUser);
    Assert.assertEquals("checkEmailAdress should return " + aReturn + " for eMail " 
                        + anEmail + " but returned " + checkEmailAdresse, aReturn, checkEmailAdresse);
  }
  
  /**
   * Test for the method checkBirthday
   */
  @Test
  public void testCheckBirthday() {
    // Test with a malformed format
    testBirthdayFormat("dd.mm.yyyy", User.GEBURTSTAG_FORMAT);
    
    // Test with a wellformed format but not valid for date
    testBirthdayFormat("32.13.0000", User.GEBURTSTAG_FAULT);
    
    // Test with a valid date but under age
    GregorianCalendar gregorianCalendar = new GregorianCalendar();
    
    gregorianCalendar.set(GregorianCalendar.YEAR, gregorianCalendar.get(GregorianCalendar.YEAR) - ONE_OVER_AGE);
    testBirthdayFormat(new SimpleDateFormat("dd.MM.yyyy").format(gregorianCalendar.getTime()), User.GEBURTSTAG_AGE);
    
    // Test with a valid age
    gregorianCalendar = new GregorianCalendar();
    gregorianCalendar.set(GregorianCalendar.YEAR, gregorianCalendar.get(GregorianCalendar.YEAR) - AGE);
    gregorianCalendar.set(GregorianCalendar.DAY_OF_YEAR, gregorianCalendar.get(GregorianCalendar.DAY_OF_YEAR) - 1);
    testBirthdayFormat(new SimpleDateFormat("dd.MM.yyyy").format(gregorianCalendar.getTime()), 0);
  }
  
  private void testBirthdayFormat(String aDate, int aReturn) {
    int checkBirthday = Util.checkBirthday(aDate);
    Assert.assertEquals("checkBirthday should return " + aReturn + " for " 
                        + aDate + " but returned " + checkBirthday, aReturn, checkBirthday);
  }
  
}
