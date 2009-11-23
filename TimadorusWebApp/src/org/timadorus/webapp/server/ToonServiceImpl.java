package org.timadorus.webapp.server;

import java.util.Set;

import org.timadorus.webapp.client.Toon;
import org.timadorus.webapp.client.ToonService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class ToonServiceImpl extends RemoteServiceServlet implements ToonService {
	private static final long serialVersionUID = 5362197212261054307L;
	
	private final ToonDataBaseSource dataBaseSource = new ToonDataBaseSource();
	
	public Boolean createToon(Toon toonOb){
		
		return dataBaseSource.createToon(toonOb);
	}

	public Toon getToonInformation(String toonName)
	{
		
		return dataBaseSource.getToonByName(toonName);
	}

	public void setToonRace(String race) {
		// TODO Auto-generated method stub
		
	}

	public void toonCommit() {
		// TODO Auto-generated method stub
		
	}

	public void toonInitialize(String _toonName) {
	     dataBaseSource.getToonByName(_toonName);
		
	}

	public Set<Toon> getToonsOfUser(String _userName) {		
		return dataBaseSource.getToonsOfUser(_userName);
	}
}