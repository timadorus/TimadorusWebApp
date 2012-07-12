package org.timadorus.webapp.server.rpc.service.campaign;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.Extent;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import org.timadorus.webapp.beans.Campaign;

/** handle the management of campaigns.
 * 
 * This class should be used in a singleton pattern. The class does not enforce this
 * by itself, but relies on guice for that. 
 * 
 * @author sage
 *
 */
public final class CampaignProvider {

  private final PersistenceManagerFactory pmf;

  /**
   * 
   * @param pmf factory to use for this object
   */
  public  CampaignProvider(PersistenceManagerFactory pmf) {
    this.pmf = pmf;
  }
    
  
  /** create a new campaign from a bean.
   * 
   * @param campaign the campaign to create.
   * @return SUCCESS in the case the writign was successfull, FAILURE otherwise.
   */
  public String createCampaign(Campaign campaign) {
    if (campaign != null) {
      System.out.println("\n\n" + campaign.toString() + "\n\n");
      return saveCampaignToDB(campaign);
    } else {
      return "FAILURE";
    }
  }

  /** actually write the new campaign to the database.
   * 
   * @param campaign the campaign to write
   * @return SUCCESS in the case the writign was successfull, FAILURE otherwise.
   */
  private String saveCampaignToDB(Campaign campaign) {
    
    PersistenceManager pm = pmf.getPersistenceManager();
    
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

  /** Checks if campaign name already exists.
   * 
   * @param campaignName the name candidate to check
   * @return FAILURE if the name exists in the database, SUCCESS otherwise
   */
  public String existsCampaign(String campaignName) {
    PersistenceManager pm = pmf.getPersistenceManager();

    Extent<Campaign> campaignExtent = pm.getExtent(Campaign.class, true);
    Query campaignQuery = pm.newQuery(campaignExtent, "name == '" + campaignName + "'");
    @SuppressWarnings("unchecked")
    List<Campaign> chars = (List<Campaign>) campaignQuery.execute();
    if (!chars.isEmpty()) {
      return "FAILURE";
    }
    return "SUCCESS";
  }

  /** the campaigns for a user.
   * 
   * @param username name of the user to retrieve the campaigns for.
   * @return a list of campaigns that the user is involved in
   */
  public List<Campaign> getCampaigns(String username) {
    try {
      PersistenceManager pm = pmf.getPersistenceManager();

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
