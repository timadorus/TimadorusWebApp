package org.timadorus.webapp.client.character.ui.selectname;

import org.timadorus.webapp.beans.Character;
import org.timadorus.webapp.beans.User;
import org.timadorus.webapp.client.DefaultTimadorusWebApp;
import org.timadorus.webapp.client.character.ui.DefaultActionHandler;
import org.timadorus.webapp.client.character.ui.DefaultDialog;
import org.timadorus.webapp.client.character.ui.DefaultDisplay;
import org.timadorus.webapp.client.eventhandling.events.ShowSelectNameEvent;
import org.timadorus.webapp.client.eventhandling.handler.ShowDialogHandler;

import com.google.gwt.user.client.ui.RootPanel;

/**
 * 
 * @author aaz210
 * 
 */
public class SelectNameDialog extends DefaultDialog<SelectNameDialog.Display> implements ShowDialogHandler {

  public interface Display extends DefaultDisplay {

    /**
     * Gets the entered name of the character
     * 
     * @return {@link String}
     */
    public String getCharacterName();

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

    public void loadSelectCharacterReadyPanel(DefaultTimadorusWebApp entry, Character character);

    public void loadSelectAppearancePanel(DefaultTimadorusWebApp entry, Character character, User user);

    public void sendCharacterToServerToSave(Character aCharacter);
  }

  private Character character;

  private User user;

  public SelectNameDialog(Display display, DefaultTimadorusWebApp entry, Character character, User user) {
    super(display, entry);
    this.character = character;
    this.user = user;
    entry.addHandler(ShowSelectNameEvent.SHOWDIALOG, this);
  }

  private void initDisplay(Display display) {
    setDisplay(display);
    display.addNextButtonHandler(new DefaultActionHandler() {

      @Override
      public void onAction() {
        getCharacter().setName(getDisplay().getCharacterName());
        getCharacter().setUsername(getUser().getUsername());

        getDisplay().sendCharacterToServerToSave(getCharacter());
        getDisplay().loadSelectCharacterReadyPanel(getEntry(), getCharacter());
      }
    });
    display.addPrevButtonHandler(new DefaultActionHandler() {

      @Override
      public void onAction() {
        getDisplay().loadSelectAppearancePanel(getEntry(), getCharacter(), getUser());
      }
    });
  }

  private Character getCharacter() {
    return character;
  }

  private User getUser() {
    return user;
  }

  public static SelectNameDialog getDialog(DefaultTimadorusWebApp entry, Character character, User user) {
    // SelectNameDialog.Display display = new SelectNameWidget(character);
    SelectNameDialog dialog = new SelectNameDialog(null, entry, character, user);
    return dialog;
  }

  @Override
  public void show(DefaultTimadorusWebApp entry, Character character, User user) {
    this.character = character;
    this.user = user;
    SelectNameDialog.Display display = new SelectNameWidget(character);
    initDisplay(display);
    RootPanel.get("content").clear();
    RootPanel.get("content").add(getFormPanel());
  }

}
