package org.timadorus.webapp.server.rpc.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import org.timadorus.webapp.client.User;
import org.timadorus.webapp.client.rpc.service.RegisterService;
import org.timadorus.webapp.server.RegisteredUserList;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class RegisterServiceImpl extends RemoteServiceServlet implements RegisterService {

  private static final long serialVersionUID = 270628040929463623L;

  // Mindestalter für User; "0" wenn keine Einschränkung
  private static final int MIN_AGE_USER = 18;

  private User data;

  public String register(User data) {
    System.out.println("Register aufgerufen");
    this.data = data;
    int value = isValid();
    if (value == User.OK) {
      RegisteredUserList userList = RegisteredUserList.getInstance();
      Boolean added = userList.addUser(data);
      if (!added) {
        value = User.USERNAME_FAULT;
      }
    }
    return String.valueOf(value);
  }

  private int isValid() {
    int out = User.OK;
    out += checkBirthday();
    out += checkUsernameFree();
    out += checkEmailAdresse();
    return out;
  }

  private int checkEmailAdresse() {
    if (data.getEmail().startsWith("www.") || !data.getEmail().matches(".+@.+\\.[a-z]+")) { return User.EMAIL_FORMAT; }
    return 0;
  }

  private int checkUsernameFree() {
    RegisteredUserList userList = RegisteredUserList.getInstance();
    if (!userList.usernameAvailable(data.getUsername())) { return User.USERNAME_FAULT; }
    return 0;
  }

  private int checkBirthday() {
    // Format "dd.mm.jjjj" erlaubt
    if (!data.getGeburtstag().matches("[0-9]{2}.[0-9]{2}.[0-9]{4}")) {
      return User.GEBURTSTAG_FORMAT;
    } else {
      try {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date date = dateFormat.parse(data.getGeburtstag());
        if (age(date) < MIN_AGE_USER) { return User.GEBURTSTAG_AGE; }
      } catch (ParseException e) {
        return User.GEBURTSTAG_FAULT;
      }
    }
    return 0;
  }

  private int age(Date gebdat) {
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
}