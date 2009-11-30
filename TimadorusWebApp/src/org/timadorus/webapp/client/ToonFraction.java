package org.timadorus.webapp.client;

	import java.io.Serializable;

	import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.Persistent;
	public class ToonFraction implements Serializable {

	  /**
		 * 
		 */
	  private static final long serialVersionUID = 1L;
	  @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	  private Long fractionID=null;
	  private  String names=null;
	  private String description=null;

		 public ToonFraction() throws Exception {
			throw new Exception("no argument given"); 
		 }
		 
		

	  ToonFraction( String racename) {
	     names= racename;
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

	}
