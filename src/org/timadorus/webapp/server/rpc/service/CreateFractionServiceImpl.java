package org.timadorus.webapp.server.rpc.service;

import java.util.List;

import javax.jdo.Extent;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import org.timadorus.webapp.client.campaign.Campaign;
import org.timadorus.webapp.client.campaign.Fraction;
import org.timadorus.webapp.client.rpc.service.CreateFractionService;
import org.timadorus.webapp.server.RegisteredUserList;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class CreateFractionServiceImpl extends RemoteServiceServlet implements CreateFractionService {

  private static final long serialVersionUID = 8010756267869833209L;
  
  private static final PersistenceManagerFactory PMF = RegisteredUserList.PMF;

  @Override
  public String createFraction(Fraction fraction) {
    if (fraction != null) {
      System.out.println("\n\n" + fraction.toString() + "\n\n");
      return saveFractionToDB(fraction);
    } else {
      return "FAILURE";
    }
  }

  private String saveFractionToDB(Fraction fraction) {
    PersistenceManager pm = PMF.getPersistenceManager();

    try {        
      //campaign.setAllAttrInfo();

      pm.makePersistent(fraction);
      
     pm.close();
   } catch (Exception e) {
     e.printStackTrace();
   }
   return "SUCCESS";
  }

  @SuppressWarnings("unchecked")
  @Override
  //Checks if campaign name already exists
  public String existsFraction(String fractionName, String campaignName) {
    PersistenceManager pm = PMF.getPersistenceManager();

    Extent<Fraction> fractionExtent = pm.getExtent(Fraction.class, true);
    Query fractionQuery = pm.newQuery(fractionExtent, "name == '" + fractionExtent 
                                      + "' and campaignname == '" + campaignName + "'");
    List<Campaign> chars = (List<Campaign>) fractionQuery.execute();
    if (!chars.isEmpty()) {
      return "FAILURE";
    }
    return "SUCCESS";
  }

}
