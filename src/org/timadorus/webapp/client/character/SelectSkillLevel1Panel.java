package org.timadorus.webapp.client.character;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

import org.timadorus.webapp.client.TimadorusWebApp;

//import com.google.gwt.dev.util.collect.HashMap;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Image;

//FormPanel for selecting Skill-Level-1 items of a Character-Object
public class SelectSkillLevel1Panel extends FormPanel implements ChangeHandler {

  final TimadorusWebApp entry;

  final Character character;

  Button nextButton = new Button("weiter");

  Button prevButton = new Button("zurück");

  VerticalPanel panel = new VerticalPanel();

  FlexTable buttonGrid = new FlexTable();

  FlexTable selectSkillGrid = new FlexTable();

  ListBox skillListBox = new ListBox();

  Button hinzufuegenButton = new Button("+");

  Button entferneButton = new Button("-");

  ListBox addedskillListBox = new ListBox();

  private Set<String> tlist = new HashSet<String>();

  Label skillLabel = new Label("L1 Fertigkeiten wählen: ");

  Label addedskillLabel = new Label("L1 Ausgewählte Fertigkeiten: ");

  FlexTable selectStatGrid = new FlexTable();

  FlexTable selectSkillGridBox = new FlexTable();

  Button resetPage = new Button("reset");
  
  List<Skill> backupList; //=new ArrayList<Skill>(entry.getTestValues().getSkills());

  // Map<String,TextBox[]> tbMap=new HashMap(); // Map lässt sich nicht Compilieren unter GWT 2.0
  List<TextBox[]> tbObjList = new ArrayList<TextBox[]>();

  private String[] titleList = { "Skill-Name", "Cost", "Rank", "Rk_Bn", "Stat_Bn", "Level_Bn", "Item", "Total" };

  public SelectSkillLevel1Panel(final TimadorusWebApp entryIn, final Character characterIn) {
    super();
    this.entry = entryIn;
    this.character = characterIn;

    // TestValues

    // Create a handler for the sendButton and nameField
    class MyHandler implements ClickHandler {
      public void onClick(ClickEvent event) {
        // prevButton onclick
        if (event.getSource().equals(prevButton)) {
          loadSelectSkillPanel();
          // nextButton onclick
        } else if (event.getSource().equals(nextButton)) {
          saveSelectedSkillsInCharacter();
          loadSelectNamePanel();

        } else if (event.getSource().equals(skillListBox)) {
          // show skill informations
          String skillName = skillListBox.getValue(skillListBox.getSelectedIndex());
          ListIterator<Skill> skillIterator = entryIn.getTestValues().getSkillsLevel1().listIterator();
          RootPanel.get("information").clear();
          while (skillIterator.hasNext()) {
            Skill newSkill = (Skill) skillIterator.next();
            if (newSkill.getName().equals(skillName)) {
              RootPanel.get("information").add(new HTML("<h1>" + newSkill.getName() + "</h1><p>"
                                                        + newSkill.getDescription() + "</p>" + "<p>"
                                                        + newSkill.toString() + "</p>"));

            }
          }

        } else if (event.getSource().equals(hinzufuegenButton)) {

          String skillName = skillListBox.getValue(skillListBox.getSelectedIndex());

          if (!tlist.contains(skillName)) {
            addedskillListBox.addItem(skillName);
            tlist.add(skillName);
            skillCostTable();
          }
          readyToSave();

        } else if (event.getSource().equals(entferneButton)) {
          String skillName = addedskillListBox.getValue(addedskillListBox.getSelectedIndex());
          int ind = addedskillListBox.getSelectedIndex();
          if (addedskillListBox.getItemCount() > 0) {
            if (ind >= 0) {
              addedskillListBox.removeItem(ind);
              tlist.remove(skillName);
              skillCostTable();
            }

          }
          readyToSave();
        } else if (event.getSource().equals(resetPage)) {
          loadSelectSkillLevel1Panel(); //reset Seite
        }

      }

    }
    addedskillLabel.setStyleName("labelColorRed");
    nextButton.setEnabled(false);
    Image progressBar = new Image("media/images/progressbar_7.png");

    selectSkillGrid.setBorderWidth(0);
    selectSkillGrid.setStylePrimaryName("selectGrid");

    ListIterator<Skill> skillIterator = entryIn.getTestValues().getSkillsLevel1().listIterator();
    while (skillIterator.hasNext()) {
      Skill newSkill = (Skill) skillIterator.next();
      skillListBox.addItem(newSkill.getName());
    }

    skillListBox.setVisibleItemCount(skillListBox.getItemCount());
    addedskillListBox.setVisibleItemCount(skillListBox.getItemCount());
    addedskillListBox.setWidth("150");

    // Label choosenskillLabel = new Label("Ausgewählte Fertigkeiten: ");
    selectSkillGrid.setWidget(0, 0, skillLabel);
    selectSkillGrid.setWidget(0, 12, addedskillLabel);
    // selectSkillGrid.setWidget(1, 1, choosenskillLabel);
    selectSkillGrid.setWidget(1, 0, skillListBox);
    selectSkillGrid.setWidget(1, 8, hinzufuegenButton);
    selectSkillGrid.setWidget(2, 8, entferneButton);
    selectSkillGrid.setWidget(1, 12, addedskillListBox);

    buttonGrid.getCellFormatter().setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_RIGHT);
    buttonGrid.setWidth("350px");
    buttonGrid.setWidget(0, 0, prevButton);
    buttonGrid.setWidget(0, 1, nextButton);

