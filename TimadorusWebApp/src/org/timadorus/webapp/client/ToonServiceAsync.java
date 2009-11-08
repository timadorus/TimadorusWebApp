package org.timadorus.webapp.client;


import com.google.appengine.repackaged.com.google.common.base.Flag.String;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ToonServiceAsync {

	void createToon( Toon _toonOb, AsyncCallback<Boolean>callback);



	void setToonRace(String _race, AsyncCallback<Void> callback);

	void toonCommit(AsyncCallback<Void> callback);

	void toonInitialize(String _toonName, AsyncCallback<Void> callback);

	//void setToonPoints(int _tempPoints, int _statPoints, int _statsPoints,
		//	int _raceBonus, AsyncCallback<Void> callback);


	void getToonInformation(String _toonName, AsyncCallback<Toon> callback);
	

}
