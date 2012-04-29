package org.timadorus.webapp.client.character.ui.characterlist;

import java.util.List;

import org.timadorus.webapp.beans.Character;
import org.timadorus.webapp.beans.User;
import org.timadorus.webapp.client.rpc.service.CharacterService;
import org.timadorus.webapp.client.rpc.service.CharacterServiceAsync;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class CharacterListSync {

  private List<Character> characterList;

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
    CharacterServiceAsync characterServiceAsync = GWT.create(CharacterService.class);

    AsyncCallback<List<Character>> asyncCallback = new AsyncCallback<List<Character>>() {
      /**
       * Updates the character list.
       * 
       * @param result
       *          the list of all the current user's characters
       */
      public void onSuccess(List<Character> result) {
        if (result != null) {
          setCharacterList(result);
        }
      }

      // Shows a dialog box with the error message.
      public void onFailure(Throwable caught) {
        setCharacterList(null);
        System.out.println(caught);
      }
    };

    characterServiceAsync.getCharacterList(user, asyncCallback);
  }
}
