package org.timadorus.webapp.client.rpc.service;

import java.util.List;

import org.timadorus.webapp.beans.Campaign;

import com.google.gwt.user.client.rpc.AsyncCallback;

//Interface for Asyncronous "character"-Method-Calls (RPC-calls) between client and server
public interface CreateCampaignServiceAsync {
  
  void createCampaign(Campaign name, AsyncCallback<String> callback);
  
  void existsCampaign(String campaignName, AsyncCallback<String> callback);

  void getCampaigns(String username, AsyncCallback<List<Campaign>> asyncCallback);

}
