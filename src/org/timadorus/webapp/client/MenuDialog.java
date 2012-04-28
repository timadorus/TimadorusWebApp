/* -*- java -*- */
/*
 * Filename:          org.timadorus.webapp.client/MenuDialog.java
 *                                                                       *
 * Project:           TimadorusWebApp
 *
 * This file is distributed under the GNU Public License 2.0
 * See the file Copying for more information
 *
 * copyright (c) 2012 Lutz Behnke <lutz.behnke@gmx.de>
 *
 * THE AUTHOR MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE
 * SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR
 * A PARTICULAR PURPOSE, OR NON-INFRINGEMENT. THE AUTHOR SHALL NOT BE
 * LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING,
 * MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS DERIVATIVES.
 */

package org.timadorus.webapp.client;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.timadorus.webapp.beans.Campaign;
import org.timadorus.webapp.beans.User;

import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.IsWidget;

/**
 * @author sage
 *
 */
public class MenuDialog implements ClientUIElement {

  private class LinkInfo {
    public final String text;
    public final String historyToken;
    Set<Role> roles = EnumSet.noneOf(Role.class);
    
    public LinkInfo(Role role, String text, String historyToken) {
      this.text = text;
      this.historyToken = historyToken;
      roles.add(role);
    }
    
    public void addRole(Role role) {
      roles.add(role);
    }

  }

  /**
   * 
   * @author sage
   *
   */
  public interface Display {
    
    /**
     * 
     * @return the whole panel
     */
    IsWidget getPanel();
    
    /** remove all menu entries from the panel
     * 
     */
    void clear();
    
    /** create a hype link from displayed text and history token as target.
     * 
     * @param  text text to display
     * @param historyToken target token
     * @return a valid label
     */
    void addLink(String text, String historyToken);
    
    /**
     * 
     * @return the label to hold status information
     */
    HasText getStatusLabel();
  }

  Map<String, LinkInfo> links = new HashMap<String, LinkInfo>();
  
  private Display display;

  private User user = null;
  private Campaign campaign = null;

  public MenuDialog(Display display) {
    this.display = display;
  }
  
  @Override
  public void go(HasWidgets parent) {
    parent.add(display.getPanel().asWidget());
  }

  /**
   * 
   * @param user user to set.
   */
  public void setUser(User user) {
    this.user = user;
    setPanel();
  }

  /** reset the user to null, in case of log out
   * 
   */
  public void resetUser() {
    user = null;
    setPanel();
  }
  
  /**
   * @param campaign the campaign to set
   */
  public void setCampaign(Campaign campaign) {
    this.campaign = campaign;
    setPanel();
  }


  /** add a new link to the menu.
   * 
   * @param role the role for which the link is valid
   * @param text the text to display the link with.
   * @param historyToken history token to switch to.
   */
  public void addLink(Role role, String text, String historyToken) {
    
    LinkInfo info = links.get(text);
    if (info != null) {
      info.addRole(role);
    } else {
      info = new LinkInfo(role, text, historyToken);
    }
   
    links.put(text, info);
    setPanel();
  }
  
  private void setPanel() {
    display.clear();
    Role currRole = Role.GUEST;
    StringBuffer statusBuff = new StringBuffer();
    
    if (user != null) {
      statusBuff.append("you are logged in as " + user.getDisplayname());
      
      if (!user.getActive()) {
        statusBuff.append("<br>your account has not been activated yet!");
      } else {
        currRole = Role.USER;        
      }
    } else {      
      statusBuff.append("Please log in or register a new account");
    }
    display.getStatusLabel().setText(statusBuff.toString());
    
    // add all applicable links
    for (LinkInfo info : links.values()) {
      if (info.roles.contains(currRole)) {
        display.addLink(info.text, info.historyToken);
      }
    }
  }

}
