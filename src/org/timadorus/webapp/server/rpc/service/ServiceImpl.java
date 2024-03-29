package org.timadorus.webapp.server.rpc.service;

import java.util.List;

import org.timadorus.webapp.beans.Campaign;
import org.timadorus.webapp.beans.Character;
import org.timadorus.webapp.beans.Fraction;
import org.timadorus.webapp.beans.User;
import org.timadorus.webapp.client.service.Service;
import org.timadorus.webapp.shared.transporttypes.ExsFractionTransporttype;
import org.timadorus.webapp.server.rpc.service.campaign.CampaignProvider;
import org.timadorus.webapp.server.rpc.service.character.CharacterProvider;
import org.timadorus.webapp.server.rpc.service.fraction.FractionProvider;
import org.timadorus.webapp.server.rpc.service.register.RegisterProvider;
import org.timadorus.webapp.server.rpc.service.login.LoginProvider;
import org.timadorus.webapp.server.rpc.service.user.UserProvider;
import org.timadorus.webapp.shared.Action;
import org.timadorus.webapp.shared.Response;
import javax.servlet.http.HttpSession;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class ServiceImpl extends RemoteServiceServlet implements Service {

  /**
   * 
   */
  private static final long serialVersionUID = -2109378966670594123L;

  @Override
  public Response execute(Action action) {
    Response response = null;  
    String errorCode;
    User actionUser;
    switch (action.getType()) {
    case LOGIN:
      String state = LoginProvider.login((User) action.getContent());
      if (state.equals(User.USER_VERIFIED)) {
        HttpSession httpSession = getThreadLocalRequest().getSession();
        state = httpSession.getId();
      }
      response = new Response<String>(state);
      break;
    case REGISTER:
      errorCode = RegisterProvider.register((User) action.getContent());
      response = new Response<String>(errorCode);
      break;
    case GETCHARACTERS:
      List<Character> listCharResponse = CharacterProvider.getCharacterList((User) action.getContent());
      response = new Response<List<Character>>(listCharResponse);
      break;
    case DELCHARACTER:
      errorCode = CharacterProvider.deleteCharacter((Character) action.getContent());
      response = new Response<String>(errorCode);
      break;
    case CRTCHARACTER:
      errorCode = CharacterProvider.createCharacter((Character) action.getContent());
      response = new Response<String>(errorCode);
      break;
    case MKPOTSTAT:
      int potStatResponse = CharacterProvider.makePotStat((Integer) action.getContent());
      response = new Response<Integer>(potStatResponse);
      break;
    case GETUSER:
      User userRespone = UserProvider.getUser((User) action.getContent());
      response = new Response<User>(userRespone);
      break;
    case DELUSER:
      errorCode = UserProvider.delete((User) action.getContent());
      response = new Response<String>(errorCode);
      break;
    case UPDUSER:
      actionUser = (User) action.getContent();
      Integer errorVal = UserProvider.update(actionUser.getId(), actionUser);
      response = new Response<Integer>(errorVal);
      break;
    case VERFMAIL:
      actionUser = (User) action.getContent();
      errorCode = UserProvider.verifyMail(actionUser.getActivationCode(), actionUser);
      response = new Response<String>(errorCode);
      break;
    case CRTCAMPAIGN:
      errorCode = CampaignProvider.createCampaign((Campaign) action.getContent());
      response = new Response<String>(errorCode);
      break;
    case EXSCAMPAIGN:
      errorCode = CampaignProvider.existsCampaign((String) action.getContent());
      response = new Response<String>(errorCode);
      break;
    case GETCAMPAIGN:
      List<Campaign> listCampResponse = CampaignProvider.getCampaigns((String) action.getContent());
      response = new Response<List<Campaign>>(listCampResponse);
      break;
    case CRTFRACTION:
       errorCode = FractionProvider.createFraction((Fraction) action.getContent());      
       response = new Response<String>(errorCode);
       break;
    case EXSFRACTION:
      
      ExsFractionTransporttype exsFractionTransport = (ExsFractionTransporttype) action.getContent();
      
      errorCode = FractionProvider.existsFraction(exsFractionTransport.getFractionName(),
                                                  exsFractionTransport.getCampaignName());
      response = new Response<String>(errorCode);
      break;
    default:
      break;
    }
    return response;
  }
}
