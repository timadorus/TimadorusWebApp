package org.timadorus.webapp.server.rpc.service;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.Extent;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import org.timadorus.webapp.client.campaign.Campaign;
import org.timadorus.webapp.client.rpc.service.CreateCampaignService;
import org.timadorus.webapp.server.RegisteredUserList;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * Implementing CreateCampaignService according to GWT-RPC-Plumbing-diagram.
 */
public class CreateCampaignServiceImpl extends RemoteServiceServlet implements CreateCampaignService {
  
  private static final long serialVersionUID = 22989914514554878L;
  
  private static final PersistenceManagerFactory PMF = RegisteredUserList.PMF;
  
  @Override
  public String createCampaign(Campaign campaign) {
    if (campaign != null) {
      System.out.println("\n\n" + campaign.toString() + "\n\n");
      return saveCampaignToDB(campaign);
    } else {
      return "FAILURE";
    }
  }

  //@SuppressWarnings("unchecked")
  private String saveCampaignToDB(Campaign campaign) {
    
    PersistenceManager pm = PMF.getPersistenceManager();
    
    // check if campaign name already exists
    //Extent<Campaign> campaignExtent = pm.getExtent(Campaign.class, true);
    //Query campaignQuery = pm.newQuery(campaignExtent, "name == " + campaign.getName());
    //List<Campaign> chars = (List<Campaign>) campaignQuery.execute();
    //if (!chars.isEmpty()) {
    //  return "FAILURE";
    //}
    try {        
      //campaign.setAllAttrInfo();

      pm.makePersistent(campaign);
      
     pm.close();
   } catch (Exception e) {
     e.printStackTrace();
   }
   return "SUCCESS";
  }

  @SuppressWarnings("unchecked")
  @Override
  //Checks if campaign name already exists
  public String existsCampaign(String campaignName) {
    PersistenceManager pm = PMF.getPersistenceManager();

    Extent<Campaign> campaignExtent = pm.getExtent(Campaign.class, true);
    Query campaignQuery = pm.newQuery(campaignExtent, "name == '" + campaignName + "'");
    List<Campaign> chars = (List<Campaign>) campaignQuery.execute();
    if (!chars.isEmpty()) {
      return "FAILURE";
    }
    return "SUCCESS";
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Campaign> getCampaigns(String username) {
    PersistenceManager pm = PMF.getPersistenceManager();
    Extent<Campaign> campaignExtent = pm.getExtent(Campaign.class, true);
    Query campaignQuery = pm.newQuery(campaignExtent, "username == '" + username + "'");
    
    List<Campaign> ret = new ArrayList<Campaign>();
    for (Campaign campaign : (List<Campaign>) campaignQuery.execute()) {
      pm.retrieve(campaign);
      ret.add(pm.detachCopy(campaign));
    }
    
    return ret;
  }

}
