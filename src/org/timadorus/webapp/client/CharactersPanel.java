package org.timadorus.webapp.client;

import java.util.ArrayList;
import java.util.List;

import org.timadorus.webapp.client.TimadorusWebApp;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.HistoryListener;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

@SuppressWarnings("deprecation")
public class CharactersPanel extends FormPanel implements HistoryListener {
	
	public static final String LOGIN_STATE = "login";
	public static final String WELCOME_STATE = "welcome";
	public static final String CREATE_STATE = "create";
	public static final String REGISTER_STATE = "register";
	
	Grid grid = new Grid(9, 6);
	/**
	 * GUI Elemente Charakter
	 */
	Label lbl_name = new Label();
	TextBox tb_charname = new TextBox();

	Label lbl_gender = new Label();
	ListBox cb_gender = new ListBox();

	Label lbl_fraction = new Label();
	ListBox cb_fraction = new ListBox();

	Label lbl_race = new Label();
	ListBox cb_race = new ListBox();

	Label lbl_profession = new Label();
	ListBox cb_profession = new ListBox();

	Button saveButton = new Button();
	HTML dialogText = new HTML();
	
	
	List<String> listeGender = new ArrayList<String>();
	List<String> listeFraction_1 = new ArrayList<String>();
	List<String> listeFraction_2 = new ArrayList<String>();
	List<String> listeFraction_3 = new ArrayList<String>();
	List<String> listeFraction_4 = new ArrayList<String>();

	List<String> listeRace_1 = new ArrayList<String>();
	List<String> listeRace_2 = new ArrayList<String>();
	List<String> listeRace_3 = new ArrayList<String>();
	List<String> listeRace_4 = new ArrayList<String>();
	
	List<String> listeProfession_1 = new ArrayList<String>();
	List<String> listeProfession_2 = new ArrayList<String>();
	List<String> listeProfession_3 = new ArrayList<String>();
	List<String> listeProfession_4 = new ArrayList<String>();
	
	TimadorusWebApp entry ;
	
	public CharactersPanel(TimadorusWebApp _entry) {
		super();
		this.entry = _entry;
		
		createUI();
		setupHistory();
		setWidget(grid);
	}

	private void setupHistory() {
		History.addHistoryListener(this);
		//History.onHistoryChanged("create");
	}
	
