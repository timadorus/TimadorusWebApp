package org.timadorus.webapp.client.character.ui;

import org.timadorus.webapp.client.DefaultTimadorusWebApp;
import org.timadorus.webapp.client.character.ui.characterlist.ShowCharacterListDialog;
import org.timadorus.webapp.client.character.ui.createcharacter.CreateCharacterWidget;
import org.timadorus.webapp.client.character.ui.createcharacter.CreateDialog;
import org.timadorus.webapp.client.character.ui.potstat.PotStatsDialog;
import org.timadorus.webapp.client.character.ui.potstat.PotStatsWidget;
import org.timadorus.webapp.client.character.ui.premadecharacter.PremadeCharacterDialog;
import org.timadorus.webapp.client.character.ui.premadecharacter.PremadeCharacterWidget;
import org.timadorus.webapp.client.character.ui.ready.CharacterReadyWidget;
import org.timadorus.webapp.client.character.ui.ready.ReadyDialog;
import org.timadorus.webapp.client.character.ui.selectappearance.SelectAppearanceDialog;
import org.timadorus.webapp.client.character.ui.selectappearance.SelectAppearanceWidget;
import org.timadorus.webapp.client.character.ui.selectclass.SelectClassDialog;
import org.timadorus.webapp.client.character.ui.selectclass.SelectClassWidget;
import org.timadorus.webapp.client.character.ui.selectfraction.SelectFactionDialog;
import org.timadorus.webapp.client.character.ui.selectname.SelectNameDialog;
import org.timadorus.webapp.client.character.ui.selectname.SelectNameWidget;
import org.timadorus.webapp.client.character.ui.selectrace.SelectRaceDialog;
import org.timadorus.webapp.client.character.ui.selectrace.SelectRaceWidget;
import org.timadorus.webapp.client.character.ui.selectskill.DefaultSkillLevelWidget;
import org.timadorus.webapp.client.character.ui.selectskill.SelectSkillLevel0Dialog;
import org.timadorus.webapp.client.character.ui.selectskill.SelectSkillLevel1Dialog;
import org.timadorus.webapp.client.character.ui.selecttempstats.SelectTempStatsDialog;
import org.timadorus.webapp.client.character.ui.selecttempstats.SelectTempStatsWidget;
import org.timadorus.webapp.client.character.ui.showcharacter.ShowCharacterDialog;
import org.timadorus.webapp.client.character.ui.showcharacter.ShowCharacterWidget;

/**
 * This class holds and initiate the UI elements of the character handling.
 * 
 * @author Sebastian
 * 
 */
public class CharacterHandler {

  DefaultTimadorusWebApp entry;

  private SelectRaceDialog selectRaceDialog;

  private ShowCharacterListDialog characterListDialog;

  private PremadeCharacterDialog premadeCharacterDialog;

  private PotStatsDialog potStatsDialog;

  private ReadyDialog characterReadyDialog;

  private SelectClassDialog selectClassDialog;

  private SelectFactionDialog selectFactionDialog;

  private SelectNameDialog selectNameDialog;

  private SelectSkillLevel0Dialog selectSkillLevel0Dialog;

  private SelectSkillLevel1Dialog selectSkillLevel1Dialog;

  private SelectTempStatsDialog selectTempStatsDialog;

  private ShowCharacterDialog showCharacterDialog;

  private SelectAppearanceDialog selectAppearanceDialog;

  private Object createCharacterDialog;

  public CharacterHandler(DefaultTimadorusWebApp entry) {
    super();
    this.entry = entry;
    init();
  }

  private void init() {
    selectRaceDialog = new SelectRaceDialog(new SelectRaceWidget(), getEntry());
    createCharacterDialog = CreateDialog.getCreateDialog(new CreateCharacterWidget(), getEntry());
    characterListDialog = ShowCharacterListDialog.getDialog(getEntry(), null);
    premadeCharacterDialog = PremadeCharacterDialog.getDialog(new PremadeCharacterWidget(), getEntry());
    potStatsDialog = new PotStatsDialog(new PotStatsWidget(getEntry()), getEntry());
    characterReadyDialog = new ReadyDialog(new CharacterReadyWidget(), getEntry());
    selectClassDialog = new SelectClassDialog(new SelectClassWidget(), getEntry());
    selectFactionDialog = new SelectFactionDialog(null, getEntry(), null, null);
    selectNameDialog = new SelectNameDialog(new SelectNameWidget(), getEntry());
    selectSkillLevel0Dialog = new SelectSkillLevel0Dialog(new DefaultSkillLevelWidget(), getEntry());
    selectSkillLevel1Dialog = new SelectSkillLevel1Dialog(new DefaultSkillLevelWidget(), getEntry());
    selectTempStatsDialog = new SelectTempStatsDialog(new SelectTempStatsWidget(), getEntry());
    showCharacterDialog = new ShowCharacterDialog(new ShowCharacterWidget(), getEntry());
    selectAppearanceDialog = new SelectAppearanceDialog(new SelectAppearanceWidget(), getEntry());
  }

  public DefaultTimadorusWebApp getEntry() {
    return entry;
  }

  public void setEntry(DefaultTimadorusWebApp entry) {
    this.entry = entry;
  }

}
