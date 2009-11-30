	package org.timadorus.webapp.client;

    import java.io.Serializable;

	
	
   public class ToonClass implements Serializable 
	{
		
	private String name=null;
	
	private String description=null;
	
	private static final long serialVersionUID = 1L;

	public ToonClass() throws Exception {
		throw new Exception("No parameter set");
	}
	
	
	public ToonClass(String name, String description) {
	
	  this.name = name;
	  this.description = description;
	
	}
	
	public String getName() {
	  return name;
	}
	
	public void setName(String name) {
	  this.name = name;
	}
	
	public String getDescription() {
	  return description;
	}
	
	public void setDescription(String description) {
	  this.description = description;
	}
	

	}
