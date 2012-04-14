package org.timadorus.webapp.client.character.ui.ready;

import org.timadorus.webapp.client.TimadorusWebApp;
import org.timadorus.webapp.client.character.Character;

import com.google.gwt.user.client.ui.FormPanel;

public class ReadyDialog {
  public interface Display {

    /**
     * Returns the GWI FormPanal in which the widgets offer his controls.
     * 
     * @return {@link FormPanel}
     */
    public FormPanel getFormPanel();

    public void setContragulationMessage(String msg);

    public void setInformationMessage(String msg);
  }

  private final String congratulationMsg = "<p>Herzlichen Glückwunsch.</p><p>Ihr Charakter "
      + "wurde gespeichert. Loggen sie sich auf dem Client mit ihren Accountdaten ein";

  private final String informationMsg = "<h1>Charakter erstellt</h1><p>Herzlichen Glückwunsch"
      + ". Sie können nun den Client starten und ihren neuen Charakter ausprobieren!";

  private TimadorusWebApp entry;

  private ReadyDialog.Display display;

  public ReadyDialog(TimadorusWebApp entry, ReadyDialog.Display display) {
    this.entry = entry;
    this.display = display;
    this.display.setContragulationMessage(congratulationMsg);
    this.display.setInformationMessage(informationMsg);
  }

  public static ReadyDialog getReadyDialog(TimadorusWebApp entry, Character characterIn) {
    ReadyDialog.Display display = (Display) new CharacterReadyWidget(characterIn);
    ReadyDialog dialog = new ReadyDialog(entry, display);
    return dialog;
  }

  public FormPanel getFormPanel() {
    return display.getFormPanel();
  }

  public TimadorusWebApp getEntry() {
    return entry;
  }

}
