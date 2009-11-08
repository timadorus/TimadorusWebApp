package org.timadorus.webapp.client;

import com.google.appengine.repackaged.com.google.common.base.Flag.Long;
import com.google.appengine.repackaged.com.google.common.base.Flag.String;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ToonServiceAsync {

	void createToon( Toon _toonOb, AsyncCallback<Boolean>callback);
	void set_gender(String gender, AsyncCallback<Void>callback );

	void setFraktion(String fraktion, AsyncCallback<Void> callback);

	void setRace(String race, AsyncCallback<Void> callback) ;

	void setProfession(String profession,AsyncCallback<Void>back);

	void setToonID(Long toonID, AsyncCallback<Void>back) ;

	void getToonID(AsyncCallback<Long> callback);

	void getProfession(AsyncCallback<String> callback);

	void getRace(AsyncCallback<String> callback);

	void getFraktion(AsyncCallback<String> callback);

	void getGender(AsyncCallback<String> callback);

	void getName(AsyncCallback<String> callback);

	void getPresence(AsyncCallback<Integer> callback);

	void getIntution(AsyncCallback<Integer> callback);

	void getEmpathy(AsyncCallback<Integer> callback);

	void getQuickness(AsyncCallback<Integer> callback);

	void getStrength(AsyncCallback<Integer> callback);

	void getLuck(AsyncCallback<Integer> callback);

	void getReasoning(AsyncCallback<Integer> callback);

	void getMemory(AsyncCallback<Integer> callback);

	void getSelfDiscipline(AsyncCallback<Integer> callback);

	void getAgility(AsyncCallback<Integer> callback);

	void getConstitution(AsyncCallback<Integer> callback);

	void getUserIF(AsyncCallback<Long> callback);
	void getSerialversionuid(AsyncCallback<Long> callback);

}
