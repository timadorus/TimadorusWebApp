package org.timadorus.webapp.client;

import java.io.Serializable;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

public class Fraktion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	Long  fraktionID;
		
	@Persistent
	String namen;
	
	@Persistent
	String Beschreibung;
	
	Fraktion(){
		super();
	}
	
	
	public Long getFraktionID() {
		return fraktionID;
	}

	public void setFraktionID(Long fraktionID) {
		this.fraktionID = fraktionID;
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
