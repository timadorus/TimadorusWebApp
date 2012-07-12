/* -*- java -*- */
/*
 * Filename:          org.timadorus.webapp.server/TimadorusGuiceServletContextListener.java
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

import javax.servlet.ServletContextEvent;

import org.timadorus.webapp.server.rpc.service.CreateCharacterServiceImpl;
import org.timadorus.webapp.server.rpc.service.ServiceImpl;
import org.timadorus.webapp.server.rpc.service.SessionServiceImpl;
import org.timadorus.webapp.server.rpc.service.UserServiceImpl;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;

/**
 * @author sage
 *
 */
public class TimadorusGuiceServletContextListener extends GuiceServletContextListener {

  
  @Override
  public void contextDestroyed(ServletContextEvent servletContextEvent) {
    super.contextDestroyed(servletContextEvent);
  }

  @Override
  public void contextInitialized(ServletContextEvent servletContextEvent) {
    super.contextInitialized(servletContextEvent);
  }

  @Override
  protected Injector getInjector() {
    return Guice.createInjector(new JDOPersistenceModule(), new ServletModule() {
       // set the servlets
      @Override
      protected void configureServlets() {
        
        bind(ServiceImpl.class);
        serve("/timadoruswebapp/actionService").with(ServiceImpl.class);

        bind(SessionServiceImpl.class);
        serve("/timadoruswebapp/session").with(SessionServiceImpl.class);

        bind(UserServiceImpl.class);
        serve("/timadoruswebapp/user").with(UserServiceImpl.class);

        bind(CreateCharacterServiceImpl.class);
        serve("/timadoruswebapp/createCharacter").with(CreateCharacterServiceImpl.class);
      }

    });

  }

}
