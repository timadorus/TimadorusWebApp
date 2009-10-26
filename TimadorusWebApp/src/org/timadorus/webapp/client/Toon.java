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

import com.google.appengine.repackaged.com.google.common.base.Flag.Long;
import com.google.appengine.repackaged.com.google.common.base.Flag.String;

/**
 * @author maddin
 * 
 */
@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Toon implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	/*
	 * Stat Abr. Temp Stat Points Dev Points Stat Bonus Race Bonus Constitution
	 * CO 30 0 ### +0 +0 Agility AG 30 0 ### +0 +0 Self Discipline SD 30 0 ###
	 * +0 +0 Memory ME 30 0 ### +0 +0 Reasoning RE 30 0 ### +0 +0 Luck LU 30 0
	 * ### +0 +0 Strength ST 30 0 +0 +0 Quickness QU 30 0 +0 +0 Empathy EM 30 0
	 * +0 +0 Intution IN 30 0 +0 +0 Presence PR 30 0 +0 +0
	 */
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
	Integer Constitution;

	@Persistent
	Integer Agility;

	@Persistent
	Integer SelfDiscipline;

	@Persistent
	Integer Memory;

	@Persistent
	Integer Reasoning;

	@Persistent
	Integer Luck;

	@Persistent
	Integer Strength;

	@Persistent
	Integer Quickness;

	@Persistent
	Integer Empathy;

	@Persistent
	Integer Intution;

	@Persistent
	Integer Presence;

	@Persistent
	boolean complete;
	
	
	

	public boolean isComplete() {
		return complete;
	}

	public Toon() {
		super();
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getUserIF() {
		return userIF;
	}

	public Integer getConstitution() {
		return Constitution;
	}

	public Integer getAgility() {
		return Agility;
	}

	public Integer getSelfDiscipline() {
		return SelfDiscipline;
	}

	public Integer getMemory() {
		return Memory;
	}

	public Integer getReasoning() {
		return Reasoning;
	}

	public Integer getLuck() {
		return Luck;
	}

	public Integer getStrength() {
		return Strength;
	}

	public Integer getQuickness() {
		return Quickness;
	}

	public Integer getEmpathy() {
		return Empathy;
	}

	public Integer getIntution() {
		return Intution;
	}

	public Integer getPresence() {
		return Presence;
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
