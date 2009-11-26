package org.timadorus.webapp.client;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;

public class StartPanel extends VerticalPanel {
	private final TimadorusWebApp appReference;
	
	public StartPanel() throws Exception {
		throw new Exception("No parameter set");
	}
	
	public StartPanel(TimadorusWebApp _appReference) {
		super();
		this.appReference = _appReference;
		initialize();
	}
	
	private void initialize() {
		this.add(new HTML("<h2>Start</h2>"));
		if (this.appReference.getUserLoggedIn())
		{
//			 if(this.appReference.getToonCreateIn()==1)
//			 {	 
//				 this.add(new HTML("You Toon name was saved: "+this.appReference.getToonToCreateInUserObject().getName()));
//			 }else if(this.appReference.getToonCreateIn()==2){
//				 this.add(new HTML("Complet to create your Toon: "+this.appReference.getToonToCreateInUserObject().getName()));
//			 }
//			 
			 this.add(new HTML("You are logged in as: "+this.appReference.getLoggedInUsername()));
		}else
			this.add(new HTML("Please login or register first."));
		
		
	}
}
