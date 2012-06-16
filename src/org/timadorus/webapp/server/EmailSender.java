package org.timadorus.webapp.server;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.timadorus.webapp.beans.User;

public class EmailSender extends Thread {

  private static final String HOSTNAME = "mailgate.informatik.haw-hamburg.de";

  private static final String SENDER = "root@informatik.haw-hamburg.de";

  private static final String SUBJECT = "Timadorus Registration Mail";

  private User myUser;

  public EmailSender(User myUser) {
    super();
    this.myUser = myUser;
    start();
  }

  private void send(User user) {
    Email email = new SimpleEmail();
    email.setHostName(HOSTNAME);
    email.setTLS(true);
    // email.setAuthenticator(new DefaultAuthenticator("username", "password"));

    try {
      email.setFrom(SENDER);
    } catch (EmailException e) {
      // TODO do some proper handling if the sender mail is invalid.
    }
    email.setSubject(SUBJECT);

    // TODO: get this from some template. Also to be set in the admin panel. And i18n'ized
    try {
      email.setMsg("Hello " + user.getDisplayname() + ",\n\nPlease register your user " + user.getUsername()
          + "by visiting the following address: \n\n"
          + "http://www.timadorus.org:8080/TimadorusWebApp.html?activationCode=" + user.getActivationCode()
          + "\n\nthe Timadorus-Team.");

    } catch (EmailException e) {
      // only likely to happen upon a mime error
    }

    try {
      email.addTo(user.getEmail());
    } catch (EmailException e) {
      // TODO: handle an invalid to address.
    }

    try {
      email.send();
    } catch (EmailException e) {
      // TODO handle a failed mail.
    }
  }

  @Override
  public void run() {
    send(myUser);
  }

}
