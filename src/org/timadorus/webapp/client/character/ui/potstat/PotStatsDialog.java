package org.timadorus.webapp.client.character.ui.potstat;

import java.util.List;

import org.timadorus.webapp.beans.Character;
import org.timadorus.webapp.beans.User;
import org.timadorus.webapp.client.DefaultTimadorusWebApp;
import org.timadorus.webapp.client.character.ui.DefaultActionHandler;
import org.timadorus.webapp.client.character.ui.DefaultDialog;
import org.timadorus.webapp.client.character.ui.DefaultDisplay;
import org.timadorus.webapp.client.eventhandling.events.ShowPotStatsDialogEvent;
import org.timadorus.webapp.client.eventhandling.events.ShowSelectSkill0Event;
import org.timadorus.webapp.client.eventhandling.events.ShowSelectTempStatsEvent;
import org.timadorus.webapp.client.eventhandling.handler.ShowDialogHandler;

import com.google.gwt.user.client.ui.FormPanel;

public class PotStatsDialog extends DefaultDialog<PotStatsDialog.Display> implements ShowDialogHandler {
  public interface Display extends DefaultDisplay {
    /**
     * Sets a new HTML element to the diplayed grid.
     * 
     * @param row
     *          integer row
     * @param column
     *          integer column
     * @param text
     *          the to be displayed text
     */
    public void addGridEntry(int row, int column, String text);

    public void addNextButtonHandler(DefaultActionHandler handler);

    public void addPrevButtonHandler(DefaultActionHandler handler);

    public List<Integer> calculatePotStats(List<Integer> tempStat);
    
    /**
     * Adds the {@link Character} information to the display.
     * @param character
     */
    public void setCharacter(Character character);
    
    public void addToRootPanel(FormPanel aFormPanel);

  }

  private Character character;

  private User user;

  /**
   * list holding characters potstats
   */
  private List<Integer> potStats;

  public PotStatsDialog(Display display, DefaultTimadorusWebApp entry) {
    super(display, entry);
    initDisplay(display);
    entry.addHandler(ShowPotStatsDialogEvent.SHOWDIALOG, this);
  }

  private void initDisplay(Display display) {
    setDisplay(display);
    display.addNextButtonHandler(new DefaultActionHandler() {

      @Override
      public void onAction() {
        getEntry().fireEvent(new ShowSelectSkill0Event(getUser(), getCharacter()));
      }
    });

    display.addPrevButtonHandler(new DefaultActionHandler() {

      @Override
      public void onAction() {
        getEntry().fireEvent(new ShowSelectTempStatsEvent(getUser(), getCharacter()));
      }
    });
  }

  private Character getCharacter() {
    return character;
  }

  private User getUser() {
    return user;
  }

  @Override
  public void show(DefaultTimadorusWebApp entry, Character character, User user) {
    
    this.user = user;
    this.character = character;

    getDisplay().setCharacter(character);
    
    potStats = getDisplay().calculatePotStats(character.getTempStat());
    this.character.setPotStats(potStats);

    getDisplay().addToRootPanel(getFormPanel());
  }

}
