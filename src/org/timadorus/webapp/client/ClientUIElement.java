/* -*- java -*- */
/*
 * Filename:          de.haw_hamburg.informatik.webting.client/Component.java
 *                                                                       *
 * Project:           webting
 *
 * This file is distributed under the GNU Public License 2.0
 * See the file Copying for more information
 *
 * copyright (c) 2011 Lutz Behnke <lutz.behnke@gmx.de>
 *
 * THE AUTHOR MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE
 * SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR
 * A PARTICULAR PURPOSE, OR NON-INFRINGEMENT. THE AUTHOR SHALL NOT BE
 * LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING,
 * MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS DERIVATIVES.
 */

package org.timadorus.webapp.client;

import com.google.gwt.user.client.ui.HasWidgets;

/** element that can be placed into other widget.
 * 
 * all interaction will be registered with the event bus
 * 
 * @author sage
 *
 */
public interface ClientUIElement {
  /** place the component into the parent widget.
   * 
   * @param parent the parent widget
   */
  public void go(HasWidgets parent);
}
