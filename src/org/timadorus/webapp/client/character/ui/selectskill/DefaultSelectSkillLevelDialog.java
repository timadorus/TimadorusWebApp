package org.timadorus.webapp.client.character.ui.selectskill;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.timadorus.webapp.beans.Character;
import org.timadorus.webapp.beans.Skill;
import org.timadorus.webapp.beans.User;
import org.timadorus.webapp.client.DefaultTimadorusWebApp;
import org.timadorus.webapp.client.character.ui.DefaultActionHandler;
import org.timadorus.webapp.client.character.ui.DefaultDialog;
import org.timadorus.webapp.client.character.ui.DefaultDisplay;
import org.timadorus.webapp.client.eventhandling.handler.ShowDialogHandler;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;




public class DefaultSelectSkillLevelDialog 
extends DefaultDialog<DefaultSelectSkillLevelDialog.Display> implements ShowDialogHandler {

  public interface Display extends DefaultDisplay {
    
    public void addTextBoxHanlder(TextBoxHandler handler);

    public void addNextButtonHandler(DefaultActionHandler handler);

    public void addPrevButtonHandler(DefaultActionHandler handler);

    public void addAddButtonHandler(DefaultActionHandler handler);

    public void addRemoveButtonHandler(DefaultActionHandler handler);

    public void addResetButtonHandler(DefaultActionHandler handler);

    public void addSkillListBoxHandler(DefaultActionHandler handler);
    
    public String getSelectedItemSkills();

    public String getSelectedItemAddedSkills();

    public void removeItemAddedSkills(String itemname);

    public void addItemAddedSkills(String item);

    public void readyToSave(boolean hasSelectedItems);

    public void setCharacter(Character character);

    void setChooseableSkills(List<Skill> skills);

    void setNextButtonEnable(boolean enabled);

    void setSkillCostTable(Set<Skill> addedSkills);

    void onChange(ChangeEvent event);

    void setInformation(HTML information);
  }
  

  private Character character;

  private User user;

  protected Set<String> addedSkillList;

  private List<Skill> backupSkillList;

  protected List<Skill> skillList;
  
  
  public DefaultSelectSkillLevelDialog(Display display, DefaultTimadorusWebApp entry) {
    super(display, entry);

    initDisplay();
  }
    
  private void initDisplay() {

    getDisplay().addNextButtonHandler(new DefaultActionHandler() {

      @Override
      public void onAction() {
        onNextButtonClick();
      }
    });
    
    
    getDisplay().addPrevButtonHandler(new DefaultActionHandler() {

      @Override
      public void onAction() {
        onPrevButtonClick();
      }
    });
        
    getDisplay().addAddButtonHandler(new DefaultActionHandler() {

      @Override
      public void onAction() {
        onAddButtonClick();
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
        onResetButtonClick();
      }
    });
    
    getDisplay().addSkillListBoxHandler(new DefaultActionHandler() {

      @Override
      public void onAction() {
        onSkillListBoxClick();
      }
    });

    getDisplay().addTextBoxHanlder(new TextBoxHandler() {

      @Override
      public void onChange(String[] skInfo) {
        onTextboxChange(skInfo);
      }
    });
  }
  
  public Character getCharacter() {
    return character;
  }

  public User getUser() {
    return user;
  }
  
  public void onPrevButtonClick() {
  }
  
  public void onNextButtonClick() { 
    saveSelectedSkillsInCharacter();
  }

  public void onResetButtonClick() {
    skillList = new ArrayList<Skill>(backupSkillList);
  }
  
  private void setSkillCostTable() {
    Set<Skill> skillSet = new HashSet<Skill>();

    for (String skillName : addedSkillList) {
      for (Skill skill : skillList) {
        if (skill.getName().equals(skillName)) {
          skillSet.add(skill);
        }
      }
    }    
    getDisplay().setSkillCostTable(skillSet);
  }
  
  private void onRemoveButtonClick() {
    String skillName = getDisplay().getSelectedItemAddedSkills();

    if (skillName != null) {
      getDisplay().removeItemAddedSkills(skillName);
      addedSkillList.remove(skillName);
      
      setSkillCostTable();
    }
    getDisplay().readyToSave(!addedSkillList.isEmpty());
  }
  
  private void onSkillListBoxClick() {

    String skillName = getDisplay().getSelectedItemSkills();
    String content = new String();

    for (Skill newSkill : skillList) {
      if (newSkill.getName().equals(skillName)) {
        content += "<h1>" + newSkill.getName() + "</h1>";
        content += "<p>" + newSkill.getDescription() + "</p>";
        content += "<p>" + newSkill.toString() + "</p>";
      }
    }    
    getDisplay().setInformation(new HTML(content));
  }

  public void saveSelectedSkillsInCharacter() {
    for (String skillName : addedSkillList) {
      for (Skill skill : skillList) {
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
     setSkillCostTable();
    }
    getDisplay().readyToSave(!addedSkillList.isEmpty());
  }

  private void onTextboxChange(String[] skInfo) {

    // List<Skill> skillList = getEntry().getTestValues().getSkillsLevel1();
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
  }

  @Override
  public void show(DefaultTimadorusWebApp entry, Character character, User user) {
    this.character = character;
    this.user = user;
    this.skillList = entry.getTestValues().getSkillsLevel1();
    this.backupSkillList = new ArrayList<Skill>(entry.getTestValues().getSkillsLevel1());
    this.addedSkillList = new HashSet<String>();
   
    getDisplay().setCharacter(character);
    getDisplay().setChooseableSkills(skillList); 
    
    RootPanel.get("content").clear();
    RootPanel.get("content").add(getFormPanel());    
  }
}
