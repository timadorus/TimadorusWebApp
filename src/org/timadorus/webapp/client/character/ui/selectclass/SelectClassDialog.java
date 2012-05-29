package org.timadorus.webapp.client.character.ui.selectclass;

import java.util.ArrayList;
import java.util.List;

import org.timadorus.webapp.beans.CClass;
import org.timadorus.webapp.beans.Character;
import org.timadorus.webapp.beans.Faction;
import org.timadorus.webapp.beans.Race;
import org.timadorus.webapp.beans.User;
import org.timadorus.webapp.client.DefaultTimadorusWebApp;
import org.timadorus.webapp.client.character.ui.DefaultActionHandler;
import org.timadorus.webapp.client.character.ui.DefaultDialog;
import org.timadorus.webapp.client.character.ui.DefaultDisplay;
import org.timadorus.webapp.client.eventhandling.events.ShowSelectClassEvent;
import org.timadorus.webapp.client.eventhandling.events.ShowSelectFractionEvent;
import org.timadorus.webapp.client.eventhandling.events.ShowSelectRaceEvent;
import org.timadorus.webapp.client.eventhandling.handler.ShowDialogHandler;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;

public class SelectClassDialog extends DefaultDialog<SelectClassDialog.Display> implements ShowDialogHandler {
  public interface Display extends DefaultDisplay {

    public void addClassHandler(final DefaultActionHandler handler);
    
    public void addPrevButtonHandler(DefaultActionHandler handler);

    public void addNextButtonHandler(DefaultActionHandler handler);
      
    public void setNextButtonEnable(boolean enabled);
    
    public String getSelectedClass();
    
    public void setClasses(List<CClass> classes);
    
    public void setCharacter(Character c);
    
    public void setInformation(HTML information);
  }

  private Character character;
  
  private User user;
    
  public SelectClassDialog(Display display, DefaultTimadorusWebApp entry) {
    super(display, entry);
    
    initDisplay();
    
    entry.addHandler(ShowSelectClassEvent.SHOWDIALOG, this);
  }
  
  private void initDisplay() {
    getDisplay().setNextButtonEnable(false);

    getDisplay().addClassHandler(new DefaultActionHandler() {

      @Override
      public void onAction() {
        
        chooseClass();
      }
    });
    
    getDisplay().addNextButtonHandler(new DefaultActionHandler() {

      @Override
      public void onAction() {
        getEntry().fireEvent(new ShowSelectFractionEvent(getUser(), getCharacter())); 
      }
    });

    getDisplay().addPrevButtonHandler(new DefaultActionHandler() {

      @Override
      public void onAction() {
        getEntry().fireEvent(new ShowSelectRaceEvent(getUser(), getCharacter()));
      }
    });
    
  }
  
  private Character getCharacter() {
    return character;
  }

  private User getUser() {
    return user;
  }
 
  private void chooseClass() {
    setInformation();
    character.setCharClass(getSelectedClass());
  }
  
  private CClass getSelectedClass() {

    for (CClass selectedClass : getEntry().getTestValues().getClasses()) {
      String className = selectedClass.getName();
      String selectedName = getDisplay().getSelectedClass();
      if (className.equals(selectedName)) { return selectedClass; }
    }
    return null;
  }
  
  private void setClasses(Race race) {
    
    List<CClass> availableClasses = new ArrayList<CClass>();
    
    for (CClass newClass : getEntry().getTestValues().getClasses()) {
      if (race.containsClass(newClass)) {
        availableClasses.add(newClass);       
      }
    }
    
    getDisplay().setClasses(availableClasses);    
  }
  
  private void setInformation() {

    String content = new String();
    
    String className = getDisplay().getSelectedClass();

    for (CClass newClass : getEntry().getTestValues().getClasses()) {
      if (newClass.getName().equals(className)) {
        
        content += "<h1>" + newClass.getName() + "</h1>";
        content += "<p>" + newClass.getDescription() + "</p>";
        content += "<h2>Wählbare Fraktionen</h2>";
        
        String availableFactions = new String("<ul>");
        String nextFaction = new String();
        for (Faction newFaction : newClass.getAvailableFactions()) {
          nextFaction = newFaction.getName();
          if (character.getRace().getAvailableFactions().contains(newFaction)) {
            availableFactions += "<li>" + nextFaction + "</li>";
          }
        }
        availableFactions += "</ul>";
        
        content += availableFactions;
      }
   }
       
    getDisplay().setInformation(new HTML(content));
  }

  @Override
  public void show(DefaultTimadorusWebApp entry, Character character, User user) {
    this.character = character;
    this.user = user;
   
    getDisplay().setCharacter(character);
    setClasses(character.getRace());
    
    RootPanel.get("content").clear();
    RootPanel.get("content").add(getFormPanel());
  }
}
