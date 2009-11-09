package org.timadorus.webapp.server;


import org.timadorus.webapp.client.Toon;

public interface I_ToonDataBaseSource {

	boolean createToon( Toon _toonOb);

	void toonInitialize( String _toonName);
	void setToonRace( String _race);
	//void setToonPoints(int _tempPoints, int _statPoints, int _statsPoints, int _raceBonus);
	Toon getToonByName(String _toonName);
	void toonCommit();
	
	
}
