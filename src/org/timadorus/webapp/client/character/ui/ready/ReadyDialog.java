package org.timadorus.webapp.client.character.ui.ready;

import org.timadorus.webapp.beans.Character;
import org.timadorus.webapp.beans.User;
import org.timadorus.webapp.client.DefaultTimadorusWebApp;
import org.timadorus.webapp.client.character.ui.DefaultDialog;
import org.timadorus.webapp.client.character.ui.DefaultDisplay;
import org.timadorus.webapp.client.eventhandling.events.ShowReadyDialogEvent;
import org.timadorus.webapp.client.eventhandling.handler.ShowDialogHandler;

import com.google.gwt.user.client.ui.FormPanel;

public class ReadyDialog extends DefaultDialog<ReadyDialog.Display> implements ShowDialogHandler {
  public interface Display extends DefaultDisplay {

    public void setContragulationMessage(String msg);

    public void setInformationMessage(String msg);
    
    public void addToRootPanel(FormPanel aFormPanel);
  }

  private final String congratulationMsg = "<p>Herzlichen Glückwunsch.</p><p>Ihr Charakter "
      + "wurde gespeichert. Loggen sie sich auf dem Client mit ihren Accountdaten ein";

  private final String informationMsg = "<h1>Charakter erstellt</h1><p>Herzlichen Glückwunsch"
      + ". Sie können nun den Client starten und ihren neuen Charakter ausprobieren!";

  public ReadyDialog(ReadyDialog.Display display, DefaultTimadorusWebApp entry) {
    super(display, entry);
    entry.addHandler(ShowReadyDialogEvent.SHOWDIALOG, this);
    this.getDisplay().setContragulationMessage(congratulationMsg);
    this.getDisplay().setInformationMessage(informationMsg);
  }

  @Override
  public void show(DefaultTimadorusWebApp entry, Character character, User user) {
    getDisplay().addToRootPanel(getFormPanel());
  }

}
