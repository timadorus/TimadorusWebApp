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
public class Race implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	Long raceID;
	
	@Persistent
	String name;
	
	@Persistent
	String description;
	

	public Race() {
		super();
	}
	
	
	public Long getRaceID() {
		return raceID;
	}


	public void setRaceID(Long raceID) {
		this.raceID = raceID;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}


	public void setdescription(String description) {
		this.description = description;
	}



}
