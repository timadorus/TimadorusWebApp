package org.timadorus.webapp.client.rpc.service;

import java.util.List;

import org.timadorus.webapp.beans.Campaign;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

//Service for create-Campaign-Progress

@RemoteServiceRelativePath("createCampaign")
public interface CreateCampaignService extends RemoteService {
  
  String createCampaign(Campaign campaign);
  
  String existsCampaign(String campaignName);

  List<Campaign> getCampaigns(String username);

}