    selectStatGrid = getSkillCostTableLabel();

    // Add it to the root panel.

    HTML headline = new HTML("<h1>L1 Fertigkeiten wählen</h1>");

    panel.setStyleName("panel");
    panel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
    panel.setWidth("100%");

    panel.add(progressBar);
    panel.add(new Label("Schritt 7 von 7"));
    panel.add(new Label("Geschlecht: " + characterIn.getGender() + " | Rasse: " + characterIn.getRace().getName()));
    panel.add(new Label("Klasse: " + characterIn.getCharClass().getName() + " | Faction: "
                        + characterIn.getFaction().getName() + " | Skills_L0: " + characterIn.getSkillListNames()));
    
    panel.add(headline);
    panel.add(selectSkillGrid);
    panel.add(selectStatGrid);
    panel.add(selectSkillGridBox);
    panel.add(buttonGrid);

    RootPanel.get("information").clear();
    RootPanel.get("information").add(getInformation());

    RootPanel.get("content").clear();
    RootPanel.get("content").add(panel);

    // Add Handlers
    MyHandler handler = new MyHandler();

    nextButton.addClickHandler(handler);
    prevButton.addClickHandler(handler);

    hinzufuegenButton.addClickHandler(handler);
    entferneButton.addClickHandler(handler);

