package org.timadorus.webapp.client.character.ui.selectappearance;

import org.timadorus.webapp.beans.Character;
import org.timadorus.webapp.client.character.ui.DefaultActionHandler;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Panel for the selection of the characters appearance
 * 
 * @author aaz210
 * 
 */
public class SelectAppearanceWidget extends FormPanel implements SelectAppearanceDialog.Display {

  private Button nextButton;

  private Button prevButton;

  private VerticalPanel panel;

  private Image selectWhiteSkinImage;

  private Image selectBlackSkinImage;

  private Image selectBrownSkinImage;

  private Image selectYellowSkinImage;

  private Image selectRedSkinImage;

  private Image selectGreenSkinImage;

  private Image selectBlueSkinImage;

  private Image selectWhiteHairImage;

  private Image selectBlackHairImage;

  private Image selectBrownHairImage;

  private Image selectYellowHairImage;

  private Image selectRedHairImage;

  private Image selectGreenHairImage;

  private Image selectBlueHairImage;

  private Label skinColor;

  private Label hairColor;

  private FlexTable selectSkinColorGrid;

  private FlexTable selectHairColorGrid;

  // grid for next/prev buttons
  private FlexTable buttonGrid;

  private Label myGenderRaceLbl;

  private Label myClassFactionLbl;

  private Label mySkillLevel0Lbl;

  private Label mySkillLevel1Lbl;

  private static int row0 = 0;
  
  private static int column0 = 0;

  private static int column1 = 1;

  private static int column2 = 2;

  private static int column3 = 3;

  private static int column4 = 4;

  private static int column5 = 5;

  private static int column6 = 6;

  public SelectAppearanceWidget() {
    super();

    // Init Controls
    nextButton = new Button("weiter");
    prevButton = new Button("zurück");
    panel = new VerticalPanel();
    selectWhiteSkinImage = new Image("media/images/whiteBox.jpg");
    selectBlackSkinImage = new Image("media/images/blackBox.jpg");
    selectBrownSkinImage = new Image("media/images/brownBox.jpg");
    selectYellowSkinImage = new Image("media/images/yellowBox.jpg");
    selectRedSkinImage = new Image("media/images/redBox.jpg");
    selectGreenSkinImage = new Image("media/images/greenBox.jpg");
    selectBlueSkinImage = new Image("media/images/blueBox.jpg");
    selectWhiteHairImage = new Image("media/images/whiteBox.jpg");
    selectBlackHairImage = new Image("media/images/blackBox.jpg");
    selectBrownHairImage = new Image("media/images/brownBox.jpg");
    selectYellowHairImage = new Image("media/images/yellowBox.jpg");
    selectRedHairImage = new Image("media/images/redBox.jpg");
    selectGreenHairImage = new Image("media/images/greenBox.jpg");
    selectBlueHairImage = new Image("media/images/blueBox.jpg");
    skinColor = new Label("Klicke auf eine Farbe um deine Hautfarbe zu bestimmen!");
    hairColor = new Label("Klicke auf eine Farbe um deine Haarfarbe zu bestimmen!");
    selectSkinColorGrid = new FlexTable();
    selectHairColorGrid = new FlexTable();
    buttonGrid = new FlexTable();

    myGenderRaceLbl = new Label("Geschlecht: | Rasse: ");
    myClassFactionLbl = new Label("Klasse: | Faction: ");
    mySkillLevel0Lbl = new Label("");
    mySkillLevel1Lbl = new Label("");
    // Arrange Controls

    nextButton.setEnabled(false);

    HTML headline = new HTML("<h1>Aussehen waehlen</h1>");

    Image progressBar = new Image("media/images/progressbar_7.png");

    RootPanel.get("information").add(new HTML("Waehle das Aussehen deines Charakters!"));

    buttonGrid.getCellFormatter().setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_RIGHT);
    buttonGrid.setWidth("350px");
    buttonGrid.setWidget(row0, column0, prevButton);
    buttonGrid.setWidget(row0, column1, nextButton);

