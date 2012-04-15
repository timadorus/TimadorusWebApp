package org.timadorus.webapp.client;

//States of the Browsing history
//Status für nächste Seite

public enum HistoryStates {
  LOGIN_STATE("login"), 
  WELCOME_STATE("welcome"),
  CREATE_CHARACTER_STATE("createCharacter"),
  CHARACTER_LIST_STATE("characterList"),
  REGISTER_STATE("register"),
  LOGOUT_STATE("logout"),
  PROFILE_STATE("profile"),
  VERIFY_MAIL_STATE("verifyMail"),
  CREATE_CAMPAIGN_STATE("createCampaign"),
  EDIT_CAMPAIGN_STATE("editCampaign");

  private final String myStringRepresentation;

  HistoryStates(String aStringRepresentation) {
    myStringRepresentation = aStringRepresentation;
  }

  public String getStringRepresentation() {
   return myStringRepresentation;
  }

  public static HistoryStates findByStringRepresentation(String aStringRepresentation) {
    for (HistoryStates theHistoryState : values()) {
      if (theHistoryState.getStringRepresentation().equals(aStringRepresentation)) {
        return theHistoryState;
      }
    }
    return null;
  } 

}
