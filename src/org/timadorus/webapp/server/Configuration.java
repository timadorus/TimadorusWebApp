package org.timadorus.webapp.server;

import java.io.Serializable;
import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.jdo.annotations.Unique;

/**
 * Configuration class for every a attribute of the WebApp.
 *
 */
@PersistenceCapable(identityType = IdentityType.APPLICATION)
public final class Configuration implements Serializable {
  
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private static final int FIELD_SIZE = 256;
 
  @PrimaryKey
  @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
  private Long id;
  
  @Unique
  @Persistent
  @Column(length = FIELD_SIZE)
  private String key;
  
  @Persistent
  @Column(length = FIELD_SIZE)
  private String value;
  
  /** store arbitrary configuration data as key/value pair.
   * 
   * @param key the key
   * @param value the value
   */
  public Configuration(String key, String value) {
    this.key = key;
    this.value = value;
  }
 
  /**
   * 
   * @return the id
   */
  public final Long getId() {
    return id;
  }
  
  /**
   * 
   * @return the value
   */
  public String getValue() {
    return this.value;
  }
  
  /**
   * 
   * @param value the value to set
   */
  public void setValue(String value) {
    this.value = value;
  }
  
}
