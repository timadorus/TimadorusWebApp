package org.timadorus.webapp.client;

	import java.io.Serializable;
import java.util.ArrayList;

	import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.Persistent;
	public class ToonFraction implements Serializable {

	  /**
		 * 
		 */
	  private static final long serialVersionUID = 1L;
	
	  private ArrayList <String>  fractionList =  new ArrayList<String>();
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	  private Long fractionID=null;
	  private  String names=null;
	  private String description=null;

		 public ToonFraction() throws Exception {
			throw new Exception("no argument given"); 
		 }
		 
		

	  ToonFraction( String fractioname) {
	     names= fractioname;
	     fractionList.add(fractioname);
	  }

	
	  
	  public Long getFractionID() {
	    return fractionID;
	  }

	  public void setFractionID(Long fractionID) {
	    this.fractionID = fractionID;
	  }

	  public String getNames() {
	    return names;
	  }

	  public void setNames(String name) {
	    this.names = name;
	  }

	  public String getDescription() {
	    return description;
	  }

	  public void setdescription(String description) {
	    this.description = description;
	  }
	 
	  public ArrayList<String> getFractionList() {
		return fractionList;
	}
	}
