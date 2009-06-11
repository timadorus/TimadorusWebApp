package org.timadorus.webapp.client;

import org.timadorus.webapp.client.rpc.service.SessionService;
import org.timadorus.webapp.client.rpc.service.SessionServiceAsync;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class BookkeeperWebApp implements EntryPoint {
	
	private static final long serialVersionUID = -5138823406762920058L;

	private final SessionId sessionId = new SessionId();

	private HorizontalPanel mainPanel = new HorizontalPanel();
	private VerticalPanel loginPanel = new VerticalPanel();
	private VerticalPanel registerPanel = new VerticalPanel();

	{
		sessionId.setSessionId(Cookies.getCookie("session"));
	}
	
	public void onModuleLoad() {
		validateSession();
	}
	
	private void validateSession(){

		SessionServiceAsync myServiceAsync = GWT.create(SessionService.class);

		AsyncCallback<SessionId> asyncCallback = new AsyncCallback<SessionId>(){
			public void onFailure(Throwable caught) {
				System.out.println(caught);
			}
			public void onSuccess(SessionId result) {
				if(result != null && sessionId.getSessionId().equals(result.getSessionId())){
					RootPanel.get("content").clear();
					RootPanel.get("content").add(new Label("Eingeloggt"));
				} else {
					RootPanel.get("content").clear();
					// Assemble Main panel.
					loginPanel.add(new Label("Bestehenden Account einloggen:"));
					loginPanel.add(new LoginPanel(sessionId));
					mainPanel.add(loginPanel);
					registerPanel.add(new Label("Einen Account anlegen:"));
					registerPanel.add(new RegisterPanel());
					mainPanel.add(registerPanel);
					
					// Associate the Main panel with the HTML host page.
					RootPanel.get("content").add(mainPanel);
				}
			}
		};
		myServiceAsync.session(sessionId, asyncCallback);
	}
}
