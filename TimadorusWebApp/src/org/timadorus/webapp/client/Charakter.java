/**
 * 
 */
package org.timadorus.webapp.client;

import java.io.Serializable;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/**
 * @author maddin
 *
 */
@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Charakter implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	Long characterID;
	
	@Persistent
	Long userIF;
	
	@Persistent
	String name;
	
	@Persistent
	String geschlecht;
	
	@Persistent
	String fraktion;
	
	@Persistent
	String rasse;
	
	@Persistent
	String profession;

	@Persistent
	boolean complete;
	
	public Charakter() {
		super();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGeschlecht() {
		return geschlecht;
	}

	public void setGeschlecht(String geschlecht) {
		this.geschlecht = geschlecht;
	}

	public String getFraktion() {
		return fraktion;
	}

	public void setFraktion(String fraktion) {
		this.fraktion = fraktion;
	}

	public String getRasse() {
		return rasse;
	}

	public void setRasse(String rasse) {
		this.rasse = rasse;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public Long getCharacterID() {
		return characterID;
	}

	public void setCharacterID(Long characterID) {
		this.characterID = characterID;
	}


}
