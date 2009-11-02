package org.timadorus.webapp.client;

import java.io.Serializable;

public class User implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5316339072058393508L;
	
	private String firstname	= null;
	private String surname		= null;
	private String userName		= null;
	private String birthday		= null;
	private String email		= null;
	private String password		= null;
	
	@Override
	public int hashCode() {
		return this.userName.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}

	
	
	
	
	
	
	public User() throws Exception {
		throw new Exception("No Username specified");
	}
	
	public User(String _userName) {
		this(_userName, "", "", "", "", "");
	}
	
	public User(String _userName, String _firstname, String _surname, String _birthday, String _email, String _password) {
		userName 	= _userName;
		firstname	= _firstname;
		surname		= _surname;
		birthday	= _birthday;
		email		= _email;
		password	= _password;
	}
	
	public String getUserName() {
		return this.userName;
	}
	
	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}	
	
	public void setPassword(String password) {
		this.password = password;
	}

}
