package org.timadorus.webapp.client.rpc.service;

import org.timadorus.webapp.client.campaign.Campaign;

import com.google.gwt.user.client.rpc.AsyncCallback;

//Interface for Asyncronous "character"-Method-Calls (RPC-calls) between client and server
public interface CreateCampaignServiceAsync {
  
  void createCampaign(Campaign name, AsyncCallback<String> callback);

}
