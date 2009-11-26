package org.timadorus.webapp.server;

import java.util.HashSet;

import java.util.Set;

import javax.swing.text.html.HTML;

import org.timadorus.webapp.client.Toon;

public class ToonDataBaseSource implements I_ToonDataBaseSource {
	private Set<Toon> toonSet = null;

	public ToonDataBaseSource() {
		toonSet = new HashSet<Toon>();

		Toon toonForTest_1 = new Toon("test", "norsk_priest");
		Toon toonForTest_2 = new Toon("test","super_lowe");
		
		Toon toonForTest_3 = new Toon("test","super_diable");
		Toon toonForTest_4 = new Toon("test", "super_angel");
		Toon toonForTest_5 = new Toon("test", "fallen_angel"); 
		
		
		
		toonForTest_1.setGender("male");
		toonForTest_1.setRace("titan");
		toonForTest_1.setFraction("evil");
		toonForTest_1.setProffesion("black smith");

	
		toonForTest_2.setGender("female");
		toonForTest_2.setRace("angel");
		toonForTest_2.setFraction("evil");
		toonForTest_2.setProffesion("warrior");

		toonForTest_3.setGender("female");
		toonForTest_3.setRace("diable");
		toonForTest_3.setFraction("super");
		toonForTest_3.setProffesion("doctor");
		
		toonForTest_5.setGender("test");
		toonForTest_5.setRace("test");
		toonForTest_5.setFraction("test");
		toonForTest_5.setProffesion(" test");
		
		toonSet.add(toonForTest_1);
		toonSet.add(toonForTest_2);
		toonSet.add(toonForTest_3);
		toonSet.add(toonForTest_4);
		toonSet.add(toonForTest_5);
	

	}

	public Set<Toon> getToonsOfUser(String _userName) {
		Set<Toon> toonsOfUser = new HashSet<Toon>();

		for (Toon toonOfUser : this.toonSet)
			if (toonOfUser.getUsername().equals(_userName))
				toonsOfUser.add(toonOfUser);

		return toonsOfUser;	
	}

	public boolean createToon(Toon _toonOb)	{
		if (this.toonSet.contains(_toonOb)){
			return false;
		}
		
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
}
