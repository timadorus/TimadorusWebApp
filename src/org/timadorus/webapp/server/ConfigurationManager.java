package org.timadorus.webapp.server;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

/**
 * ConfigurationManager class to handle the WebApp's Configuration
 *
 */
public final class ConfigurationManager {

  public static final PersistenceManagerFactory PMF = JDOHelper.getPersistenceManagerFactory("transactions-optional");
  
  private ConfigurationManager() {
  }
  
  /**
   * This method sets a configuration.
   * 
   */
  public static void setConfiguration(String key, String value) {
    
    PersistenceManager pm = PMF.getPersistenceManager();
    Configuration config = new Configuration(key, value);
    
    Query query = pm.newQuery(Configuration.class, "key == " + key);
    query.setUnique(true);
    Configuration configuration = (Configuration) query.execute(query);
    
    if (configuration == null) {
      pm.makePersistent(config);
    } else {
      configuration.setValue(value);
      pm.makePersistent(configuration);
    }
    pm.close();
    System.out.println("Datastore: Configuration hinzugefügt mit Key, Value: " + key + ", " + value);
  }

  /**
   * This method gets a configuration .
   * 
   * @param The key for the mapped value
   * @return The value
   */
  public static String getConfiguration(String key) {
    PersistenceManager pm = PMF.getPersistenceManager();
    Query query = pm.newQuery(Configuration.class, "key == " + key);
    query.setUnique(true);
    Configuration config = (Configuration) query.execute(query);
    return config.getValue();
  }
  
}
