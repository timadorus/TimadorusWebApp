package org.timadorus.webapp.client.character.ui.selectfraction;

import java.util.ArrayList;
import java.util.List;

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

    public void setCharacter(Character character);

    public void setNextButtonEnable(boolean b);

    public void setFactions(List<String> factions);
  }

  private Character character;

  private User user;

  public SelectFactionDialog(Display display, DefaultTimadorusWebApp entry) {
    super(display, entry);
    
    initDisplay();
    
    entry.addHandler(ShowSelectFractionEvent.SHOWDIALOG, this);
  }
  
  private void initDisplay() {
    getDisplay().setNextButtonEnable(false);
  
    getDisplay().addPrevButtonHandler(new DefaultActionHandler() {

      @Override
      public void onAction() {
        getEntry().fireEvent(new ShowSelectClassEvent(user, character));
      }
    });
    
    getDisplay().addNextButtonHandler(new DefaultActionHandler() {

      @Override
      public void onAction() {
        saveSelectedFaction();
        getEntry().fireEvent(new ShowSelectTempStatsEvent(user, character));
      }
    });
    
    getDisplay().addSelectFactionGridHandler(new DefaultActionHandler() {

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
  
  public void saveSelectedFaction() {
    character.setFaction(null);
  }

  // returns currently select faction from the listbox
  public Faction getSelectedFaction() {
    for (Faction faction : getEntry().getTestValues().getFactions()) {
      if (faction.getName().equals(getDisplay().getSelectedFaction())) { return faction; }
    }
    return null;
  }
  
  public void setFactions() {
    List<String> factions = new ArrayList<String>();
    
    for (Faction faction : getEntry().getTestValues().getFactions()) {
      if (character.getCharClass().containsFaction(faction)) {
        if (character.getRace().containsFaction(faction)) {
          factions.add(faction.getName());
        }
      }
    }
    
    getDisplay().setFactions(factions);
  }
   
  @Override
  public void show(DefaultTimadorusWebApp entry, Character character, User user) {
    this.character = character;
    this.user = user;
    
    getDisplay().setCharacter(character);
    setFactions();

    RootPanel.get("content").clear();
    RootPanel.get("content").add(getFormPanel());
  }

}
