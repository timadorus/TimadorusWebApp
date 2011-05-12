package org.timadorus.webapp.server;

import java.io.Serializable;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

/**
 * Configuration class for every a attribute of the WebApp
 *
 */

public final class Configuration implements Serializable {
  
  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  public static final PersistenceManagerFactory PMF = JDOHelper.getPersistenceManagerFactory("transactions-optional");
  
  private static String key, value;

  private Configuration() {
  }
 
  public String getValue() {
    return this.value;
  }
  
  public void setValue(String value) {
    this.value = value;
  }
  
  /**
   * This method sets a configuration.
   * 
   */
  public static void setConfiguration(String key1, String value1) {
    System.out.println("Test");
    PersistenceManager pm = PMF.getPersistenceManager();
    key = key1;
    value = value1;
    
//    Query query = pm.newQuery(Configuration.class, "key == " + key);
//    query.setUnique(true);
//    Configuration configuration = (Configuration) query.execute(query);
//    
//    if (configuration == null) {
//      pm.makePersistent(Configuration.class);
//    } else {
//      configuration.setValue(value1);
//      pm.makePersistent(configuration);
//    }
    
    
    pm.makePersistent(Configuration.class);
    
    
    
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
    Configuration configuration = (Configuration) query.execute(query);
    return configuration.getValue();
  }
  
}
