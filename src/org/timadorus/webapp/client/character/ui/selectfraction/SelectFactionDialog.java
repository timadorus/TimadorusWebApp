package org.timadorus.webapp.client.character.ui.selectfraction;

import org.timadorus.webapp.beans.Character;
import org.timadorus.webapp.beans.Faction;
import org.timadorus.webapp.beans.User;
import org.timadorus.webapp.client.DefaultTimadorusWebApp;
import org.timadorus.webapp.client.character.ui.DefaultActionHandler;
import org.timadorus.webapp.client.character.ui.DefaultDialog;
import org.timadorus.webapp.client.character.ui.DefaultDisplay;
import org.timadorus.webapp.client.eventhandling.events.ShowSelectClassEvent;
import org.timadorus.webapp.client.eventhandling.events.ShowSelectFractionEvent;
import org.timadorus.webapp.client.eventhandling.events.ShowSelectTempStatsEvent;
import org.timadorus.webapp.client.eventhandling.handler.ShowDialogHandler;

import com.google.gwt.user.client.ui.RootPanel;

public class SelectFactionDialog extends DefaultDialog<SelectFactionDialog.Display> implements ShowDialogHandler {

  public interface Display extends DefaultDisplay {
    /**
     * Gets the name of the selected faction in the ui.
     * 
     * @return {@link String}
     */
    public String getSelectedFaction();

    public void addPrevButtonHandler(DefaultActionHandler handler);

    public void addNextButtonHandler(DefaultActionHandler handler);

    public void addSelectFactionGridHandler(DefaultActionHandler handler);

    /**
     * Clears the root information panel and places the given message in it.
     * 
     * @param msg
     */
    public void setInformation(String msg);
  }

  private Character character;

  private User user;

  public SelectFactionDialog(Display display, DefaultTimadorusWebApp entry, Character character, User user) {
    super(display, entry);
    this.character = character;
    this.user = user;
    entry.addHandler(ShowSelectFractionEvent.SHOWDIALOG, this);
  }

  private void intiDisplay(Display display) {
    setDisplay(display);
    display.addPrevButtonHandler(new DefaultActionHandler() {

      @Override
      public void onAction() {
        loadSelectClassPanel();
      }
    });
    display.addNextButtonHandler(new DefaultActionHandler() {

      @Override
      public void onAction() {
        saveSelectedFaction();
        loadSelectTempStatsPanel();
      }
    });
    display.addSelectFactionGridHandler(new DefaultActionHandler() {

      @Override
      public void onAction() {

        String factionName = getDisplay().getSelectedFaction();

        for (Faction faction : getEntry().getTestValues().getFactions()) {
          if (faction.getName().equals(factionName)) {
            getDisplay().setInformation("<h1>" + faction.getName() + "</h1><p>" + faction.getDescription() + "</p>");
          }
        }
      }
    });
  }

  // returns currently select faction from the listbox
  public Faction getSelectedFaction() {
    for (Faction faction : getEntry().getTestValues().getFactions()) {
      if (faction.getName().equals(getDisplay().getSelectedFaction())) { return faction; }
    }
    return null;
  }

  // save selected factions
  public void saveSelectedFaction() {
    character.setFaction(getSelectedFaction());
  }

  // clear "content" #div and add Class SelectClassPanel to it
  public void loadSelectClassPanel() {
    getEntry().fireEvent(new ShowSelectClassEvent(user, character));
  }

  // clear "content" #div and add Class SelectTempStats to it
  public void loadSelectTempStatsPanel() {
    getEntry().fireEvent(new ShowSelectTempStatsEvent(user, character));
  }

  public static SelectFactionDialog getDialog(DefaultTimadorusWebApp entry, Character character, User user) {
    SelectFactionDialog.Display display = new SelectFactionWidget(entry, character);
    SelectFactionDialog dialog = new SelectFactionDialog(display, entry, character, user);
    return dialog;
  }

  @Override
  public void show(DefaultTimadorusWebApp entry, Character character, User user) {
    this.character = character;
    this.user = user;
    SelectFactionDialog.Display display = new SelectFactionWidget(entry, character);
    intiDisplay(display);
    RootPanel.get("content").clear();
    RootPanel.get("content").add(getFormPanel());
  }

}
