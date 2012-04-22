package org.timadorus.webapp.client.character.ui.selectappearance;

import org.timadorus.webapp.beans.User;
import org.timadorus.webapp.client.DefaultTimadorusWebApp;
import org.timadorus.webapp.client.character.Character;
import org.timadorus.webapp.client.character.attributes.CharacterColors;
import org.timadorus.webapp.client.character.ui.DefaultActionHandler;
import org.timadorus.webapp.client.character.ui.DefaultDialog;
import org.timadorus.webapp.client.character.ui.DefaultDisplay;
import org.timadorus.webapp.client.character.ui.selectname.SelectNameDialog;
import org.timadorus.webapp.client.character.ui.selectskilllevel.SelectSkillLevelDialog;

import com.google.gwt.user.client.ui.RootPanel;

public class SelectAppearanceDialog extends DefaultDialog<SelectAppearanceDialog.Display> {

  public interface Display extends DefaultDisplay {
    public void addBlackHairHandler(DefaultActionHandler handler);

    public void addWhiteHairHandler(DefaultActionHandler handler);

    public void addBrownHairHandler(DefaultActionHandler handler);

    public void addGreenHairHandler(DefaultActionHandler handler);

    public void addRedHairHandler(DefaultActionHandler handler);

    public void addYellowHairHandler(DefaultActionHandler handler);

    public void addBlueHairHandler(DefaultActionHandler handler);

    public void addBlackSkinHandler(DefaultActionHandler handler);

    public void addWhiteSkinHandler(DefaultActionHandler handler);

    public void addBrownSkinHandler(DefaultActionHandler handler);

    public void addGreenSkinHandler(DefaultActionHandler handler);

    public void addRedSkinHandler(DefaultActionHandler handler);

    public void addYellowSkinHandler(DefaultActionHandler handler);

    public void addBlueSkinHandler(DefaultActionHandler handler);

    public void addNextButtonHandler(DefaultActionHandler handler);

    public void addPrevButtonHandler(DefaultActionHandler handler);

    /**
     * Enables or disables the next button
     * 
     * @param enalbe
     *          <code>true</code> if the button shall be enabled. Otherwise <code>false</code>.
     */
    public void setNextButtonEnable(boolean enabled);

    public void setHairColorText(String text);

    public void setSkinColorText(String text);
  }

  private Character character;

  private User user;

  private boolean hairColorChosen;

  private boolean skinColorChosen;