	private void createUI() {
		fillLists();
		initComboBoxes();

		
		
		// Event Handler
		MyHandler handler = new MyHandler();

		grid.setWidget(2, 2, new Label("Charaktername"));
		grid.setWidget(2, 4, tb_charname);
		grid.setWidget(3, 2, new Label("Geschlecht"));
		cb_gender.setWidth("120px");

		cb_gender.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				ListBox lb = (ListBox) event.getSource();

				System.out.println(lb.getValue(lb.getSelectedIndex()));
			}

		});

		grid.setWidget(3, 4, cb_gender);

		lbl_fraction.setText("Fraktion");
		grid.setWidget(4, 2, lbl_fraction);

		cb_fraction.setWidth("180px");

		cb_fraction.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				ListBox lb = (ListBox) event.getSource();

				cb_race.clear();
				
				System.out.println(lb.getValue(lb.getSelectedIndex()));
				if (lb.getValue(lb.getSelectedIndex()).toLowerCase().equals(
						"fraction 1")) {

					System.out.println("gewähltes Element: "
							+ lb.getValue(lb.getSelectedIndex()));

					for (String races : listeRace_1) {
						// System.out.println(races);
						cb_race.addItem(races);
					}
				} else if (lb.getValue(lb.getSelectedIndex()).toLowerCase()
						.equals("fraction 2")) {
					System.out.println("gewähltes Element: "
							+ lb.getValue(lb.getSelectedIndex()));
					for (String races : listeRace_2) {
						// System.out.println(races);
						cb_race.addItem(races);
					}

				} else if (lb.getValue(lb.getSelectedIndex()).toLowerCase()
						.equals("fraction 3")) {
					for (String races : listeRace_3) {
						// System.out.println(races);
						cb_race.addItem(races);
					}

				} else if (lb.getValue(lb.getSelectedIndex()).toLowerCase()
						.equals("fraction 4")) {
					for (String races : listeRace_4) {
						// System.out.println(races);
						cb_race.addItem(races);
					}

				}

			}

		});

		grid.setWidget(4, 4, cb_fraction);

		lbl_race.setText("Rasse");

		cb_race.setWidth("180px");
		cb_race.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				ListBox lb = (ListBox) event.getSource();

				System.out.println(lb.getValue(lb.getSelectedIndex()));
				cb_profession.clear();
				
				if (lb.getValue(lb.getSelectedIndex()).toLowerCase().equals(
						"race 1")) {

					System.out.println("gewähltes Element: "
							+ lb.getValue(lb.getSelectedIndex()));

					for (String profession : listeProfession_1) {
						// System.out.println(races);
						cb_profession.addItem(profession);
					}
				} else if (lb.getValue(lb.getSelectedIndex()).toLowerCase()
						.equals("race 2")) {
					System.out.println("gewähltes Element: "
							+ lb.getValue(lb.getSelectedIndex()));
					for (String profession : listeProfession_2) {
						// System.out.println(races);
						cb_profession.addItem(profession);
					}

				} else if (lb.getValue(lb.getSelectedIndex()).toLowerCase()
						.equals("race 3")) {
					for (String profession : listeProfession_3) {
						// System.out.println(races);
						cb_profession.addItem(profession);
					}

				} else if (lb.getValue(lb.getSelectedIndex()).toLowerCase().equals("race 4")) {
					for (String profession : listeProfession_4) {
						// System.out.println(races);
						cb_profession.addItem(profession);
					}

				}

			}

		});

		grid.setWidget(5, 2, lbl_race);
		grid.setWidget(5, 4, cb_race);

		lbl_profession.setText("Profession");
		cb_profession.setWidth("180px");

		cb_profession.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				ListBox lb = (ListBox) event.getSource();

				System.out.println(lb.getValue(lb.getSelectedIndex()));
			}

		});

		grid.setWidget(6, 2, lbl_profession);
		grid.setWidget(6, 4, cb_profession);

		saveButton.setText("Daten speichern");

		saveButton.addClickHandler(handler);
		grid.setWidget(8, 5, saveButton);
	}

	private void fillLists() {
		listeGender.add("maennlich");
		listeGender.add("weiblich");

		listeFraction_1.add("Fraction 1");
		listeFraction_1.add("Fraction 2");
		listeFraction_1.add("Fraction 3");
		listeFraction_1.add("Fraction 4");

		listeRace_1.add("Race 1");
		listeRace_1.add("Race 2");
		listeRace_1.add("Race 3");
		listeRace_1.add("Race 4");
		

		listeRace_2.add("Race 1");
		listeRace_2.add("Race 2");
		listeRace_2.add("Race 3");
		listeRace_2.add("Race 4");
		

		listeRace_3.add("Race 1");
		listeRace_3.add("Race 2");
		listeRace_3.add("Race 3");
		listeRace_3.add("Race 4");
		

		listeRace_4.add("Race 1");
		listeRace_4.add("Race 2");
		listeRace_4.add("Race 3");
		listeRace_4.add("Race 4");
		
		
		
		listeProfession_1.add("Profession_1");
		listeProfession_2.add("Profession_2");
		listeProfession_3.add("Profession_3");
		listeProfession_4.add("Profession_4");

	}

	private void initComboBoxes() {
		for (String gender : listeGender) {
			cb_gender.addItem(gender);
		}

		for (String fraction : listeFraction_1) {
			cb_fraction.addItem(fraction);
		}

	}

	// Create a handler for the sendButton and nameField
	class MyHandler implements ClickHandler, KeyUpHandler {
		/**
		 * Fired when the user clicks on the sendButton.
		 */
		public void onClick(ClickEvent event) {
			createvisibleDialog();
		//	sendToServer();
			System.out.println("onClick Event saveButton");
			// System.out.println("Name korrekt" +
			// prüfeNamen(tb_charname.getText()));
			History.newItem("welcome");
		}

		/**
		 * Fired when the user types in the nameField.
		 */
		public void onKeyUp(KeyUpEvent event) {
			if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {

			}
		}
	}

	/**
	 * Namen auf Gültigkeit prüfen Name des Characters. Zulässig sind die große
	 * und kleine Buchstaben, sowie Bindestrich, Leerzeichen und Single-Quote
	 * (')
	 * 
	 * @param namen
	 * @return
	 */
