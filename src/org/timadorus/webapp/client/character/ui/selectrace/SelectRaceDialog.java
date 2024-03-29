package org.timadorus.webapp.client.character.ui.selectrace;

import java.util.List;

import org.timadorus.webapp.beans.CClass;
import org.timadorus.webapp.beans.Character;
import org.timadorus.webapp.beans.Faction;
import org.timadorus.webapp.beans.Race;
import org.timadorus.webapp.beans.User;
import org.timadorus.webapp.client.DefaultTimadorusWebApp;
import org.timadorus.webapp.client.character.ui.DefaultActionHandler;
import org.timadorus.webapp.client.character.ui.DefaultDialog;
import org.timadorus.webapp.client.character.ui.DefaultDisplay;
import org.timadorus.webapp.client.eventhandling.events.CreateCharacterEvent;
import org.timadorus.webapp.client.eventhandling.events.ShowSelectRaceEvent;
import org.timadorus.webapp.client.eventhandling.events.ShowSelectClassEvent;
import org.timadorus.webapp.client.eventhandling.handler.ShowDialogHandler;
import org.timadorus.webapp.util.ListUtil;
import org.timadorus.webapp.util.ListUtil.DefaultListCollector;

import com.google.gwt.user.client.ui.RootPanel;

public class SelectRaceDialog extends DefaultDialog<SelectRaceDialog.Display> implements ShowDialogHandler {
  public interface Display extends DefaultDisplay {
    public String getSelectedRace();

    public String getSelectedGender();

    public void addRaceSelectionHandler(DefaultActionHandler handler);

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

    public void showRaceSelection(String raceName, String raceDescription, List<String> availableClasses,
      List<String> availableFactions);

    /**
     * Adds the available fist of race names to the widget.
     * 
     * @param racenames
     */
    public void addRaces(List<String> racenames);
  }

  private Character character;

  private User user;

  public SelectRaceDialog(Display display, DefaultTimadorusWebApp entry) {
    super(display, entry);

    entry.addHandler(ShowSelectRaceEvent.SHOWDIALOG, this);

    display.addNextButtonHandler(new DefaultActionHandler() {

      @Override
      public void onAction() {
        saveSelectedRace();
        saveSelectedGender();
        getEntry().fireEvent(new ShowSelectClassEvent(getUser(), getCharacter()));
      }
    });
    display.addPrevButtonHandler(new DefaultActionHandler() {

      @Override
      public void onAction() {
        getEntry().fireEvent(new CreateCharacterEvent(getUser()));
      }
    });
    display.addRaceSelectionHandler(new DefaultActionHandler() {

      @Override
      public void onAction() {
        onRaceSelectionHandler();
      }
    });
  }

  private void saveSelectedRace() {
    Race race = getRaceByName(getDisplay().getSelectedRace());
    character.setRace(race);
  }

  private void saveSelectedGender() {
    character.setGender(getDisplay().getSelectedGender());
  }

  private Race getRaceByName(String name) {
    for (Race race : getEntry().getTestValues().getRaces()) {
      if (race.getName().equals(name)) { return race; }
    }
    return null;
  }

  private Character getCharacter() {
    return character;
  }

  private User getUser() {
    return user;
  }

  private void onRaceSelectionHandler() {
    Race race = getRaceByName(getDisplay().getSelectedRace());

    DefaultListCollector<CClass, String> defaultListCollector = new DefaultListCollector<CClass, String>() {

      @Override
      public String collect(CClass aCollectableObject) {
        return aCollectableObject.getName();
      }

    };

    List<String> theNamesOfAvailbaleClasses = ListUtil.collectListItems(defaultListCollector,
                                                                        race.getAvailableClasses());

    DefaultListCollector<Faction, String> aListCollector2 = new DefaultListCollector<Faction, String>() {

      @Override
      public String collect(Faction aCollectableObject) {
        return aCollectableObject.getName();
      }

    };
    List<String> theNameOfAvailbaleFactions = ListUtil.collectListItems(aListCollector2, race.getAvailableFactions());

    getDisplay().showRaceSelection(race.getName(), race.getDescription(), theNamesOfAvailbaleClasses,
                                   theNameOfAvailbaleFactions);
  }

  @Override
  public void show(DefaultTimadorusWebApp entry, Character character, User user) {
    // setting class variables
    this.character = character;
    this.user = user;

    // load race names
    List<String> racenames = ListUtil.collectListItems(new DefaultListCollector<Race, String>() {
      @Override
      public String collect(Race aCollectableObject) {
        return aCollectableObject.getName();
      }

    }, entry.getTestValues().getRaces());

    // putting race names into the widget
    getDisplay().addRaces(racenames);

    // display content
    RootPanel.get("content").clear();
    RootPanel.get("content").add(getFormPanel());
  }

}
