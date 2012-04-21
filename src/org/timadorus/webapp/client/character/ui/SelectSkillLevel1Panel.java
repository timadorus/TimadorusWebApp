package org.timadorus.webapp.client.character.ui;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

import org.timadorus.webapp.beans.User;
import org.timadorus.webapp.client.DefaultTimadorusWebApp;
import org.timadorus.webapp.client.character.Character;
import org.timadorus.webapp.client.character.attributes.Skill;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

//FormPanel for selecting Skill-Level-1 items of a Character-Object
public class SelectSkillLevel1Panel extends FormPanel implements ChangeHandler {

  private final DefaultTimadorusWebApp entry;

  private final Character character;

  private User user;

  private Button nextButton = new Button("weiter");

  private Button prevButton = new Button("zurück");

  private VerticalPanel panel = new VerticalPanel();

  private FlexTable buttonGrid = new FlexTable();

  private FlexTable selectSkillGrid = new FlexTable();

  private ListBox skillListBox = new ListBox();

  private Button addButton = new Button("+");

  private Button removeButton = new Button("-");

  private ListBox addedskillListBox = new ListBox();

  private Set<String> tlist = new HashSet<String>();

  private Label skillLabel = new Label("L1 Fertigkeiten wählen: ");

  private Label addedskillLabel = new Label("L1 Ausgewählte Fertigkeiten: ");

  private FlexTable selectStatGrid = new FlexTable();

  private FlexTable selectSkillGridBox = new FlexTable();

  private Button resetPage = new Button("reset");

  private List<Skill> backupList;

  private List<TextBox[]> tbObjList = new ArrayList<TextBox[]>();

  private String[] titleList = { "Skill-Name", "Cost", "Rank", "Rk_Bn", "Stat_Bn", "Level_Bn", "Item", "Total" };

  public SelectSkillLevel1Panel(final DefaultTimadorusWebApp entryIn, final Character characterIn, User user) {
    super();
    this.entry = entryIn;
    this.character = characterIn;
    this.user = user;

    // TestValues

    // Create a handler for the sendButton and nameField
    class MyHandler implements ClickHandler {
      public void onClick(ClickEvent event) {
        Object source = event.getSource();
        if (source.equals(prevButton)) {
          loadSelectSkillPanel();

        } else if (source.equals(nextButton)) {
          onNextButtonClick();

        } else if (source.equals(skillListBox)) {
          onSkillListBoxClick();

        } else if (source.equals(addButton)) {
          onAddButtonClick();

        } else if (source.equals(removeButton)) {
          onRemoveButtonClick();

        } else if (source.equals(resetPage)) {
          onResetButtonClick(); // reset Seite
        }

      }

    }
    addedskillLabel.setStyleName("labelColorRed");
    nextButton.setEnabled(false);
    Image progressBar = new Image("media/images/progressbar_7.png");

    selectSkillGrid.setBorderWidth(0);
    selectSkillGrid.setStylePrimaryName("selectGrid");

    for (Skill skill : entryIn.getTestValues().getSkillsLevel1()) {
      skillListBox.addItem(skill.getName());
    }

    skillListBox.setVisibleItemCount(skillListBox.getItemCount());
    addedskillListBox.setVisibleItemCount(skillListBox.getItemCount());
    addedskillListBox.setWidth("150");

    // Label choosenskillLabel = new Label("Ausgewählte Fertigkeiten: ");
    selectSkillGrid.setWidget(0, 0, skillLabel);
    selectSkillGrid.setWidget(0, 12, addedskillLabel);
    selectSkillGrid.setWidget(1, 0, skillListBox);
    selectSkillGrid.setWidget(1, 8, addButton);
    selectSkillGrid.setWidget(2, 8, removeButton);
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
    panel.add(new Label("Schritt 7 von 9"));
    panel.add(new Label("Geschlecht: " + characterIn.getGender() + " | Rasse: " + characterIn.getRace().getName()));
    panel.add(new Label("Klasse: " + characterIn.getCharClass().getName() + " | Faction: "
        + characterIn.getFaction().getName()));
    panel.add(new Label("Skills_L0: " + characterIn.getSkillListNames()));

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

    addButton.addClickHandler(handler);
    removeButton.addClickHandler(handler);

    skillListBox.addClickHandler(handler);
    addedskillListBox.addClickHandler(handler);
    resetPage.addClickHandler(handler);

  }

