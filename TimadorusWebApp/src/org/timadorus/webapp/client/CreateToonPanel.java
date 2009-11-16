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


	public CreateToonPanel() throws Exception {
		throw new Exception("No parameter set");
	}

	public CreateToonPanel(TimadorusWebApp _appReference) {
		super();
		appReference = _appReference;
		initialize();
	}



	private void initialize(){
    

		final Grid 	  toonCreateGrid					= new Grid(2, 2);
		final TextBox nameTextBox						= new TextBox();
    

   

		final Button submitButton						= new Button("Toon create");
    
    
		submitButton.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event)
			{

				Toon toonObj = new Toon( user.getSurname(),nameTextBox.getText());

				appReference.createToon(toonObj);
			}
		
		});
		
				toonCreateGrid.setWidget(0, 0, new Label("Name"));
				toonCreateGrid.setWidget(0, 1, nameTextBox);

		toonCreateGrid.setWidget(2, 1, submitButton);
		this.add(new HTML("<h2>Toon create </h2>"));
		this.add(toonCreateGrid);
    }
	}





//final TextBox GenderTextBox						= new TextBox();
//final TextBox fractionBox						= new TextBox();
//
//
//final TextBox RaceTextBox						= new TextBox();
//final TextBox proffesionTextBox					= new TextBox();
//


////if(appReference.isToonCreateIn()){
//
//					toonObj.setGender(GenderTextBox.getText());
//
//					toonObj.setFraction(fractionBox.getText());
//					toonObj.setRace(RaceTextBox.getText());
//
//					toonObj.setProffesion(proffesionTextBox.getText());
//
//					toonObj.setCommitFlag(true);
//
//				//}







//
//toonCreateGrid.setWidget(1, 0, new Label("Gender"));
//toonCreateGrid.setWidget(1, 1, GenderTextBox);
//
//toonCreateGrid.setWidget(2, 0, new Label("Fraction"));
//toonCreateGrid.setWidget(2, 1, fractionBox);
//
//toonCreateGrid.setWidget(3, 0, new Label("Race"));
//	toonCreateGrid.setWidget(3, 1, RaceTextBox);
//
//	toonCreateGrid.setWidget(4, 0, new Label("Proffesion"));
//	toonCreateGrid.setWidget(4, 1, proffesionTextBox);

