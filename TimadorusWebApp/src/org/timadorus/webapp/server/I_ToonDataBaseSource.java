package org.timadorus.webapp.server;

public interface I_ToonDataBaseSource {

	void toonInitialize( String _toonName);
	void setToonRace( String _race);
	void setToonName(int _tempPoints, int _statPoints, int _statsPoints, int _raceBonus);
	void setToonName(String _toonName);
	void toonCommit();

}
