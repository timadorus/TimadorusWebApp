package org.timadorus.webapp.client.character.ui.createcharacter;

import org.timadorus.webapp.beans.User;
import org.timadorus.webapp.client.TimadorusWebApp;
import org.timadorus.webapp.client.character.ui.DefaultActionHandler;
import org.timadorus.webapp.client.character.ui.DefaultDisplay;
import org.timadorus.webapp.client.character.ui.DefaultDialog;

public class CreateDialog extends DefaultDialog<CreateDialog.Display> {

  public interface Display extends DefaultDisplay {

    public void setHandlerPremade(DefaultActionHandler handler);

    public void setHandlerCustom(DefaultActionHandler handler);

    public void setHandlerNextButton(DefaultActionHandler handler);

    public void setPremadeInformation(String text);

    public void setCustomInformation(String text);

    public boolean isPremade();

    public boolean isCustom();

    public void loadPremadeCharacter(User user, TimadorusWebApp entry);

    public void loadCustomCharacter(User user, TimadorusWebApp entry);
  }

  private User user;

  public CreateDialog(Display display, TimadorusWebApp entry, User user) {
    super(display, entry);
    display.setHandlerPremade(new DefaultActionHandler() {

      @Override
      public void onAction() {
        getDisplay().setPremadeInformation(getPremadeInformation());
      }
    });

    display.setHandlerCustom(new DefaultActionHandler() {

      @Override
      public void onAction() {
        getDisplay().setCustomInformation(getCustomInformation());
      }
    });

    display.setHandlerNextButton(new DefaultActionHandler() {

      @Override
      public void onAction() {
        if (getDisplay().isCustom()) {
          getDisplay().loadCustomCharacter(getUser(), getEntry());
        } else if (getDisplay().isPremade()) {
          getDisplay().loadPremadeCharacter(getUser(), getEntry());
        }
      }
    });
  }

  private static final String getPremadeInformation() {
    return "<h1>Vorgefertigter Charakter</h1><p>Du bist ein echter MANN. Deine Arme "
        + "sind dick wie junge Bäume, dein Schwert kriegen die meisten nicht mal "
        + "vom Boden hoch, und dein Schwanz ist 25 cm lang. Kein Scheiß! Du "
        + "durchschwimmst reißende Flüsse, erkletterst Berge und jagst Tiger und "
        + "Bären in den tiefen Wäldern deiner Heimat. Du metzelst deine Feinde "
        + "nieder wie Vieh und watest knöcheltief in ihrem Blut, und du fürchtest "
        + "keinen Gegner. Nach gewonnener Schlacht lässt du dich mit Met vollaufen "
        + "und suchst dir ein Weib, das dir einen bläst. Kurz: Du bist ein "
        + "BARBAR!</p> Quelle: http://www.wildelande.de/spiel.html";
  }

  private static final String getCustomInformation() {
    return "<h1>Eigener Charakter</h1><p>Die Wilden Lande sind eine raue Welt der "
        + "Entbehrungen und Gefahren, aber auch der Wunder und der Schönheit. "
        + "Schroffe Felsen, schnee­bedeckte Berge, reißende Flüsse, tiefe Wälder,"
        + "feuerspuckende Vulkane, brodelnde Geysire, geheimnisvolle Tempel und "
        + "furchtein­flößende Raubtiere wirst du hier finden. Drachen, urtümliche"
        + " Bestien von grausamer Kraft. Zurückgezogene Sippen wunderschöner"
        + ", tödlicher Amazonen. Und natürlich die Stämme der Barbaren, ihre "
        + "Bündnisse und Fehden, ihre Bruderkriege und Feldzüge in fremde Lande, "
        + "ihre Fruchtbarkeitsfeste und ihre große Drachenjagd.</p>Quelle: " + "http://www.wildelande.de/spiel.html";
  }

  private User getUser() {
    return user;
  }

  public static CreateDialog getCreateDialog(TimadorusWebApp entry, User user) {
    CreateDialog.Display display = new CreateCharacterWidget();
    CreateDialog dialog = new CreateDialog(display, entry, user);
    return dialog;
  }
}