  public SelectAppearanceDialog(Display display, DefaultTimadorusWebApp entry, Character character, User user) {
    super(display, entry);

    this.character = character;
    this.user = user;

    hairColorChosen = false;
    skinColorChosen = false;

    getDisplay().setNextButtonEnable(false);

    // === Black =============================
    getDisplay().addBlackHairHandler(new DefaultActionHandler() {

      @Override
      public void onAction() {
        chooseHairColor(CharacterColors.BLACK);
      }
    });
    getDisplay().addBlackSkinHandler(new DefaultActionHandler() {

      @Override
      public void onAction() {
        chooseSkinColor(CharacterColors.BLACK);
      }
    });

    // === White =============================
    getDisplay().addWhiteHairHandler(new DefaultActionHandler() {

      @Override
      public void onAction() {
        chooseHairColor(CharacterColors.WHITE);
      }
    });
    getDisplay().addWhiteSkinHandler(new DefaultActionHandler() {

      @Override
      public void onAction() {
        chooseSkinColor(CharacterColors.WHITE);
      }
    });

    // === brown =============================
    getDisplay().addBrownHairHandler(new DefaultActionHandler() {

      @Override
      public void onAction() {
        chooseHairColor(CharacterColors.BROWN);
      }
    });
    getDisplay().addBrownSkinHandler(new DefaultActionHandler() {

      @Override
      public void onAction() {
        chooseSkinColor(CharacterColors.BROWN);
      }
    });

    // === Green =============================
    getDisplay().addGreenHairHandler(new DefaultActionHandler() {

      @Override
      public void onAction() {
        chooseHairColor(CharacterColors.GREEN);
      }
    });
    getDisplay().addGreenSkinHandler(new DefaultActionHandler() {

      @Override
      public void onAction() {
        chooseSkinColor(CharacterColors.GREEN);
      }
    });

    // === Red =============================
    getDisplay().addRedHairHandler(new DefaultActionHandler() {

      @Override
      public void onAction() {
        chooseHairColor(CharacterColors.RED);
      }
    });
    getDisplay().addRedSkinHandler(new DefaultActionHandler() {

      @Override
      public void onAction() {
        chooseSkinColor(CharacterColors.RED);
      }
    });

    // === Yellow =============================
    getDisplay().addYellowHairHandler(new DefaultActionHandler() {

      @Override
      public void onAction() {
        chooseHairColor(CharacterColors.YELLOW);
      }
    });
    getDisplay().addYellowSkinHandler(new DefaultActionHandler() {

      @Override
      public void onAction() {
        chooseSkinColor(CharacterColors.YELLOW);
      }
    });

    // === Blue =============================
    getDisplay().addBlueHairHandler(new DefaultActionHandler() {

      @Override
      public void onAction() {
        chooseHairColor(CharacterColors.BLUE);
      }
    });
    getDisplay().addBlueSkinHandler(new DefaultActionHandler() {

      @Override
      public void onAction() {
        chooseSkinColor(CharacterColors.BLUE);
      }
    });

    // === NEXT ===============================
    getDisplay().addNextButtonHandler(new DefaultActionHandler() {

      @Override
      public void onAction() {
        loadSelectNamePanel();
      }
    });

    // === Prev ===============================
    getDisplay().addPrevButtonHandler(new DefaultActionHandler() {

      @Override
      public void onAction() {
        loadSelectSkillLvl1Panel();
      }
    });

    DefaultActionHandler allChoosenHandler = new DefaultActionHandler() {

      @Override
      public void onAction() {
        if (skinColorChosen && hairColorChosen) {
          getDisplay().setNextButtonEnable(true);
        }

      }
    };

    getDisplay().addBlackHairHandler(allChoosenHandler);
    getDisplay().addWhiteHairHandler(allChoosenHandler);
    getDisplay().addBrownHairHandler(allChoosenHandler);
    getDisplay().addGreenHairHandler(allChoosenHandler);
    getDisplay().addRedHairHandler(allChoosenHandler);
    getDisplay().addYellowHairHandler(allChoosenHandler);
    getDisplay().addBlueHairHandler(allChoosenHandler);
    getDisplay().addBlackSkinHandler(allChoosenHandler);
    getDisplay().addWhiteSkinHandler(allChoosenHandler);
    getDisplay().addBrownSkinHandler(allChoosenHandler);
    getDisplay().addGreenSkinHandler(allChoosenHandler);
    getDisplay().addRedSkinHandler(allChoosenHandler);
    getDisplay().addYellowSkinHandler(allChoosenHandler);
    getDisplay().addBlueSkinHandler(allChoosenHandler);
  }

  /**
   * Sets the given {@link CharacterColors} to the skin of the character.
   * 
   * @param color
   */
  private void chooseSkinColor(CharacterColors color) {
    getDisplay().setSkinColorText("Selected skin color: " + color.getName());
    character.setSkinColor(color);
    skinColorChosen = true;
  }

  private void chooseHairColor(CharacterColors color) {
    getDisplay().setHairColorText("Selected hair color: " + color.getName());
    character.setHairColor(color);
    hairColorChosen = true;
  }

  /**
   * Loads the SelectSkillLvl1Panel.
   */
  public void loadSelectSkillLvl1Panel() {
    RootPanel.get("content").clear();
    RootPanel.get("content").add(SelectSkillLevelDialog.getDialog(getEntry(), character, user).getFormPanel());
  }

  /**
   * Loads the SelectSkillNamePanel.
   */
  public void loadSelectNamePanel() {
    RootPanel.get("content").clear();
    RootPanel.get("content").add(SelectNameDialog.getDialog(getEntry(), character, user).getFormPanel());
  }

  public static SelectAppearanceDialog getDialog(DefaultTimadorusWebApp entry, Character character, User user) {
    SelectAppearanceDialog.Display display = new SelectAppearanceWidget(character);
    SelectAppearanceDialog dialog = new SelectAppearanceDialog(display, entry, character, user);
    return dialog;
  }
}
