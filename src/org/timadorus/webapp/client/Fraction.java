package org.timadorus.webapp.client;

import java.io.Serializable;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

public class Fraction implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	Long  fractionID;
		
	@Persistent
	String names;
	
	@Persistent
	String description;
	
	Fraction(){
		super();
	}
	
	
	public Long getFractionID() {
		return fractionID;
	}

	public void setFractionID(Long fractionID) {
		this.fractionID = fractionID;
	}

	public String getNames() {
		return names;
	}

	public void setNames(String name) {
		this.names = name;
	}

	public String getDescription() {
		return description;
	}

	public void setdescription(String description) {
		this.description = description;
	}


}
