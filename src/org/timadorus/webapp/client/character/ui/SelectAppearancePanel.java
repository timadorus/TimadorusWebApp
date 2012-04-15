package org.timadorus.webapp.client.character.ui;

import org.timadorus.webapp.beans.User;
import org.timadorus.webapp.client.DefaultTimadorusWebApp;
import org.timadorus.webapp.client.character.Character;
import org.timadorus.webapp.client.character.ui.selectname.SelectNameDialog;

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

// Panel for the selection of the characters appearance
public class SelectAppearancePanel extends FormPanel {

  private DefaultTimadorusWebApp entry;

  private Character character;

  private User user;

  Button nextButton = new Button("weiter");

  Button prevButton = new Button("zurück");

  VerticalPanel panel = new VerticalPanel();
  
  Image selectWhiteSkinImage = new Image("media/images/whiteBox.jpg");
  
  Image selectBlackSkinImage = new Image("media/images/blackBox.jpg");
  
  Image selectBrownSkinImage = new Image("media/images/brownBox.jpg");
  
  Image selectYellowSkinImage = new Image("media/images/yellowBox.jpg");
  
  Image selectRedSkinImage = new Image("media/images/redBox.jpg");
  
  Image selectGreenSkinImage = new Image("media/images/greenBox.jpg");
  
  Image selectBlueSkinImage = new Image("media/images/blueBox.jpg");
  
  Image selectWhiteHairImage = new Image("media/images/whiteBox.jpg");
  
  Image selectBlackHairImage = new Image("media/images/blackBox.jpg");
  
  Image selectBrownHairImage = new Image("media/images/brownBox.jpg");
  
  Image selectYellowHairImage = new Image("media/images/yellowBox.jpg");
  
  Image selectRedHairImage = new Image("media/images/redBox.jpg");
  
  Image selectGreenHairImage = new Image("media/images/greenBox.jpg");
  
  Image selectBlueHairImage = new Image("media/images/blueBox.jpg");
  
  Label skinColor = new Label("Klicke auf eine Farbe um deine Hautfarbe zu bestimmen!");
  
  Label hairColor = new Label("Klicke auf eine Farbe um deine Haarfarbe zu bestimmen!");

  FlexTable selectSkinColorGrid = new FlexTable();
  
  FlexTable selectHairColorGrid = new FlexTable();

  // grid for next/prev buttons
  FlexTable buttonGrid = new FlexTable();
  
  private String blackColor = "#000000";
  
  private String whiteColor = "#FFFFFF";
  
  private String brownColor = "#593107";
  
  private String greenColor = "#003000";
  
  private String redColor = "#CF0000";
  
  private String yellowColor = "#FFC600";
  
  private String blueColor = "#0017C7";
  
  private boolean hairColorChosen = false;
  
  private boolean skinColorChosen = false;
  
  public SelectAppearancePanel(final DefaultTimadorusWebApp entryIn, Character characterIn, User user) {
    super();
    this.entry = entryIn;
    this.character = characterIn;
    this.user = user;
    
    nextButton.setEnabled(false);

    
    // Create a handler for all the buttons and images.
    class MyHandler implements ClickHandler {
      
      /**
       * Will be triggered if the button was clicked.
       * 
       * @param event The ClickEvent object
       */
      public void onClick(ClickEvent event) {
        // prevButton onclick
        if (event.getSource().equals(prevButton)) {
          loadSelectSkillLvl1Panel();

          // nextButton onclick
        } else if (event.getSource().equals(nextButton)) {
          loadSelectNamePanel();

        } else if (event.getSource().equals(selectBlackSkinImage)) {          
          skinColor.setText("Schwarze Hautfarbe ausgewählt!");
          character.setSkinColor(blackColor);
          skinColorChosen = true;
        } else if (event.getSource().equals(selectBrownSkinImage)) {          
          skinColor.setText("Braune Hautfarbe ausgewählt!");
          character.setSkinColor(brownColor);
          skinColorChosen = true;
        } else if (event.getSource().equals(selectGreenSkinImage)) {          
          skinColor.setText("Grüne Hautfarbe ausgewählt!");
          character.setSkinColor(greenColor);
          skinColorChosen = true;
        } else if (event.getSource().equals(selectRedSkinImage)) {          
          skinColor.setText("Rote Hautfarbe ausgewählt!");
          character.setSkinColor(redColor);
          skinColorChosen = true;
        } else if (event.getSource().equals(selectWhiteSkinImage)) {          
          skinColor.setText("Weiße Hautfarbe ausgewählt!");
          character.setSkinColor(whiteColor);
          skinColorChosen = true;
        } else if (event.getSource().equals(selectYellowSkinImage)) {          
          skinColor.setText("Gelbe Hautfarbe ausgewählt!");
          character.setSkinColor(yellowColor);
          skinColorChosen = true;
        } else if (event.getSource().equals(selectBlueSkinImage)) {          
          skinColor.setText("Blaue Hautfarbe ausgewählt!");
          character.setSkinColor(blueColor);
          skinColorChosen = true;
        } else if (event.getSource().equals(selectBlackHairImage)) {          
          hairColor.setText("Schwarze Haarfarbe ausgewählt!"); 
          character.setHairColor(blackColor);
          hairColorChosen = true;
        } else if (event.getSource().equals(selectBrownHairImage)) {          
          hairColor.setText("Braune Haarfarbe ausgewählt!");
          character.setHairColor(brownColor);
          hairColorChosen = true;
        } else if (event.getSource().equals(selectGreenHairImage)) {          
          hairColor.setText("Grüne Haarfarbe ausgewählt!");
          character.setHairColor(greenColor);
          hairColorChosen = true;
        } else if (event.getSource().equals(selectRedHairImage)) {          
          hairColor.setText("Rote Haarfarbe ausgewählt!");
          character.setHairColor(redColor);
          hairColorChosen = true;
        } else if (event.getSource().equals(selectWhiteHairImage)) {          
          hairColor.setText("Weiße Haarfarbe ausgewählt!");
          character.setHairColor(whiteColor);
          hairColorChosen = true;
        } else if (event.getSource().equals(selectYellowHairImage)) {          
          hairColor.setText("Gelbe Haarfarbe ausgewählt!");
          character.setHairColor(yellowColor);
          hairColorChosen = true;
        } else if (event.getSource().equals(selectBlueHairImage)) {          
          skinColor.setText("Blaue Haarfarbe ausgewählt!");
          character.setHairColor(blueColor);
          hairColorChosen = true;
        }
        if (skinColorChosen && hairColorChosen) {
          nextButton.setEnabled(true);
        }
      }      
    }
    MyHandler handler = new MyHandler();
    selectBlackHairImage.addClickHandler(handler);
    selectBlackSkinImage.addClickHandler(handler);
    selectBlueSkinImage.addClickHandler(handler);
    selectBlueHairImage.addClickHandler(handler);
    selectBrownHairImage.addClickHandler(handler);
    selectBrownSkinImage.addClickHandler(handler);
    selectGreenHairImage.addClickHandler(handler);
    selectGreenSkinImage.addClickHandler(handler);
    selectRedHairImage.addClickHandler(handler);
    selectRedSkinImage.addClickHandler(handler);
    selectWhiteHairImage.addClickHandler(handler);
    selectWhiteSkinImage.addClickHandler(handler);
    selectYellowHairImage.addClickHandler(handler);
    selectYellowSkinImage.addClickHandler(handler);
    nextButton.addClickHandler(handler);
    prevButton.addClickHandler(handler);
    
    HTML headline = new HTML("<h1>Aussehen wählen</h1>");

    Image progressBar = new Image("media/images/progressbar_7.png");
    
    RootPanel.get("information").add(new HTML("Wähle das Aussehen deines Charakters!"));
    
    buttonGrid.getCellFormatter().setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_RIGHT);
    buttonGrid.setWidth("350px");
    buttonGrid.setWidget(0, 0, prevButton);
    buttonGrid.setWidget(0, 1, nextButton);

