package org.timadorus.webapp.client.character.ui.premadecharacter;

import org.timadorus.webapp.beans.User;
import org.timadorus.webapp.client.DefaultTimadorusWebApp;
import org.timadorus.webapp.client.character.Character;
import org.timadorus.webapp.client.character.ui.DefaultActionHandler;
import org.timadorus.webapp.client.character.ui.DefaultDialog;
import org.timadorus.webapp.client.character.ui.DefaultDisplay;
import org.timadorus.webapp.client.rpc.service.CreateCharacterService;
import org.timadorus.webapp.client.rpc.service.CreateCharacterServiceAsync;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class PremadeCharacterDialog extends DefaultDialog<PremadeCharacterDialog.Display> {

  public interface Display extends DefaultDisplay {
    /**
     * Sets an action handler for the wizzard selection.
     * 
     * @param handler
     */
    public void addWizzardHandler(DefaultActionHandler handler);

    /**
     * Sets an action handler for the hunter selection.
     * 
     * @param handler
     */
    public void addHunterHandler(DefaultActionHandler handler);

    /**
     * Sets an action handler for the barbarian selection.
     * 
     * @param handler
     */
    public void addBarbarianHandler(DefaultActionHandler handler);

    public void addNextButtonHandler(DefaultActionHandler handler);

    public void addPrevButtonHandler(DefaultActionHandler handler);

    public void loadCharacterReadyPanel(Character character, DefaultTimadorusWebApp entry);

    public void loadCharacterPanel(DefaultTimadorusWebApp entry, User user);

    public void setInformation(String text);
  }

  private User user;

  public PremadeCharacterDialog(Display display, DefaultTimadorusWebApp entry, User user) {
    super(display, entry);
    this.user = user;
    display.addHunterHandler(new DefaultActionHandler() {

      @Override
      public void onAction() {
        getDisplay().setInformation(getHunterInformation());
      }
    });
    display.addBarbarianHandler(new DefaultActionHandler() {

      @Override
      public void onAction() {
        getDisplay().setInformation(getBarbarianInformation());
      }
    });

    display.addWizzardHandler(new DefaultActionHandler() {

      @Override
      public void onAction() {
        getDisplay().setInformation(getWizzardInformation());
      }
    });
    display.addNextButtonHandler(new DefaultActionHandler() {

      @Override
      public void onAction() {
        Character character = getEntry().getTestValues().createTestCharacter(getUser().getUsername());
        sendToServer(character);
        getDisplay().loadCharacterReadyPanel(character, getEntry());
      }
    });
    display.addPrevButtonHandler(new DefaultActionHandler() {

      @Override
      public void onAction() {
        getDisplay().loadCharacterPanel(getEntry(), getUser());
      }
    });

  }

  private void sendToServer(Character character) {
    CreateCharacterServiceAsync createCharacterServiceAsync = GWT
        .create(CreateCharacterService.class);
    AsyncCallback<String> asyncCallback = new AsyncCallback<String>() {

      public void onSuccess(String result) {
        if (result.equals("SUCCESS")) {
          History.newItem("welcome");
        } else {
          System.out.println("Character creation failed!");
          History.newItem("welcome");
        }
      }

      public void onFailure(Throwable caught) {
        System.out.println(caught);
      }
    };
    createCharacterServiceAsync.createCharacter(character, asyncCallback);
  }

  

  // return and stores information for this panel
  private String getBarbarianInformation() {
    return "<h1>Der Barbar</h1><p>Die Wilden Lande sind eine raue Welt der Entbehrungen "
        + "und Gefahren, aber auch der Wunder und der Schönheit. Schroffe Felsen, "
        + "schnee­bedeckte Berge, reißende Flüsse, tiefe Wälder, feuerspuckende "
        + "Vulkane, brodelnde Geysire, geheimnisvolle Tempel und furchtein­flößende "
        + "Raubtiere wirst du hier finden. Drachen, urtümliche Bestien von grausamer "
        + "Kraft. Zurückgezogene Sippen wunderschöner, tödlicher Amazonen. Und "
        + "natürlich die Stämme der Barbaren, ihre Bündnisse und Fehden, ihre "
        + "Bruderkriege und Feldzüge in fremde Lande, ihre Fruchtbarkeitsfeste "
        + "und ihre große Drachenjagd.</p>Quelle: http://www.wildelande.de/spiel.html";
  }

  // returns and stores information about class wizzard
  private String getWizzardInformation() {
    return "<h1>Der Zauberer</h1><p>Du bist ein echter MANN. Deine Arme sind dick wie "
        + "junge Bäume, dein Schwert kriegen die meisten nicht mal vom Boden hoch, "
        + "und dein Schwanz ist 25cm lang. Kein Scheiß! Du durchschwimmst reißende "
        + "Flüsse, erkletterst Berge und jagst Tiger und Bären in den tiefen "
        + "Wäldern deiner Heimat. Du metzelst deine Feinde nieder wie Vieh und "
        + "watest knöcheltief in ihrem Blut, und du fürchtest keinen Gegner. Nach "
        + "gewonnener Schlacht lässt du dich mit Met vollaufen und suchst dir ein "
        + "Weib, das dir einen bläst. Kurz: Du bist ein BARBAR!</p> "
        + "Quelle: http://www.wildelande.de/spiel.html";
  }

  private String getHunterInformation() {
    return "<h1>Der Waldläufer</h1><p>Du bist der Waldläufer. blablub. "
        + "dsdggggggggggggggggggggdsgfwragewargearhbaerhaerher gearg g ewrgewr geragh erhgre "
        + "gerg werg earghrea erhg ergher hergh aegdf gdfbher ghwer gerahg dfdf bbnetgwer age";
  }

  private User getUser() {
    return user;
  }

  public static PremadeCharacterDialog getDialog(DefaultTimadorusWebApp entry, User user) {
    PremadeCharacterDialog.Display display = new PremadeCharacterWidget();
    PremadeCharacterDialog dialog = new PremadeCharacterDialog(display, entry, user);
    return dialog;
  }

}
