package org.timadorus.webapp.client.character;

import org.timadorus.webapp.client.TimadorusWebApp;
import org.timadorus.webapp.client.User;

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

public class SelectAppearancePanel extends FormPanel {

  private TimadorusWebApp entry;

  private Character character;

  private User user;

  Button nextButton = new Button("weiter");

  Button prevButton = new Button("zurück");

  VerticalPanel panel = new VerticalPanel();
  
  Image selectWhiteSkinImage = new Image("media/images/whiteBox.jpg");
  
  Image selectBlackSkinImage = new Image("media/images/blackBox.jpg");
  
  Image selectBrownSkinImage = new Image("media/images/whiteBox.jpg");
  
  Image selectYellowSkinImage = new Image("media/images/yellowBox.jpg");
  
  Image selectRedSkinImage = new Image("media/images/redBox.jpg");
  
  Image selectGreenSkinImage = new Image("media/images/greenBox.jpg");
  
  Image selectWhiteHairImage = new Image("media/images/whiteBox.jpg");
  
  Image selectBlackHairImage = new Image("media/images/blackBox.jpg");
  
  Image selectBrownHairImage = new Image("media/images/whiteBox.jpg");
  
  Image selectYellowHairImage = new Image("media/images/yellowBox.jpg");
  
  Image selectRedHairImage = new Image("media/images/redBox.jpg");
  
  Image selectGreenHairImage = new Image("media/images/greenBox.jpg");
  
  Label skinColor = new Label("Klicke auf eine Farbe um deine Hautfarbe zu bestimmen!");
  
  Label hairColor = new Label("Klicke auf eine Farbe um deine Haarfarbe zu bestimmen!");

  // grid for handling selections
  FlexTable selectFactionGrid = new FlexTable();

  // grid for next/prev buttons
  FlexTable buttonGrid = new FlexTable();

  public SelectAppearancePanel(final TimadorusWebApp entryIn, Character characterIn, User user) {
    super();
    this.entry = entryIn;
    this.character = characterIn;
    this.user = user;

    class MyHandler implements ClickHandler {
      public void onClick(ClickEvent event) {
        // prevButton onclick
        if (event.getSource().equals(prevButton)) {
          loadSelectSkillLvl1Panel();

          // nextButton onclick
        } else if (event.getSource().equals(nextButton)) {
          saveSelectedSkinColor();
          saveSelectedHairColor();
          loadSelectNamePanel();

        } else if (event.getSource().equals(selectBlackSkinImage)) {          
          skinColor.setText("Schwarze Hautfarbe ausgewählt!");            
        } else if (event.getSource().equals(selectBrownSkinImage)) {          
          skinColor.setText("Braune Hautfarbe ausgewählt!");            
        } else if (event.getSource().equals(selectGreenSkinImage)) {          
          skinColor.setText("Grüne Hautfarbe ausgewählt!");            
        } else if (event.getSource().equals(selectRedSkinImage)) {          
          skinColor.setText("Rote Hautfarbe ausgewählt!");            
        } else if (event.getSource().equals(selectWhiteSkinImage)) {          
          skinColor.setText("Weiße Hautfarbe ausgewählt!");            
        } else if (event.getSource().equals(selectYellowSkinImage)) {          
          skinColor.setText("Gelbe Hautfarbe ausgewählt!");            
        } else if (event.getSource().equals(selectBlackHairImage)) {          
          hairColor.setText("Schwarze Haarfarbe ausgewählt!");            
        } else if (event.getSource().equals(selectBrownHairImage)) {          
          hairColor.setText("Braune Haarfarbe ausgewählt!");            
        } else if (event.getSource().equals(selectGreenHairImage)) {          
          hairColor.setText("Grüne Haarfarbe ausgewählt!");            
        } else if (event.getSource().equals(selectRedHairImage)) {          
          hairColor.setText("Rote Haarfarbe ausgewählt!");            
        } else if (event.getSource().equals(selectWhiteHairImage)) {          
          hairColor.setText("Weiße Haarfarbe ausgewählt!");            
        } else if (event.getSource().equals(selectYellowHairImage)) {          
          hairColor.setText("Gelbe Haarfarbe ausgewählt!");            
        }        
      }      
    }
    MyHandler handler = new MyHandler();
    selectBlackHairImage.addClickHandler(handler);
    selectBlackSkinImage.addClickHandler(handler);
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
    
    panel.add(headline);

    panel.add(buttonGrid);

    RootPanel.get("information").clear();
    RootPanel.get("information").add(getInformation());

    RootPanel.get("content").clear();
    RootPanel.get("content").add(panel);
  }
  
  public void saveSelectedHairColor() {
    // TODO Auto-generated method stub
    
  }

  public void saveSelectedSkinColor() {
    // TODO Auto-generated method stub
    
  }
  
  private static final HTML getInformation() {
    HTML information = new HTML("<h1>Aussehen wählen</h1><p>Wählen Sie hier das Aussehen ihres Charakters.</p>");
    return information;
  }
  
  public void loadSelectSkillLvl1Panel() {
    RootPanel.get("content").clear();
    RootPanel.get("content").add(SelectSkillLevel1Panel.getSelectSkillLevel1Panel(entry, character, user));
  }
  
  public void loadSelectNamePanel() {
    RootPanel.get("content").clear();
    RootPanel.get("content").add(SelectNamePanel.getSelectNamePanel(entry, character, user));
  }
  
  public static SelectAppearancePanel getSelectAppearancePanel(TimadorusWebApp entry, Character character, User user) {
    return new SelectAppearancePanel(entry, character, user);
  }
}
