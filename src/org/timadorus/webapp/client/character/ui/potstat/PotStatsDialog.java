package org.timadorus.webapp.client.character.ui.potstat;

import java.util.List;

import org.timadorus.webapp.beans.Character;
import org.timadorus.webapp.beans.User;
import org.timadorus.webapp.client.DefaultTimadorusWebApp;
import org.timadorus.webapp.client.character.ui.DefaultActionHandler;
import org.timadorus.webapp.client.character.ui.DefaultDialog;
import org.timadorus.webapp.client.character.ui.DefaultDisplay;

public class PotStatsDialog extends DefaultDialog<PotStatsDialog.Display> {
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
    
    public void loadSelectTempStatsPanel(DefaultTimadorusWebApp entry, Character character, User user);
    
    public void loadSelectSkillPanel(DefaultTimadorusWebApp entry, Character character, User user);
  }

  private Character character;

  private User user;

  /**
   * list holding characters potstats
   */
  private List<Integer> potStats;

  public PotStatsDialog(Display display, DefaultTimadorusWebApp entry, Character character,
                        User user) {
    super(display, entry);

    this.user = user;
    this.character = character;

    display.addNextButtonHandler(new DefaultActionHandler() {

      @Override
      public void onAction() {
        getDisplay().loadSelectSkillPanel(getEntry(), getCharacter(), getUser());
      }
    });

    display.addPrevButtonHandler(new DefaultActionHandler() {

      @Override
      public void onAction() {
        getDisplay().loadSelectTempStatsPanel(getEntry(), getCharacter(), getUser());
      }
    });

    potStats = getDisplay().calculatePotStats(character.getTempStat());
    character.setPotStats(potStats);
  }

  private Character getCharacter() {
    return character;
  }

  private User getUser() {
    return user;
  }

  public static PotStatsDialog getDialog(DefaultTimadorusWebApp entry, Character character,
                                         User user) {
    PotStatsDialog.Display display = new PotStatsWidget(entry, character);
    PotStatsDialog dialog = new PotStatsDialog(display, entry, character, user);
    return dialog;
  }

}
