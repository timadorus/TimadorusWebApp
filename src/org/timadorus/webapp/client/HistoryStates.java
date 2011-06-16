package org.timadorus.webapp.client;

// States of the Browsing history
// Status für nächste Seite

// Turn checkstyle off, because this design pattern is necessary for
// this project
// CHECKSTYLE OFF
interface HistoryStates {
  
  public static final String LOGIN_STATE = "login";

  public static final String WELCOME_STATE = "welcome";

  public static final String CREATE_CHARACTER_STATE = "createCharacter";
  
  public static final String CHARACTER_LIST_STATE = "characterList";
  
  public static final String REGISTER_STATE = "register";
  
  public static final String LOGOUT_STATE = "logout";
  
  public static final String PROFILE_STATE = "profile";
  
  public static final String VERIFY_MAIL_STATE = "verifyMail";
  
  public static final String CREATE_CAMPAIGN_STATE = "createCampaign";
  
  public static final String EDIT_CAMPAIGN_STATE = "editCampaign";
  
}
// CHECKSTYLE ON
