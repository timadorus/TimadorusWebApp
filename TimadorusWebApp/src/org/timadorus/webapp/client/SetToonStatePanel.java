package org.timadorus.webapp.client;

import com.google.gwt.event.dom.client.ClickEvent;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class SetToonStatePanel extends VerticalPanel {

	
	private  TimadorusWebApp appReference;
	private User user ;
	private  String toonName="";
	private CreateToonPanel panel;
	
	
	public SetToonStatePanel() throws Exception {
		throw new Exception("No parameter set");
	}

	public SetToonStatePanel(TimadorusWebApp _appReference) {
		super();
		appReference  = _appReference;
		// user= username;
		//this.toonName = tooname;
		System.out.println("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhueuuiooooooooooo");
		initialize();
	  
	}

	private void initialize()
	{
     if(appReference.getUserLoggedIn() && appReference.getToonCreateIn()==1)
     {
    
		final Grid 	  toonCharaterCreateGrid			= new Grid(5, 2);
		//final TextBox nameTextBox						= new TextBox();
		final TextBox genderTextBox						= new TextBox();
		final TextBox fractionBox						= new TextBox();
		
		final TextBox RaceTextBox						= new TextBox();
		final TextBox proffesionTextBox					= new TextBox();
		

		final Button submitButton						= new Button("next");
    
		submitButton.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event)
			{
			    user = panel.getUser();
			    toonName= panel.getToonName();
				Toon toonObj = new Toon( user.getSurname(),toonName);
         
				toonObj.setGender(genderTextBox.getText());
				toonObj.setFraction(fractionBox.getText());
				toonObj.setRace(RaceTextBox.getText());
				toonObj.setProffesion(proffesionTextBox.getText());
			
				appReference.createToon(toonObj);
			}
		
		});
		
			   
		toonCharaterCreateGrid.setWidget(0, 0, new Label("Gender"));
		toonCharaterCreateGrid.setWidget(0, 1,  genderTextBox);
		
		toonCharaterCreateGrid.setWidget(1, 0, new Label("Race"));
		toonCharaterCreateGrid.setWidget(1, 1, RaceTextBox);
			   
			   
		toonCharaterCreateGrid.setWidget(2, 0, new Label("Fraction"));
		toonCharaterCreateGrid.setWidget(2, 1, fractionBox);
			   
		toonCharaterCreateGrid.setWidget(3, 0, new Label("Proffesion"));
		toonCharaterCreateGrid.setWidget(3, 1, proffesionTextBox);
		

		toonCharaterCreateGrid.setWidget(4, 1, submitButton);
	    this.add(new HTML("<h2>Please Enter Your Toon feacture  </h2>"));
	    this.add(toonCharaterCreateGrid);
		       
		           
		       
    }
	}
	
}
