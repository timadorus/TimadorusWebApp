package org.timadorus.webapp.client.character;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.timadorus.webapp.client.HistoryStates;
import org.timadorus.webapp.client.TimadorusWebApp;
import org.timadorus.webapp.client.register.RegisterPanel;
import org.timadorus.webapp.client.character.SelectClassPanel;
import org.timadorus.webapp.client.character.TestCharacterValues;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.HistoryListener;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.HasHorizontalAlignment.HorizontalAlignmentConstant;

//ClassPanel allows you to choosing the Classes and Races of Character via Listbox
public class SelectSkillPanel extends FormPanel implements HistoryStates {

  final TimadorusWebApp entry;

  final Character character;

  Button nextButton = new Button("weiter");

  Button prevButton = new Button("zurück");

  VerticalPanel panel = new VerticalPanel();

  FlexTable buttonGrid = new FlexTable();

  FlexTable selectSkillGrid = new FlexTable();

  ListBox skillListBox = new ListBox();

  public SelectSkillPanel(final TimadorusWebApp entry, final Character character) {
    super();
    this.entry = entry;
    this.character = character;

    // TestValues

    // Create a handler for the sendButton and nameField
    class MyHandler implements ClickHandler {
      public void onClick(ClickEvent event) {
        // prevButton onclick
        if (event.getSource().equals(prevButton)) {
          loadSelectStatsPanelS0();
          // nextButton onclick
        } else if (event.getSource().equals(nextButton)) {
          // saveSelectedSkill();
          // saveSelectedGender();
          // loadSelectClassPanel();
          // skilllistitem onclick
        } else if (event.getSource().equals(skillListBox)) {
          // show skill informations
          String skillName = skillListBox.getValue(skillListBox.getSelectedIndex());
          ListIterator<Skill> skillIterator = entry.getTestValues().getSkills().listIterator();
          RootPanel.get("information").clear();
          while (skillIterator.hasNext()) {
            Skill newSkill = (Skill) skillIterator.next();
            if (newSkill.getName().equals(skillName)) {
              RootPanel.get("information").add(
                                               new HTML("<h1>" + newSkill.getName() + "</h1><p>"
                                                   + newSkill.getDescription() + "</p>"+"<p>"
                                                   + newSkill.toString() + "</p>"));

              // // Show available Lists
              // RootPanel.get("information").add(new HTML("<h2>Wählbare Klassen</h2>"));
              // ListIterator<CClass> classIterator = newSkill.getAvailableClasses().listIterator();
              // String availableClasses = new String("<ul>");
              // String nextClass = new String();
              // while (classIterator.hasNext()) {
              // CClass newClass = (CClass) classIterator.next();
              // nextClass = newClass.getName();
              // availableClasses = availableClasses + "<li>" + nextClass + "</li>";
              // }
              // availableClasses = availableClasses + "</ul>";
              // RootPanel.get("information").add(new HTML(availableClasses));
              //              
              // // Show available Factions
              // RootPanel.get("information").add(new HTML("<h2>Wählbare Fraktionen</h2>"));
              // ListIterator<Faction> factionIterator = newSkill.getAvailableFactions().listIterator();
              // String availableFactions4 = new String("<ul>");
              // String nextFaction = new String();
              // while (factionIterator.hasNext()) {
              // Faction newFaction = (Faction) factionIterator.next();
              // nextFaction = newFaction.getName();
              // if (newSkill.getAvailableFactions().contains(newFaction)) {
              // availableFactions4 = availableFactions4 + "<li>" + nextFaction + "</li>";
              // }
              // }
              // availableFactions4 = availableFactions4 + "</ul>";
              // RootPanel.get("information").add(new HTML(availableFactions4));

            }
          }

        }
      }
    }

    Image progressBar = new Image("media/images/progressbar_5.png");

    selectSkillGrid.setBorderWidth(0);
    selectSkillGrid.setStylePrimaryName("selectGrid");

    ListIterator<Skill> skillIterator = entry.getTestValues().getSkills().listIterator();
    while (skillIterator.hasNext()) {
      Skill newSkill = (Skill) skillIterator.next();
      skillListBox.addItem(newSkill.getName());
    }

    Label skillLabel = new Label("Fertigkeiten wählen: ");
    selectSkillGrid.setWidget(0, 0, skillLabel);
    selectSkillGrid.setWidget(0, 1, skillListBox);

    skillListBox.setVisibleItemCount(skillListBox.getItemCount());

    buttonGrid.getCellFormatter().setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_RIGHT);
    buttonGrid.setWidth("350px");
    buttonGrid.setWidget(0, 0, prevButton);
    buttonGrid.setWidget(0, 1, nextButton);

    // Add it to the root panel.

    HTML headline = new HTML("<h1>Fertigkeiten wählen</h1>");

    panel.setStyleName("panel");
    panel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
    panel.setWidth("100%");

    panel.add(progressBar);
    panel.add(new Label("Schritt 5 von 6"));
    panel.add(new Label("Geschlecht: " + character.getGender() + " | Rasse: " + character.getRace().getName()));
    panel.add(new Label("Klasse: " + character.getCharClass().getName() + " | Faction: "
        + character.getFaction().getName()));
    panel.add(headline);

    panel.add(selectSkillGrid);

    panel.add(buttonGrid);

    RootPanel.get("information").clear();
    RootPanel.get("information").add(getInformation());

    RootPanel.get("content").clear();
    RootPanel.get("content").add(panel);

    // Add Handlers
    MyHandler handler = new MyHandler();
    nextButton.addClickHandler(handler);
    prevButton.addClickHandler(handler);

    skillListBox.addClickHandler(handler);

  }

  public void loadSelectStatsPanelS0() {
    RootPanel.get("content").clear();
    RootPanel.get("content").add(SelectTempStatsPanel.getSelectTempStatsPanel(entry, character));
  }

  // public void saveSelectedSkill() {
  // character.setSkill(getSelectedSkill());
  // }

  public Skill getSelectedSkill() {
    Skill skill = entry.getTestValues().getSkills().get(skillListBox.getSelectedIndex());
    return skill;
  }

  public static SelectSkillPanel getSelectSkillPanel(TimadorusWebApp entry, Character character) {

    return new SelectSkillPanel(entry, character);
  }

  private static final HTML getInformation() {
    HTML information = new HTML(
                                "<h1>Fertigkeiten Wählen</h1><p>Wählen sie hier die Fertigkeiten ihres Charakteres. Beachten Sie, dass bestimmte Fertigkeiten ab bestimmten Level-Grad zu beziehen sind.</p>");

    return information;
  }

  public TimadorusWebApp getEntry() {
    return entry;
  }
}