/* -*- java -*- */
/*
 * Filename:          org.timadorus.webapp.tests.client/MenuDialogTest.java
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

package org.timadorus.webapp.tests.client;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.timadorus.webapp.beans.User;
import org.timadorus.webapp.client.MenuDialog;
import org.timadorus.webapp.client.Role;

import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.IsWidget;

/**
 * @author sage
 *
 */
public class MenuDialogTest {
  
  private class TestLabel implements HasText {

    private String text =  "";
    
    @Override
    public String getText() {
      return text;
    }

    @Override
    public void setText(String text) {
      this.text = text;
    }
    
  }
  
  /**
   * 
   * @author sage
   *
   */
  private class LinkInfo {
    String text;
    String token;
    
    /**
     * @param text
     * @param token
     */
    public LinkInfo(String text, String token) {
      this.text = text;
      this.token = token;
    }
    
  }
  private class TestWidget implements MenuDialog.Display {

    TestLabel status = new TestLabel();
    
    List<LinkInfo> links = new ArrayList<LinkInfo>();
    
    @Override
    public IsWidget getPanel() {
      return null;
    }

    @Override
    public void clear() {
    }

    @Override
    public HasText getStatusLabel() {
      return status;
    }

    @Override
    public void addLink(String text, String historyToken) {
      links.add(new LinkInfo(text, historyToken));
    }
    
  }
  
  /**
   * 
   * @throws Exception for any exception not caught by test
   */
  @Test
  public void testPanelSetup() throws Exception {
    TestWidget widget = new TestWidget();
    MenuDialog dialog = new MenuDialog(widget);
    
    dialog.addLink(Role.GUEST, "test1", "test1-token");
    
    User user = new User();
    user.setUsername("testarossa");
    dialog.setUser(user);
    String statusText = widget.getStatusLabel().getText();
    
    assertTrue("could not find the user name in status", statusText.indexOf(user.getUsername()) != -1);
    
  }
}
