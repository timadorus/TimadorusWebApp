package org.timadorus.webapp.client.character.ui.selectname;

import org.timadorus.webapp.beans.User;
import org.timadorus.webapp.client.TimadorusWebApp;
import org.timadorus.webapp.client.character.Character;
import org.timadorus.webapp.client.character.ui.DefaultActionHandler;
import org.timadorus.webapp.client.character.ui.DefaultDialog;
import org.timadorus.webapp.client.character.ui.DefaultDisplay;
import org.timadorus.webapp.client.rpc.service.CreateCharacterService;
import org.timadorus.webapp.client.rpc.service.CreateCharacterServiceAsync;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * 
 * @author aaz210
 * 
 */
public class SelectNameDialog extends DefaultDialog<SelectNameDialog.Display> {

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

    public void loadSelectCharacterReadyPanel(TimadorusWebApp entry, Character character);

    public void loadSelectAppearancePanel(TimadorusWebApp entry, Character character, User user);
  }

  private Character character;

  private User user;

  public SelectNameDialog(Display display, TimadorusWebApp entry, Character character, User user) {
    super(display, entry);
    this.character = character;
    this.user = user;
    display.addNextButtonHandler(new DefaultActionHandler() {

      @Override
      public void onAction() {
        saveSelectedName(getDisplay().getCharacterName());
        getCharacter().setUsername(getUser().getUsername());
        sendCharacterToServerToSave();
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

  private void sendCharacterToServerToSave() {
    CreateCharacterServiceAsync createServiceAsync = GWT.create(CreateCharacterService.class);

    AsyncCallback<String> asyncCallback = new AsyncCallback<String>() {

      @Override
      public void onFailure(Throwable caught) {
        // TODO Auto-generated method stub
        System.out.println("Client/Server Create Character Service Failure!");
      }

      @Override
      public void onSuccess(String result) {
        // TODO Auto-generated method stub
        System.out.println(result);
      }
    };

    createServiceAsync.createCharacter(character, asyncCallback);
  }

  public void saveSelectedName(String text) {
    character.setName(text);
  }

  public Character getCharacter() {
    return character;
  }

  public User getUser() {
    return user;
  }

  public static SelectNameDialog getDialog(TimadorusWebApp entry, Character character, User user) {
    SelectNameDialog.Display display = new SelectNameWidget(character);
    SelectNameDialog dialog = new SelectNameDialog(display, entry, character, user);
    return dialog;
  }

}
