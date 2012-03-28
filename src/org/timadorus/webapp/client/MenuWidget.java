/* -*- java -*- */
/*
 * Filename:          org.timadorus.webapp.client/MenuWidget.java
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

import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * @author sage
 *
 */
public class MenuWidget implements MenuDialog.Display {

  private final VerticalPanel panel;
  private final Label status;

  public MenuWidget() {
    panel = new VerticalPanel();
    status = new Label();
    panel.setStylePrimaryName("menuPanel");  
  }
  
  @Override
  public IsWidget getPanel() {
    return panel;
  }

  @Override
  public void clear() {
    panel.clear();
    panel.add(status);
  }

  @Override
  public HasText getStatusLabel() {
    return status;
  }

  @Override
  public void addLink(String text, String historyToken) {
    panel.add(new Hyperlink(text, historyToken));
  }

}