//	 private boolean prüfeNamen(String namen){
//		 return namen.matches("[\\s\\d\\W']+");
//	 }
	 
	 
	 
	private void createvisibleDialog() {

		final DialogBox dialogBox = new DialogBox();
		dialogBox.setText("Felder in Liste");
		dialogBox.setAnimationEnabled(true);

		final Button closeButton = new Button("Close");
		// We can set the id of a widget by accessing its Element
		closeButton.getElement().setId("closeButton");
		final Label textToServerLabel = new Label();
		final HTML serverResponseLabel = new HTML();

		StringBuffer sb = new StringBuffer();

		sb.append(tb_charname.getText()).append("\n");

		if (cb_gender.getSelectedIndex() != -1) {
			sb.append(cb_gender.getItemText(cb_gender.getSelectedIndex()))
					.append("\n");
		} else {
			sb.append("\nfalscher index von cb_gender");
		}

		if (cb_fraction.getSelectedIndex() != -1) {
			sb.append(cb_fraction.getItemText(cb_fraction.getSelectedIndex()))
					.append("\n");
		} else {
			sb.append("\falscher index von cb_fraction");
		}
		if (cb_race.getSelectedIndex() != -1) {
			sb.append(cb_race.getItemText(cb_race.getSelectedIndex())).append(
					"\n");
		}
		else {
			sb.append("\nfalscher index von cb_race");
		}
		if (cb_profession.getSelectedIndex() != -1) {
			sb
					.append(
							cb_profession.getItemText(cb_profession
									.getSelectedIndex())).append("\n");
		} else {
			sb.append("\nfalscher index von cb_profession");
		}

		serverResponseLabel.setHTML(sb.toString());
		
		VerticalPanel dialogVPanel = new VerticalPanel();
		dialogVPanel.addStyleName("dialogVPanel");
		dialogVPanel.add(new HTML("<b>Felder</b>"));
		dialogVPanel.add(textToServerLabel);
		dialogVPanel.add(serverResponseLabel);
		dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
		dialogVPanel.add(closeButton);
		dialogBox.setWidget(dialogVPanel);
		dialogBox.center();
		dialogBox.setVisible(true);

		// Add a handler to close the DialogBox
		closeButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				dialogBox.hide();
				saveButton.setEnabled(true);
				saveButton.setFocus(true);
			}
		});
	
		
		
	
	}

	@Override
	public void onHistoryChanged(String historyToken) {
		if (LOGIN_STATE.equals(historyToken)) {
			getEntry().loadLoginPanel();
		} 
		else if (WELCOME_STATE.equals(historyToken)) {
			getEntry().loadWelcomePanel();
			
		} 
		else if (CREATE_STATE.equals(historyToken)) {
			getEntry().loadCreateCharacter();
		} 
		else if(REGISTER_STATE.equals(historyToken)){
			getEntry().loadRegisterPanel();
		}
	}

	private TimadorusWebApp getEntry() {
		return entry;
	}
	
//	/**
//	 * Username und Passwort an Server senden
//	 */
//	private void sendToServer() {
//		CreateCharacterServiceAsync createCharacterServiceAsync = GWT.create(CreateCharacterService.class);
//
//		AsyncCallback<String> asyncCallback = new AsyncCallback<String>() {
//			
//			
//			public void onSuccess(String result) {
//				if(result != null){
//					System.out.println("vom Server " + result);
//				}
////					if(result.equals(character.CHARACTER_INVALID)){
////						//loginInvalid("Username und/oder Passwort falsch!");
////						saveButton.setEnabled(true);
////					} else {
////						RootPanel.get("content").clear();
////						RootPanel.get("content").add(new Label("Eingeloggt"));
////						Cookies.setCookie("session", result, new Date(System.currentTimeMillis() + TWO_MIN));
////						sessionId.setSessionId(result);
////						System.out.println("login session => "+result);
////					}
//				}
//			
//			public void onFailure(Throwable caught) {
//				
//				System.out.println(caught);
//			}
//		};
//
//		createCharacterServiceAsync.create(character, asyncCallback);
//	}
}
