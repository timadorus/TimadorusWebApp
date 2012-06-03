package org.timadorus.webapp.tests.client.character.ui.selectskilllevel;

import static org.mockito.Matchers.anyListOf;
import static org.mockito.Matchers.anySetOf;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.timadorus.webapp.beans.Character;
import org.timadorus.webapp.beans.Skill;
import org.timadorus.webapp.beans.User;
import org.timadorus.webapp.client.DefaultTimadorusWebApp;
import org.timadorus.webapp.client.character.TestCharacterValues;
import org.timadorus.webapp.client.character.ui.DefaultActionHandler;
import org.timadorus.webapp.client.character.ui.selectskill.DefaultSelectSkillLevelDialog;
import org.timadorus.webapp.client.character.ui.selectskill.SelectSkillLevel1Dialog;
import org.timadorus.webapp.client.character.ui.selectskill.TextBoxHandler;

public class SelectSkillLevelDialogTest {
  private SelectSkillLevel1Dialog mySelectSkillLevelDialog;

  @Mock
  DefaultSelectSkillLevelDialog.Display myDisplayMock;

  @Mock
  DefaultTimadorusWebApp myDefaultTimadorusWebApp;

  private Character myCharacter;

  private User myUser;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);

    myCharacter = new Character();
    myUser = new User();

    TestCharacterValues tcv = new TestCharacterValues();

    mySelectSkillLevelDialog = new SelectSkillLevel1Dialog(myDisplayMock, myDefaultTimadorusWebApp);

    Mockito.when(myDefaultTimadorusWebApp.getTestValues()).thenReturn(new TestCharacterValues());
  }

  @Test
  public void testAddButtonHandler() {
    final String aSelectedItemSkill = "aSelectedItemSkill";
    when(myDisplayMock.getSelectedItemSkills()).thenReturn(aSelectedItemSkill);

    ArgumentCaptor<DefaultActionHandler> theArgumentCaptor = ArgumentCaptor.forClass(DefaultActionHandler.class);
    verify(myDisplayMock).addAddButtonHandler(theArgumentCaptor.capture());

    theArgumentCaptor.getValue().onAction();
    theArgumentCaptor.getValue().onAction();

    verify(myDisplayMock).addItemAddedSkills(aSelectedItemSkill);
    verify(myDisplayMock).reloadSkillCostTable(anySetOf(String.class), anyListOf(Skill.class));
    verify(myDisplayMock, times(2)).readyToSave(true);

  }

  @Test
  public void testNextButtonHandler() {
    final String aSelectedItemSkill = "Armorsmithing";
    when(myDisplayMock.getSelectedItemSkills()).thenReturn(aSelectedItemSkill);

    ArgumentCaptor<DefaultActionHandler> theArgumentCaptor = ArgumentCaptor.forClass(DefaultActionHandler.class);
    verify(myDisplayMock).addAddButtonHandler(theArgumentCaptor.capture());

    theArgumentCaptor.getValue().onAction();

    ArgumentCaptor<DefaultActionHandler> theArgumentCaptor2 = ArgumentCaptor.forClass(DefaultActionHandler.class);
    verify(myDisplayMock).addNextButtonHandler(theArgumentCaptor2.capture());

    theArgumentCaptor2.getValue().onAction();

    // verify(myDisplayMock).loadSelectAppearancePanel();
    // Assert.assertTrue("SkillListLever1Set should be filled with one element " + aSelectedItemSkill,
    // !myCharacter.getSkillListLevel1().isEmpty()
    // && myCharacter.getSkillListLevel1().iterator().next().getName().equals(aSelectedItemSkill));
  }

  @Test
  public void testPrevButtonHandler() {
    ArgumentCaptor<DefaultActionHandler> theArgumentCaptor = ArgumentCaptor.forClass(DefaultActionHandler.class);
    verify(myDisplayMock).addPrevButtonHandler(theArgumentCaptor.capture());

    theArgumentCaptor.getValue().onAction();

    // verify(myDisplayMock).loadSelectSkillPanel(myDefaultTimadorusWebApp, myCharacter, myUser);
  }

  @Test
  public void testRemoveButtonHandler() {
    // eine Skill setzen
    final String aSelectedItemSkill = "Armorsmithing";
    when(myDisplayMock.getSelectedItemSkills()).thenReturn(aSelectedItemSkill);

    ArgumentCaptor<DefaultActionHandler> theArgumentCaptor = ArgumentCaptor.forClass(DefaultActionHandler.class);
    verify(myDisplayMock).addAddButtonHandler(theArgumentCaptor.capture());

    theArgumentCaptor.getValue().onAction();

    // und wieder löschen
    when(myDisplayMock.getSelectedItemAddedSkills()).thenReturn(aSelectedItemSkill, (String) null);
    ArgumentCaptor<DefaultActionHandler> theArgumentCaptor2 = ArgumentCaptor.forClass(DefaultActionHandler.class);
    verify(myDisplayMock).addRemoveButtonHandler(theArgumentCaptor2.capture());

    theArgumentCaptor2.getValue().onAction();
    theArgumentCaptor2.getValue().onAction();

    verify(myDisplayMock).removeItemAddedSkills(aSelectedItemSkill);

    verify(myDisplayMock, times(2)).readyToSave(false);
  }

  @Test
  public void testResetButtonHandler() {
    ArgumentCaptor<DefaultActionHandler> theArgumentCaptor = ArgumentCaptor.forClass(DefaultActionHandler.class);
    verify(myDisplayMock).addResetButtonHandler(theArgumentCaptor.capture());

    theArgumentCaptor.getValue().onAction();

    // verify(myDisplayMock).loadSkillLevelDialog(myDefaultTimadorusWebApp, myCharacter, myUser);
  }

  @Test
  public void testSkillListBoxHandler() {
    ArgumentCaptor<DefaultActionHandler> theArgumentCaptor = ArgumentCaptor.forClass(DefaultActionHandler.class);
    verify(myDisplayMock).addSkillListBoxHandler(theArgumentCaptor.capture());

    theArgumentCaptor.getValue().onAction();

    // verify(myDisplayMock).showSkillInformation(anyString(), anyListOf(Skill.class));
  }

  @Test
  public void testTextBoxHanlder() {
    ArgumentCaptor<TextBoxHandler> theArgumentCaptor = ArgumentCaptor.forClass(TextBoxHandler.class);
    verify(myDisplayMock).addTextBoxHanlder(theArgumentCaptor.capture());

    theArgumentCaptor.getValue().onChange(new String[] { "", "", "" });
  }

}
