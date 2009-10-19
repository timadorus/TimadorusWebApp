/**
 * 
 */
package org.timadorus.webapp.client;

import java.io.Serializable;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/**
 * @author maddin
 *
 */
public class Rasse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	Long rasseID;
	
	@Persistent
	String namen;
	
	@Persistent
	String Beschreibung;
	

	public Rasse() {
		super();
	}
	
	
	public Long getRasseID() {
		return rasseID;
	}


	public void setRasseID(Long rasseID) {
		this.rasseID = rasseID;
	}


	public String getNamen() {
		return namen;
	}


	public void setNamen(String namen) {
		this.namen = namen;
	}


	public String getBeschreibung() {
		return Beschreibung;
	}


	public void setBeschreibung(String beschreibung) {
		Beschreibung = beschreibung;
	}



}
