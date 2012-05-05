package org.timadorus.webapp.client.character.ui.selectskill;

import java.util.List;

import org.timadorus.webapp.beans.Character;
import org.timadorus.webapp.beans.Skill;
import org.timadorus.webapp.beans.User;
import org.timadorus.webapp.client.DefaultTimadorusWebApp;

public class SelectSkillLevel1Dialog extends DefaultSelectSkillLevelDialog {

  public SelectSkillLevel1Dialog(Display display, DefaultTimadorusWebApp entry, Character character, User user,
                                 List<Skill> skills) {
    super(display, entry, character, user, skills);
    // TODO Auto-generated constructor stub
  }

  @Override
  public void saveSelectedSkillsInCharacter() {
    for (String skillName : addedSkillList) {
      for (Skill skill : skillList) {
        if (skill.getName().equals(skillName)) {
          getCharacter().getSkillListLevel1().add(skill);
        }
      }
    }
  }

  public static SelectSkillLevel1Dialog getDialog(DefaultTimadorusWebApp entry, Character character, User user) {
    List<Skill> skills = entry.getTestValues().getSkillsLevel1();
    DefaultSelectSkillLevelDialog.Display display = new DefaultSkillLevelWidget(character, skills);
    SelectSkillLevel1Dialog dialog = new SelectSkillLevel1Dialog(display, entry, character, user, skills);
    return dialog;
  }

  @Override
  public void onPrevButtonClick() {
    getDisplay().onPrevButtonClick(getEntry(), getCharacter(), getUser());
  }

  @Override
  protected void onNextButtonClick() {
    super.onNextButtonClick();
    getDisplay().onNextButtonClick(getEntry(), getCharacter(), getUser());
  }

}
