package org.timadorus.webapp.server.rpc.service;

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
      System.out.println("\n\n**********" + campaign.toString() + "\n"
                         + "\n****************\n\n");
      return saveCampaignToDB(campaign);
    } else {
      return "FAILURE";
    }
  }

  @SuppressWarnings("unchecked")
  private String saveCampaignToDB(Campaign campaign) {
    
    PersistenceManager pm = PMF.getPersistenceManager();
    
    // check if campaign name already exists
    Extent<Campaign> campaignExtent = pm.getExtent(Campaign.class, true);
    Query campaignQuery = pm.newQuery(campaignExtent, "name == '" + campaign.getName() + "'");
    List<Campaign> chars = (List<Campaign>) campaignQuery.execute();
    if (!chars.isEmpty()) {
      return "FAILURE";
    }
    try {        
      //campaign.setAllAttrInfo();

      pm.makePersistent(campaign);
      System.out.println("\n\n******WRITE TO DB FOLLOW CHARACTER-OBJECT **********");
      //System.out.println("\n" + character.getAllAttrInfo_Part1() + "\n" + character.getAllAttrInfo_Part2());
      System.out.println("\n*********************************************************\n");
      
     pm.close();
   } catch (Exception e) {
     e.printStackTrace();
   }
   return "SUCCESS";
  }

}