  private void onNextButtonClick() {
    saveSelectedSkillsInCharacter();
    loadSelectAppearancePanel();
  }

  private void onAddButtonClick() {
    String skillName = skillListBox.getValue(skillListBox.getSelectedIndex());

    if (!tlist.contains(skillName)) {
      addedskillListBox.addItem(skillName);
      tlist.add(skillName);
      skillCostTable();
    }
    readyToSave();
  }

  public void loadSelectSkillPanel() {
    RootPanel.get("content").clear();
    RootPanel.get("content").add(SelectSkillPanel.getSelectSkillPanel(entry, character, user));
  }

  public static SelectSkillLevel1Panel getSelectSkillLevel1Panel(DefaultTimadorusWebApp entry, Character character,
    User user) {

    return new SelectSkillLevel1Panel(entry, character, user);
  }

  private static final HTML getInformation() {
    HTML information = new HTML("<h1>L1 Fertigkeiten Wählen</h1><p>Wählen sie hier die L1 Fertigkeiten ihres "
        + ". Beachten Sie, dass bestimmte Fertigkeiten ab bestimmten Level-Grad zu beziehen " + "sind.</p>");
    return information;
  }

  public DefaultTimadorusWebApp getEntry() {
    return entry;
  }

  public void saveSelectedSkillsInCharacter() {
    for (String skillName : tlist) {
      for (Skill skill : entry.getTestValues().getSkillsLevel1()) {
        if (skill.getName().equals(skillName)) {
          character.getSkillListLevel1().add(skill);

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
      tbObjList.add(tbArr); // merke zu jedem Skill die TextBox-Felder in einer Liste
      j++;

    }
    if (skillSet.size() != 0) {
      selectSkillGrid.setWidget(j + 1, 4, resetPage);
    } else {
      selectSkillGrid.remove(resetPage);
    }

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
    String evTexboxID = "" + ((Object) (TextBox) event.getSource()).hashCode();
    String[] skInfo = new String[3];
    for (TextBox[] tb : tbObjList) {
      String skillN = "";
      for (int i = 0; i < tb.length; i++) {
        if (tb[i] != null) {
          if (i == 0) {
            skillN = tb[i].getText();
            int m = Integer.valueOf(tb[3].getText()) + Integer.valueOf(tb[4].getText()); // update "Total"-Cell
            tb[7].setText("" + m);
          }

          String texBoxID = "" + ((Object) tb[i]).hashCode();
          if (texBoxID.equals(evTexboxID)) {
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
    for (Skill skill : skillList) {
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

    // überschreibe alte originale Skill-Liste mit der über die GUI editierten Skill-Liste
    entry.getTestValues().setSkills(skillList);

    if (event.getSource().equals(prevButton)) {
      // TODO: loadSelectStatsPanelS0();
      // nextButton onclick
    }
  }

  public void onResetButtonClick() {
    entry.getTestValues().setSkillsLevel1(new ArrayList<Skill>(backupList)); // reset to "Skill begin list"
    RootPanel.get("content").clear();
    RootPanel.get("content").add(SelectSkillLevel1Panel.getSelectSkillLevel1Panel(entry, character, user));
  }

  public void loadSelectAppearancePanel() {
    RootPanel.get("content").clear();
  }

  private void onRemoveButtonClick() {
    int selectedIndex = addedskillListBox.getSelectedIndex();
    String skillName = addedskillListBox.getValue(selectedIndex);

    if (addedskillListBox.getItemCount() > 0) {
      if (selectedIndex >= 0) {
        addedskillListBox.removeItem(selectedIndex);
        tlist.remove(skillName);
        skillCostTable();
      }
    }
    readyToSave();
  }

  private void onSkillListBoxClick() {
    // show skill informations
    String skillName = skillListBox.getValue(skillListBox.getSelectedIndex());
    ListIterator<Skill> skillIterator = entry.getTestValues().getSkillsLevel1().listIterator();
    RootPanel.get("information").clear();
    while (skillIterator.hasNext()) {
      Skill newSkill = (Skill) skillIterator.next();
      if (newSkill.getName().equals(skillName)) {
        RootPanel.get("information").add(new HTML("<h1>" + newSkill.getName() + "</h1><p>" + newSkill.getDescription()
                                             + "</p>" + "<p>" + newSkill.toString() + "</p>"));

      }
    }
  }
}
