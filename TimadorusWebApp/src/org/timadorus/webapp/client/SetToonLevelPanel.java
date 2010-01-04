package org.timadorus.webapp.client;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class SetToonLevelPanel extends VerticalPanel {


	private  TimadorusWebApp appReference;
	private User user ;

	private Map<String , String> mapLevel   = new HashMap<String, String>();
	private Map<String , String> map_Race_Desc   = new HashMap<String, String>();



	List<ToonRace> race_List = new LinkedList<ToonRace>();
	//final Button showRaceButton	  	 	= new Button("showRace");
	//List<ToonFraction> fraction_List = new LinkedList<ToonFraction>();
	//List<String> profession_List = new LinkedList<String>();

	public SetToonLevelPanel() throws Exception
	{
		throw new Exception("No parameter set");
	}

	public SetToonLevelPanel(TimadorusWebApp _appReference)
	{
		super();
		appReference  = _appReference;
		initialize();

	}

	private void initialize()
	{
		if(appReference.getUserLoggedIn() && appReference.getToonCreateIn()==1)
		{
			final ListBox RaceListBox= new ListBox();
			final ListBox levelListBox= new ListBox();
			final ListBox genderBox= new ListBox();
			final Button submitButton	= new Button("next");
			final Button prevButton	    = new Button("previous");
			final Button descriptionButton	    = new Button("description");

			VerticalPanel panel = new VerticalPanel();
			FlexTable setClassGrid = new FlexTable();
			FlexTable buttonGrid = new FlexTable();


			HTML info = new HTML("<h2> choose Race</h2>");
			Label GenderLabel = new Label("Gender:");
			Label ClassLabel = new Label("Level:");
			genderBox.addItem("female");
			genderBox.addItem("Male");


			setClassGrid.setWidget(0, 0, GenderLabel);
			setClassGrid.setWidget(0, 1, genderBox);
			setClassGrid.setWidget(1, 0, ClassLabel);
			setClassGrid.setWidget(1, 1,levelListBox);


			levelListBox.setVisibleItemCount(levelListBox.getItemCount());

			buttonGrid.getCellFormatter().setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_RIGHT);
			buttonGrid.setWidth("300px");
			buttonGrid.setWidget(2, 2, prevButton);
			buttonGrid.setWidget(2, 3, descriptionButton);
			buttonGrid.setWidget(2, 4, submitButton);

			panel.setStyleName("panel");
			panel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);

			panel.add(info);
			panel.add(setClassGrid);

			panel.add(buttonGrid);

			RootPanel.get("information").clear();

			RootPanel.get("content").clear();
			RootPanel.get("content").add(panel);


			mapLevel = appReference.getToonValue().getlevelMap();
			for(Map.Entry<String, String> entry: mapLevel .entrySet())
			{
				levelListBox.addItem(entry.getKey());
			}

			map_Race_Desc = appReference.getToonValue().getMapRace_Name_desc();
			for(Map.Entry<String, String> entry: map_Race_Desc .entrySet())
			{
				RaceListBox.addItem(entry.getKey());
			}


			descriptionButton.addClickHandler(new ClickHandler() 
			{

				public void onClick(ClickEvent event)
				{  
					String levelName = levelListBox.getValue(levelListBox.getSelectedIndex());
					//String raceName = RaceListBox.getValue(RaceListBox.getSelectedIndex());


					for(Map.Entry<String, String> entry: mapLevel.entrySet())
					{
					
						
						if(levelName.equals(entry.getKey()) )
						{
							if(RootPanel.getBodyElement()!=null){
								RootPanel.get("information").clear();
							}
							RootPanel.get("information").add(
									new HTML("<h1>" + entry.getKey() + "</h1><p>"
						   		+  mapLevel.get(entry.getKey())+ "</p>"));
							
					     }
						}
				
				}
			});

			}
		RootPanel.get("information").clear();

		}
	}






