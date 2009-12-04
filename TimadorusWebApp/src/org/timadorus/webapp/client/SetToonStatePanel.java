package org.timadorus.webapp.client;

import java.util.ArrayList;

import com.google.gwt.event.dom.client.ClickEvent;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;

import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;

import com.google.gwt.user.client.ui.VerticalPanel;

public class SetToonStatePanel extends VerticalPanel {

	
	private  TimadorusWebApp appReference;
	private User user ;
	
	private Level toonLevel = new Level("", "");
	private ArrayList<ToonFraction>  fractionList =  new ArrayList<ToonFraction>();
    private ArrayList <ToonRace> tRace= new ArrayList <ToonRace>();
	
	private  String toonName="";
	private CreateToonPanel panel;
	
	
	public SetToonStatePanel() throws Exception
	{
		throw new Exception("No parameter set");
	}

	public SetToonStatePanel(TimadorusWebApp _appReference)
	{
		super();
		appReference  = _appReference;
		initialize();
	  
	}

	private void initialize()
	{
     if(appReference.getUserLoggedIn() && appReference.getToonCreateIn()==1)
     {
    	final Grid 	  toonCharaterCreateGrid			= new Grid(6, 2);
		final ListBox genderlist = new ListBox();
		genderlist.addItem("female");
		genderlist.addItem("male");
		
		final ListBox levelList= new ListBox();
		levelList.addItem("Level1", "tmp");
		levelList.addItem("Level2", "tmp");
		levelList.addItem("Level3", "tmp");
		 
		final  ListBox fractionList  = new  ListBox();
		fractionList.addItem("test1");
		fractionList.addItem("test2");
		fractionList.addItem("test3");
		fractionList.addItem("test4");
		
		
		final  ListBox raceList  = new  ListBox();
		raceList.addItem("human");
		raceList.addItem("biology");
	
		final  ListBox proffessionList  = new  ListBox();
		proffessionList.addItem("smallBlack");
		proffessionList.addItem("smallBlack3");
		

		final Button submitButton	= new Button("next");
    
		submitButton.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event)
			{  
				int i=0;
			    user = panel.getUser();
			    toonName= panel.getToonName();
				Toon toonObj = new Toon( user.getSurname(),toonName);
				toonObj.setGender(genderlist.getItemText(i));
         
				toonObj.setFraction(fractionList.getItemText(i));
			
				toonObj.setRace(raceList.getItemText(i));
				toonObj.setProffesion(levelList.getItemText(i));
				toonObj.setProffesion(proffessionList.getItemText(i));
			
				appReference.createToon(toonObj);
			}
		
		});
		
			   
		toonCharaterCreateGrid.setWidget(0, 0, new Label("Gender"));
	
		toonCharaterCreateGrid.setWidget(0, 1,  genderlist);
		
		toonCharaterCreateGrid.setWidget(1, 0, new Label("Race"));
		toonCharaterCreateGrid.setWidget(1, 1, raceList);
		
		toonCharaterCreateGrid.setWidget(2, 0, new Label("Level"));
		toonCharaterCreateGrid.setWidget(2, 1, levelList);
		
		toonCharaterCreateGrid.setWidget(3, 0, new Label("Fraction"));
		toonCharaterCreateGrid.setWidget(3, 1, fractionList);
			   
		toonCharaterCreateGrid.setWidget(4, 0, new Label("Proffesion"));
		toonCharaterCreateGrid.setWidget(4, 1, proffessionList);

		toonCharaterCreateGrid.setWidget(5, 1, submitButton);
	    this.add(new HTML("<h2>Toon feactures </h2>"));
	    this.add(toonCharaterCreateGrid);
		       
		           
		       
    }
	    
	}
	
}
