package org.timadorus.webapp.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("toon")
public interface ToonService extends RemoteService {
	Boolean createToon(Toon _toonOb);
	void toonInitialize( String _toonName);
	void setToonRace( String _race);
	//void setToonPoints(int _tempPoints, int _statPoints, int _statsPoints, int _raceBonus);
	Toon getToonInformation(String _toonName);
	void toonCommit();
	



}
