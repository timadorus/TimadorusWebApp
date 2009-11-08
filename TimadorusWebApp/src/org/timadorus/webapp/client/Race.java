package org.timadorus.webapp.client;
/**
 * 
 */


import java.io.Serializable;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/**
 * @author Manto Mireille
 *
 */
public class Race implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long _raceID= null;
	private String _toonNamen= null;
    private String _description= null;
	
	public Race() {
		super();
	}
	
	public Long getRaceID() {

		return _raceID;
	}

	public void setRaceID(Long _raceID) {
		this._raceID = _raceID;
	}


	public String getNamen() {
		return _toonNamen;
	}


	public void setNamen(String _name) {
		this._toonNamen =_name;
	}


	public String getDescription() {
		return _description;
	}


	public void setDescription(String description) {
	this._description = description;
	}



}
