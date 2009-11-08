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

	public CreateToonPanel() throws Exception {
		throw new Exception("No parameter set");
	}

	public CreateToonPanel(TimadorusWebApp _appReference) {
		super();
		this.appReference = _appReference;
		initialize();
	}

	

	private void initialize(){

		final Grid 	  toonCreateGrid					= new Grid(8, 2);
		final TextBox nameTextBox						= new TextBox();
		final TextBox IDTextBox							= new TextBox();
		final TextBox userIfTextBox						= new TextBox();

		final TextBox GenderTextBox						= new TextBox();
		final TextBox fraktionBox						= new TextBox();


		final TextBox RaceTextBox						= new TextBox();
		final TextBox professionTextBox					= new TextBox();
		final TextBox constitionBox						= new TextBox();

		final TextBox agilityTextBox					= new TextBox();
		final TextBox selDisciplineTextBox				= new TextBox();
		final TextBox memoryBox							= new TextBox();


		final TextBox reasoningBox					    = new TextBox();
		final TextBox luckBox							= new TextBox();
		final TextBox strengthBox						= new TextBox();


		final TextBox quicknessBox						= new TextBox();
		final TextBox empathyBox						= new TextBox();
		final TextBox intutionBox						= new TextBox();
		final TextBox presenceBox						= new TextBox();


		final Button submitButton						   = new Button("Toon create");

		submitButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				Toon toonObj = new Toon(nameTextBox.getText());
				toonObj.set_gender(GenderTextBox.getText());
				toonObj.setFraktion(fraktionBox.getText());
				toonObj.setRace(RaceTextBox.getText());

				toonObj.setCommitFlag(true);


			appReference.createToon(toonObj);
			}
		});



		toonCreateGrid.setWidget(0, 0, new Label("ID"));
		toonCreateGrid.setWidget(0, 1, IDTextBox);
		toonCreateGrid.setWidget(1, 0, new Label("UserIF"));
		toonCreateGrid.setWidget(1, 1, userIfTextBox);
		toonCreateGrid.setWidget(2, 0, new Label("Name"));
		toonCreateGrid.setWidget(2, 1, nameTextBox);
		toonCreateGrid.setWidget(3, 0, new Label("Gender"));
		toonCreateGrid.setWidget(3, 1, GenderTextBox);
		toonCreateGrid.setWidget(4, 0, new Label("fraktion"));
		toonCreateGrid.setWidget(4, 1, fraktionBox);
		toonCreateGrid.setWidget(5, 0, new Label("Race"));
		toonCreateGrid.setWidget(5, 1, RaceTextBox);
		toonCreateGrid.setWidget(6, 0, new Label("Profession"));
		toonCreateGrid.setWidget(6, 1, professionTextBox);

		toonCreateGrid.setWidget(6, 0, new Label("constition"));
		toonCreateGrid.setWidget(6, 1, constitionBox);

		toonCreateGrid.setWidget(6, 0, new Label("agility"));
		toonCreateGrid.setWidget(6, 1, agilityTextBox);

		toonCreateGrid.setWidget(6, 0, new Label("SelDiscipline"));
		toonCreateGrid.setWidget(6, 1, selDisciplineTextBox);

		toonCreateGrid.setWidget(6, 0, new Label("memory"));
		toonCreateGrid.setWidget(6, 1, memoryBox);
		
		
		
		toonCreateGrid.setWidget(6, 0, new Label("reasoning"));
		toonCreateGrid.setWidget(6, 1, reasoningBox);
		
		
		toonCreateGrid.setWidget(6, 0, new Label("Luck"));
		toonCreateGrid.setWidget(6, 1, luckBox);
		
		toonCreateGrid.setWidget(6, 0, new Label("Strength"));
		toonCreateGrid.setWidget(6, 1, strengthBox);
		
		toonCreateGrid.setWidget(6, 0, new Label("quickness"));
		toonCreateGrid.setWidget(6, 1, quicknessBox);
	
		toonCreateGrid.setWidget(6, 0, new Label("empathy"));
		toonCreateGrid.setWidget(6, 1, empathyBox);
		
		toonCreateGrid.setWidget(6, 0, new Label("intution"));
		toonCreateGrid.setWidget(6, 1,  intutionBox);

		toonCreateGrid.setWidget(6, 0, new Label("Presence"));
		toonCreateGrid.setWidget(6, 1, presenceBox);
		
		
		toonCreateGrid.setWidget(7, 1, submitButton);
		this.add(new HTML("<h2>Toon create </h2>"));
		this.add(toonCreateGrid);

	}
}
