package org.timadorus.webapp.client.character.ui.selectclass;

import org.timadorus.webapp.beans.CClass;
import org.timadorus.webapp.beans.Character;
import org.timadorus.webapp.beans.Faction;
import org.timadorus.webapp.beans.User;
import org.timadorus.webapp.client.DefaultTimadorusWebApp;
import org.timadorus.webapp.client.character.ui.DefaultActionHandler;
import org.timadorus.webapp.client.character.ui.DefaultDialog;
import org.timadorus.webapp.client.character.ui.DefaultDisplay;
import org.timadorus.webapp.client.character.ui.selectfraction.SelectFactionDialog;
import org.timadorus.webapp.client.character.ui.selectrace.SelectRaceDialog;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;

public class SelectClassDialog extends DefaultDialog<SelectClassDialog.Display> {
  public interface Display extends DefaultDisplay {

    /**
     * Gets the name of the selected class of the ui.
     * 
     * @return {@link String}
     */
    public String getSelectedClass();

    public void setPrevButtonHandler(DefaultActionHandler handler);

    public void setNextButtonHandler(DefaultActionHandler handler);

    public void setClassGridButtonHandler(DefaultActionHandler handler);
  }

  private Character character;

  private User user;

  public SelectClassDialog(Display display, DefaultTimadorusWebApp entry, Character character, User user) {
    super(display, entry);
    this.character = character;
    this.user = user;

    display.setClassGridButtonHandler(new DefaultActionHandler() {

      @Override
      public void onAction() {
        doClassGridClick();
      }
    });
    display.setNextButtonHandler(new DefaultActionHandler() {

      @Override
      public void onAction() {
        doNextButtonClick();
      }
    });
    display.setPrevButtonHandler(new DefaultActionHandler() {

      @Override
      public void onAction() {
        doPrevButtonClick();
      }
    });
  }

  private void doClassGridClick() {
    String className = getDisplay().getSelectedClass();
    RootPanel.get("information").clear();
    for (CClass newClass : getEntry().getTestValues().getClasses()) {
      if (newClass.getName().equals(className)) {
        RootPanel.get("information").add(new HTML("<h1>" + newClass.getName() + "</h1><p>"
                                             + newClass.getDescription() + "</p>"));

        // Show available Factions in "information" #div
        RootPanel.get("information").add(new HTML("<h2>Wählbare Fraktionen</h2>"));
        String availableFactions = new String("<ul>");
        String nextFaction = new String();
        for (Faction newFaction : newClass.getAvailableFactions()) {
          nextFaction = newFaction.getName();
          if (character.getRace().getAvailableFactions().contains(newFaction)) {
            availableFactions = availableFactions + "<li>" + nextFaction + "</li>";
          }
        }
        availableFactions = availableFactions + "</ul>";
        RootPanel.get("information").add(new HTML(availableFactions));
      }
    }
  }

  // clear "content" #div and add Class SelectRacePanel to it
  private void doPrevButtonClick() {
    RootPanel.get("content").clear();
    RootPanel.get("content").add(SelectRaceDialog.getDialog(getEntry(), user, character).getFormPanel());
  }

  // clear "content" #div and add Class SelectFactionPanel to it
  private void doNextButtonClick() {
    character.setCharClass(getSelectedClass());
    RootPanel.get("content").clear();
    RootPanel.get("content").add(SelectFactionDialog.getDialog(getEntry(), character, user).getFormPanel());
  }

  private CClass getSelectedClass() {

    for (CClass selectedClass : getEntry().getTestValues().getClasses()) {
      String className = selectedClass.getName();
      String selectedName = getDisplay().getSelectedClass();
      if (className.equals(selectedName)) {
        return selectedClass;
      }
    }
    return null;
  }

  /**
   * Creates the dialog with its widget.
   * 
   * @param entry
   *          {@link TimadorusWebApp} entry point.
   * @param character
   * @param user
   * @return {@link SelectClassDialog}
   */
  public static SelectClassDialog getSelecteddDialog(DefaultTimadorusWebApp entry, Character character,
    User user) {
    SelectClassDialog.Display display = new SelectClassWidget(character,
                                                              entry.getTestValues().getClasses());
    SelectClassDialog dialog = new SelectClassDialog(display, entry, character, user);
    return dialog;
  }

}
