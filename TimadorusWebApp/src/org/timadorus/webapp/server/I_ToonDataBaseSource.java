package org.timadorus.webapp.server;


import org.timadorus.webapp.client.Toon;

public interface I_ToonDataBaseSource {

	void initializeToon(String _toonName);
	

	
	void commitToon(String _toonName);
	
	Toon getToonByName(String _toonName);
}
