package org.timadorus.webapp.client.character.ui.selectrace;

import java.util.ArrayList;
import java.util.List;

import org.timadorus.webapp.beans.User;
import org.timadorus.webapp.client.TimadorusWebApp;
import org.timadorus.webapp.client.character.Character;
import org.timadorus.webapp.client.character.attributes.CClass;
import org.timadorus.webapp.client.character.attributes.Faction;
import org.timadorus.webapp.client.character.attributes.Race;
import org.timadorus.webapp.client.character.ui.DefaultActionHandler;
import org.timadorus.webapp.client.character.ui.DefaultDialog;
import org.timadorus.webapp.client.character.ui.DefaultDisplay;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;

public class SelectRaceDialog extends DefaultDialog<SelectRaceDialog.Display> {
  public interface Display extends DefaultDisplay {
    public String getSelectedRace();

    public String getSelectedGender();

    public void addRaceSelectionHanlder(DefaultActionHandler handler);

    /**
     * Adds an action handler to the next button.
     * 
     * @param handler
     *          {@link DefaultActionHandler}
     */
    public void addNextButtonHandler(DefaultActionHandler handler);

    /**
     * Adds an action handler to the prev button.
     * 
     * @param handler
     *          {@link DefaultActionHandler}
     */
    public void addPrevButtonHandler(DefaultActionHandler handler);

    public void loadSelectClassPanel(TimadorusWebApp entry, User user, Character character);

    public void loadCharacterPanel(User user, TimadorusWebApp entry);

    public void showRaceSelection(String raceName, String raceDescription,
                                  List<String> availableClasses, List<String> availableFactions);
  }

  private Character character;

  private User user;

  public SelectRaceDialog(Display display, TimadorusWebApp entry, Character character, User user) {
    super(display, entry);
    this.character = character;
    this.user = user;
    display.addNextButtonHandler(new DefaultActionHandler() {

      @Override
      public void onAction() {
        saveSelectedRace();
        saveSelectedGender();
        getDisplay().loadSelectClassPanel(getEntry(), getUser(), getCharacter());
      }
    });
    display.addPrevButtonHandler(new DefaultActionHandler() {

      @Override
      public void onAction() {
        getDisplay().loadCharacterPanel(getUser(), getEntry());
      }
    });
    display.addRaceSelectionHanlder(new DefaultActionHandler() {

      @Override
      public void onAction() {
        doRaceSelection();
      }
    });
  }

  private void doRaceSelection() {

    RootPanel.get("information").clear();

    Race newRace = getRaceByName(getDisplay().getSelectedRace());

    RootPanel.get("information").add(new HTML("<h1>" + newRace.getName() + "</h1><p>"
                                         + newRace.getDescription() + "</p>"));

    // Show available Classes
    RootPanel.get("information").add(new HTML("<h2>Wählbare Klassen</h2>"));

    String availableClasses = new String("<ul>");
    for (CClass newClass : newRace.getAvailableClasses()) {

      availableClasses = availableClasses + "<li>" + newClass.getName() + "</li>";
    }
    availableClasses = availableClasses + "</ul>";
    RootPanel.get("information").add(new HTML(availableClasses));

    // Show available Factions
    RootPanel.get("information").add(new HTML("<h2>Wählbare Fraktionen</h2>"));

    String availableFactions4 = new String("<ul>");
    String nextFaction = new String();

    for (Faction newFaction : newRace.getAvailableFactions()) {
      nextFaction = newFaction.getName();
      availableFactions4 = availableFactions4 + "<li>" + nextFaction + "</li>";

    }
    availableFactions4 = availableFactions4 + "</ul>";
    RootPanel.get("information").add(new HTML(availableFactions4));

  }

  public void saveSelectedRace() {
    Race race = getRaceByName(getDisplay().getSelectedRace());
    character.setRace(race);
  }

  public void saveSelectedGender() {
    character.setGender(getDisplay().getSelectedGender());
  }

  private Race getRaceByName(String name) {
    for (Race race : getEntry().getTestValues().getRaces()) {
      if (race.getName().equals(name)) {
        return race;
      }
    }
    return null;
  }

  public Character getCharacter() {
    return character;
  }

  public User getUser() {
    return user;
  }

  public static SelectRaceDialog getDialog(TimadorusWebApp entry, User user, Character character) {
    List<Race> races = entry.getTestValues().getRaces();
    List<String> racenames = new ArrayList<String>();
    for (Race race : races) {
      racenames.add(race.getName());
    }
    SelectRaceDialog.Display display = new SelectRaceWidget(racenames);
    SelectRaceDialog dialog = new SelectRaceDialog(display, entry, character, user);
    return dialog;
  }

}
