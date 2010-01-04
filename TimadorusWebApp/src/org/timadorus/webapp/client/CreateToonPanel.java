package org.timadorus.webapp.client;

import java.util.HashSet;
import java.util.Set;

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
	private Set<String> toonNameSet =new HashSet<String>();

	private String toonName;

	SetToonLevelPanel setPanel;
	public CreateToonPanel() throws Exception {
		throw new Exception("No parameter set");
	}

	public CreateToonPanel(TimadorusWebApp _appReference)
	{
		super();
		appReference =_appReference;
		initialize();

	}

	private void initialize()
	{

		final Grid 	  toonCreateGrid					= new Grid(2, 2);
		final TextBox nameTextBox						= new TextBox();

		final Button submitButton						= new Button("next");

		submitButton.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event)
			{

				if(appReference.getUserLoggedIn())
				{
					Toon toonObj = new Toon( user.getSurname(),nameTextBox.getText());
					toonName= nameTextBox.getText().toString();
					if(toonNameSet.contains(toonName)){
						System.err.println("Toon name is now used, Please choice a new name for Your Toon");

					}else{
						toonNameSet.add(toonName);

					}
					System.out.println("toonName     "+ toonName);
					appReference.createToon(toonObj);

				}

			}



		});


		toonCreateGrid.setWidget(0, 0, new Label("Name"));
		toonCreateGrid.setWidget(0, 1, nameTextBox);

		toonCreateGrid.setWidget(1, 1, submitButton);
		this.add(new HTML("Toon name "));
		this.add(toonCreateGrid);

	}


	public User getUser() {
		return user;
	}



	public String getToonName() {
		return toonName;
	}


	public TimadorusWebApp getappReference() {
		return appReference;
	}
}



