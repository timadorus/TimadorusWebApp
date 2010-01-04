	package org.timadorus.webapp.client;
	
	import java.util.HashMap;
	import java.util.LinkedList;
	import java.util.List;
	import java.util.Map;
	
	
	public class ToonTestValue {
		
		Map <String, String> levelMap= new HashMap<String, String>();
		private Map<String , String> mapRace_Name_desc = new HashMap<String, String>();
		private Map<String , String> mapFraction_Name_desc = new HashMap<String, String>();
		private Map<String , String> mapProfession_Name_desc = new HashMap<String, String>();
		
	
		List<Level> LevelList = new LinkedList<Level>();
		List<ToonRace> raceList = new LinkedList<ToonRace>();
		List<ToonFraction> fractionList = new LinkedList<ToonFraction>();
		List<String> professionList = new LinkedList<String>();
	  
		public ToonTestValue()
		{
			 testValue();
		}
	
		
		public void testValue()
		{
		Level  level1 = new  Level("Heroes", 
		" someone who is strong, intelligent, handsome, and daring." +
		" Upon closer examination, many different qualities than these become apparent. Courage, honesty, bravery, selflessnes");


		Level level2 = new  Level("warrior", 
		"a person experienced in or capable of engaging in combat or warfare");



		Level level3 = new  Level("The warrior goddess", 
		"compare to Athena of Greek mythology ");
		

		levelMap.put(level1.getName(), level1.getDescription());
		levelMap.put(level2.getName(), level2.getDescription());
		levelMap.put(level3.getName(), level3.getDescription());

		LevelList.add(level1);
		LevelList.add(level2);
		LevelList.add(level3);
		
		//******************************************* ToonRace***********************************
		ToonRace toonRace1 = new ToonRace("Negritoes ");
		ToonRace toonRace2 = new ToonRace("Mongoloids");
		ToonRace toonRace3 = new ToonRace("Esquimaux");
		ToonRace toonRace4 = new ToonRace("Mongoloids");
		ToonRace toonRace5 = new ToonRace("Bushmen");
		ToonRace toonRace6 = new ToonRace("Melanochroi");
		
		mapRace_Name_desc.put(toonRace1.getNamen(), toonRace1.getDescription());
		mapRace_Name_desc.put(toonRace2.getNamen(), toonRace2.getDescription());
		
		mapRace_Name_desc.put(toonRace3.getNamen(), toonRace3.getDescription());
		
		mapRace_Name_desc.put(toonRace4.getNamen(), toonRace4.getDescription());
		
		mapRace_Name_desc.put(toonRace5.getNamen(), toonRace5.getDescription());
		
		mapRace_Name_desc.put(toonRace6.getNamen(), toonRace5.getDescription());
		
		raceList.add(toonRace1);
		raceList.add(toonRace2);
		raceList.add(toonRace3);
		raceList.add(toonRace4);
		raceList.add(toonRace5);
		raceList.add(toonRace6);
		
		//*************************************************** Fraction  ********************************
		ToonFraction  toonFraction1 = new  ToonFraction("test1");
		ToonFraction  toonFraction2 = new  ToonFraction("test2");
		ToonFraction  toonFraction3 = new  ToonFraction("test3");
		ToonFraction  toonFraction4 = new  ToonFraction("test4");
		ToonFraction  toonFraction5 = new  ToonFraction("test5");
		ToonFraction  toonFraction6 = new  ToonFraction("test6");
		toonFraction1.setdescription("Hall01");
		toonFraction2.setdescription("Hall02");
	    toonFraction3.setdescription("Hall03");
	    toonFraction4.setdescription("Hall04");
	    toonFraction5.setdescription("Hall05");
	    toonFraction6.setdescription("Hall06");
	    
	  
	    mapFraction_Name_desc.put(toonFraction1.getNames(),  toonFraction1.getDescription());
	    
	    mapFraction_Name_desc.put(toonFraction2.getNames(),  toonFraction2.getDescription());
	    
	    mapFraction_Name_desc.put(toonFraction3.getNames(),  toonFraction3.getDescription());
	    
	    mapFraction_Name_desc.put(toonFraction4.getNames(),  toonFraction4.getDescription());
	    
	    mapFraction_Name_desc.put(toonFraction5.getNames(),  toonFraction5.getDescription());
	    
	    mapFraction_Name_desc.put(toonFraction6.getNames(),  toonFraction6.getDescription());
	  
	    
		 fractionList.add( toonFraction1);
		 fractionList.add( toonFraction2);
		 fractionList.add( toonFraction3);
		 fractionList.add( toonFraction4);
		 fractionList.add( toonFraction5);
		 fractionList.add( toonFraction6);
		 
		 
		//*****************************************************Proffesion**************************
		 
		 professionList.add("Doctor");

		 professionList.add("informatiker");

		 professionList.add("Ingenieur");

		 professionList.add("Worker");

		 professionList.add("seller");
		 
		 mapProfession_Name_desc.put("Doctor", " Er hat das Leben der Patienten au seine Händen");
		 mapProfession_Name_desc.put(" informatiker", " Er muss sicher sein , seine  software Optimal ist");
		 mapProfession_Name_desc.put("ingenieur", "Er arbeite hart um seine Firma auf die Spitze zu bringen");
		 mapProfession_Name_desc.put("Worker", "Er muss auf einen Land arbeiten");
		 mapProfession_Name_desc.put("Seller", "test3");
		 
		 
		
		 
		
		}
		
		
		public Map<String, String> getLevelMap()
		{
			return levelMap;
		}

		public List<ToonRace> getRaceList()
		{
			return raceList;
		}


		public List<ToonFraction> getFractionList()
		{
			return fractionList;
		}

		public List<String> getProfessionList()
		{
			return professionList;
		}
		public Map<String, String> getMapRace_Name_desc()
		{
			return mapRace_Name_desc;
		}

		public Map<String, String> getMapFraction_Name_desc()
		{
			return mapFraction_Name_desc;
		}
			
		public Map<String, String> getlevelMap()
		{
			return levelMap;
		}
		public List<Level> getLevelList()
		{
			return LevelList;
		}
		public Map<String, String> getMapProfession_Name_desc() {
			return mapProfession_Name_desc;
		}
		
}