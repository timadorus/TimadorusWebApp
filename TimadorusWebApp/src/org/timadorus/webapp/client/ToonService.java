package org.timadorus.webapp.client;

import java.util.Set;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("toon")
public interface ToonService extends RemoteService {
	Boolean createToon(Toon _toonOb);
	void toonInitialize( String _toonName);
	 Boolean setToonFeacture(Toon toonObj);
	//void setToonPoints(int _tempPoints, int _statPoints, int _statsPoints, int _raceBonus);
	Toon getToonInformation(String _toonName);
	Set<Toon> getToonsOfUser(String _userName);
	void toonCommit();
	



}
