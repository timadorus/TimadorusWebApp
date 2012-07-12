/* -*- java -*- */
/*
 * Filename:          org.timadorus.webapp.server/JDOPersistenceModule.java
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

package org.timadorus.webapp.server;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

import com.google.inject.AbstractModule;

/** Initialize all classes that require access to the pmf.
 * 
 * @author sage
 *
 */
final class JDOPersistenceModule extends AbstractModule {

  @Override
  public void configure() {
    PersistenceManagerFactory factory = JDOHelper.getPersistenceManagerFactory("timadorus-db-development");
    
    bind(PersistenceManagerFactory.class).toInstance(factory);    

  }

}
