package org.timadorus.webapp.client;

import com.google.appengine.repackaged.com.google.common.base.Flag.Long;
import com.google.appengine.repackaged.com.google.common.base.Flag.String;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("toon")
public interface ToonService extends RemoteService {

	Long getSerialversionuid();
	
	boolean createToon( Toon _toonOb);


	Long getUserIF() ;


	Integer getConstitution() ;

	Integer getAgility() ;

	Integer getSelfDiscipline() ;

	Integer getMemory() ;


	Integer getReasoning() ;


	Integer getLuck() ;

	Integer getStrength();


	Integer getQuickness() ;

	Integer getEmpathy() ;


	Integer getIntution();


	Integer getPresence();


	String getName() ;



	String getGender() ;


	void set_gender(String gender);

	String getFraktion() ;

	void setFraktion(String fraktion);


	String getRace() ;

	void setRace(String race) ;


	String getProfession();

	void setProfession(String profession);


	Long getToonID();

	void setToonID(Long toonID) ;

}
