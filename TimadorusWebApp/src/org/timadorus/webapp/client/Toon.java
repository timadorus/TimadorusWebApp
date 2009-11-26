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
	 @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((agility == null) ? 0 : agility.hashCode());
		result = prime * result
				+ ((constitution == null) ? 0 : constitution.hashCode());
		result = prime * result + ((empathy == null) ? 0 : empathy.hashCode());
		result = prime * result
				+ ((fraction == null) ? 0 : fraction.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result
				+ ((intution == null) ? 0 : intution.hashCode());
		result = prime * result + (isCommitFlag ? 1231 : 1237);
		result = prime * result + ((luck == null) ? 0 : luck.hashCode());
		result = prime * result + ((memory == null) ? 0 : memory.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((presence == null) ? 0 : presence.hashCode());
		result = prime * result
				+ ((proffesion == null) ? 0 : proffesion.hashCode());
		result = prime * result
				+ ((quickness == null) ? 0 : quickness.hashCode());
		result = prime * result + ((race == null) ? 0 : race.hashCode());
		result = prime * result
				+ ((reasoning == null) ? 0 : reasoning.hashCode());
		result = prime * result
				+ ((selfDiscipline == null) ? 0 : selfDiscipline.hashCode());
		result = prime * result
				+ ((strength == null) ? 0 : strength.hashCode());
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Toon other = (Toon) obj;
		if (agility == null) {
			if (other.agility != null)
				return false;
		} else if (!agility.equals(other.agility))
			return false;
		if (constitution == null) {
			if (other.constitution != null)
				return false;
		} else if (!constitution.equals(other.constitution))
			return false;
		if (empathy == null) {
			if (other.empathy != null)
				return false;
		} else if (!empathy.equals(other.empathy))
			return false;
		if (fraction == null) {
			if (other.fraction != null)
				return false;
		} else if (!fraction.equals(other.fraction))
			return false;
		if (gender == null) {
			if (other.gender != null)
				return false;
		} else if (!gender.equals(other.gender))
			return false;
		if (intution == null) {
			if (other.intution != null)
				return false;
		} else if (!intution.equals(other.intution))
			return false;
		if (isCommitFlag != other.isCommitFlag)
			return false;
		if (luck == null) {
			if (other.luck != null)
				return false;
		} else if (!luck.equals(other.luck))
			return false;
		if (memory == null) {
			if (other.memory != null)
				return false;
		} else if (!memory.equals(other.memory))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (presence == null) {
			if (other.presence != null)
				return false;
		} else if (!presence.equals(other.presence))
			return false;
		if (proffesion == null) {
			if (other.proffesion != null)
				return false;
		} else if (!proffesion.equals(other.proffesion))
			return false;
		if (quickness == null) {
			if (other.quickness != null)
				return false;
		} else if (!quickness.equals(other.quickness))
			return false;
		if (race == null) {
			if (other.race != null)
				return false;
		} else if (!race.equals(other.race))
			return false;
		if (reasoning == null) {
			if (other.reasoning != null)
				return false;
		} else if (!reasoning.equals(other.reasoning))
			return false;
		if (selfDiscipline == null) {
			if (other.selfDiscipline != null)
				return false;
		} else if (!selfDiscipline.equals(other.selfDiscipline))
			return false;
		if (strength == null) {
			if (other.strength != null)
				return false;
		} else if (!strength.equals(other.strength))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

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


	 
	 public Toon(String toonname) {
		 name= toonname;
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