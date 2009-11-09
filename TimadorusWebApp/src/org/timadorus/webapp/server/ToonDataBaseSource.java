package org.timadorus.webapp.server;

import java.util.HashSet;
import java.util.Set;

import org.timadorus.webapp.client.Toon;

public class ToonDataBaseSource implements I_ToonDataBaseSource {
private Set<Toon> toonSet = null;
	
	public ToonDataBaseSource() {
		toonSet = new HashSet<Toon>();
		
		Toon toonForTest_1 = new Toon("test", "norsk_priest", "male", "evil", "titans", "black smith"); 
		Toon toonForTest_2 = new Toon("test", "fallen_angel", "female", "evil", "angel", "warrior"); 
		
		toonSet.add(toonForTest_1);
		toonSet.add(toonForTest_2);
	}

	public boolean createToon(Toon _toonOb)	{
		if (this.toonSet.contains(_toonOb))
			return false;
		
		this.toonSet.add(_toonOb);
		
		return true;
	}
	

	public Toon getToonByName(String _toonName)	{
		for (Toon tmptoon : this.toonSet)
		if (tmptoon.getName().equals(_toonName))
			return tmptoon;
	
		return null;
	}

	public void commitToon(String toonName) {
		// TODO Auto-generated method stub
		
	}

	public void initializeToon(String toonName) {
		// TODO Auto-generated method stub
		
	}

	public void setToonFraction(String fraction) {
		// TODO Auto-generated method stub
		
	}

	public void setToonGender(String gender) {
		// TODO Auto-generated method stub
		
	}

	public void setToonProffesion(String profesion) {
		// TODO Auto-generated method stub
		
	}

	public void setToonRace(String race) {
		// TODO Auto-generated method stub
		
	}
	
}
