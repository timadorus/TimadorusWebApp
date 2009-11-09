package org.timadorus.webapp.client;

/**
 * @author Manto Mireille
 *
 */

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import com.google.gwt.user.client.rpc.IsSerializable;

@PersistenceCapable(identityType = IdentityType.APPLICATION)

public class Toon implements IsSerializable {	
	
	private static final long serialVersionUID = -4167661698740143894L;
	
	private String name = null;
	 private String gender =null;
	 private String fraction=null; 
	 private String race=null;
	 private String proffesion=null;
	 private Integer constitution=null;
	 private Integer agility=null;
	 private Integer selfDiscipline=null;
	 private Integer memory=null;
	 private Integer luck=null;
	 private Integer strength=null;
	 private Integer quickness=null;
	 private Integer empathy=null;
	 private Integer intution=null;
	 private Integer presence=null;
	 private  boolean isCommitFlag = false;
	 private String username="";
	 private Integer reasoning=null;
	 
	 public Toon() {
		 
	 }
	 
	 public Toon( String userName, String toonName){
		 username= userName;
		 name= toonName;
		  isCommitFlag=false;
		 
	 }


	 
	 public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getName() {
		return name;
	}

	public String getGender() {
		return gender;
	}

	public String getFraction() {
		return fraction;
	}

	public String getRace() {
		return race;
	}

	public String getProffesion() {
		return proffesion;
	}

	public Integer getConstitution() {
		return constitution;
	}

	public Integer getAgility() {
		return agility;
	}

	public Integer getSelfDiscipline() {
		return selfDiscipline;
	}

	public Integer getMemory() {
		return memory;
	}

	public Integer getReasoning() {
		return reasoning;
	}

	public Integer getLuck() {
		return luck;
	}

	public Integer getStrength() {
		return strength;
	}

	public Integer getQuickness() {
		return quickness;
	}

	public Integer getEmpathy() {
		return empathy;
	}

	public Integer getIntution() {
		return intution;
	}

	public Integer getPresence() {
		return presence;
	}

	public boolean isCommitFlag() {
		return isCommitFlag;
	}

	public String getUsername() {
		return username;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setFraction(String fraktion) {
		this.fraction = fraktion;
	}

	public void setRace(String race) {
		this.race = race;
	}

	public void setProffesion(String profession) {
		this.proffesion = profession;
	}

	public void setConstitution(Integer constitution) {
		this.constitution = constitution;
	}

	public void setAgility(Integer agility) {
		this.agility = agility;
	}

	public void setSelfDiscipline(Integer selfDiscipline) {
		this.selfDiscipline = selfDiscipline;
	}

	public void setMemory(Integer memory) {
		this.memory = memory;
	}

	public void setReasoning(Integer reasoning) {
		this.reasoning = reasoning;
	}

	public void setLuck(Integer luck) {
		this.luck = luck;
	}

	public void setStrength(Integer strength) {
		this.strength = strength;
	}
	
	public void setQuickness(Integer quickness) {
		this.quickness = quickness;
	}

	public void setEmpathy(Integer empathy) {
		this.empathy = empathy;
	}

	public void setIntution(Integer intution) {
		this.intution = intution;
	}

	public void setPresence(Integer presence) {
		this.presence = presence;
	}

	public void setCommitFlag(boolean isCommitFlag) {
		this.isCommitFlag = isCommitFlag;
	}
	
	public  String  toString(){
		return name;
		
	}
	
	
	
	
	
	
	
	
}