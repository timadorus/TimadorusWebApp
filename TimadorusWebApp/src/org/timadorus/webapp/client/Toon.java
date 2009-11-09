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
	private User user;
	private static final long serialVersionUID = -4167661698740143894L;
	 private String _name = null;
	 private String _gender =null;
	 private String _fraktion=null; 
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
		 private String _username="";
		 
		 
	 
	 
	 public void setUser(User user) {
		this.user = user;
	}

	public void set_name(String name) {
		_name = name;
	}

	public void set_fraktion(String fraktion) {
		_fraktion = fraktion;
	}

	public void set_race(String race) {
		_race = race;
	}

	public void set_profession(String profession) {
		_profession = profession;
	}

	public void set_constitution(Integer constitution) {
		_constitution = constitution;
	}

	public void set_agility(Integer agility) {
		_agility = agility;
	}

	public void set_selfDiscipline(Integer selfDiscipline) {
		_selfDiscipline = selfDiscipline;
	}

	public void set_memory(Integer memory) {
		_memory = memory;
	}

	public void set_reasoning(Integer reasoning) {
		_reasoning = reasoning;
	}

	public void set_luck(Integer luck) {
		_luck = luck;
	}

	public void set_strength(Integer strength) {
		_strength = strength;
	}

	public void set_quickness(Integer quickness) {
		_quickness = quickness;
	}

	public void set_empathy(Integer empathy) {
		_empathy = empathy;
	}

	public void set_intution(Integer intution) {
		_intution = intution;
	}

	public void set_presence(Integer presence) {
		_presence = presence;
	}

	public void set_username(String username) {
		_username = username;
	}


	 
	 public Toon() throws Exception {
		throw new Exception("no argument given"); 
	 }
	 
	public  Toon(String username, String name){
        _username= username;
		 _name= name;
		 setCommitFlag(false);
	 }
	 
	public Toon( String name, String gender, String Fraktion, String race, String profession){
	
		_name= name;
		 _gender=gender;
		 _fraktion=Fraktion;
		 _race=race;
		 _profession= profession;
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

	
	public static long getSerialversionuid()
	{
		return serialVersionUID;
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

		return _fraktion;
	}

	public void setFraktion(String fraktion)
	{ 

		this._fraktion = fraktion;
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

	public void setCommitFlag(boolean isCommitFlag) {
		this.isCommitFlag = isCommitFlag;
	}


	public boolean isCommitFlag() {
		return isCommitFlag;
	}

}
