package org.timadorus.webapp.client.character.ui.selectskilllevel;

import java.util.List;
import java.util.Set;

import org.timadorus.webapp.beans.User;
import org.timadorus.webapp.client.DefaultTimadorusWebApp;
import org.timadorus.webapp.client.character.Character;
import org.timadorus.webapp.client.character.attributes.Skill;
import org.timadorus.webapp.client.character.ui.DefaultActionHandler;
import org.timadorus.webapp.client.character.ui.DefaultDialog;
import org.timadorus.webapp.client.character.ui.DefaultDisplay;

import com.google.gwt.dev.util.collect.HashSet;

public class SelectSkillLevelDialog extends DefaultDialog<SelectSkillLevelDialog.Display> {

  public interface Display extends DefaultDisplay {
    public String getSelectedItemSkills();

    public String getSelectedItemAddedSkills();

    public void removeItemAddedSkills(String itemname);

    public void addItemAddedSkills(String item);

    public void readyToSave(boolean hasSelectedItems);

    public void reloadSkillCostTable(Set<String> addedSkillList, List<Skill> skillLevel1);

    public void addTextBoxHanlder(TextBoxHandler handler);

    public void addNextButtonHandler(DefaultActionHandler handler);

    public void addPrevButtonHandler(DefaultActionHandler handler);

    public void addAddButtonHandler(DefaultActionHandler handler);

    public void addRemoveButtonHandler(DefaultActionHandler handler);

    public void addResetButtonHandler(DefaultActionHandler handler);

    public void addSkillListBoxHandler(DefaultActionHandler handler);
    
    public void loadSkillLevelDialog(DefaultTimadorusWebApp entry, Character character, User user);
    
    public void loadSelectAppearancePanel();
    
    public void loadSelectSkillPanel(DefaultTimadorusWebApp entry, Character character, User user);
    
    public void showSkillInformation(String skillName, List<Skill> skillsLevel1);
  }

  private Character character;

  private User user;

  private Set<String> addedSkillList;

  public SelectSkillLevelDialog(Display display, DefaultTimadorusWebApp entry, Character character, User user) {
    super(display, entry);

    this.character = character;
    this.user = user;

    addedSkillList = new HashSet<String>();
    getDisplay().addAddButtonHandler(new DefaultActionHandler() {

      @Override
      public void onAction() {
        onAddButtonClick();
      }
    });

    getDisplay().addNextButtonHandler(new DefaultActionHandler() {

      @Override
      public void onAction() {
        saveSelectedSkillsInCharacter();
        getDisplay().loadSelectAppearancePanel();
      }
    });

    getDisplay().addPrevButtonHandler(new DefaultActionHandler() {

      @Override
      public void onAction() {
        getDisplay().loadSelectSkillPanel(getEntry(), getCharacter(), getUser());
      }
    });

    getDisplay().addRemoveButtonHandler(new DefaultActionHandler() {

      @Override
      public void onAction() {
        onRemoveButtonClick();
      }
    });

    getDisplay().addResetButtonHandler(new DefaultActionHandler() {

      @Override
      public void onAction() {
        getEntry().getTestValues().setSkillsLevel1(getEntry().getTestValues().getBackupSkills_Level1());
        getDisplay().loadSkillLevelDialog(getEntry(), getCharacter(), getUser());
      }
    });
    getDisplay().addSkillListBoxHandler(new DefaultActionHandler() {

      @Override
      public void onAction() {
        getDisplay().showSkillInformation(getDisplay().getSelectedItemSkills(), 
                                          getEntry().getTestValues().getSkillsLevel1());
      }
    });

    getDisplay().addTextBoxHanlder(new TextBoxHandler() {

      @Override
      public void onChange(String[] skInfo) {
        onTextboxChange(skInfo);
      }
    });
  }

  private void onRemoveButtonClick() {
    String skillName = getDisplay().getSelectedItemAddedSkills();

    if (skillName != null) {
      getDisplay().removeItemAddedSkills(skillName);
      addedSkillList.remove(skillName);
      getDisplay().reloadSkillCostTable(addedSkillList, getEntry().getTestValues().getSkillsLevel1());
    }
    getDisplay().readyToSave(!addedSkillList.isEmpty());
  }


  private void saveSelectedSkillsInCharacter() {
    for (String skillName : addedSkillList) {
      for (Skill skill : getEntry().getTestValues().getSkillsLevel1()) {
        if (skill.getName().equals(skillName)) {
          character.getSkillListLevel1().add(skill);
        }
      }
    }
  }

  private void onAddButtonClick() {
    String skillName = getDisplay().getSelectedItemSkills();

    if (!addedSkillList.contains(skillName)) {
      getDisplay().addItemAddedSkills(skillName);
      addedSkillList.add(skillName);
      getDisplay().reloadSkillCostTable(addedSkillList, getEntry().getTestValues().getSkillsLevel1());
    }
    getDisplay().readyToSave(!addedSkillList.isEmpty());
  }

  private void onTextboxChange(String[] skInfo) {

    List<Skill> skillList = getEntry().getTestValues().getSkillsLevel1();
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
    getEntry().getTestValues().setSkills(skillList);

  }

  private Character getCharacter() {
    return character;
  }

  private User getUser() {
    return user;
  }

  public static SelectSkillLevelDialog getDialog(DefaultTimadorusWebApp entry, Character character, User user) {
    SelectSkillLevelDialog.Display display = new SelectSkillLevelWidget(character, entry.getTestValues()
        .getSkillsLevel1());
    SelectSkillLevelDialog dialog = new SelectSkillLevelDialog(display, entry, character, user);
    return dialog;
  }

}
