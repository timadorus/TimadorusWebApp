package org.timadorus.webapp.client.character.ui.characterlist;

import java.util.List;

import org.timadorus.webapp.beans.Character;
import org.timadorus.webapp.beans.User;
import org.timadorus.webapp.client.service.Service;
import org.timadorus.webapp.client.service.ServiceAsync;
import org.timadorus.webapp.client.service.ServiceType;
import org.timadorus.webapp.shared.Action;
import org.timadorus.webapp.shared.Response;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class CharacterListSync {

  private List<Character> characterList;

  private final ServiceAsync<User, List<Character>> getService = GWT.create(Service.class);

  public CharacterListSync(User user) {
    getCharactersFromServer(user);
  }

  public List<Character> getCharacterList() {
    return characterList;
  }

  private void setCharacterList(List<Character> characterList) {
    this.characterList = characterList;
  }

  /**
   * Gets all the characters of the current user from the server.
   */
  private void getCharactersFromServer(User user) {

    Action<User> action = new Action<User>(ServiceType.GETCHARACTERS, user);
    AsyncCallback<Response<List<Character>>> response = new AsyncCallback<Response<List<Character>>>() {

      @Override
      public void onFailure(Throwable caught) {
        System.out.println(caught);
      }

      @Override
      public void onSuccess(Response<List<Character>> result) {
        if (result.getResult() != null) {
          setCharacterList(result.getResult());
        }
      }
    };

    getService.execute(action, response);

    // CharacterServiceAsync characterServiceAsync = GWT.create(CharacterService.class);
    //
    // AsyncCallback<List<Character>> asyncCallback = new AsyncCallback<List<Character>>() {
    // /**
    // * Updates the character list.
    // *
    // * @param result
    // * the list of all the current user's characters
    // */
    // public void onSuccess(List<Character> result) {
    // if (result != null) {
    // setCharacterList(result);
    // }
    // }
    //
    // // Shows a dialog box with the error message.
    // public void onFailure(Throwable caught) {
    // setCharacterList(null);
    // System.out.println(caught);
    // }
    // };
    //
    // characterServiceAsync.getCharacterList(user, asyncCallback);
  }
}
