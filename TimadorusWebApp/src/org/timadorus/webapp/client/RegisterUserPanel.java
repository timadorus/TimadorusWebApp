package org.timadorus.webapp.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class RegisterUserPanel extends VerticalPanel {
	private final TimadorusWebApp appReference;
	
	public RegisterUserPanel() throws Exception {
		throw new Exception("No parameter set");
	}
	
	public RegisterUserPanel(TimadorusWebApp _appReference) {
		super();
		this.appReference = _appReference;
		initialize();
	}
	
	private void initialize() {
		final Grid registerGrid							= new Grid(8, 2);
		final TextBox firstnameTextBox					= new TextBox();
		final TextBox surnameTextBox					= new TextBox();
		final TextBox birthdayTextBox					= new TextBox();
		final TextBox emailTextBox						= new TextBox();
		final TextBox userNameTextBox					= new TextBox();
		final PasswordTextBox userPasswordTextBox		= new PasswordTextBox();
		final PasswordTextBox userPasswordRepeatTextBox = new PasswordTextBox();
	    final Button submitButton						= new Button("register");
		
	    submitButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				User userObj = new User(userNameTextBox.getText());
				userObj.setFirstname(firstnameTextBox.getText());
				userObj.setSurname(surnameTextBox.getText());
				userObj.setBirthday(birthdayTextBox.getText());
				userObj.setPassword(userPasswordTextBox.getText());
				userObj.setEmail(emailTextBox.getText());
				
				appReference.registerUser(userObj);
			}
		});
	    
		registerGrid.setWidget(0, 0, new Label("Firstname"));
		registerGrid.setWidget(0, 1, firstnameTextBox);
		registerGrid.setWidget(1, 0, new Label("Surname"));
		registerGrid.setWidget(1, 1, surnameTextBox);
		registerGrid.setWidget(2, 0, new Label("Birthday"));
		registerGrid.setWidget(2, 1, birthdayTextBox);
		registerGrid.setWidget(3, 0, new Label("E-Mail"));
		registerGrid.setWidget(3, 1, emailTextBox);
		registerGrid.setWidget(4, 0, new Label("Username"));
		registerGrid.setWidget(4, 1, userNameTextBox);
		registerGrid.setWidget(5, 0, new Label("Password"));
		registerGrid.setWidget(5, 1, userPasswordTextBox);
		registerGrid.setWidget(6, 0, new Label("Repeat Password"));
		registerGrid.setWidget(6, 1, userPasswordRepeatTextBox);

		registerGrid.setWidget(7, 1, submitButton);
		
		this.add(new HTML("<h2>Register User</h2>"));
		this.add(registerGrid);
	}
}
