package org.timadorus.webapp.client.character.ui.selectskill;

import java.util.List;

import org.timadorus.webapp.beans.Character;
import org.timadorus.webapp.beans.Skill;
import org.timadorus.webapp.beans.User;
import org.timadorus.webapp.client.DefaultTimadorusWebApp;
import org.timadorus.webapp.client.eventhandling.events.ShowPotStatsDialogEvent;
import org.timadorus.webapp.client.eventhandling.events.ShowSelectSkill0Event;
import org.timadorus.webapp.client.eventhandling.events.ShowSelectSkill1Event;
import org.timadorus.webapp.client.eventhandling.handler.ShowDialogHandler;

import com.google.gwt.user.client.ui.RootPanel;

//FormPanel for selecting Skill-Level-0 items of a Character-Object
public class SelectSkillLevel0Dialog extends DefaultSelectSkillLevelDialog implements ShowDialogHandler {

  public SelectSkillLevel0Dialog(Display display, DefaultTimadorusWebApp entry) {
    super(display, entry);
    entry.addHandler(ShowSelectSkill0Event.SHOWDIALOG, this);
  }

  @Override
  public void saveSelectedSkillsInCharacter() {
    for (String skillName : addedSkillList) {
      for (Skill skill : skillList) {
        if (skill.getName().equals(skillName)) {
          getCharacter().getSkillList().add(skill);
        }
      }
    }
  }

  @Override
  public void onPrevButtonClick() {
    getEntry().fireEvent(new ShowPotStatsDialogEvent(getUser(), getCharacter()));
  }

  @Override
  protected void onNextButtonClick() {
    super.onNextButtonClick();
    getEntry().fireEvent(new ShowSelectSkill1Event(getUser(), getCharacter()));
  }

  @Override
  public void show(DefaultTimadorusWebApp entry, Character character, User user) {
    setCharacter(character);
    setUser(user);
    
    List<Skill> skills = entry.getTestValues().getSkills();
    
    setBackupSkillList(skills);
    setSkillList(skills);
    
    getDisplay().setContent(character, skills);
    
    RootPanel.get("content").clear();
    RootPanel.get("content").add(getFormPanel());
  }

}