    skillListBox.addClickHandler(handler);
    addedskillListBox.addClickHandler(handler);
    resetPage.addClickHandler(handler);

  }

  public void loadSelectSkillPanel() {
    RootPanel.get("content").clear();
    RootPanel.get("content").add(SelectSkillPanel.getSelectSkillPanel(entry, character));
  }

  public Skill getSelectedSkill() {
    Skill skill = entry.getTestValues().getSkillsLevel1().get(skillListBox.getSelectedIndex());
    return skill;
  }

  public static SelectSkillLevel1Panel getSelectSkillLevel1Panel(TimadorusWebApp entry, Character character) {

    return new SelectSkillLevel1Panel(entry, character);
  }

  private static final HTML getInformation() {
    HTML information = new HTML("<h1>L1 Fertigkeiten Wählen</h1><p>Wählen sie hier die L1 Fertigkeiten ihres "
                                + ". Beachten Sie, dass bestimmte Fertigkeiten ab bestimmten Level-Grad zu beziehen "
                                + "sind.</p>");
    return information;
  }

  public TimadorusWebApp getEntry() {
    return entry;
  }

  public void saveSelectedSkillsInCharacter() {
    for (String skillName : tlist) {
      ListIterator<Skill> skillIterator = entry.getTestValues().getSkillsLevel1().listIterator();
      while (skillIterator.hasNext()) {
        Skill sk1 = (Skill) skillIterator.next();
        if (sk1.getName().equals(skillName)) {
          character.getSkillListLevel1().add(sk1);

        }
      }
    }
  }

  public void readyToSave() {
    if (tlist.isEmpty()) {
      addedskillLabel.setStyleName("labelColorRed");
      nextButton.setEnabled(false);
      resetPage.setEnabled(false);
    } else {
      addedskillLabel.setStyleName("labelColorGreen");
      nextButton.setEnabled(true);
      resetPage.setEnabled(true);
    }
  }

  public void skillCostTable() {
    backupList = new ArrayList<Skill>(entry.getTestValues().getBackupSkills_Level1());
    Set<Skill> skillSet = new HashSet<Skill>();

    for (String skillName : tlist) {
      ListIterator<Skill> skillIterator = entry.getTestValues().getSkillsLevel1().listIterator();
      while (skillIterator.hasNext()) {
        Skill newSkill = (Skill) skillIterator.next();
        if (newSkill.getName().equals(skillName)) {
          skillSet.add(newSkill);
        }
      }
    }
    panel.remove(selectStatGrid);
    // panel.remove(saveSkCostButton);

    //selectStatGrid.removeAllRows();
    selectStatGrid = getSkillCostTableLabel();

    int j = 0;
    TextBox[] tbArr = new TextBox[8];
    
    for (Skill skill : skillSet) {
      tbArr = new TextBox[8];
      for (int i = 0; i < 8; i++) {
        TextBox tb = new TextBox();
        if (i == 0) {
          tb.setWidth("100");
        } else {
          tb.setWidth("60");
        }

        tb.setText(skill.getGesamtInfo()[i]);
        tb.setTitle(titleList[i]);
        selectStatGrid.setWidget(j + 1, i, tb);
        tbArr[i] = tb;

        tb.addChangeHandler(this);
      }
      // tbMap.put(skill.getName(), tbArr); //merke zu jedem Skill die TextBox-Felder im Map
      // tbArray[j]=tbArr; //merke zu jedem Skill die TextBox-Felder im Object-Array
      tbObjList.add(tbArr); // merke zu jedem Skill die TextBox-Felder in einer Liste
      j++;

    }
    if (skillSet.size() != 0) {
      selectSkillGrid.setWidget(j + 1, 4, resetPage);
    } else {
      selectSkillGrid.remove(resetPage);
    }

    // panel = new VerticalPanel();

    panel.add(selectStatGrid);
    panel.add(selectSkillGridBox);
    panel.add(resetPage);
    panel.add(buttonGrid);

    RootPanel.get("content").add(panel);
  }

  public FlexTable getSkillCostTableLabel() {

    FlexTable selectStatGrid1 = new FlexTable();
    selectStatGrid1.setBorderWidth(0);
    selectStatGrid1.setStyleName("selectGrid");

    selectStatGrid1.setWidget(0, 0, new Label("Skill                 "));
    selectStatGrid1.setWidget(0, 1, new Label("Cost   "));
    selectStatGrid1.setWidget(0, 2, new Label("Rank   "));
    // selectStatGrid1.setWidget(0, 3, new Label(""));
    selectStatGrid1.setWidget(0, 3, new Label("Rk_Bn  "));
    selectStatGrid1.setWidget(0, 4, new Label("Stat_Bn"));
    selectStatGrid1.setWidget(0, 5, new Label("Level_Bn."));
    selectStatGrid1.setWidget(0, 6, new Label("Item   "));
    selectStatGrid1.setWidget(0, 7, new Label("Total"));

    for (int i = 0; i < 8; i++) {
      TextBox tb = new TextBox();
      if (i == 0) {
        tb.setWidth("100");
      } else {
        tb.setWidth("60");
      }

      selectStatGrid1.setWidget(1, i, tb);
    }

    return selectStatGrid1;
  }

  @Override
  public void onChange(ChangeEvent event) {
    System.out.println("\n" + ((TextBox) event.getSource()).getValue());
//    Object otb = ((TextBox) event.getSource()).getValue();
//    String evTexboxText=((TextBox) event.getSource()).getText(); 
    String evTexboxID = "" + ((Object) (TextBox) event.getSource()).hashCode();
    String[] skInfo = new String[3];
    for (TextBox[] tb : tbObjList) {
      String skillN = "";
//      String skillAttTotal = "";
      for (int i = 0; i < tb.length; i++) {
        if (tb[i] != null) {
          if (i == 0) {
             skillN = tb[i].getText();
             //getRk_Bn() + getStat_Bn();
             int m = Integer.valueOf(tb[3].getText()) + Integer.valueOf(tb[4].getText()); //update "Total"-Cell
             tb[7].setText("" + m);
          }
          
//          String texbText = tb[i].getText();
          String texBoxID = "" + ((Object) tb[i]).hashCode();
          if (texBoxID.equals(evTexboxID)) {
//          if (textBox.getText().equalsIgnoreCase(evTBSkName)) {
//            String skillName = tb[i].getText();
            String skAtt = ((TextBox) event.getSource()).getTitle();
            String newAttrWert = ((TextBox) event.getSource()).getValue();
            skInfo[0] = skillN;
            skInfo[1] = skAtt;
            skInfo[2] = newAttrWert;
          }
        }
      }
    }
    
    List<Skill> skillList = entry.getTestValues().getSkillsLevel1();
    ListIterator<Skill> skillIterator = skillList.listIterator();
    while (skillIterator.hasNext()) {
      Skill skill = (Skill) skillIterator.next();
      if (skill.getName().equals(skInfo[0])) {
        if (skInfo[1].equalsIgnoreCase("Cost")) {
          skill.setCost(Integer.valueOf(skInfo[2]));
        }
        if (skInfo[1].equalsIgnoreCase("Rank")) {
          skill.setRank(Integer.valueOf(skInfo[2]));
        }
        if (skInfo[1].equalsIgnoreCase("Rk_Bn")) {
          skill.setRk_Bn(Integer.valueOf(skInfo[2]));
        }
        if (skInfo[1].equalsIgnoreCase("Stat_Bn")) {
          skill.setStat_Bn(Integer.valueOf(skInfo[2]));
        }
        if (skInfo[1].equalsIgnoreCase("Level_Bn")) {
          skill.setLevel_Bn(Integer.valueOf(skInfo[2]));
        }
        if (skInfo[1].equalsIgnoreCase("Item")) {
          skill.setItem(Integer.valueOf(skInfo[2]));
        }
        
        skill.setTotal("update");
        skillList.remove(skill);
        skillList.add(skill);
      }
    }
    
    //überschreibe alte originale Skill-Liste mit der über die GUI editierten Skill-Liste
    entry.getTestValues().setSkills(skillList); 
    
    if (event.getSource().equals(prevButton)) {
      // TODO: loadSelectStatsPanelS0();
      // nextButton onclick
    }
    // else

  }
  
  public void loadSelectSkillLevel1Panel() {
    entry.getTestValues().setSkillsLevel1(new ArrayList<Skill>(backupList)); //reset to "Skill begin list"
    RootPanel.get("content").clear();
    RootPanel.get("content").add(SelectSkillLevel1Panel.getSelectSkillLevel1Panel(entry, character));
  }
  
  public void loadSelectNamePanel() {
    RootPanel.get("content").clear();
    RootPanel.get("content").add(SelectNamePanel.getSelectNamePanel(entry, character));
  }
  
}
