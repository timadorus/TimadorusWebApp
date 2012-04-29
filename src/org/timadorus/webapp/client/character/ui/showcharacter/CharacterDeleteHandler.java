package org.timadorus.webapp.client.character.ui.showcharacter;

import org.timadorus.webapp.beans.Character;

public interface CharacterDeleteHandler {
  public void onDelete(Character character, String password);
}
