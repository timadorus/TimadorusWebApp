package org.timadorus.webapp.server.rpc.service.campaign;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.Extent;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import org.timadorus.webapp.client.campaign.Campaign;
import org.timadorus.webapp.server.RegisteredUserList;

public final class CampaignProvider {
  
  private CampaignProvider() {
    
  }
    
  private static final PersistenceManagerFactory PMF = RegisteredUserList.PMF;
  
  public static String createCampaign(Campaign campaign) {
    if (campaign != null) {
      System.out.println("\n\n" + campaign.toString() + "\n\n");
      return saveCampaignToDB(campaign);
    } else {
      return "FAILURE";
    }
  }

  //@SuppressWarnings("unchecked")
  private static String saveCampaignToDB(Campaign campaign) {
    
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
  //Checks if campaign name already exists
  public static String existsCampaign(String campaignName) {
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
  public static List<Campaign> getCampaigns(String username) {
    try {
      PersistenceManager pm = PMF.getPersistenceManager();

      Extent<Campaign> extent = pm.getExtent(Campaign.class, true);
      Query query = pm.newQuery(extent, "username == '" + username + "'");

      List<Campaign> ret = new ArrayList<Campaign>();
      for (Campaign campaign : (List<Campaign>) query.execute()) {
        pm.retrieve(campaign);
        ret.add(pm.detachCopy(campaign));
      }
      return ret;
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    return null;
  }

}
