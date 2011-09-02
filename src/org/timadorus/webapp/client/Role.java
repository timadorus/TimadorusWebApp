package org.timadorus.webapp.client;

public enum Role {
  GUEST("guest", "a user that is not currently logged in", false),
  USER("registered user", "any user that has successfully logged in", false),
  GM("game master", "a user that may change the settings of a campaign", true),
  ADMIN("administrator", "a system administrator", false);
  
  private final String displayName;
  private final String desc;
  private final boolean campaignCtx;
  
  /**
   * @param displayName
   * @param desc
   * @param campaignCtx
   */
  private Role(String displayName, String desc, boolean campaignCtx) {
    this.displayName = displayName;
    this.desc = desc;
    this.campaignCtx = campaignCtx;
  }

  /**
   * @return the displayName
   */
  public String getDisplay_name() {
    return displayName;
  }

  /**
   * @return the desc
   */
  public String getDesc() {
    return desc;
  }

  /**
   * @return the campaignCtx
   */
  public boolean isCampaignCtx() {
    return campaignCtx;
  }
  
  
}