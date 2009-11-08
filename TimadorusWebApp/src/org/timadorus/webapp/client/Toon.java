package org.timadorus.webapp.client;

/**
 * @author Manto Mireille
 *
 */
/**

 * 
 */


import java.io.Serializable;



import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;


import com.google.appengine.repackaged.com.google.common.base.Flag.Long;

import java.lang.String;


@PersistenceCapable(identityType = IdentityType.APPLICATION)

public class Toon implements Serializable {
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	 private Long _toonID= null;
	 private Long _userIF =null;
	 private String _name = null;
	 private String _gender =null;
	 private String fraktion=null; 
	 private String _race=null;
	 private String _profession=null;
	 private Integer _constitution=null;
	 private Integer _agility=null;
	 private Integer _selfDiscipline=null;
	 private Integer _memory=null;
	 private Integer _reasoning=null;
	 private Integer _luck=null;
	 private Integer _strength=null;
	 private Integer _quickness=null;
	 private Integer _empathy=null;
	 private Integer _intution=null;
	 private Integer _presence=null;
	 private  boolean isCommitFlag = false;
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	public Toon(String name){
		this._name= name;
		setCommitFlag(false);
	}

	
	public static long getSerialversionuid()
	{
		return serialVersionUID;
	}

	public Long getUserIF()
	{
		return _userIF;
	}

	public Integer getConstitution()
	{
		return _constitution;
	}

	public Integer getAgility()
	{
		return _agility;
	}

	public Integer getSelfDiscipline()
	{
		return _selfDiscipline;
	}

	public Integer getMemory()
	{
		return _memory;
	}

	public Integer getReasoning()
	{
		return _reasoning;
	}

	public Integer getLuck()
	{
		return _luck;
	}

	public Integer getStrength()
	{
		return _strength;
	}

	public Integer getQuickness()
	{
		return _quickness;
	}

	public Integer getEmpathy()
	{
		return _empathy;
	}

	public Integer getIntution()
	{
		return _intution;
	}

	public Integer getPresence()
	{
		return _presence;
	}

	public String getName()
	{
		return _name;
	}


	public String getGender()
	{
		return _gender;
	}

	public void set_gender(String gender)
	{
		this._gender = gender;
	}

	public String getFraktion()
	{ 

		return fraktion;
	}

	public void setFraktion(String fraktion)
	{ 

		this.fraktion = fraktion;
	}

	public String getRace() {
		return _race;
	}

	public void setRace(String race)
	{
		this._race = race;
	}

	public String getProfession()
	{
		return _profession;
	}

	public void setProfession(String profession)
	{
		this._profession = profession;
	}

	public Long getToonID()
	{
		return _toonID;
	}

	public void setToonID(Long toonID)
	{
		this._toonID = toonID;
	}


	public void setCommitFlag(boolean isCommitFlag) {
		this.isCommitFlag = isCommitFlag;
	}


	public boolean isCommitFlag() {
		return isCommitFlag;
	}

}
