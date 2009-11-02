package org.timadorus.webapp.client;

import org.timadorus.webapp.client.rpc.service.SessionService;
import org.timadorus.webapp.client.rpc.service.SessionServiceAsync;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.HistoryListener;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

@SuppressWarnings("deprecation")
public class TimadorusWebApp implements EntryPoint, HistoryListener {

	private static final long serialVersionUID = -5138823406762920058L;


	private SessionId sessionId = new SessionId();



	public void onModuleLoad() {
		sessionId.setSessionId(Cookies.getCookie("session"));
		History.onHistoryChanged("welcome");
		System.out.println("Session " + sessionId.getSessionId());
		validateSession();
	}

	private void validateSession() {

		SessionServiceAsync myServiceAsync = GWT.create(SessionService.class);

		AsyncCallback<SessionId> asyncCallback = new AsyncCallback<SessionId>() {
			public void onFailure(Throwable caught) {
				System.out.println(caught);
			}

			public void onSuccess(SessionId result) {
				// if(result != null &&
				// sessionId.getSessionId().equals(result.getSessionId())){
				System.out.println("Result " + result + " SessionID "
						+ sessionId.getSessionId());
				loadWelcomePanel();

			}

			private void loadWelcomePanel() {
			
				RootPanel.get("content").clear();
				RootPanel.get("content").add(new Label("Sie sind eingeloggt als:"));
				
			}
		};
		myServiceAsync.session(sessionId, asyncCallback);
	}

	public void onHistoryChanged(String historyToken) {
		// TODO Auto-generated method stub
		
	}



}
	

		


