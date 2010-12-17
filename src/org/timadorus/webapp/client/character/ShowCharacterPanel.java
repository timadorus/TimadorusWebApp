package org.timadorus.webapp.client.character;

import org.timadorus.webapp.client.TimadorusWebApp;
import org.timadorus.webapp.client.User;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public final class ShowCharacterPanel extends FormPanel {

  public static final int SHORT_MODE = 1;
  public static final int DETAIL_MODE = 2;
  
  private final int rows = 3;
  private final int columns = 4;
  private final int fourthCol = 3;
  
  private final int detailRows = 11;
  private final int detailColumns = 2;
  
  VerticalPanel panel = new VerticalPanel();
  private Grid grid = new Grid(rows, columns);
  private Grid detailGrid = new Grid(detailRows, detailColumns);
  private Label name = new Label("Name:");
  private Label gender = new Label("Gender:");
  private Label race = new Label("Race:");
  private Label cclass = new Label("Klasse:");
  private Label faction = new Label("Faction:");
  private Button back = new Button("Zurück");
  private Button delete = new Button("Löschen");

  private User user;
  private Character character;

  private ShowCharacterPanel(final TimadorusWebApp entryIn, final User userIn, 
    final Character characterIn, int modeIn) {
    super();

    this.user = userIn;
    this.character = characterIn;
    
    if (modeIn == SHORT_MODE) {
      buildShortMode();
    } else if (modeIn == DETAIL_MODE) {
      buildDetailMode();
    }
    
    class MyHandler implements ClickHandler {
      public void onClick(ClickEvent event) {
        if (event.getSource().equals(delete)) {
          
        } else if (event.getSource().equals(back)) {
          RootPanel.get("content").clear();
          RootPanel.get("content").add(ShowCharacterlistPanel.getShowCharacterlistPanel(entryIn, userIn));
        }      
      }      
    }
    MyHandler handler = new MyHandler();
    delete.addClickHandler(handler);
    back.addClickHandler(handler);    
  }
  
  private void buildShortMode() {
    grid.setBorderWidth(1);
    
    // Display name
    grid.setWidget(0, 0, name);
    grid.setWidget(0, 1, new Label(character.getName()));
    
    // Display gender
    grid.setWidget(0, 2, gender);
    grid.setWidget(0, fourthCol, new Label(character.getGender()));
    
    // Display race
    grid.setWidget(1, 0, race);
    if (character.getRace() != null) {
      grid.setWidget(1, 1, new Label(character.getRace().getName()));
    } else {
      grid.setWidget(1, 1, new Label("--"));
    }
    
    // Display character class
    grid.setWidget(1, 2, cclass);
    if (character.getCharClass() != null) {
      grid.setWidget(1, fourthCol, new Label(character.getCharClass().getName()));
    } else {
      grid.setWidget(1, fourthCol, new Label("--"));
    }
    
    // Display faction
    grid.setWidget(2, 0, faction);
    if (character.getFaction() != null) {
      grid.setWidget(2, 1, new Label(character.getFaction().getName()));
    } else {
      grid.setWidget(2, 1, new Label("--"));
    }
    
    setWidget(grid);
    setStyleName("formPanel");
  }
  
  private void buildDetailMode() {
    detailGrid.setBorderWidth(1);
    
    // Display name
    detailGrid.setWidget(0, 0, new HTML("<b>Name:</b>"));
    detailGrid.setWidget(0, 1, new Label(character.getName()));
    
    // Display gender
    detailGrid.setWidget(1, 0, new HTML("<b>Gender:</b>"));
    detailGrid.setWidget(1, 1, new Label(character.getGender()));
    
    // Display race
    detailGrid.setWidget(2, 0, new HTML("<b>Race:</b>"));
    if (character.getRace() != null) {
      detailGrid.setWidget(2, 1, new Label(character.getRace().getName()));
    } else {
      detailGrid.setWidget(2, 1, new Label("--"));
    }
    
    // Display character class
    detailGrid.setWidget(3, 0, new HTML("<b>Class:</b>"));
    if (character.getCharClass() != null) {
      detailGrid.setWidget(3, 1, new Label(character.getCharClass().getName()));
    } else {
      detailGrid.setWidget(3, 1, new Label("--"));
    }
    
    // Display faction
    detailGrid.setWidget(4, 0, new HTML("<b>Faction:</b>"));
    if (character.getFaction() != null) {
      detailGrid.setWidget(4, 1, new Label(character.getFaction().getName()));
    } else {
      detailGrid.setWidget(4, 1, new Label("--"));
    }
    
    detailGrid.setWidget(5, 0, new HTML("<b>Hair color:</b>"));
    if (character.getHairColor() != null) {
      detailGrid.setWidget(5, 1, new Label(character.getHairColor()));
    } else {
      detailGrid.setWidget(5, 1, new Label("--"));
    }
    
    detailGrid.setWidget(6, 0, new HTML("<b>Skin color:</b>"));
    if (character.getSkinColor() != null) {
      detailGrid.setWidget(6, 1, new Label(character.getSkinColor()));
    } else {
      detailGrid.setWidget(6, 1, new Label("--"));
    }    
    
    detailGrid.setWidget(7, 0, new HTML("<b>Skills L0:</b>"));
    if (character.getSkillList() != null) {
      String html = "";      
      for (Skill skill : character.getSkillList()) {
        String skillDetails = skill.toString();
        html = html + skillDetails + "<p>";
      }
      html = html.substring(0, html.length() - 3);
      HTML skillsDetails = new HTML(html);
      detailGrid.setWidget(7, 1, skillsDetails);
    } else {
      detailGrid.setWidget(7, 1, new Label("--"));
    }
    
    detailGrid.setWidget(8, 0, new HTML("<b>Skills L1:</b>"));
    if (character.getSkillList() != null) {
      String html = "";      
      for (Skill skill : character.getSkillListLevel1()) {
        String skillDetails = skill.toString();
        html = html + skillDetails + "<p>";
      }
      html = html.substring(0, html.length() - 3);
      HTML skillsDetails = new HTML(html);
      detailGrid.setWidget(8, 1, skillsDetails);
    } else {
      detailGrid.setWidget(8, 1, new Label("--"));
    }
    
    detailGrid.setWidget(9, 0, new HTML("<b>Temp stats:</b>"));
    if (character.getSkillList() != null) {
      String html = "";
      int position = 0;
      for (Stat stat : character.getStatList()) {
        html = html + "<b>Name:</b> " + stat.getName() + "<br><b>Beschreibung:</b> "
        + stat.getDescription() + "<br><b>Stufe:</b> " + character.getTempStats().get(position++) + "<p>";
      }
      html = html.substring(0, html.length() - 3);
      HTML tempStatsDetails = new HTML(html);
      detailGrid.setWidget(9, 1, tempStatsDetails);
    } else {
      detailGrid.setWidget(9, 1, new Label("--"));
    }
    
    detailGrid.setWidget(10, 0, new HTML("<b>Pot stats:</b>"));
    if (character.getSkillList() != null) {
      String html = "";
      int position = 0;
      for (Stat stat : character.getStatList()) {
        html = html + "<b>Name:</b> " + stat.getName() + "<br><b>Beschreibung:</b> "
        + stat.getDescription() + "<br><b>Stufe:</b> " + character.getPotStats().get(position++) + "<p>";
      }
      html = html.substring(0, html.length() - 3);
      HTML tempStatsDetails = new HTML(html);
      detailGrid.setWidget(10, 1, tempStatsDetails);
    } else {
      detailGrid.setWidget(10, 1, new Label("--"));
    }
    
    FlexTable buttonGrid = new FlexTable();
    buttonGrid.setWidget(0, 0, back);
    buttonGrid.setWidget(0, 1, delete);
    
    panel.add(buttonGrid);
    panel.add(detailGrid);
    
    setWidget(panel);
    setStyleName("formPanel");    
  }

  public static final ShowCharacterPanel getShowShortCharacterPanel(TimadorusWebApp e, User u, Character c) {
    return new ShowCharacterPanel(e, u, c, ShowCharacterPanel.SHORT_MODE);
  }

  public static final ShowCharacterPanel getShowDetailCharacterPanel(TimadorusWebApp e, User u, Character c) {
    return new ShowCharacterPanel(e, u, c, ShowCharacterPanel.DETAIL_MODE);
  }

  public User getUser() {
    return user;
  }

  public void setUser(User userIn) {
    this.user = userIn;
  }
}
