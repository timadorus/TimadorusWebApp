	package org.timadorus.webapp.client;

    import java.io.Serializable;

	
	
   public class Level implements Serializable 
	{
		
	private String name=null;
	
	private String description=null;
	
	private static final long serialVersionUID = 1L;

	public Level() throws Exception {
		throw new Exception("No parameter set");
	}
	
	
	public Level(String name, String description) {
	
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
