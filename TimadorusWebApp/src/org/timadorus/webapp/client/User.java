package org.timadorus.webapp.client;

import java.io.Serializable;

public class User implements Serializable {
	private static final long serialVersionUID = 2126117484936404051L;

	public final static int OK = 0;
	public static int GEBURTSTAG_FAULT = 1;
	public static int GEBURTSTAG_FORMAT = 2;
	public static int GEBURTSTAG_AGE = 4;
	public static int EMAIL_FAULT = 8;
	public static int USERNAME_FAULT = 16;
	public static int PASSWORD_FAULT = 32;
	
	private String vorname = "";
	private String nachname = "";
	private String geburtstag = "";
	private String email = "";
	private String username = "";
	private String password = "";
	
	public User() {
		super();
	}
	
	public User(String vorname, String nachname, String geburtstag,
			String email, String username, String password) {
		super();
		this.vorname = vorname;
		this.nachname = nachname;
		this.geburtstag = geburtstag;
		this.email = email;
		this.username = username;
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
		this.username = username;
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

	public String getPassword() {
		return password;
	}
	
	public Boolean isValid() {
		if(vorname.length() == 0) {
			return false;
		} else if(nachname.length() == 0) {
			return false;
		} else if(geburtstag.length() == 0) {
			return false;
		} else if(email.length() == 0) {
			return false;
		} else if(username.length() == 0) {
			return false;
		} else if(password.length() == 0) {
			return false;
		} 
		return true;
	}
}
