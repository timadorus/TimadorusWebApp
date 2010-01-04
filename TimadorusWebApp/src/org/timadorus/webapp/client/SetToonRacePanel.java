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

public class SetToonRacePanel extends VerticalPanel {


	private  TimadorusWebApp appReference;
	private User user ;

	private Map<String , String> map_Race_Desc   = new HashMap<String, String>();


	List<ToonRace> race_List = new LinkedList<ToonRace>();
	
	public SetToonRacePanel() throws Exception
	{
		throw new Exception("No parameter set");
	}

	public SetToonRacePanel(TimadorusWebApp _appReference)
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

			final Button submitButton	= new Button("next");
			final Button prevButton	    = new Button("previous");
			final Button DescriptButton = new Button("description");
			
		



			VerticalPanel panel = new VerticalPanel();

			FlexTable setClassGrid = new FlexTable();

			FlexTable buttonGrid = new FlexTable();

		

			HTML info = new HTML("<h2> choose Race</h2>");
			//Label classLabel = new Label("choose Level and Gender: ");

			//setClassGrid.setWidget(0, 0, classLabel);
			setClassGrid.setWidget(0, 1, RaceListBox);
	

			RaceListBox.setVisibleItemCount(RaceListBox.getItemCount());
		
			buttonGrid.getCellFormatter().setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_RIGHT);
			buttonGrid.setWidth("350px");
			buttonGrid.setWidget(2, 2, prevButton);
			buttonGrid.setWidget(2, 3, DescriptButton);
			buttonGrid.setWidget(2, 4, submitButton);

			panel.setStyleName("panel");
			panel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);

			panel.add(info);
			panel.add(setClassGrid);

			panel.add(buttonGrid);

			RootPanel.get("information").clear();

			RootPanel.get("content").clear();
			RootPanel.get("content").add(panel);


			map_Race_Desc = appReference.getToonValue().getlevelMap();
			for(Map.Entry<String, String> entry: map_Race_Desc .entrySet())
			{
				RaceListBox.addItem(entry.getKey());
			}
			DescriptButton.addClickHandler(new ClickHandler() 
			{

				public void onClick(ClickEvent event)
				{  
					String levelName = RaceListBox.getValue(RaceListBox.getSelectedIndex());

					for(Map.Entry<String, String> entry: map_Race_Desc.entrySet())
					{
						if(levelName.equals(entry.getKey()))
						{
							if(RootPanel.getBodyElement()!=null){
								RootPanel.get("information").clear();
							}
							RootPanel.get("information").add(
									new HTML("<h1>" + entry.getKey() + "</h1><p>"
											+  map_Race_Desc.get(entry.getKey())+ "</p>"));
						}
					}		
				}
			});

		}
		RootPanel.get("information").clear();
		
		
	}



}

