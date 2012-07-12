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

/** 
 * 
 * @author sage
 *
 */
@SuppressWarnings("rawtypes")
public class ServiceImpl extends RemoteServiceServlet implements Service {

  /**
   * 
   */
  private static final long serialVersionUID = -2109378966670594123L;
  
  private final CampaignProvider campaignProvider;
  private final LoginProvider loginProvider;
  private final CharacterProvider characterProvider;
  private final RegisterProvider registerProvider;
  private final UserProvider userProvider;
  private final FractionProvider fractionProvider;

  /**
   * 
   * @param campp 
   * @param lp
   */
  
  /**
   * 
   * @param campP the campaign provider to use
   * @param loginP  the login provider to use
   * @param charP  the character provider to use
   * @param regP the register provider to use
   * @param userP the user provider to use
   * @param fracP the fraction provider to use
   *  
   */
  public ServiceImpl(CampaignProvider campP, LoginProvider loginP, CharacterProvider charP,
                     RegisterProvider regP, UserProvider userP, FractionProvider fracP) {
    this.campaignProvider = campP;
    this.loginProvider = loginP;
    this.characterProvider = charP;
    this.registerProvider = regP;
    this.userProvider = userP;
    this.fractionProvider = fracP;
  }
  
  
  @Override
  public Response execute(Action action) {
    Response response = null;  
    String errorCode;
    User actionUser;
    switch (action.getType()) {
    case LOGIN:
      String state = loginProvider.login((User) action.getContent());
      if (state.equals(User.USER_VERIFIED)) {
        HttpSession httpSession = getThreadLocalRequest().getSession();
        state = httpSession.getId();
      }
      response = new Response<String>(state);
      break;
    case REGISTER:
      errorCode = registerProvider.register((User) action.getContent());
      response = new Response<String>(errorCode);
      break;
    case GETCHARACTERS:
      List<Character> listCharResponse = characterProvider.getCharacterList((User) action.getContent());
      response = new Response<List<Character>>(listCharResponse);
      break;
    case DELCHARACTER:
      errorCode = characterProvider.deleteCharacter((Character) action.getContent());
      response = new Response<String>(errorCode);
      break;
    case CRTCHARACTER:
      errorCode = characterProvider.createCharacter((Character) action.getContent());
      response = new Response<String>(errorCode);
      break;
    case MKPOTSTAT:
      int potStatResponse = characterProvider.makePotStat((Integer) action.getContent());
      response = new Response<Integer>(potStatResponse);
      break;
    case GETUSER:
      User userRespone = userProvider.getUser((User) action.getContent());
      response = new Response<User>(userRespone);
      break;
    case DELUSER:
      errorCode = userProvider.delete((User) action.getContent());
      response = new Response<String>(errorCode);
      break;
    case UPDUSER:
      actionUser = (User) action.getContent();
      Integer errorVal = userProvider.update(actionUser.getId(), actionUser);
      response = new Response<Integer>(errorVal);
      break;
    case VERFMAIL:
      actionUser = (User) action.getContent();
      errorCode = userProvider.verifyMail(actionUser.getActivationCode(), actionUser);
      response = new Response<String>(errorCode);
      break;
    case CRTCAMPAIGN:
      errorCode = campaignProvider.createCampaign((Campaign) action.getContent());
      response = new Response<String>(errorCode);
      break;
    case EXSCAMPAIGN:
      errorCode = campaignProvider.existsCampaign((String) action.getContent());
      response = new Response<String>(errorCode);
      break;
    case GETCAMPAIGN:
      List<Campaign> listCampResponse = campaignProvider.getCampaigns((String) action.getContent());
      response = new Response<List<Campaign>>(listCampResponse);
      break;
    case CRTFRACTION:
       errorCode = fractionProvider.createFraction((Fraction) action.getContent());      
       response = new Response<String>(errorCode);
       break;
    case EXSFRACTION:
      
      ExsFractionTransporttype exsFractionTransport = (ExsFractionTransporttype) action.getContent();
      
      errorCode = fractionProvider.existsFraction(exsFractionTransport.getFractionName(),
                                                  exsFractionTransport.getCampaignName());
      response = new Response<String>(errorCode);
      break;
    default:
      break;
    }
    return response;
  }
}
