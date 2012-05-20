package org.timadorus.webapp.server.rpc.service.fraction;

import java.util.List;

import javax.jdo.Extent;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import org.timadorus.webapp.beans.Campaign;
import org.timadorus.webapp.beans.Fraction;
import org.timadorus.webapp.server.RegisteredUserList;

public final class FractionProvider {
  
  private FractionProvider() {
    
  }

 private static final long serialVersionUID = 8010756267869833209L;
  
  private static final PersistenceManagerFactory PMF = RegisteredUserList.PMF;

  
  public static String createFraction(Fraction fraction) {
    if (fraction != null) {
      System.out.println("\n\n" + fraction.toString() + "\n\n");
      return saveFractionToDB(fraction);
    } else {
      return "FAILURE";
    }
  }

  private static String saveFractionToDB(Fraction fraction) {
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
  //Checks if fraction name already exists and if it belongs to a certain campaign
  public static String existsFraction(String fractionName, String campaignName) {
    PersistenceManager pm = PMF.getPersistenceManager();

    Extent<Fraction> fractionExtent = pm.getExtent(Fraction.class, true);
    Query fractionQuery = pm.newQuery(fractionExtent, "name == '" + fractionName + "'");
    List<Fraction> fracs = (List<Fraction>) fractionQuery.execute();
    
    Query campaignNameQuery = pm.newQuery(fractionExtent, "campaignName == '" + campaignName + "'");
    List<Campaign> camps = (List<Campaign>) campaignNameQuery.execute();
    if (!fracs.isEmpty() && !camps.isEmpty()) {
      return "FAILURE";
    }
    return "SUCCESS";
  }
  
  
}
