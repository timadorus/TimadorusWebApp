package org.timadorus.webapp.shared.transporttypes;

public class ExsFractionTransporttype {

  private String fractioName;
  private String campaignName;
  
  
  public ExsFractionTransporttype(String fractioName, String campaignName) {
    this.fractioName = fractioName;
    this.campaignName = campaignName;
  }
  
  public String getFractionName() {
    return fractioName;
  }
  
  public String getCampaignName() {
    return campaignName;
  }
  
  
}
