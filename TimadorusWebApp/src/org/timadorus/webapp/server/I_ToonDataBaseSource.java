package org.timadorus.webapp.server;


import org.timadorus.webapp.client.Toon;

public interface I_ToonDataBaseSource {

	void initializeToon(String _toonName);
	
	/*
	void setToonGender(String _gender);	
	void setToonRace(String _race);
	void setToonFraction(String _fraction);
	void setToonProffesion(String _profesion);
	*/
	
	void commitToon(String _toonName);
	
	Toon getToonByName(String _toonName);
}
