package org.timadorus.webapp.client.character.ui.selectskill;

import java.util.List;

import org.timadorus.webapp.beans.Character;
import org.timadorus.webapp.beans.Skill;
import org.timadorus.webapp.beans.User;
import org.timadorus.webapp.client.DefaultTimadorusWebApp;
import org.timadorus.webapp.client.eventhandling.events.ShowSelectAppearanceEvent;
import org.timadorus.webapp.client.eventhandling.events.ShowSelectSkill0Event;
import org.timadorus.webapp.client.eventhandling.events.ShowSelectSkill1Event;
import org.timadorus.webapp.client.eventhandling.handler.ShowDialogHandler;

import com.google.gwt.user.client.ui.RootPanel;

public class SelectSkillLevel1Dialog extends DefaultSelectSkillLevelDialog implements ShowDialogHandler {

  public SelectSkillLevel1Dialog(Display display, DefaultTimadorusWebApp entry, Character character, User user,
                                 List<Skill> skills) {
    super(display, entry, character, user, skills);
    // TODO Auto-generated constructor stub
    entry.addHandler(ShowSelectSkill1Event.SHOWDIALOG, this);
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
    SelectSkillLevel1Dialog dialog = new SelectSkillLevel1Dialog(null, entry, character, user, skills);
    return dialog;
  }

  @Override
  public void onPrevButtonClick() {
    getEntry().fireEvent(new ShowSelectSkill0Event(getUser(), getCharacter()));
  }

  @Override
  protected void onNextButtonClick() {
    super.onNextButtonClick();
    getEntry().fireEvent(new ShowSelectAppearanceEvent(getUser(), getCharacter()));
  }

  @Override
  public void show(DefaultTimadorusWebApp entry, Character character, User user) {
    DefaultSelectSkillLevelDialog.Display display = new DefaultSkillLevelWidget(character, skillList);
    setCharacter(character);
    setUser(user);
    initDisplay(display);
    RootPanel.get("content").clear();
    RootPanel.get("content").add(getFormPanel());
  }

}
