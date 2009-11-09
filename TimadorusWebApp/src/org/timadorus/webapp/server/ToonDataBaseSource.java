package org.timadorus.webapp.server;

import java.util.HashSet;
import java.util.Set;

import org.timadorus.webapp.client.Toon;


import com.google.appengine.repackaged.com.google.common.base.Flag.String;

public class ToonDataBaseSource implements I_ToonDataBaseSource {
private Set<Toon> toonSet = null;
	
	public ToonDataBaseSource() {
		toonSet = new HashSet<Toon>();	
		createToon(new Toon("test", "test", "test", " test ", " test "));// TODO
		createToon(new Toon("test"));
		
	}

	public boolean createToon(Toon _toonOb)
	{
		if (this.toonSet.contains(_toonOb))
			return false;
		
		this.toonSet.add(_toonOb);
		
		return true;
	}
	

	public Toon getToonByName(String _toonName)
	{
		for (Toon tmptoon : this.toonSet)
		if (tmptoon.getName().equals(_toonName))
			return tmptoon;
	
	return null;
}
	

	public void setToonRace(String race)
	{
		// TODO Auto-generated method stub
		
	}

	public void toonCommit() {
		// TODO Auto-generated method stub
		
	}

	public void toonInitialize(String toonName)
	{
		// TODO Auto-generated method stub
		
	}

	public Toon getToonByName(java.lang.String toonName) {
		// TODO Auto-generated method stub
		return null;
	}

	public void setToonRace(java.lang.String race) {
		// TODO Auto-generated method stub
		
	}

	public void toonInitialize(java.lang.String toonName) {
		// TODO Auto-generated method stub
		
	}

	

	

}
