package org.timadorus.webapp.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;



public class CreateToonPanel extends VerticalPanel {
	private final TimadorusWebApp appReference;
	private final User user = new User("");
	private String toonName="";
	public CreateToonPanel() throws Exception {
		throw new Exception("No parameter set");
	}

	public CreateToonPanel(TimadorusWebApp _appReference) {
		super();
		appReference = _appReference;
		initialize();
	  
	}



	private void initialize()
	{
    
		final Grid 	  toonCreateGrid					= new Grid(2, 2);
		final TextBox nameTextBox						= new TextBox();
    
		final Button submitButton						= new Button("Toon create");
    
		submitButton.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event)
			{
				if(appReference.getUserLoggedIn())
				{
				Toon toonObj = new Toon( user.getSurname(),nameTextBox.getText());
				toonName= nameTextBox.getText().toString();
				System.out.println("toonName"+ toonName);
				appReference.createToon(toonObj);
				}
			}
		
		});
		
			   toonCreateGrid.setWidget(0, 0, new Label("Name"));
			   toonCreateGrid.setWidget(0, 1, nameTextBox);

			   toonCreateGrid.setWidget(1, 1, submitButton);
			 
		       this.add(toonCreateGrid);

		       toonCharacterInitialize();
    }
	
	
	private void toonCharacterInitialize()
	{
     if(appReference.getUserLoggedIn() && appReference.getToonCreateIn()==1)
     {
    
		final Grid 	  toonCharaterCreateGrid			= new Grid(4, 2);
		final TextBox nameTextBox						= new TextBox();
		final TextBox genderTextBox						= new TextBox();
		final TextBox fractionBox						= new TextBox();
		
		final TextBox RaceTextBox						= new TextBox();
		final TextBox proffesionTextBox					= new TextBox();
		

		final Button submitButton						= new Button("Toon create");
    
		submitButton.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event)
			{
				Toon toonObj = new Toon( user.getSurname(),toonName);

				toonObj.setName(toonName);
				toonObj.setGender(genderTextBox.getText());
				toonObj.setFraction(fractionBox.getText());
				toonObj.setRace(RaceTextBox.getText());
				toonObj.setProffesion(proffesionTextBox.getText());
				
				
				appReference.createToon(toonObj);
			}
		
		});
		
		toonCharaterCreateGrid.setWidget(0, 0, new Label("Name"));
		toonCharaterCreateGrid.setWidget(0, 1, nameTextBox);
			   
		toonCharaterCreateGrid.setWidget(1, 0, new Label("Gender"));
		toonCharaterCreateGrid.setWidget(1, 1,  genderTextBox);
			   
		toonCharaterCreateGrid.setWidget(2, 0, new Label("Fraction"));
		toonCharaterCreateGrid.setWidget(2, 1, fractionBox);
			   
		toonCharaterCreateGrid.setWidget(3, 0, new Label("Proffesion"));
		toonCharaterCreateGrid.setWidget(3, 1, proffesionTextBox);
		

		toonCharaterCreateGrid.setWidget(4, 1, submitButton);
			   this.add(new HTML("<h2>Your Toon  are created </h2>"));
		       this.add(toonCharaterCreateGrid);
		       
		           
		       
    }
	}
	
	}



