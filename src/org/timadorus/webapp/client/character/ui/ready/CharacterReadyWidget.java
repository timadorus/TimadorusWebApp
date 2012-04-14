package org.timadorus.webapp.client.character.ui.ready;

import org.timadorus.webapp.client.character.Character;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

//This is the FormPanel for the End of creating-Character-Object-procedure
public class CharacterReadyWidget extends FormPanel implements ReadyDialog.Display {

  private Button nextButton;

  private VerticalPanel panel;

  private FlexTable selectGrid;

  private HTML congratulationLabel;

  public CharacterReadyWidget(Character characterIn) {
    super();

    nextButton = new Button("weiter");
    panel = new VerticalPanel();
    selectGrid = new FlexTable();
    congratulationLabel = new HTML();

    panel.setStyleName("panel");
    panel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
    panel.setWidth("100%");

    panel.add(congratulationLabel);

    RootPanel.get("content").clear();
    RootPanel.get("content").add(panel);

  }

  @Override
  public FormPanel getFormPanel() {
    return this;
  }

  @Override
  public void setContragulationMessage(String msg) {
    congratulationLabel.setText(msg);
  }

  @Override
  public void setInformationMessage(String msg) {
    HTML information = new HTML(msg);
    RootPanel.get("information").clear();
    RootPanel.get("information").add(information);
  }
}