    panel.setStyleName("panel");
    panel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
    panel.setWidth("100%");

    panel.add(progressBar);
    panel.add(new Label("Schritt 8 von 9"));
    panel.add(myGenderRaceLbl);
    panel.add(myClassFactionLbl);
    panel.add(mySkillLevel0Lbl);
    panel.add(mySkillLevel1Lbl);

    selectSkinColorGrid.setWidget(row0, column0, selectWhiteSkinImage);
    selectSkinColorGrid.setWidget(row0, column1, selectBlackSkinImage);
    selectSkinColorGrid.setWidget(row0, column2, selectBrownSkinImage);
    selectSkinColorGrid.setWidget(row0, column3, selectGreenSkinImage);
    selectSkinColorGrid.setWidget(row0, column4, selectYellowSkinImage);
    selectSkinColorGrid.setWidget(row0, column5, selectRedSkinImage);
    selectSkinColorGrid.setWidget(row0, column6, selectBlueSkinImage);

    selectHairColorGrid.setWidget(row0, column0, selectWhiteHairImage);
    selectHairColorGrid.setWidget(row0, column1, selectBlackHairImage);
    selectHairColorGrid.setWidget(row0, column2, selectBrownHairImage);
    selectHairColorGrid.setWidget(row0, column3, selectGreenHairImage);
    selectHairColorGrid.setWidget(row0, column4, selectYellowHairImage);
    selectHairColorGrid.setWidget(row0, column5, selectRedHairImage);
    selectHairColorGrid.setWidget(row0, column6, selectBlueHairImage);

    panel.add(headline);

    panel.add(new Label("Waehlen Sie die Hautfarbe Ihres Charakters: "));
    panel.add(selectSkinColorGrid);
    panel.add(skinColor);

    panel.add(new Label("Waehlen Sie die Haarfarbe Ihres Charakters: "));
    panel.add(selectHairColorGrid);
    panel.add(hairColor);

    panel.add(buttonGrid);

    RootPanel.get("information").clear();
    RootPanel.get("information").add(getInformation());

