package org.timadorus.webapp.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;

public class LoginPanel extends VerticalPanel {
	private final TimadorusWebApp appReference;
	
	public LoginPanel() throws Exception {
		throw new Exception("No parameter set");
	}
	
	public LoginPanel(TimadorusWebApp _appReference)
	{
		super();
		this.appReference = _appReference;
		initialize();
	}
	
	private void initialize() {
		final Grid loginGrid = new Grid(3,2); 
		
		final Button submitButton					= new Button("submit");
		final TextBox userNameTextBox				= new TextBox();
	    final PasswordTextBox userPasswordTextBox	= new PasswordTextBox();
	    
	    submitButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				appReference.isValidUserPasswordPair(userNameTextBox.getText(), userPasswordTextBox.getText());
			}
	    });
	    
		loginGrid.setWidget(0, 0, new Label("Username"));
		loginGrid.setWidget(1, 0, new Label("Password"));
		loginGrid.setWidget(0, 1, userNameTextBox);
		loginGrid.setWidget(1, 1, userPasswordTextBox);
		loginGrid.setWidget(2, 1, submitButton);
		
		this.add(new HTML("<h2>User login</h2>"));
		this.add(loginGrid);
	}
}
