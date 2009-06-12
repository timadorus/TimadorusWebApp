package org.timadorus.webapp.client;

import org.timadorus.webapp.client.rpc.service.RegisterService;
import org.timadorus.webapp.client.rpc.service.RegisterServiceAsync;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;

public class RegisterPanel extends FormPanel {
	Grid grid = new Grid(9, 2);
    Button submit = new Button("Registrieren");
	private TextBox vornameTextBox = new TextBox();
	private TextBox nachnameTextBox = new TextBox();
	private TextBox geburtstagTextBox = new TextBox();
	private TextBox emailTextBox = new TextBox();
	private TextBox emailRepeatTextBox = new TextBox();
	private TextBox usernameTextBox = new TextBox();
	private PasswordTextBox passwordTextBox = new PasswordTextBox();
	private PasswordTextBox passwordRepeatTextBox = new PasswordTextBox();

    public RegisterPanel() {
        super();

    	grid.setWidget(0, 0, new Label("Vorname"));
    	grid.setWidget(1, 0, new Label("Nachname"));
    	grid.setWidget(2, 0, new Label("Geburtstag (dd.mm.jjjj)"));
    	grid.setWidget(3, 0, new Label("Email"));
    	grid.setWidget(4, 0, new Label("Email (Wiederholung)"));
    	grid.setWidget(5, 0, new Label("Benutzername"));
    	grid.setWidget(6, 0, new Label("Passwort"));
    	grid.setWidget(7, 0, new Label("Passwort (Wiederholung)"));

    	grid.setWidget(0, 1, vornameTextBox);
    	grid.setWidget(1, 1, nachnameTextBox);
    	grid.setWidget(2, 1, geburtstagTextBox);
    	grid.setWidget(3, 1, emailTextBox);
    	grid.setWidget(4, 1, emailRepeatTextBox);
    	grid.setWidget(5, 1, usernameTextBox);
    	grid.setWidget(6, 1, passwordTextBox);
    	grid.setWidget(7, 1, passwordRepeatTextBox);
    	
    	grid.setWidget(8, 1, submit);

    	vornameTextBox.setText("Vorname");
    	nachnameTextBox.setText("Nachname");
    	geburtstagTextBox.setText("01.01.1980");
    	emailTextBox.setText("me@home.de");
    	emailRepeatTextBox.setText("me@home.de");
    	usernameTextBox.setText("Username");
    	passwordTextBox.setText("passwort");
    	passwordRepeatTextBox.setText("passwort");

        // Create a handler for the sendButton and nameField
		class MyHandler implements ClickHandler, KeyUpHandler {
			/**
			 * Wird ausgelöst, wenn Button gedrückt wurde
			 */
			public void onClick(ClickEvent event) {
				handleEvent();
			}

			/**
			 * Prüft ob "Enter" gedrückt wurde
			 */
			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					handleEvent();
				}
			}

			private void handleEvent() {
				submit.setEnabled(false);
				User register = new User(
											vornameTextBox.getText(),
											nachnameTextBox.getText(),
											geburtstagTextBox.getText(),
											emailTextBox.getText(),
											usernameTextBox.getText(),
											passwordTextBox.getText()
										);

				if(!register.isValid()) {
					registerInvalid("Bitte alle Felder ausfüllen");
				} else if(!emailTextBox.getText().equals(emailRepeatTextBox.getText())) {
					registerInvalid("Email-Adresse und Wiederholung stimmen nicht überein");
				} else if(!passwordTextBox.getText().equals(passwordRepeatTextBox.getText())) {
					registerInvalid("Passwort und Wiederholung stimmen nicht überein");
				} else {
					sendToServer(register);
				}
				submit.setEnabled(true);
			}
			
			/**
			 * Formular-Inhalt an Server senden
			 */
			private void sendToServer(User register) {
				RegisterServiceAsync registerServiceAsync = GWT.create(RegisterService.class);
				AsyncCallback<String> asyncCallback = new AsyncCallback<String>() {
					public void onSuccess(String result) {
						if(result != null) {// && result.equals(String.valueOf(Registration.OK))){
							int value = Integer.parseInt(result);
							if(value == User.OK) {
								RootPanel.get("content").clear();
								RootPanel.get("content").add(new Label("Registriert..."));
							} else {
								if(value >= User.PASSWORD_FAULT) {
									value-=User.PASSWORD_FAULT;
									// Derzeit nicht genutzt; darf nicht vorkommen
									//registerInvalid("");
								} else if(value >= User.USERNAME_FAULT) {
									value-=User.USERNAME_FAULT;
									registerInvalid("Der Username ist bereits vergeben");
								} else if(value >= User.EMAIL_FAULT) {
									value-=User.EMAIL_FAULT;
									// Derzeit nicht genutzt; darf nicht vorkommen
									// TODO: Wohlgeformtheit der Email-Adresse feststellen
									//registerInvalid("");
								} else if(value >= User.GEBURTSTAG_AGE) {
									value-=User.GEBURTSTAG_AGE;
									registerInvalid("Du bist zu jung! Mindestalter 18 Jahre");
								} else if(value >= User.GEBURTSTAG_FORMAT) {
									value-=User.GEBURTSTAG_FORMAT;
									registerInvalid("Format des Geburtstags ungültig: 'dd.mm.jjjj' erwartet");
								} else if(value >= User.GEBURTSTAG_FAULT) {
									value-=User.GEBURTSTAG_FAULT;
									registerInvalid("Fehler beim ermitteln des Geburtstags.");
								}
								submit.setEnabled(true);
							}
						} else {
							submit.setEnabled(true);
						}
					}
					public void onFailure(Throwable caught) {
						Window.alert(caught.toString());
						System.out.println(caught);
					}
				};
				registerServiceAsync.register(register, asyncCallback);
			}
		}

		MyHandler handler = new MyHandler();
		submit.addClickHandler(handler);
        
        setWidget(grid);
        setStyleName("formPanel");
    }
    
    /**
     * In dieser Methode wird das Ereignis "Registrierung ungültig" verarbeitet.
     */
    public void registerInvalid(String error) {
    	Window.alert(error);
    }
}
