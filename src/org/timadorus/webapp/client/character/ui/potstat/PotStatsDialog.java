package org.timadorus.webapp.client.character.ui.potstat;

import java.util.ArrayList;

import org.timadorus.webapp.beans.User;
import org.timadorus.webapp.client.DefaultTimadorusWebApp;
import org.timadorus.webapp.client.character.Character;
import org.timadorus.webapp.client.character.ui.DefaultActionHandler;
import org.timadorus.webapp.client.character.ui.DefaultDialog;
import org.timadorus.webapp.client.character.ui.DefaultDisplay;
import org.timadorus.webapp.client.character.ui.SelectSkillPanel;
import org.timadorus.webapp.client.character.ui.SelectTempStatsPanel;
import org.timadorus.webapp.client.rpc.service.CreateCharacterService;
import org.timadorus.webapp.client.rpc.service.CreateCharacterServiceAsync;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;

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
  }

  /**
   * 
   * @author sage
   * 
   */
  private class PotFieldCallback implements AsyncCallback<Integer> {

    private int fieldNum;

    /**
     * 
     * @param fieldNum
     *          the number of the pot field to set.
     */
    public PotFieldCallback(int fieldNum) {
      this.fieldNum = fieldNum;
    }

    @Override
    public void onFailure(Throwable caught) {
      getEntry().showDialogBox("Remote Service Failure",
                               "Client/Server Create Character Service Failure!\n"
                                   + "please contact the support service or server admin. \n"
                                   + "RPC that failed was: int makePotStat(int)");
    }

    @Override
    public void onSuccess(Integer result) {
      potStats.add(fieldNum, result);
      getDisplay().addGridEntry(fieldNum + 1, 2, result.toString());
    }
  };

  private Character character;

  private User user;

  /**
   * list holding characters potstats
   */
  private ArrayList<Integer> potStats;

  public PotStatsDialog(Display display, DefaultTimadorusWebApp entry, Character character,
                        User user) {
    super(display, entry);

    this.user = user;
    this.character = character;

    display.addNextButtonHandler(new DefaultActionHandler() {

      @Override
      public void onAction() {
        loadSelectTempStatsPanel(getEntry(), getCharacter(), getUser());
      }
    });

    display.addPrevButtonHandler(new DefaultActionHandler() {

      @Override
      public void onAction() {
        loadSelectSkillPanel(getEntry(), getCharacter(), getUser());
      }
    });

    potStats = new ArrayList<Integer>();
    character.setPotStats(potStats);

    calculatePotStats();
  }

  // calculates potStats
  public void calculatePotStats() {
    CreateCharacterServiceAsync createServiceAsync = GWT.create(CreateCharacterService.class);

    AsyncCallback<Integer> asyncCallback;

    for (int i = 0; i < character.getTempStat().size(); i++) {
      asyncCallback = new PotFieldCallback(i);
      int temp = character.getTempStat().get(i);
      createServiceAsync.makePotStat(temp, asyncCallback);
    }
  }

  // clear "content" #div and add Class SelectTempStats to it
  public void
      loadSelectTempStatsPanel(DefaultTimadorusWebApp entry, Character character, User user) {
    RootPanel.get("content").clear();
    RootPanel.get("content").add(SelectTempStatsPanel.getSelectTempStatsPanel(entry, character,
                                                                              user));
  }

  // clear "content" #div and add Class SelectSkillPanel to it
  public void loadSelectSkillPanel(DefaultTimadorusWebApp entry, Character character, User user) {
    RootPanel.get("content").clear();
    RootPanel.get("content").add(SelectSkillPanel.getSelectSkillPanel(entry, character, user));
  }

  public Character getCharacter() {
    return character;
  }

  public User getUser() {
    return user;
  }

  public static PotStatsDialog getDialog(DefaultTimadorusWebApp entry, Character character,
                                         User user) {
    PotStatsDialog.Display display = new PotStatsWidget(character);
    PotStatsDialog dialog = new PotStatsDialog(display, entry, character, user);
    return dialog;
  }

}
