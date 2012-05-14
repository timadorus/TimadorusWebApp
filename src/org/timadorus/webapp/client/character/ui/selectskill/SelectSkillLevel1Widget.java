package org.timadorus.webapp.client.character.ui.selectskill;

import java.util.List;

import org.timadorus.webapp.beans.Character;
import org.timadorus.webapp.beans.Skill;
import org.timadorus.webapp.beans.User;
import org.timadorus.webapp.client.DefaultTimadorusWebApp;
import org.timadorus.webapp.client.eventhandling.events.ShowSelectAppearanceEvent;

import com.google.gwt.user.client.ui.RootPanel;

public class SelectSkillLevel1Widget extends DefaultSkillLevelWidget {

  public SelectSkillLevel1Widget(Character characterIn, List<Skill> chooseableSkills) {
    super(characterIn, chooseableSkills);
  }

  @Override
  public void onNextButtonClick(DefaultTimadorusWebApp entry, Character character, User user) {
    entry.fireEvent(new ShowSelectAppearanceEvent(user, character));
  }

  @Override
  public void onPrevButtonClick(DefaultTimadorusWebApp entry, Character character, User user) {
    RootPanel.get("content").clear();
    RootPanel.get("content").add(SelectSkillLevel0Dialog.getDialog(entry, character, user).getFormPanel());
  }
}
