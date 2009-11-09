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
	/**
	 * 
	 */




	private static final long serialVersionUID = -4167661698740143894L;
	 private String name = null;
	 private String gender =null;
	 private String fraktion=null; 
	 private String race=null;
	 private String profession=null;
	 private Integer constitution=null;
	 private Integer agility=null;
	 private Integer selfDiscipline=null;
	 private Integer memory=null;
	 

		 private Integer reasoning=null;
		 private Integer luck=null;
		 private Integer strength=null;
		 private Integer quickness=null;
		 private Integer empathy=null;
		 private Integer intution=null;
		 private Integer presence=null;
		 private  boolean isCommitFlag = false;
		 private String username="";
		 
		 
	 public Toon( String userName, String toonName){
		 username= userName;
		 name= toonName;
		  isCommitFlag=false;
		 
	 }

	public Toon( String toonName, String toonGender, String  toonFraktion, String toonRace, String toonProfession){
	
		name= toonName;
		 gender=toonGender;
		 fraktion=toonFraktion;
		 race=toonRace;
		 profession=toonProfession;
		setCommitFlag(false);
	}

	
//	public Toon(Long id, Long userIf, String name, String gender,String fraktion
//			 , String race,  String profession , Integer constitution,  int agility
//			 , Integer selDiscipline, Integer  memory, Integer  reasoning ,  Integer luck
//			 , Integer strength,Integer quickness, Integer empathy, Integer intution
//			 , Integer presence)
//	{
//		
//		_toonID=id;
//		_userIF= userIf;
//		_name= name;
//		_gender= gender;
//		_fraktion= fraktion;
//		_race= race;
//		_profession= profession;
//		_constitution= constitution;
//		_agility= agility;
//		_selfDiscipline= selDiscipline;
//		_memory=memory;
//		_reasoning=reasoning;
//		_luck= luck;
//		_strength= strength;
//		_quickness= quickness;
//		_empathy= empathy;
//		_intution= intution;
//		_presence= presence;
//		setCommitFlag(true);
//	}







	public void setName(String name) {
		this.name = name;
	}




	public void setGender(String gender) {
		this.gender = gender;
	}




	public void setFraktion(String fraktion) {
		this.fraktion = fraktion;
	}




	public void setRace(String race) {
		this.race = race;
	}




	public void setProfession(String profession) {
		this.profession = profession;
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




}
