package org.timadorus.webapp.client;

import java.util.Date;

import org.timadorus.webapp.client.rpc.service.LoginService;
import org.timadorus.webapp.client.rpc.service.LoginServiceAsync;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;

public class LoginPanel extends FormPanel {
	
	private Grid grid = new Grid(4, 2);
	private TextBox userBox = new TextBox();
    private PasswordTextBox passBox = new PasswordTextBox();
    private Label userLabel = new Label("Benutzername");;
    private Label passLabel = new Label("Passwort");
    private HTML errorHTML = new HTML();
    private Button submit = new Button("Einloggen");
    private User user = new User();
    private SessionId sessionId;
	private final static long TWO_MIN = 1000 * 60 * 2; 

    public LoginPanel(SessionId session) {
        super();
        
        this.sessionId = session;
        
        grid.setWidget(0, 0, userLabel);
        grid.setWidget(0, 1, userBox);
        grid.setWidget(1, 0, passLabel);
        grid.setWidget(1, 1, passBox);
        grid.setWidget(2, 1, errorHTML);
        grid.setWidget(3, 1, submit);

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
				clearError();
				user.setUsername(userBox.getText());
				user.setPassword(passBox.getText());
				if(user.getUsername().equals("") || user.getPassword().equals("")) {
					loginInvalid("Bitte Felder ausfüllen!");
				} else {
					sendToServer();
				}
			}
			
			/**
			 * Username und Passwort an Server senden
			 */
			private void sendToServer() {
				LoginServiceAsync loginServiceAsync = GWT.create(LoginService.class);

				AsyncCallback<String> asyncCallback = new AsyncCallback<String>() {
					public void onSuccess(String result) {
						if(result != null){
							RootPanel.get("content").clear();
							RootPanel.get("content").add(new Label("Eingeloggt"));
							Cookies.setCookie("session", result, new Date(System.currentTimeMillis() + TWO_MIN));
							sessionId.setSessionId(result);
							System.out.println("login session => "+result);
						}else{
							loginInvalid("Username und/oder Passwort falsch!");
							submit.setEnabled(true);
						}//end else
					}//end onSuccess(String result) {
					public void onFailure(Throwable caught) {
						loginInvalid("Fehler bei der Anmeldung!");
						System.out.println(caught);
					}//end onFailure(Throwable caught) {
				};//end asyncCallback

				loginServiceAsync.login(user, asyncCallback);
			}
		}

		// Add a handler to send the name to the server
		MyHandler handler = new MyHandler();
		submit.addClickHandler(handler);
		userBox.addKeyUpHandler(handler);
		passBox.addKeyUpHandler(handler);
        
        setWidget(grid);
        setStyleName("formPanel");
    }
    
    /**
     * In dieser Methode wird das Ereignis "Login ungültig" verarbeitet.
     */
    private void loginInvalid(String message) {
    	errorHTML.setHTML("<span class=\"error\">" + message + "</span>");
    }
    
    private void clearError() {
   		errorHTML.setHTML("");
    }
}
