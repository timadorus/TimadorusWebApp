package org.timadorus.webapp.client;

import java.io.Serializable;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.jdo.annotations.Unique;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class User implements Serializable {
  private static final long serialVersionUID = 2126117484936404051L;

  public final static int OK = 0;

  public final static int GEBURTSTAG_FAULT = 1;

  public final static int GEBURTSTAG_FORMAT = 2;

  public final static int GEBURTSTAG_AGE = 4;

  public final static int EMAIL_FAULT = 8;

  public static final int EMAIL_FORMAT = 16;

  public final static int USERNAME_FAULT = 32;

  public final static int PASSWORD_FAULT = 64;

  public static final int VORNAME_NACHNAME_EMPTY = 128;

  public static final int GEBURTSTAG_EMPTY = 256;

  public static final int EMAIL_EMPTY = 512;

  public static final int EMAILREPEAT_EMPTY = 1024;

  public static final int USERNAME_EMPTY = 2048;

  public static final int PASSWORD_EMPTY = 4096;

  public static final int PASSWORDREPEAT_EMPTY = 8192;

  public static final String USER_INVALID = "INVALID";

  public static final String USER_INACTIVE = "INACTIVE";

  @PrimaryKey
  @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
  private Long id;

  @Persistent
  private String vorname = "";

  @Persistent
  private String nachname = "";

  @Persistent
  private String geburtstag = "";

  @Persistent
  private String email = "";

  @Persistent
  @Unique
  private String username = "";

  @Persistent
  private String displayname = "";

  @Persistent
  private String password = "";

  @Persistent
  private Boolean active = false;

  public Long getId() {
    return id;
  }

  public User() {
    super();
  }

  public User(String vorname, String nachname, String geburtstag, String email, String username, String password) {
    super();
    this.vorname = vorname;
    this.nachname = nachname;
    this.geburtstag = geburtstag;
    this.email = email;
    this.username = username.toLowerCase();
    this.displayname = username;
    this.password = password;
  }

  public String getVorname() {
    return vorname;
  }

  public void setVorname(String vorname) {
    this.vorname = vorname;
  }

  public void setNachname(String nachname) {
    this.nachname = nachname;
  }

  public void setGeburtstag(String geburtstag) {
    this.geburtstag = geburtstag;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setUsername(String username) {
    this.username = username.toLowerCase();
    this.displayname = username;
  }

  public void setPassword(String password) {
    this.password = password;
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

  public void setActive(Boolean active) {
    this.active = active;
  }

  public Boolean getActive() {
    return active;
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
