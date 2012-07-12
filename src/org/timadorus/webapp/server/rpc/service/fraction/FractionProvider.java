package org.timadorus.webapp.server.rpc.service.fraction;

import java.util.List;

import javax.jdo.Extent;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import org.timadorus.webapp.beans.Campaign;
import org.timadorus.webapp.beans.Fraction;

/**
 * 
 * @author sage
 *
 */
public final class FractionProvider {

  private final PersistenceManagerFactory pmf;

  
  /**
   * 
   * @param pmf factory to use
   */
  public FractionProvider(PersistenceManagerFactory pmf) {
    this.pmf = pmf;
  }

  /** create a fraction.
   * 
   * @param fraction bean containing information on the fraction to create.
   * @return SUCCESS if the creation succeeded, FAILURE otherwise.
   */
  public String createFraction(Fraction fraction) {
    if (fraction != null) {
      System.out.println("\n\n" + fraction.toString() + "\n\n");
      return saveFractionToDB(fraction);
    } else {
      return "FAILURE";
    }
  }

  /** write fraction to database.
   * 
   * @param fraction the bean containing information on the fraction to write.
   * @return SUCCESS if the writing succeeded, FAILURE otherwise.
   */
  private String saveFractionToDB(Fraction fraction) {
    PersistenceManager pm = pmf.getPersistenceManager();

    try {        
      //campaign.setAllAttrInfo();

      pm.makePersistent(fraction);
      
     pm.close();
   } catch (Exception e) {
     e.printStackTrace();
   }
   return "SUCCESS";
  }

  /** Check if fraction name already exists and if it belongs to a certain campaign.
   * 
   * @param fractionName name of the fraction to check
   * @param campaignName name of the campaign that fraction belongs to.
   * @return SUCCESS if the fraction by that name belongs to that campaign. FAILURE otherwise
   */
  @SuppressWarnings("unchecked")
  public String existsFraction(String fractionName, String campaignName) {
    PersistenceManager pm = pmf.getPersistenceManager();

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
