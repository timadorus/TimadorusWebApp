package org.timadorus.webapp.client;


import java.util.Set;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ToonServiceAsync {

	void createToon( Toon _toonOb, AsyncCallback<Boolean>callback);
	void setToonFeacture(Toon toonObj, AsyncCallback<Boolean>callback) ;
	void toonCommit(AsyncCallback<Void> callback);
	void toonInitialize(String _toonName, AsyncCallback<Void> callback);
	void getToonInformation(String _toonName, AsyncCallback<Toon> callback);
	void getToonsOfUser(String _userName, AsyncCallback<Set<Toon>> callback);
	

}
