package org.timadorus.webapp.client.character.ui.characterlist;

import org.timadorus.webapp.client.DefaultTimadorusWebApp;
import org.timadorus.webapp.client.character.ui.DefaultDialog;
import org.timadorus.webapp.client.character.ui.DefaultDisplay;

public class ShowCharacterListDialog extends DefaultDialog<ShowCharacterListDialog.Display>{
  
  public interface Display extends DefaultDisplay {
  }

  public ShowCharacterListDialog(Display display, DefaultTimadorusWebApp entry) {
    super(display, entry);
    // TODO Auto-generated constructor stub
  }
}