    RootPanel.get("content").clear();
    RootPanel.get("content").add(panel);
  }

  /**
   * Gets the HTML for the information panel.
   * 
   * @return the HTML that specifies the text and of the information panel
   */
  private static final HTML getInformation() {
    HTML information = new HTML("<h1>Aussehen wählen</h1><p>Wählen Sie hier das Aussehen ihres Charakters.</p>");
    return information;
  }

  @Override
  public FormPanel getFormPanel() {
    return this;
  }

  @Override
  public void addBlackHairHandler(final DefaultActionHandler handler) {
    selectBlackHairImage.addClickHandler(new ClickHandler() {

      @Override
      public void onClick(ClickEvent arg0) {
        handler.onAction();
      }
    });
  }

  @Override
  public void addWhiteHairHandler(final DefaultActionHandler handler) {
    selectWhiteHairImage.addClickHandler(new ClickHandler() {

      @Override
      public void onClick(ClickEvent arg0) {
        handler.onAction();
      }
    });
  }

  @Override
  public void addBrownHairHandler(final DefaultActionHandler handler) {
    selectBrownHairImage.addClickHandler(new ClickHandler() {

      @Override
      public void onClick(ClickEvent arg0) {
        handler.onAction();
      }
    });
  }

  @Override
  public void addGreenHairHandler(final DefaultActionHandler handler) {
    selectGreenHairImage.addClickHandler(new ClickHandler() {

      @Override
      public void onClick(ClickEvent arg0) {
        handler.onAction();
      }
    });
  }

  @Override
  public void addRedHairHandler(final DefaultActionHandler handler) {
    selectRedHairImage.addClickHandler(new ClickHandler() {

      @Override
      public void onClick(ClickEvent arg0) {
        handler.onAction();
      }
    });
  }

  @Override
  public void addYellowHairHandler(final DefaultActionHandler handler) {
    selectYellowHairImage.addClickHandler(new ClickHandler() {

      @Override
      public void onClick(ClickEvent arg0) {
        handler.onAction();
      }
    });
  }

  @Override
  public void addBlueHairHandler(final DefaultActionHandler handler) {
    selectBlueHairImage.addClickHandler(new ClickHandler() {

      @Override
      public void onClick(ClickEvent arg0) {
        handler.onAction();
      }
    });
  }

  @Override
  public void addBlackSkinHandler(final DefaultActionHandler handler) {
    selectBlackSkinImage.addClickHandler(new ClickHandler() {

      @Override
      public void onClick(ClickEvent arg0) {
        handler.onAction();
      }
    });
  }

  @Override
  public void addWhiteSkinHandler(final DefaultActionHandler handler) {
    selectWhiteSkinImage.addClickHandler(new ClickHandler() {

      @Override
      public void onClick(ClickEvent arg0) {
        handler.onAction();
      }
    });
  }

  @Override
  public void addBrownSkinHandler(final DefaultActionHandler handler) {
    selectBrownSkinImage.addClickHandler(new ClickHandler() {

      @Override
      public void onClick(ClickEvent arg0) {
        handler.onAction();
      }
    });
  }

  @Override
  public void addGreenSkinHandler(final DefaultActionHandler handler) {
    selectGreenSkinImage.addClickHandler(new ClickHandler() {

      @Override
      public void onClick(ClickEvent arg0) {
        handler.onAction();
      }
    });
  }

  @Override
  public void addRedSkinHandler(final DefaultActionHandler handler) {
    selectRedSkinImage.addClickHandler(new ClickHandler() {

      @Override
      public void onClick(ClickEvent arg0) {
        handler.onAction();
      }
    });
  }

  @Override
  public void addYellowSkinHandler(final DefaultActionHandler handler) {
    selectYellowSkinImage.addClickHandler(new ClickHandler() {

      @Override
      public void onClick(ClickEvent arg0) {
        handler.onAction();
      }
    });
  }

  @Override
  public void addBlueSkinHandler(final DefaultActionHandler handler) {
    selectBlueSkinImage.addClickHandler(new ClickHandler() {

      @Override
      public void onClick(ClickEvent arg0) {
        handler.onAction();
      }
    });
  }

  @Override
  public void addNextButtonHandler(final DefaultActionHandler handler) {
    nextButton.addClickHandler(new ClickHandler() {

      @Override
      public void onClick(ClickEvent arg0) {
        handler.onAction();
      }
    });
  }

  @Override
  public void addPrevButtonHandler(final DefaultActionHandler handler) {
    prevButton.addClickHandler(new ClickHandler() {

      @Override
      public void onClick(ClickEvent arg0) {
        handler.onAction();
      }
    });
  }

  @Override
  public void setNextButtonEnable(boolean enabled) {
    nextButton.setEnabled(enabled);
  }

  @Override
  public void setHairColorText(String text) {
    hairColor.setText(text);
  }

  @Override
  public void setSkinColorText(String text) {
    skinColor.setText(text);
  }

  @Override
  public void setCharacter(Character character) {
    String aClass = character.getClass().toString();
    String aFaction = character.getFaction().toString();
    String aGender = character.getGender();
    String aRace = character.getRace().toString();
    String aSkillLevel0 = character.getSkillListNames();
    String aSkillLevel1 = character.getSkillLevel1ListNames();

    myClassFactionLbl.setText("Klasse: " + aClass + " | Fraktion: " + aFaction);
    myGenderRaceLbl.setText("Geschlecht: " + aGender + " | Rasse: " + aRace);
    mySkillLevel0Lbl.setText(aSkillLevel0);
    mySkillLevel1Lbl.setText(aSkillLevel1);
  }

  @Override
  public void addToRootPanel(FormPanel aFormPanel) {
    RootPanel.get("content").clear();
    RootPanel.get("content").add(aFormPanel);    
  }
}
