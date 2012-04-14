package org.timadorus.webapp.client.character.ui.ready;

import org.timadorus.webapp.client.TimadorusWebApp;
import org.timadorus.webapp.client.character.Character;
import org.timadorus.webapp.client.character.ui.DefaultDisplay;
import org.timadorus.webapp.client.character.ui.DefaultWidget;

public class ReadyDialog extends DefaultWidget<ReadyDialog.Display> {
  public interface Display extends DefaultDisplay {

    public void setContragulationMessage(String msg);

    public void setInformationMessage(String msg);
  }

  private final String congratulationMsg = "<p>Herzlichen Glückwunsch.</p><p>Ihr Charakter "
      + "wurde gespeichert. Loggen sie sich auf dem Client mit ihren Accountdaten ein";

  private final String informationMsg = "<h1>Charakter erstellt</h1><p>Herzlichen Glückwunsch"
      + ". Sie können nun den Client starten und ihren neuen Charakter ausprobieren!";

  public ReadyDialog(ReadyDialog.Display display, TimadorusWebApp entry) {
    super(display, entry);
    
    this.getDisplay().setContragulationMessage(congratulationMsg);
    this.getDisplay().setInformationMessage(informationMsg);
  }

  public static ReadyDialog getReadyDialog(TimadorusWebApp entry, Character characterIn) {
    ReadyDialog.Display display = (Display) new CharacterReadyWidget(characterIn);
    ReadyDialog dialog = new ReadyDialog(display, entry);
    return dialog;
  }

}
