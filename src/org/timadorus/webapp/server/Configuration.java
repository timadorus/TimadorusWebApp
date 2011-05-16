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
 * Configuration class for every a attribute of the WebApp
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
  
  public Configuration(String key, String value) {
    this.key = key;
    this.value = value;
  }
 
  public final Long getId() {
    return id;
  }
  
  public String getValue() {
    return this.value;
  }
  
  public void setValue(String value) {
    this.value = value;
  }
  
}
