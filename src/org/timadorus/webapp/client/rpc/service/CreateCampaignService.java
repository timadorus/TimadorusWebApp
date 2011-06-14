package org.timadorus.webapp.client.rpc.service;

import org.timadorus.webapp.client.campaign.Campaign;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

//Service for create-Campaign-Progress

@RemoteServiceRelativePath("createCampaign")
public interface CreateCampaignService extends RemoteService {
  
  String createCampaign(Campaign campaign);

}
