package org.timadorus.webapp.client.character.ui.showcharacter;

import org.timadorus.webapp.beans.Character;

public interface CharacterActionHandler {
  public void onAction(Character character, String password);
}