    panel.setStyleName("panel");
    panel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
    panel.setWidth("100%");
    
    panel.add(progressBar);
    panel.add(new Label("Schritt 8 von 9"));
    panel.add(new Label("Geschlecht: " + characterIn.getGender() + " | Rasse: " + characterIn.getRace().getName()));
    panel.add(new Label("Klasse: " + characterIn.getCharClass().getName() + " | Faction: "
                        + characterIn.getFaction().getName()));
    panel.add(new Label("Skills_L0: " + characterIn.getSkillListNames()));
    panel.add(new Label("Skills_L1: " + characterIn.getSkillLevel1ListNames()));
    
    selectSkinColorGrid.setWidget(0, 0, selectWhiteSkinImage);
    selectSkinColorGrid.setWidget(0, 1, selectBlackSkinImage);
    selectSkinColorGrid.setWidget(0, 2, selectBrownSkinImage);
    selectSkinColorGrid.setWidget(0, 3, selectGreenSkinImage);
    selectSkinColorGrid.setWidget(0, 4, selectYellowSkinImage);
    selectSkinColorGrid.setWidget(0, 5, selectRedSkinImage);
    selectSkinColorGrid.setWidget(0, 6, selectBlueSkinImage);
    
    selectHairColorGrid.setWidget(0, 0, selectWhiteHairImage);
    selectHairColorGrid.setWidget(0, 1, selectBlackHairImage);
    selectHairColorGrid.setWidget(0, 2, selectBrownHairImage);
    selectHairColorGrid.setWidget(0, 3, selectGreenHairImage);
    selectHairColorGrid.setWidget(0, 4, selectYellowHairImage);
    selectHairColorGrid.setWidget(0, 5, selectRedHairImage);
    selectHairColorGrid.setWidget(0, 6, selectBlueHairImage);
    
    panel.add(headline);
    
    panel.add(new Label("Wählen Sie die Hautfarbe Ihres Charakters: "));
    panel.add(selectSkinColorGrid);
    panel.add(skinColor);
    
    panel.add(new Label("Wählen Sie die Haarfarbe Ihres Charakters: "));
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
  
  /**
   * Loads the SelectSkillLvl1Panel.
   */
  public void loadSelectSkillLvl1Panel() {
    RootPanel.get("content").clear();
    RootPanel.get("content").add(SelectSkillLevel1Panel.getSelectSkillLevel1Panel(entry, character, user));
  }
  
  /**
   * Loads the SelectSkillNamePanel.
   */
  public void loadSelectNamePanel() {
    RootPanel.get("content").clear();
    RootPanel.get("content").add(SelectNameDialog.getDialog(entry, character, user).getFormPanel());
  }
  
  /**
   * Gets the SelectAppearancePanel.
   * 
   * @param entry the entry point of the application
   * @param character the character which is being build
   * @param user the user who is building the character 
   */
  public static SelectAppearancePanel getSelectAppearancePanel(DefaultTimadorusWebApp entry, 
      Character character, User user) {
    return new SelectAppearancePanel(entry, character, user);
  }
}
