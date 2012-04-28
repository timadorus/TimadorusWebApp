package org.timadorus.webapp.client.character.ui.selectskill;

import java.util.List;

import org.timadorus.webapp.beans.Character;
import org.timadorus.webapp.beans.Skill;
import org.timadorus.webapp.beans.User;
import org.timadorus.webapp.client.DefaultTimadorusWebApp;
import org.timadorus.webapp.client.character.ui.potstat.PotStatsDialog;

import com.google.gwt.user.client.ui.RootPanel;

//FormPanel for selecting Skill-Level-0 items of a Character-Object
public class SelectSkillLevel0Dialog extends DefaultSelectSkillLevelDialog {

  public SelectSkillLevel0Dialog(Display display, DefaultTimadorusWebApp entry, Character character, User user,
                                 List<Skill> skills) {
    super(display, entry, character, user, skills);
    // TODO Auto-generated constructor stub
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

  public static SelectSkillLevel0Dialog getDialog(DefaultTimadorusWebApp entry, Character character, User user) {
    List<Skill> skills = entry.getTestValues().getSkills();
    DefaultSelectSkillLevelDialog.Display display = new DefaultSkillLevelWidget(character, skills);
    SelectSkillLevel0Dialog dialog = new SelectSkillLevel0Dialog(display, entry, character, user, skills);
    return dialog;
  }

  @Override
  public void onPrevButtonClick() {
    RootPanel.get("content").clear();
    RootPanel.get("content").add(PotStatsDialog.getDialog(getEntry(), getCharacter(), getUser()).getFormPanel());
  }

  @Override
  protected void onNextButtonClick() {
    super.onNextButtonClick();
    RootPanel.get("content").clear();
    RootPanel.get("content").add(SelectSkillLevel1Dialog.getDialog(getEntry(), getCharacter(), getUser())
                                     .getFormPanel());
  }

}
