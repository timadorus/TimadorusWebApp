package org.timadorus.webapp.beans;

import java.io.Serializable;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.jdo.annotations.Unique;

@PersistenceCapable(identityType = IdentityType.APPLICATION, table = "USER")
public class User implements Serializable {
  private static final long serialVersionUID = 2126117484936404051L;

  public static final int OK = 0;

  public static final int GEBURTSTAG_FAULT = 1;

  public static final int GEBURTSTAG_FORMAT = 2;

  public static final int GEBURTSTAG_AGE = 4;

  public static final int EMAIL_FAULT = 8;

  public static final int EMAIL_FORMAT = 16;

  public static final int USERNAME_FAULT = 32;

  public static final int PASSWORD_FAULT = 64;

  public static final int VORNAME_NACHNAME_EMPTY = 128;

  public static final int GEBURTSTAG_EMPTY = 256;

  public static final int EMAIL_EMPTY = 512;

  public static final int EMAILREPEAT_EMPTY = 1024;

  public static final int USERNAME_EMPTY = 2048;

  public static final int PASSWORD_EMPTY = 4096;

  public static final int PASSWORDREPEAT_EMPTY = 8192;

  public static final String USER_INVALID = "INVALID";

  public static final String USER_INACTIVE = "INACTIVE";
  
  public static final String USER_VERIFIED = "VARIFIED";
  
  public static final String USER_WRONG_CODE = "WRONG_CODE";
  
  public static final String USER_ALREADY_ACTIVATED = "ALREADY_ACTIVATED";

  private static final int NAME_SIZE = 50;
  
  private static final int PASSWORD_SIZE = 50;
  
  private static final int DATE_SIZE = 10;
  
  private static final int EMAIL_SIZE = 50;
  
  private static final int USERNAME_SIZE = 20;
  
  private static final int ACTIVATE_CODE_SIZE = 32;
  
  @PrimaryKey
  @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
  private Long id;

  @Persistent
  @Column(length = NAME_SIZE)
  private String vorname = "";

  @Persistent
  @Column(length = NAME_SIZE)
  private String nachname = "";

  @Persistent
  @Column(length = DATE_SIZE)
  private String geburtstag = "";

  @Persistent
  @Column(length = EMAIL_SIZE)
  private String email = "";

  @Unique
  @Persistent
  @Column(length = USERNAME_SIZE)
  private String username = "";

  @Persistent
  @Column(length = USERNAME_SIZE)
  private String displayname = "";  
  
  @Persistent
  @Column(length = PASSWORD_SIZE)
  private String password = "";

  @Persistent
  private Boolean active = false;
  
  @Persistent
  @Column(length = ACTIVATE_CODE_SIZE)
  private String activationCode = "";

  public User() {
    super();
  }

  public User(final String vornameIn,
              final String nachnameIn,
              final String geburtstagIn,
              final String emailIn,
              final String usernameIn,
              final String passwordIn) {
    super();
    this.vorname = vornameIn;
    this.nachname = nachnameIn;
    this.geburtstag = geburtstagIn;
    this.email = emailIn;
    this.username = usernameIn.toLowerCase();
    this.displayname = usernameIn;
    this.password = passwordIn;
  }

  public final String getVorname() {
    return vorname;
  }

  public void setVorname(String vornameIn) {
    this.vorname = vornameIn;
  }

  public void setNachname(String nachnameIn) {
    this.nachname = nachnameIn;
  }

  public void setGeburtstag(String geburtstagIn) {
    this.geburtstag = geburtstagIn;
  }

  public void setEmail(String emailIn) {
    this.email = emailIn;
  }

  public void setUsername(String usernameIn) {
    this.username = usernameIn.toLowerCase();
    this.displayname = usernameIn;
  }

  public void setPassword(String passwordIn) {
    this.password = passwordIn;
  }

  public String getNachname() {
    return nachname;
  }

  public String getGeburtstag() {
    return geburtstag;
  }

  public String getEmail() {
    return email;
  }

  public String getUsername() {
    return username;
  }

  public String getDisplayname() {
    return displayname;
  }

  public String getPassword() {
    return password;
  }

  public void setActive(Boolean activeIn) {
    this.active = activeIn;
  }

  public Boolean getActive() {
    return active;
  }
  
  public void setActivationCode(String activationCodeIn) {
    this.activationCode = activationCodeIn;
  }
  
  public String getActivationCode() {
    return activationCode;
  }

  public final Long getId() {
    return id;
  }

  public Boolean isValid() {
    if (vorname.length() == 0 && nachname.length() == 0) {
      return false;
    } else if (geburtstag.length() == 0) {
      return false;
    } else if (email.length() == 0) {
      return false;
    } else if (username.length() == 0) {
      return false;
    } else if (password.length() == 0) { return false; }
    return true;
  }
}
