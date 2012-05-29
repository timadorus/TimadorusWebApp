package org.timadorus.webapp.client.character.ui.selectclass;

import java.util.List;

import org.timadorus.webapp.beans.CClass;
import org.timadorus.webapp.beans.Character;
import org.timadorus.webapp.client.character.ui.DefaultActionHandler;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

//Panel for selecting characters Class
public class SelectClassWidget extends FormPanel implements SelectClassDialog.Display {

  Button nextButton;
  
  Button prevButton;
  
  Label classLabel;
  
  Label genderLabel;
  
  VerticalPanel panel;

  FlexTable selectClassGrid;

  FlexTable buttonGrid;

  ListBox classListBox;
  
 
  public SelectClassWidget() {
    super();
    
    nextButton = new Button("weiter");
    prevButton = new Button("zurück");
    panel = new VerticalPanel();
    selectClassGrid = new FlexTable();      // grid for handling selections
    buttonGrid = new FlexTable();           // grid for next/prev buttons
    classListBox = new ListBox();           // listbox for available classes
    
    HTML headline = new HTML("<h1>Klasse wählen</h1>");
    Image progressBar = new Image("media/images/progressbar_2.png");

    // setting properties for selectClassGrid
    selectClassGrid.setBorderWidth(0);
    selectClassGrid.setStylePrimaryName("selectGrid");

    classLabel = new Label("Klasse wählen: ");
    genderLabel = new Label("Geschlecht: | Rasse: ");

    selectClassGrid.setWidget(0, 0, classLabel);
    selectClassGrid.setWidget(0, 1, classListBox);    

    nextButton.setEnabled(false);
    
    // set properties of buttongrid
    buttonGrid.getCellFormatter().setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_RIGHT);
    buttonGrid.setWidth("350px");
    buttonGrid.setWidget(0, 0, prevButton);
    buttonGrid.setWidget(0, 1, nextButton);
    // setting properties of the main panel
    panel.setStyleName("panel");
    panel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
    panel.setWidth("100%");

    // adding widgets to the main panel
    panel.add(progressBar);
    panel.add(new Label("Schritt 2 von 9"));
    panel.add(genderLabel);
    
    panel.add(headline);
    panel.add(selectClassGrid);
    panel.add(buttonGrid);

    // clearing "information" #div and adding actual informations for this panel
    RootPanel.get("information").clear();
    RootPanel.get("information").add(getInformation());

    // clearing "content" #div and adding this mainpanel to this #div
    RootPanel.get("content").clear();
    RootPanel.get("content").add(panel);
  }
  
  private static final HTML getInformation() {
    HTML information = new HTML("<h1>Klasse wählen</h1><p>Wählen sie hier die Klasse ihres Charakteres. "
        + "Die Klasse bestimmt wie gut sie bestimmte Fähigkeiten lernen können."
        + "</p><p>Beachten sie, dass bestimmte Klassen nur bestimmte Rassen sowie " 
        + "Fraktionen wählen können.</p>");
    return information;
  }
  
  @Override
  public void setInformation(HTML information) {
    RootPanel.get("information").clear();
    RootPanel.get("information").add(information);
  }
  
  @Override
  public FormPanel getFormPanel() {
    return this;
  }
  
  @Override
  public void addClassHandler(final DefaultActionHandler handler) {
    selectClassGrid.addClickHandler(new ClickHandler() {

      @Override
      public void onClick(ClickEvent event) {
        handler.onAction();
      }
    });
  }

  @Override
  public void addNextButtonHandler(final DefaultActionHandler handler) {
    nextButton.addClickHandler(new ClickHandler() {

      @Override
      public void onClick(ClickEvent arg0) {
        handler.onAction();
      }
    });
  }

  @Override
  public void addPrevButtonHandler(final DefaultActionHandler handler) {
    prevButton.addClickHandler(new ClickHandler() {

      @Override
      public void onClick(ClickEvent arg0) {
        handler.onAction();
      }
    });
  }
  
  @Override
  public void setNextButtonEnable(boolean enabled) {
    nextButton.setEnabled(enabled);
  }
  
  @Override
  public String getSelectedClass() {

    return classListBox.getValue(classListBox.getSelectedIndex());
  }
  
  @Override
  public void setClasses(List<CClass> classes) {

    for (CClass newClass : classes) {
        classListBox.addItem(newClass.getName());
    }
    
    classListBox.setVisibleItemCount(classListBox.getItemCount());
  }
  
  @Override
  public void setCharacter(Character c) {
      genderLabel.setText("Geschlecht: " + c.getGender() + " | Rasse: " + c.getGender());
  }
}
